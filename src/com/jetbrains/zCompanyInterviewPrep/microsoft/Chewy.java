package com.jetbrains.zCompanyInterviewPrep.microsoft;

import java.util.*;

public class Chewy {
    public static void process (String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Character key = (Character)it.next();
            System.out.println(key + " " + map.get(key)) ;
        }
    }

    public static void main(String[] args)  {
        process("strisngggg");
    }
}
