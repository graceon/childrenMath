package controller;


import java.util.ArrayList;
import java.util.List;

/**
 * 无论是整数还是带分数还是真分数
 * 都可以用分子和分母进行统一的存储
 * 1.整数表示为分母为1的分数
 * 2.带分数表示内部表示为假分数，输出时才转换格式
 * 3.普通分数直接采用分子分母存储
 */
public class FractionNum {
    boolean reduced=false;
    //分子
    int numerator;
    //分母
    int denominator;

    public FractionNum() {
    }
    public FractionNum(int n, int d) {
        numerator = n;
        denominator = d;
        reduction();
    }
    public FractionNum(int n) {
        numerator = n;
        denominator = 1;
    }

    public void set(int n, int d) {
        numerator = n;
        denominator = d;
        reduction();
    }
    public void set(FractionNum input) {
        numerator = input.numerator;
        denominator = input.denominator;
        reduction();
    }
    public void set(int n) {
        numerator = n;
        denominator = 1;
    }
    public int getNum(){
        if(denominator!=1){
            System.out.println(new Throwable().getStackTrace()[0].toString());
        }
        return numerator;
    }

    /**
     * 约分处理
     */
    public void reduction(){
        int times=gcd(numerator,denominator);
        numerator/=times;
        denominator/=times;
        if(times>1) {
            this.reduced = true;
        }
    }

    /**
     * 判断能否分解成加法
     * @return
     */
    public boolean canConvertAdd(){
        if(numerator==1&&denominator*2>Restrict._r)
            return false;
        return true;
    }
    public boolean isFraction(){
        return !(numerator%denominator==0);
    }
    public boolean isWith(){
        return numerator>denominator&&denominator!=1;
    }
    public boolean isOne(){
        return numerator==denominator;
    }
    public boolean isZero(){
        return numerator==0;
    }
    public boolean isRestrict(){
        return (numerator/denominator)>=Restrict._r;
    }
    public boolean isSmallerHalfRestrict() {
        return (!this.isFraction())&&(numerator/denominator)<=(Restrict._r/2);
    }


    public void splitAdd(FractionNum left, FractionNum right){

        if(numerator==0){
            left.set(0);
            right.set(0);
            return;
        }
//        int mixedDenominator =0;
//        for(int times=2;times*getNum()<Restrict._r;times+=1){
//            mixedDenominator=times*getNum();
//            if(mixedDenominator*numerator<Restrict._r){
//                if(mixedDenominator*numerator==0){
//                    System.out.println(new Throwable().getStackTrace()[0]);
//                }
//                int leftNumerator=RandomUnit.getInt()%(mixedDenominator*numerator);
//                if(leftNumerator==0){
//                    leftNumerator+=1;
//                }
//                left.set(leftNumerator,mixedDenominator);
//                right.set(mixedDenominator*numerator-leftNumerator,mixedDenominator);
//                return;
//            }
//        }
        int val=numerator/denominator;
        int leftInt=0;
        //限制加法左边的数不为0也不刚好是上一个值
        while(leftInt<=0) {
            leftInt=RandomUnit.getInt() % val;
        }
        left.set(leftInt,1);
        right.set(val-leftInt,1);
        if(right.getNum()>=3&&left.getNum()>3){
            int withDenominator;
            withDenominator=RandomUnit.getInt() % Restrict._r;
            if(withDenominator>=10) {
                int leftN=RandomUnit.getInt() % withDenominator;
                int rightN=withDenominator-leftN;
                left.set(FractionNum.sub(left, new FractionNum(1)));
                left.set(FractionNum.add(left, new FractionNum(leftN,withDenominator)));
                right.set(FractionNum.add(right, new FractionNum(rightN,withDenominator)));
            }
        }
    }
    public void splitFractionAdd(FractionNum left, FractionNum right){
        int i=1;
        for(;i*denominator<Restrict._r;i+=1){

        }
        i-=1;
        if(i>50){
            i/=2;
            i+=1;
        }
        int leftNumerator=0;
        while (leftNumerator<=0||leftNumerator>=i*numerator){
            if(i*numerator==0)
                i=1;
            leftNumerator=RandomUnit.getInt()%(i*numerator);
        }
        int rightNumerator=i*numerator-leftNumerator;
        left.set(leftNumerator,i*denominator);
        right.set(rightNumerator,i*denominator);
    }
//    public void splitFractionSub(FractionNum left, FractionNum right){
//        int i=1;
//        for(;i*denominator<Restrict._r;i+=1){
//
//        }
//        i-=1;
//        if(i==0){
//            i=1;
//        }
//        int newDenominator=i*denominator;
//        int leftNumerator=0;
//        while (leftNumerator<=0||leftNumerator>=newDenominator){
//            leftNumerator=newDenominator-(RandomUnit.getInt()%newDenominator);
//        }
//        int rightNumerator=newDenominator-leftNumerator;
//
//        left.set(leftNumerator,newDenominator);
//        right.set(rightNumerator,newDenominator);
//    }
    public void splitFractionSub(FractionNum left, FractionNum right){
        if(numerator>=denominator){
            System.out.println(new Throwable().getStackTrace()[0]);
        }
        int i=1;
        for(;i*denominator<Restrict._r;i+=1){

        }
        i-=1;
        if(i==0){
            i=1;
        }
    //        System.out.println(i*denominator);
        //left=
        int newNumerator=i*numerator;
        int newDenominator=i*denominator;
        int leftNumerator=0;
//        while (leftNumerator<=0||leftNumerator>=newNumerator){
//            leftNumerator=newDenominator-(RandomUnit.getInt()%newNumerator);
//        }
        int rightNumerator=RandomUnit.getInt()%(newDenominator-newNumerator);
        if(rightNumerator==0)rightNumerator=1;
        leftNumerator=newNumerator+rightNumerator;

        left.set(leftNumerator,newDenominator);
        right.set(rightNumerator,newDenominator);
//        System.out.println("===");
//        System.out.println(this);
//        System.out.println(left);
//        System.out.println(right);
    }
    /**
     * 假设将a转化为b-c则b=a+c c=b-a
     * 先生成b
     * a<b<Restrict._r
     * @param left
     * @param right
     */
    public void splitSub(FractionNum left, FractionNum right){
        int val=numerator/denominator;
        int range=Restrict._r-val;
        int leftInt=val+((RandomUnit.getInt())%range+1);
        left.set(leftInt);
        right.set(leftInt-val);
    }
    public void splitDiv(FractionNum left, FractionNum right) {
        int val=getNum();
        int i=1;
        for(;i*val<Restrict._r;i+=1){
        }
        i-=1;
        if(i>10){
            i=(int)Math.sqrt(i);
        }
        left.set(i*val);
        right.set(i);
    }
    @Override
    public String toString() {

        if(isFraction()){
            if(numerator>denominator){
                return (numerator/denominator)+"’"+numerator%denominator+"/"+denominator;
            }
            return numerator+"/"+denominator;
        }
        else return ""+numerator/denominator;
    }

    public String toKatex(){
        if(isFraction()){
            if(numerator>denominator){
                return (numerator/denominator)+"\\\\frac"+"{"+numerator%denominator+"}"+"{"+denominator+"}";
            }
            return "\\\\frac"+"{"+numerator+"}"+"{"+denominator+"}";
        }
        return ""+getNum();
    }
    /**
     * 辗转相除法（欧几里得算法）求最大公因数
     * @param a 第一个数
     * @param b 第二个数
     * @return 最大公因数
     */
    private static int gcd(int a,int b) {
        return (b==0)?a:gcd(b,a%b);
    }

    /**
     * 寻找因数用于分解乘法
     * @return
     */
    public List<Integer> findFactors(){
        int input=this.getNum();
        int k=2;
        List<Integer> list=new ArrayList<>();
        while (true) {
            if (input % k == 0) {
                list.add(k);
                input /= k;
            } else
                k++;
            if (input == 1) break;
        }
        return list;
    }

    /**
     * 分数的加减乘除操作
     * @param a 左侧分数
     * @param b 右侧分数
     * @return 结果分数
     */
    public static FractionNum add(FractionNum a,FractionNum b){
        return new FractionNum(a.numerator*b.denominator+b.numerator*a.denominator,b.denominator*a.denominator);
    }
    public static FractionNum sub(FractionNum a,FractionNum b){
        return new FractionNum(a.numerator*b.denominator-b.numerator*a.denominator,b.denominator*a.denominator);
    }
    public static FractionNum mul(FractionNum a,FractionNum b){
        return new FractionNum(a.numerator*b.numerator,b.denominator*a.denominator);
    }
    public static FractionNum div(FractionNum a,FractionNum b){
        return new FractionNum(a.numerator*b.denominator,b.numerator*a.denominator);
    }

    /**
     * 带分数的处理a’b
     * @param a a为’前的整数
     * @param b b为后面的分数
     * @return
     */
    public static FractionNum with(FractionNum a, FractionNum b) {
        return new FractionNum(b.denominator*a.getNum()+b.numerator,b.denominator);
    }

    /**
     * 比较2个分数是否相等
     * @param compare
     * @return
     */
    public boolean equal(FractionNum compare){
        return numerator==compare.numerator&&denominator==compare.denominator;
    }
}
