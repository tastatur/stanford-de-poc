package de.unidue.poc.run;

import de.unidue.poc.ExtractNerFromFile;
import de.unidue.poc.de.unidue.poc.impl.ExtractStanfordNerFromFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestDeNerStanford {
    public static void main(String[] argv) throws IOException, ClassNotFoundException {
        URL dataUrl = TestDeNerStanford.class.getClassLoader().getResource("data");
        if (dataUrl == null) {
            throw new NullPointerException("Wo sind denn unsere Daten?");
        }

        String testFiles[] = new String[2];
        testFiles[0] = dataUrl.getPath().concat("/boelsche_liebesleben03_1903.txt");
        testFiles[1] = dataUrl.getPath().concat("/fontane_brandenburg01_1862.txt");

        for (String fileName : testFiles) {
            ExtractNerFromFile nerExtractor = new ExtractStanfordNerFromFile("dewac");
            nerExtractor.extractFromResource(fileName, "/tmp/".concat(new File(fileName).getName()));
        }
    }
}
