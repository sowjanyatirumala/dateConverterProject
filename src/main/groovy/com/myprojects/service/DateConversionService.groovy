package com.myprojects.service

import org.springframework.stereotype.Service

@Service
class DateConversionService {

    /**
     * This method reads the contents of the file from the given filepath
     * and returns the contents as string.
     *
     * @param filepath - path of the file to read from
     * @return contents of the file in string format
     */
    def readFileContentsAsString(String filepath) {
        File file = new File(filepath)
        return file.text
    }

    /**
     * This method creates a new file with the input string as contents
     *
     * @param fileContents - text to be added to the file
     */
    def writeStringToFile(String fileContents) {
        File file = new File("src/main/resources/outputFile.txt")
        file.setText(fileContents)
    }
}
