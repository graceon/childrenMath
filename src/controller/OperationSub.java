package controller;

public class OperationSub extends Operation{
    public OperationSub(FractionNum value){

        FractionNum left=new FractionNum();
        FractionNum right=new FractionNum();
        if(value.isFraction()){
            //当前数字是分数
            value.splitFractionSub(left,right);
        }else{
            //当前数字是整数
            value.splitSub(left,right);
        }
        this.left=new Expression(left);
        this.right=new Expression(right);
    }
    @Override
    public String toString(){
        return this.left.toString()+" - "+this.right.toString();
    }
    public String toKatex(){
        return this.left.toKatex()+" - "+this.right.toKatex();
    }
}
