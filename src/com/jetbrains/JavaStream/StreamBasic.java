package com.jetbrains.JavaStream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamBasic {
    public static void main(String[] args) {
        String[] arr = new String[]{"ab", "b", "c"};
        List<String> li = new ArrayList<>(Arrays.asList(arr));
        li.stream().filter(i -> i.length() == 2).forEach(System.out::println);
    }
}
