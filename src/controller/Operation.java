package controller;

public class Operation {
    public String toKatex() {
        return "";
    }

    //操作符类型
    enum type{
        add,sub,mul,div
    }
    //左表达式
    public Expression left;
    //右表达式
    public Expression right;
}
