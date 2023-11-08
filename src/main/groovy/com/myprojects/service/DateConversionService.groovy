package com.myprojects.service

import org.springframework.stereotype.Service

import java.text.ParseException
import java.text.SimpleDateFormat

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

    /**
     * This method checks if the input string matches the input date format,
     * converts it to output date format if it matches and returns the converted date as string
     * otherwise returns the same string
     * The method returns null if the input string provided is null
     *
     * @param inputDateFormatString - date format of the input string to be matched
     * @param outputDateFormatString - date format of the output string
     * @param inputString - string to be converted
     *
     * @return the converted string
     */
    def convertDate(String inputDateFormatString, String outputDateFormatString, String inputString) {
        if (inputString == null) {
            return null
        }

        try {
            def inputDateFormat = new SimpleDateFormat(inputDateFormatString)
            def inputDate = inputDateFormat.parse(inputString)
            def outputDateFormat = new SimpleDateFormat(outputDateFormatString, Locale.US)

            return outputDateFormat.format(inputDate)
        } catch (ParseException pe) {
            return inputString
        }
    }
}
