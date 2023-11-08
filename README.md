# dateConverterProject
This project has the logic to read the file contents from a specified path, finds dates matching the input pattern and returns the file contents with dates in the specified output format. The resultant string after conversion is written to a new file.

###How to run the project


* Clone the git repository using the command
    ```git clone https://github.com/sowjanyatirumala/dateConverterProject.git```
* Make sure the java version is JDK 20 or above on your local machine.
* Gradle wrapper is used in the project so you don't need to have Gradle installed on your local machine.
* The project can be built and the tests can be run using the command 
    ```./gradlew clean build```
* If you want to test the application behavior, please modify DataConversionServiceSpec as needed. 
The test is currently setup to read the inputFile.txt file from src/main/resources folder and the format specified for the input date is MM/dd/yy and the expected output date format is MMMM dd, yyyy.
Any date in the file contents, that doesn't match the specified input date format remains unchanged.
Once the comversion is done, the result string will be written to src/main/resources/outputFile.txt file. 