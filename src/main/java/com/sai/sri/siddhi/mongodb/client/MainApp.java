package com.sai.sri.siddhi.mongodb.client;

import com.mongodb.*;

import java.io.IOException;
import java.io.InputStream;

public class MainApp {

    public static void main(String[] args) throws IOException {
        try {
            String mongoConnection = System.getProperty("mongo");

            if(mongoConnection==null) {
                System.out.println("Provide MongoDB Connection details. Usage -Dmongo=hostname:port");
                System.exit(1);
            }

            String[] mongoConnectionArr = mongoConnection.split(":");
            if(mongoConnectionArr.length!=2) {
                System.out.println("MongoDB Connection property format is incorrect. Usage -Dmongo=hostname:port");
                System.exit(1);
            }


            String inputFile = System.getProperty("input");

            // create MongoClient connection
            MongoClient mongoClient = new MongoClient(mongoConnectionArr[0], Integer.parseInt(mongoConnectionArr[1]));

            // Get reference to "WordCountChallenge"
            DB db = mongoClient.getDB("WordCountChallenge");

            // Get reference to "InputCollection"
            DBCollection inputCollection = db.getCollection("InputCollection");

            if(inputFile!=null) {
                System.out.println("Reading Input File: " + inputFile);

                BasicDBObject doc = new BasicDBObject("fileName", inputFile)
                                            .append("content", readFile(inputFile));

                inputCollection.insert(doc); // insert record into db
            } else {
                System.out.println("Input property is null. Assuming the data is already loaded into MongoDB Collection");
            }

            // read Map JS file
            String map = readFile("map.js");

            // read Reduce JS file
            String reduce = readFile("reduce.js");

            // execute MapReduce on the input collection and direct the result to "wordcounts" collection
            MapReduceOutput output = inputCollection.mapReduce(map, reduce,
                                                "WordCountCollection",
                                                MapReduceCommand.OutputType.MERGE, null);

            System.out.println("MapReduce results: ");
            for (DBObject o : output.results()) {
                System.out.println(o.toString());
            }

        } catch (Exception e) {
            System.out.println("Exception while processing: " + e.toString());
            e.printStackTrace();
        }
    }

    private static String readFile(String fileName) throws IOException {
        System.out.println("Reading File: " + fileName);

        InputStream fileStream = MainApp.class.getResourceAsStream("/" + fileName);
        byte[] buffer = new byte[8192 * 2];
        int size = fileStream.read(buffer);
        String result = new String(buffer, 0, size);
        System.out.println("file contents: " + result);
        return result;
    }
}
