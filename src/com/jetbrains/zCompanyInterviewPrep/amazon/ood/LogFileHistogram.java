package com.jetbrains.zCompanyInterviewPrep.amazon.ood;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogFileHistogram {

    /*
    2018-10-23T09:35:29Z sent message
    2018-10-23T09:44:01Z transmit error
    2018-10-23T09:49:29Z sent message
    2018-10-23T10:01:49Z sent message
    2018-10-23T10:05:29Z transmit error
    2018-10-23T10:06:05Z transmit error
    2018-10-23T10:07:17Z transmit error
    2018-10-23T11:23:24Z sent message
    2018-10-23T11:52:28Z sent message
    2018-10-23T12:01:13Z transmit error
     */
    public List<String> readFile(String directory, String fileName) {

        List<String> res = new ArrayList<>();

        Path dir = Paths.get(directory);
        try (BufferedReader br = Files.newBufferedReader(dir.resolve(fileName))) {

            String line = null;
            while ((line = br.readLine()) != null) {
                res.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public void writeFile(String directory, String fileName, List<String> contents) {
//        Path path = Paths.get(directory + "/" + fileName);
//        try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
//            for (String s : contents) {
//                writer.write(s);
//                writer.newLine();
//            }
////            writer.write("To be, or not to be. That is the question.");
//        }catch(IOException ex){
//            ex.printStackTrace();
//        }

        try {
            // write data
            Files.write(Paths.get(directory + "/" + fileName), contents);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void filterLogFile() {
        List<String> logFile = readFile("src/com/jetbrains/zCompanyInterviewPrep/amazon/ood", "text1");
        List<String> res = new ArrayList<>();

        for (String s : logFile) {
            int index = s.indexOf(" ");  // get the index of the first space
            String time = s.substring(0, index);
            String content = s.substring(index + 1);

            if (content.equals("transmit error")) {
                res.add(s);
            }
        }

        writeFile("src/com/jetbrains/zCompanyInterviewPrep/amazon/ood", "text2", res);
    }

    public void countOccurance() {
        List<String> logFile = readFile("src/com/jetbrains/zCompanyInterviewPrep/amazon/ood", "text2");

        System.out.println("transmit error occured " + logFile.size() + " times");

        writeFile("src/com/jetbrains/zCompanyInterviewPrep/amazon/ood", "text3", Arrays.asList(logFile.size() + ":transmit error"));
    }

    public void histogram() {
        int[] times = new int[24];
        int[] counts = new int[24];

        List<String> logFile = readFile("src/com/jetbrains/zCompanyInterviewPrep/amazon/ood", "text2");

        // assume that log records are sorted...
        for (String s : logFile) {
            int index = s.indexOf(" ");
            String time = s.substring(0, index);

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date = sdf.parse(time);

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                int hourIndex = cal.get(Calendar.HOUR);


            }catch(Exception e) {
                System.out.println(e);
            }
        }
//        Calendar cal = Calendar.getInstance(TimeZone.getDefault());



    }

    public static void main(String[] args) {
        LogFileHistogram solution = new LogFileHistogram();
        solution.filterLogFile();
        solution.countOccurance();

        solution.histogram();
    }


}
