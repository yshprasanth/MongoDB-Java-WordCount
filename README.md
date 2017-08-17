# MongoDB-Java-WordCount

Overview:

This repository contains maven project with java source to perform below operation:

> read an input xml file
> insert into mongo db ("WordCountChallenge"), collection ("WordCountCollection")
> create a map function to split the file content based on words
> create a reduce function to aggregate the count 
> execute the mapReduce function in database

Source Files:
> src\main\java\com\sai\sri\siddhi\mongodb\client\MainApp.java
> src\main\resources\map.js
> src\main\resources\reduce.js

Sample Input File:
> src\main\resources\sample_input.xml


Compilation:

mvn clean:clean compile:compile exec:java -Dmongo=localhost:27017 -Dinput=sample_input.xml

Execution:

java –Xmx8192m -jar MongoDB-Java-WordCount-1.0-SNAPSHOT.jar -Dmongo=localhost:27017 -Dinput=sample_input.xml

  
Output:

Console Output and Dev Execution screenshots can be found in the root

> ConsoleOutput.txt
> IntelliJ-Dev-Run-ScreenShot-1.png
> IntelliJ-Dev-Run-ScreenShot-2.png 
