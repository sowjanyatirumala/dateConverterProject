package com.myprojects.service

import spock.lang.Specification

class DateConversionServiceSpec extends Specification {

    DateConversionService dateConversionService = new DateConversionService()

    def "readFileContentsAsString - happy path"() {
        given:
        def filepath = "src/main/resources/inputFile.txt"

        expect:
        dateConversionService.readFileContentsAsString(filepath) == "Hi, here is the first program written on 11/06/2023."
    }

    def "writeStringToFile - happy path"() {
        given:
        def stringToWriteToFile = "Test data created on 11/07/2023"

        when:
        dateConversionService.writeStringToFile(stringToWriteToFile)

        then:
        def fileCreated = new File("src/main/resources/outputFile.txt")
        fileCreated.exists()
        fileCreated.text == stringToWriteToFile
    }

    def "convertDate - happy path"() {
        expect:
        dateConversionService.convertDate(inputDateFormat, outputDateFormat, inputString) == expectedResult

        where:
        inputDateFormat | outputDateFormat   || inputString  || expectedResult
        "mm/dd/yyyy"    | "mm-dd-yyyy"       || null         || null
        "mm/dd/yyyy"    | "mm-dd-yyyy"       || ""           || ""
        "mm/dd/yyyy"    | "mm-dd-yyyy"       || "abcd"       || "abcd"
        "mm/dd/yyyy"    | "mm-dd-yyyy"       || "123/2"      || "123/2"
        "mm-dd-yyyy"    | "mm/dd/yyyy"       || "11/07/2023" || "11/07/2023"
        "mm/dd/yyyy"    | "mm-dd-yyyy"       || "11/07/2023" || "11-07-2023"
        "mm-dd-yyyy"    | "yyyy-mm-dd"       || "11-07-2023" || "2023-11-07"
        "yyyy-MM-dd"    | "MMM dd, yyyy"     || "2023-11-07" || "Nov 07, 2023"
        "yyyy-MM-dd"    | "EEE MMM dd, yyyy" || "2023-11-07" || "Tue Nov 07, 2023"
        "MM/dd/yyyy"    | "dd MMM, yyyy"     || "11/07/2023" || "07 Nov, 2023"
        "MM/dd/yyyy"    | "dd/MM/yyyy"       || "11/07/2023" || "07/11/2023"
    }
}
