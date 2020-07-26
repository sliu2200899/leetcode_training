package com.jetbrains.JavaIONIO;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {

        // method 1: BufferedReader
//        File file = new File("/Users/sliu/Documents/code/java_code/wordcounttext.txt");
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String st;
//            while ((st = br.readLine()) != null) {
//                System.out.println(st);
//            }
//        } catch(IOException e) {
//          e.printStackTrace();
//        }

        // method 2: FileReader
//        File file = new File("/Users/sliu/Documents/code/java_code/wordcounttext.txt");
//        try {
//            Scanner sc = new Scanner(file);
//            while (sc.hasNextLine()) {
//                System.out.println(sc.nextLine());
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        }

        // method 3: reading the whole file in a list
//        List l = readFileInList("/Users/sliu/Documents/code/java_code/wordcounttext.txt");
//        Iterator<String> itr = l.iterator();
//        while(itr.hasNext()) {
//            System.out.println(itr.next());
//        }

        // method 4: reading the text file as a string
//        String data = readFileAsString("/Users/sliu/Documents/code/java_code/wordcounttext.txt");
//        System.out.println(data);

        // method 5: scanner with paths
        try {
            Scanner sc = new Scanner(Paths.get("/Users/sliu/Documents/code/java_code/wordcounttext.txt"), "UTF-8");
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private static String readFileAsString(String fileName) throws Exception {
//        String data = "";
//        data = new String(Files.readAllBytes(Paths.get(fileName)));
//        return data;
//    }

//    private static List<String> readFileInList(String fileName) {
//        List<String> lines = Collections.emptyList();
//        try {
//            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return lines;
//    }
}
