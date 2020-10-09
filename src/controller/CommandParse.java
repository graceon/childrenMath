package controller;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParse {
    /**
     * 处理命令行参数
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        Pattern number= Pattern.compile("[0-9]+");
        Pattern txt= Pattern.compile("(.)+\\.txt");

        //用于存储-r参数
        int _r = -1;
        //用于存储-n参数
        int _n = 100;

        int argIndex=0;
        int argLength=args.length;
        String get;
        for(;argIndex<argLength;argIndex+=1) {
            switch (args[argIndex]) {
                case "-r":
                    get = args[argIndex + 1];
                    if (get != null) {
                        Matcher matcher = number.matcher(get);
                        while (matcher.find()) {
                            _r = Integer.parseInt(matcher.group());
                        }
                    }
                    argIndex += 1;
                    break;
                case "-n":
                    get = args[argIndex + 1];
                    if (get != null) {
                        Matcher matcher = number.matcher(get);
                        while (matcher.find()) {
                            _n = Integer.parseInt(matcher.group());
                        }
                    }
                    argIndex += 1;
                    break;
                case "-e":
                    String exerciseFile="";
                    String answerFile="";
                    if(argIndex!=0||argLength<4||!args[2].equals("-a")){
                        return;
                    }
                    if (args[1] != null) {
                        Matcher matcher = txt.matcher(args[1]);
                        while (matcher.find()) {
                            exerciseFile = matcher.group();
                        }
                    }
                    if (args[3] != null) {
                        Matcher matcher = txt.matcher(args[3]);
                        while (matcher.find()) {
                            answerFile = matcher.group();
                        }
                    }
                    if(exerciseFile.length()<5||answerFile.length()<5){
                        System.out.println(new Throwable().getStackTrace()[0]);
                    }
                    Result.CalculateFile(exerciseFile,answerFile);
                    return;
            }
        }


        if(_r<1){
            System.out.println("请输入参数-r，并在后面输入正数");
            return;
        }
        //用于存储-n参数
        Result.expressionMax = _n;
        //用于存储-r参数
        Restrict._r = _r;

        for (int i = _r - 1; Result.expressionCnt <= Result.expressionMax && i < _r && i >= 0; i -= 1) {
            everyValue(new FractionNum(i));
        }
        for (int i = _r - 1; Result.expressionCnt <= Result.expressionMax && i < _r && i >= 0; i -= 1) {
            for (int numerator = i - 1; numerator > 0; numerator -= 1) {
                if (Result.expressionCnt >= Result.expressionMax)
                    break;
                everyValue(new FractionNum(numerator, i));
            }
        }
        Result.Write();
    }

    public static void everyValue(FractionNum num) {
        Set<Integer> conflict = new HashSet<>();
        int conflictCount;
        for (int expressionCount = 0; expressionCount <= 10; expressionCount += 1) {
            conflictCount = 0;
            String line = Result.expressionCnt+1 + ".";

            Expression expression = new Expression(num);
            expression.split(RandomUnit.getBoolean());
            Operation operation = expression.operation;
            if (operation == null) {
                break;
            }
            if (conflict.contains(operation.left.value.numerator) || conflict.contains(operation.right.value.numerator)) {
                conflictCount += 1;

                if (conflictCount >= 20)
                    break;
                else continue;
            }
            if (Result.expressionCnt >= Result.expressionMax)
                break;

            Result.expressionCnt += 1;
            Result.Exercises.append(line + expression.toString() + "\n");
            Result.Answers.append(line + expression.value.toString() + "\n");
            if(Result.expressionCnt<200) {
                Result.Katex.append("+\"" + line + expression.toKatex() + "=" + expression.value.toKatex() + "\\\\newline\\\\quad\\\\newline" + "\"" + "\n");
            }
            conflict.add(operation.left.value.numerator);
            conflict.add(operation.right.value.numerator);
        }
    }
}
