# Gabor Dicso test task for Scientific Games
This project is the implementation of a console based Quick Tip generation tool for Scientific Games.
## Requirements
You'll need Java 8 and Maven 3.x to build and run the project.
## Usage
### Build
Clone the project, cd to the `quicktipgen` folder, and run `mvn clean package`. It will build the project, run the unit tests, and package the project in a `.jar` file.
### Run
In the `quicktipgen` folder, you can run the tool from console using the `mvn exec:java` command. For example, run `mvn exec:java -Dexec.args="-a=1 '-i=src/test/resources/test_alg1_valid.xml' -s=5"`. This will instruct the tool to use Algorithm 1, provide the specified XML as input parameter for the selected algorithm, and generate 5 sheets. Note the single quotes around the path to the XML file, they are required if the path contains spaces.
Note: if the parameter values for the panel count or sheet count are unusually high (requiring the generation of thousands of panels or more), execution time can be very long.
