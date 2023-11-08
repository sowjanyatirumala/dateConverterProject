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
}
