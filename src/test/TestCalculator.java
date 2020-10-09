package test;

import controller.Calculator;
import controller.FractionNum;

public class TestCalculator {
    public static void main(String[] args) {
        //+, −, ×, ÷,’,(,)
        FractionNum num=Calculator.calculator.calculate("1+3×1’1/3+(2+2)");
        System.out.println(num);
    }
}
