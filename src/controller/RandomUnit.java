package controller;

import java.util.Random;

public class RandomUnit {
    public static Random random=new Random();
    public static int getInt(){
        return Math.abs(random.nextInt());
    }
    public static boolean getBoolean(){
        return random.nextBoolean();
    }
    public enum Side{
        Left,Right
    }
    public static Side getSide(){
        switch (random.nextInt()%2){
            case 0:
                return Side.Left;
            case 1:
                return Side.Right;
        }
        return Side.Left;
    }
    public static Operation.type getOperation(){
        switch (random.nextInt()%4){
            case 0:
                return Operation.type.add;
            case 1:
                return Operation.type.sub;
            case 2:
                return Operation.type.mul;
            case 3:
                return Operation.type.div;
        }
        return Operation.type.add;
    }
}
