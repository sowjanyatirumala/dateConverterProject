package com.myprojects.service

import spock.lang.Specification

class DateConversionServiceSpec extends Specification {

    DateConversionService dateConversionService = new DateConversionService()

    def "readFileContentsAsString - happy path"() {
        given:
        def filepath = "src/main/resources/inputFile.txt"

        expect:
        dateConversionService.readFileContentsAsString(filepath) == """```text
Dear diary, 

On 9/6/78 a really neat thing happened. But on 10/10/03 thing went poorly.
I am hopeful that 10/11 will be better.
```"""
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
        "mm/dd/yyyy"    | "mm-dd-yy"         || "11/07/2023" || "11-07-23"
        "mm/dd/yyyy"    | "mm-dd-yyyy"       || "11/07/2023" || "11-07-2023"
        "mm-dd-yyyy"    | "yyyy-mm-dd"       || "11-07-2023" || "2023-11-07"
        "yyyy-MM-dd"    | "MMM dd, yyyy"     || "2023-11-07" || "Nov 07, 2023"
        "yyyy-MM-dd"    | "EEE MMM dd, yyyy" || "2023-11-07" || "Tue Nov 07, 2023"
        "MM/dd/yyyy"    | "dd MMM, yyyy"     || "11/07/2023" || "07 Nov, 2023"
        "MM/dd/yyyy"    | "dd/MM/yyyy"       || "11/07/2023" || "07/11/2023"
    }

    def "transformDates - happy path"() {
        given:
        def filepath = "src/main/resources/inputFile.txt"
        def inputDateFormat = "MM/dd/yy"
        def outputDateFormat = "MMMM dd, yyyy"

        when:
        dateConversionService.transformDates(filepath, inputDateFormat, outputDateFormat)

        then:
        0 * _

        and:
        def fileCreated = new File("src/main/resources/outputFile.txt")
        fileCreated.exists()
        fileCreated.text == """```text
Dear diary, 

On September 06, 1978 a really neat thing happened. But on October 10, 2003 thing went poorly.
I am hopeful that 10/11 will be better.
```"""
    }
}
