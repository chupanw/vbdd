package edu.cmu.cs.varex.vbdd;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TestGenerator {

    static StringBuilder str;
    static String[] options = {"Integer", "Boolean", "String"};

    public TestGenerator(Integer numOfTests, Integer maxNumOfVars){

        // create a StringBuilder object
        // using StringBuilder() constructor
        str = new StringBuilder();

        createTests(numOfTests, maxNumOfVars); //number of tests to generate, maximum number of variables in tests

        // print string
        System.out.println(str.toString()); //print out all tests

    }

    public static void createTests(int numOfTests, int maxNumOfVars) {
        for(int i = 1; i <= numOfTests; i++ ) {
            str.append(" \n @Test \n public void flatMap" + i + "() { \n");
            int numOfVars = ThreadLocalRandom.current().nextInt(2, maxNumOfVars + 1); //random num of args between 2 and specified maxNumOfVars
            for(int j = 0; j < numOfVars; j++) {
                str.append("VNode<Boolean> var" + j + " = VBDDFactory.feature(\"var" + j +"\"); \n");
            }
            String choice = options[ThreadLocalRandom.current().nextInt(0, 3)];
            str.append(" V<? extends " + choice + " > bdd = VBDDFactory.ite(" + createBDD(choice, numOfVars, 0) + "); \n");
            String function = createFunction(choice);
            str.append(" V<? extends " + choice + " > oldMap = bdd.<"+ choice +">flatMap((aa) -> VBDDFactory.<" + choice + ">ite(var" + (numOfVars - 1) + ", this.<"+ choice + ">one(" + function + "aa ), this.<"+ choice + ">one(" + function + "aa))); \n");
            str.append(" V<? extends " + choice + " > newMap = bdd.<"+ choice +">flatMapNew((aa) -> VBDDFactory.<" + choice + ">ite(var" + (numOfVars - 1) + ", this.<"+ choice + ">one(" + function + "aa ), this.<"+ choice + ">one(" + function + "aa))); \n");
            str.append("Assert.assertEquals(oldMap, newMap); \n");
            str.append(" } \n");

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
            return random.nextInt(101) + "";
        } else if (type.equals("String")) {
            String[] randomStrings = {"cat", "dog", "rabbit", "mouse", "rat", "dolphin", "goldfish", "tiger", "elephant", "snake"};
            return "\"" + randomStrings[random.nextInt(10)] + "\"";
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
            return  ThreadLocalRandom.current().nextInt(1, 11) + operator ;
        } else if (type.equals("String")) {
            String[] randomStrings = {"cat", "dog", "rabbit", "mouse", "rat", "dolphin", "goldfish", "tiger", "elephant", "snake"};
            return "\"" + randomStrings[random.nextInt(10)] + "\" + ";
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
