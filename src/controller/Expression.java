package controller;

import java.util.List;

public class Expression {
    //表达式的拆分前的值
    public FractionNum value;
    //表达式包含的操作符
    public Operation operation = null;
    //表达式是否必须包含符号
    public boolean bracket=false;
    //用数值初始化表达式
    public Expression(FractionNum v){
        value=v;
    }

    /**
     * 将没有运算符的表达式拆分成含有操作符和左右操作数的表达式。
     * @param end 为false时，分割后的表达式不可以继续拆分子表达式，用于防止运算符超过3个以上，如果end为true表示可以继续拆分
     * @return 返回分割后表达式的操作符，传递给上层表达式来生成括号
     */
    public Operation.type split(boolean end){
        //随机分配一种运算符，若不能按照改运算符分解，则自动选择备选分解方案
        Operation.type op = RandomUnit.getOperation();
        switch (op){
            case div:
                //检查能不能分解成除法，若可以则分解
                if(this.value.isSmallerHalfRestrict()&&!this.value.isZero()){
                    operation = new OperationDiv( value);
                    op= Operation.type.div;
                    if(!end) {
                        switch (RandomUnit.getSide()) {
                            //随机选取左右，表达式进行扩展，按照规则添加必要的括号
                            case Left:
                                switch (operation.left.split(true)){
                                    case add:
                                    case sub:
                                        //添加必要的括号
                                        operation.left.bracket=true;
                                        break;
                                }
                                break;
                            case Right:
                                switch (operation.right.split(true)){
                                    case add:
                                    case sub:
                                    case div:
                                    case mul:
                                        //添加必要的括号
                                        operation.right.bracket=true;
                                        break;
                                }
                        }
                    }
                    break;
                }
            case mul:
                //检查能不能分解成乘法，若可以则分解
                if(!this.value.isFraction()&&!this.value.isOne()&&!this.value.isZero()){
                    List<Integer> factors=this.value.findFactors();
                    if(factors.size()!=1) {
                        operation = new OperationMul( value,factors);
                        op= Operation.type.mul;
                        if(!end) {
                            switch (RandomUnit.getSide()) {
                                //随机选取左右，表达式进行扩展，按照规则添加必要的括号
                                case Left:
                                    switch (operation.left.split(true)){
                                        case add:
                                        case sub:
                                            //添加必要的括号
                                            operation.left.bracket=true;
                                            break;
                                    }
                                    break;
                                case Right:
                                    switch (operation.right.split(true)){
                                        case add:
                                        case sub:
                                            //添加必要的括号
                                            operation.right.bracket=true;
                                            break;
                                    }
                            }
                        }
                        break;
                    }
                }

            case sub:
                //检查能不能分解成减法，若可以则分解
                if(!this.value.isRestrict()&&!this.value.isWith()) {//&&!this.value.isFraction()
                    operation = new OperationSub(value);
                    op= Operation.type.sub;
                    if(!end) {
                        switch (RandomUnit.getSide()) {
                            //随机选取左右，表达式进行扩展，按照规则添加必要的括号
                            case Left:
                                operation.left.split(true);
                                break;
                            case Right:
                                switch (operation.right.split(true)) {
                                    case add:
                                    case sub:
                                        //添加必要的括号
                                        operation.right.bracket=true;
                                }
                                break;
                        }
                    }
                    break;
                }
            case add:
                //检查能不能分解成加法，若可以则分解
                if(this.value.canConvertAdd()) {
                    operation = new OperationAdd( value);
                    op= Operation.type.add;
                    if(!end) {
                        switch (RandomUnit.getSide()) {
                            //随机选取左右，表达式进行扩展，按照规则添加必要的括号
                            case Left:
                                operation.left.split(true);
                                break;
                            case Right:
                                operation.right.split(true);
                                break;
                        }
                    }
                    break;
                }

        }
        return op;
    }
    @Override
    public String toString(){
        if(operation!=null){
            if(bracket) {
                return "("+operation.toString()+")";
            }
            return operation.toString();
        }
        return value.toString();
    }
    public String toKatex(){
        if(operation!=null){
            if(bracket) {
                return "("+operation.toKatex()+")";
            }
            return operation.toKatex();
        }
        return value.toKatex();
    }
}
