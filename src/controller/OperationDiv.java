package controller;

public class OperationDiv extends Operation{
    public OperationDiv(FractionNum value){

        FractionNum left=new FractionNum();
        FractionNum right=new FractionNum();

        value.splitDiv(left,right);

        this.left=new Expression(left);
        this.right=new Expression(right);
    }
    @Override
    public String toString(){
        return this.left.toString()+" ÷ "+this.right.toString();
    }
    public String toKatex(){
        return this.left.toKatex()+" \\\\div "+this.right.toKatex();
    }
}
