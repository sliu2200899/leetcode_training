package com.jetbrains.OOD;

import java.lang.reflect.Constructor;

public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        Student student = new Student("Shu");
        System.out.println(student.getName());

        Class studentClass = Class.forName("com.jetbrains.OOD.Student");
        Student studentFromRef = (Student)studentClass.getConstructor(String.class, Integer.class).newInstance("Shu", 5);
        Constructor[] consArr = studentClass.getConstructors();
        for (Constructor c : consArr) {
            System.out.println(c.toString());
        }
    }
}