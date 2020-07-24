package com.jetbrains.OOD;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
//        Student student = new Student("Shu");
//        System.out.println(student.getName());
//
//        Class studentClass = Class.forName("com.jetbrains.OOD.Student");
//        Student studentFromRef = (Student)studentClass.getConstructor(String.class, Integer.class).newInstance("Shu", 5);
//        System.out.println(studentFromRef.getName());
//
//        Field[] declaredFields = studentClass.getDeclaredFields();
//        Field[] fields = studentClass.getFields();
//
//        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//        }
//        for (Field field : fields) {
//            System.out.println(field.getName());
//        }
//
//        Method[] declaredMethods = studentClass.getDeclaredMethods();
//        Method[] methods = studentClass.getMethods();

        Person person = new Person();
        person.setName("test");
        person.setAge(20);

        Student student = new Student();
//        student.setName(person.getName());
//        student.setAge(person.getAge());
        // source -> destination
        Class<? extends Person> personClass = person.getClass();
        Class<? extends Student> studentClass = student.getClass();
        Method[] personClassDeclaredMethods = personClass.getDeclaredMethods();
        Method[] studentClassDeclaredMethods = studentClass.getDeclaredMethods();
        for (int i = 0; i < personClassDeclaredMethods.length; i++) {
            if (personClassDeclaredMethods[i].getName().contains("get")) {
                // getName getAge -> setName setAge
                String setterName = "set" + personClassDeclaredMethods[i].getName().substring(3);
                Object getterValue = personClassDeclaredMethods[i].invoke(person);

                for (int j = 0; j < studentClassDeclaredMethods.length; j++) {
                    if (studentClassDeclaredMethods[j].getName().equals(setterName)) {
                        studentClassDeclaredMethods[j].invoke(student, getterValue);
                    }
                }
            }
        }
        System.out.println(student.getName() + " - " + student.getAge());
    }
}