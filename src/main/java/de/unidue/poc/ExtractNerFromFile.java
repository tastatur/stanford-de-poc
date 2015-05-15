package de.unidue.poc;

import java.io.IOException;

public interface ExtractNerFromFile {
    void extractFromResource(String inputFileName, String outputFileName) throws IOException, ClassNotFoundException;
}
