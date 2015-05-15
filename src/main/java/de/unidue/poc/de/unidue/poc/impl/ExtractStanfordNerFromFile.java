package de.unidue.poc.de.unidue.poc.impl;

import de.unidue.poc.ExtractNerFromFile;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExtractStanfordNerFromFile implements ExtractNerFromFile {
    @SuppressWarnings("all")
    private static String DEWAC = ExtractStanfordNerFromFile.class.getClassLoader().getResource("classifiers/edu/stanford/nlp/models/ner/german.dewac_175m_600.crf.ser.gz").getPath();
    @SuppressWarnings("all")
    private static String HGC = ExtractStanfordNerFromFile.class.getClassLoader().getResource("classifiers/edu/stanford/nlp/models/ner/german.dewac_175m_600.crf.ser.gz").getPath();

    private final String classificatorName;

    public ExtractStanfordNerFromFile(String classificatorName) {
        this.classificatorName = classificatorName;
    }

    @Override
    public void extractFromResource(String inputFileName, String outputFileName) throws IOException, ClassNotFoundException {
        AbstractSequenceClassifier<CoreLabel> classifier = getClassifier(this.classificatorName);
        String fileContent = IOUtils.slurpFile(inputFileName);
        List<List<CoreLabel>> output = classifier.classify(fileContent);

        try(PrintWriter outWriter = new PrintWriter(new FileWriter(outputFileName), true)) {
            for (List<CoreLabel> sentence : output) {
                for (CoreLabel entity : sentence) {
                    outWriter.print(entity.word().concat("/").concat(entity.get(CoreAnnotations.AnswerAnnotation.class)).concat(" "));
                }
                outWriter.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private AbstractSequenceClassifier<CoreLabel> getClassifier(String classificatorName) throws IOException, ClassNotFoundException {
        if (this.classificatorName.equalsIgnoreCase("dewac")) {
            return CRFClassifier.getClassifier(DEWAC);
        } else {
            return CRFClassifier.getClassifier(HGC);
        }
    }
}
