package test;

import controller.CommandParse;
import org.junit.Test;

import java.util.Arrays;

public class TestGenerator {
    String[] checkCalculator={"-e","Exercises.txt","-a","Answers.txt"};
    String[] small_r={"-r","1"};
    @Test
    public void basicTest() {
        String[][] argsArray = {
                {"-r", "0", "-n", "1000"},
                {"-r", "-n", "10000"},
                {"-r", "100", "-n", "10000"},
        };
        for (String[] args : argsArray) {
            testCommandParse(args);
        }


    }
    @Test
    public void testCalculator() {
        testCommandParse(checkCalculator);
    }
    @Test
    public void testsmall_r() {
        testCommandParse(small_r);
    }
    public static void testCommandParse(String[] args){
        System.out.println("测试参数列表:"+Arrays.toString(args));
        CommandParse.main(args);
        System.out.println();
    }
}
