package edu.cmu.cs.varex.vbdd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TestGenerator {

    static StringBuilder str;
    static String csvFile = "performanceResults.csv";
    static String[] options = {"Integer", "Boolean", "String"};

    public TestGenerator(Integer numOfTests, Integer maxNumOfVars){

        // create a StringBuilder object
        // using StringBuilder() constructor
        str = new StringBuilder();

        createTests(numOfTests, maxNumOfVars); //number of tests to generate, maximum number of variables in tests

        // print string
        System.out.println(str.toString()); //print out all tests

    }

    //For performance test use bdd for old flatmap and bdd2 for newflatmap to make sure each has their own
    //VBDDFactory for accuracy. Otherwise, the mk function stores a cache and creates inaccurate timing results.
    // To switch to correctness testing, uncomment the assert line and have both old and new flatmap use the same bdd.
    public static void createTests(int numOfTests, int maxNumOfVars) {
        for(int i = 1; i <= numOfTests; i++ ) {
            str.append(" \n @Test \n public void flatMap" + i + "() throws IOException { \n");
            str.append("FileWriter writer = new FileWriter(\"performanceResults.csv\", true); \n");
            int numOfVars = ThreadLocalRandom.current().nextInt(2, maxNumOfVars + 1); //random num of args between 2 and specified maxNumOfVars
            for(int j = 0; j < numOfVars; j++) {
                str.append("VNode<Boolean> var" + j + " = VBDDFactory.feature(\"var" + j +"\"); \n");
                str.append("VNode<Boolean> var0" + j + " = VBDDFactory.feature(\"var" + j +"\"); \n");
            }
            String choice = options[ThreadLocalRandom.current().nextInt(0, 3)];
            str.append(" V<? extends " + choice + " > bdd = VBDDFactory.ite(" + createBDD(choice, numOfVars, 0) + "); \n");
            str.append(" V<? extends " + choice + " > bdd2 = VBDDFactory.ite(" + createBDD(choice, numOfVars, 0) + "); \n");
            String function = createFunction(choice);
            Integer randVar = ThreadLocalRandom.current().nextInt(0, numOfVars);
            str.append("Long start = System.nanoTime(); \n");
            str.append(" V<? extends " + choice + " > oldMap = bdd.<"+ choice +">flatMap((aa) -> VBDDFactory.<" + choice + ">ite(var" + randVar + ", this.<"+ choice + ">one( aa ), this.<"+ choice + ">one(" + function + "aa))); \n");
            str.append("Long oldTime = System.nanoTime() - start; \n");
            str.append("Long start2 = System.nanoTime(); \n");
            str.append(" V<? extends " + choice + " > newMap = bdd2.<"+ choice +">flatMapNew((aa) -> VBDDFactory.<" + choice + ">ite(var" + randVar + ", this.<"+ choice + ">one( aa ), this.<"+ choice + ">one(" + function + "aa))); \n");
            str.append("Long newTime = System.nanoTime() - start2; \n");
             str.append("Assert.assertEquals(oldMap, newMap); \n");
            str.append("writer.write( \"" + i + ",\" + oldTime.toString() + \",\" + newTime.toString()); \n");
            str.append("writer.write(System.getProperty( \"line.separator\" )); \n");
            str.append("writer.flush(); \n");
            str.append("writer.close(); \n");
            str.append("\n } \n");

        }

    }

    private static String createBDD(String type, Integer numOfVars, Integer currentNum) {
        if(currentNum >= numOfVars - 2) {
            return "var"+ currentNum + ", one(" + randValue(type) + "), one(" + randValue(type) + ")";
        }
        return "var" + currentNum + ", VBDDFactory.ite(" + createBDD(type, numOfVars, currentNum + 1) + "), VBDDFactory.ite(" + createBDD(type, numOfVars, currentNum + 1) +")";
    }

    private static String randValue(String type) {
        Random random = new Random();
        if(type.equals("Integer")) {
            return ThreadLocalRandom.current().nextInt(1, 6) + "";
        } else if (type.equals("String")) {
            String[] randomStrings = {"cat", "dog", "rabbit", "mouse", "rat"};
            return "\"" + randomStrings[random.nextInt(4)] + "\"";
        } else if (type.equals("Boolean")) {
            return random.nextInt(101) % 2 == 0 ? "true" : "false";
        }
        return null;
    }

    private static String createFunction(String type) {
        Random random = new Random();
        if(type.equals("Integer")) {
            String[]  operatorOptions = {" + ", " - ", " / ", " * ", " % "};
            String operator = operatorOptions[random.nextInt(5)];
            return  ThreadLocalRandom.current().nextInt(1, 6) + operator ;
        } else if (type.equals("String")) {
            String[] randomStrings = {"cat", "dog", "rabbit", "mouse", "rat"};
            return "\"" + randomStrings[random.nextInt(4)] + "\" + ";
        } else if (type.equals("Boolean")) {
            String[]  operatorOptions = {" && ", " || ", " ! "};
            String operator = operatorOptions[random.nextInt(3)];
            if(operator.equals(" ! ")) {
                return operator;
            } else {
                return (random.nextInt(101) % 2 == 0 ? "true" : "false") + operator;
            }

        }
        return null;
    }
}
