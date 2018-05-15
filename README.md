# drawing-tool
This is a drawing tool application that supports the following operations:

Create a new canvas: C w h

Create a line: L x1 y1 x2 y2

Create a rectangle: R x1 y1 x2 y2

Fill the canvas: B x y c

# Build And Run The Project

This is a maven project, so make sure to have java and maven installed and the environment variables: 
JAVA_HOME and MAVEN_HOME

From the project root folder execute the following command:

```$ mvn clean package```

The above command generates a target folder. So go there, and execute the file 
__drawing-tool-final-jar-with-dependencies.jar__ as follows:

```$ java -jar drawing-tool-final-jar-with-dependencies.jar```

# Run Unit Testing and Jacoco Coverage

To run the unit testing please execute the following command from the root folder:

```$ mvn test```

The project also supports Jacoco, so go to the following folder and open __index.html__ file to see the coverage report:

```$HOME_FOLDER/drawing-tool/target/site/jacoco/```


