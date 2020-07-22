package com.jetbrains.OOD;

import javafx.scene.Parent;

class AreaCalculator {
    private double result;

    public double getResult() {
        return this.result;
    }

    public void calculateArea(Shape s) {
        this.result = s.getArea();
    }
}

interface Shape{
    public double getArea();
}

class Triangle implements Shape {

    private float b;
    private float h;

    public Triangle(float b, float h) {
        this.b = b;
        this.h = h;
    }

    public double getArea() {
        return b * h / 2;
    }
}

class Main {
    public static void main(String[] args) {
        //
        Shape s = new Triangle(3.0f, 8.0f);
        AreaCalculator aa = new AreaCalculator();
        aa.calculateArea(s);
        System.out.println(aa.getResult());
    }
}