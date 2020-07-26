package com.jetbrains.JavaIONIO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class WriteFile {
    public static void main(String[] args) {
        // write files
//        Path filePath = Paths.get("/Users/sliu/Documents/code/java_code/wordWriteOutput.txt");
//        Charset charset = StandardCharsets.UTF_8;
//
//        try(BufferedWriter writer = Files.newBufferedWriter(filePath, charset)) {
//            //write data
//            writer.write("Hello world!");
//            writer.newLine();
//            writer.write("Learn how to write data to a File efficiently using BufferedWriter");
//            writer.newLine();
//        } catch (IOException e) {
//            System.out.format("I/O error: %s%n", e);
//        }
        String[] names = {"Alex", "Brian", "Charles", "David"};

    }
}
