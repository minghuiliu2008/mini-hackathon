package com.mhl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
//import org.junit.Assert;
//import org.junit.Test;


public class CalculateSubstringTest {


//  @Test
  public void testAll() throws IOException {

    //TODO: update the test data folder
    String testDataFolder = "/Users/colin/2018work/mini-hackathon/Mini-hackathon-Topic2/full_test_data";
    //TODO: update test case number
    int testCaseNum=1;

    long startTime = Instant.now().toEpochMilli();

    for (int i = 0; i <= testCaseNum; i++) {

      String inputFile = String.format(testDataFolder + "/input%02d.txt", i);
      String inputString = new String(Files.readAllBytes(Paths.get(inputFile))).replaceAll("\r|\n", "");

      String outputFile = String.format(testDataFolder + "/output%02d.txt", i);
      int expectedOutput = Integer.parseInt(new String(Files.readAllBytes(Paths.get(outputFile))));

      int result = CalculateSubstrings.calculateSubstrings(inputString);

      if (expectedOutput == result) {
        System.out.println(String.format("Test case:%02d pass!", i));
      } else {
        System.err.println(String.format("Test case fail:%02d!", i));
//        Assert.assertEquals(expectedOutput, result);
      }

    }

    long endTime = Instant.now().toEpochMilli();
    long output = endTime - startTime;
    System.out.println("Elapsed time in milliseconds: " + output);

  }

}