package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Result {
    static int expressionCnt=0;
    static int expressionMax=0;
    static StringBuilder Exercises= new StringBuilder();
    static StringBuilder Answers= new StringBuilder();
    static StringBuilder Katex= new StringBuilder();
    static String htmlStart="<!DOCTYPE html>\n" +
            "<html lang=\"en\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "    <title>Exercise</title>\n" +
            "    <script src=\"./katex/jquery.min.js\"></script>\n" +
            "    <link rel=\"stylesheet\" href=\"./katex/katex.min.css\">\n" +
            "    <script src=\"./katex/katex.min.js\"></script>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>前200条题目：</h1>\n" +
            "<span class=\"sp1\">...</span>\n" +
            "<script>\n" +
            "html = katex.renderToString(\" \"";
    static String htmlEnd=");\n" +
            "$(\".sp1\").html(html)\n" +
            "</script>\n" +
            "</body>1\n" +
            "</html>";
    private static final String ExercisesPath="Exercises.txt";
    private static final String AnswersPath="Answers.txt";
    private static final String KatexPath="katex.html";
    public static void Write() {
        try {
            File file = new File(ExercisesPath);  //地址
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            osw.write(Exercises.toString());
            osw.flush();
            osw.close();
        }catch (Exception e){}
        try {
            File file = new File(AnswersPath);  //地址
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            osw.write(Answers.toString());
            osw.flush();
            osw.close();
        }catch (Exception e){}
        try {
            File file = new File(KatexPath);  //地址
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            osw.write(htmlStart+Katex.toString()+htmlEnd);
            osw.flush();
            osw.close();
        }catch (Exception e){}
        System.out.println("生成完成");
    }
    public static void CalculateFile(String exercisesPath,String answersPath){
        int lineCount=0;
        int correctCount=0;
        int wrongCount=0;
        StringBuilder correct = new StringBuilder();
        StringBuilder wrong = new StringBuilder();
        FileLineReader exercises=new FileLineReader(exercisesPath);
        FileLineReader answers=new FileLineReader(answersPath);
        for(;;lineCount+=1){
            String e=exercises.read();
            if(e!=null){
                String[] lineSplit=e.split("\\.");
                   if(lineSplit.length==2){
                      e=lineSplit[1];
                   }
            }
            String a=answers.read();
            if(a!=null){
                String[] lineSplit=a.split("\\.");
                if(lineSplit.length==2){
                    a=lineSplit[1];
                }
            }
            if(e==null||a==null)
                break;
            if(Calculator.calculator.calculate(e).equal(Calculator.calculator.calculate(a))){
                correct.append(lineCount+1+",");
                correctCount+=1;
            }else {
                //System.out.println(wrong);
                wrong.append(lineCount+1+",");
                wrongCount+=1;
            }
        }
        try {
            String cs=correct.toString();
            if (cs.length()>0) {
                cs = cs.substring(0, cs.length() - 1);
            }
            String ws=wrong.toString();
            if (ws.length()>0) {
                ws=ws.substring(0,ws.length()-1);
            }

            File file = new File("Grade.txt");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(
                    "Correct: "+correctCount+" ("+cs+")"
                            +"\n"+
                            "Wrong: "+wrongCount+" ("+ws+")"
            );
            osw.flush();
            osw.close();
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("检查完成");

    }
}
