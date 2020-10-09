package controller;

import java.util.List;

public class OperationMul extends Operation{
    public OperationMul(FractionNum value, List<Integer> factors) {
        int leftNum = 1;
        int val = value.getNum();
        int size = factors.size();
        int i = size - 1;
        int kind;
        if(factors.get(i)>=3){
            kind=1;
        }else {
            kind=0;
        }
        switch (kind) {
            case 0:
                for (; i > (size / 2 - 1); i -= 1) {
                    leftNum *= factors.get(i);
                }
                {
                    FractionNum left = new FractionNum(leftNum);
                    FractionNum right = new FractionNum(val / leftNum);
                    this.left = new Expression(left);
                    this.right = new Expression(right);
                }
                break;
            case 1:
                int denominator=factors.get(i)+1;
                int numerator=factors.get(i);
                leftNum = val/numerator*denominator;
                {
                    if(leftNum>=Restrict._r || RandomUnit.getBoolean()){
                        denominator=factors.get(i);
                        numerator=factors.get(i)-1;
                        leftNum = val/denominator*numerator;
                        showSymbol="÷";
                    }
                    FractionNum left = new FractionNum(leftNum);
                    FractionNum right = new FractionNum(numerator,denominator);
                    this.left = new Expression(left);
                    this.right = new Expression(right);
                }
                break;
        }
    }
    private String showSymbol="×";
    @Override
    public String toString(){
        return this.left.toString()+" "+showSymbol+" "+this.right.toString();
    }
    public String toKatex(){
        if(this.showSymbol=="×"){
            return this.left.toKatex()+" \\\\times "+this.right.toKatex();
        }
        return this.left.toKatex()+" \\\\div "+this.right.toKatex();
    }
}
