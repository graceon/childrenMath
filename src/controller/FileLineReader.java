package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileLineReader {
    BufferedReader reader = null;
    InputStreamReader fileStream = null;
    FileInputStream file = null;
    public String read(){
        String line=null;
        try {
            line = reader.readLine();
        }catch (IOException ignore){};
        return line;
    }
    public FileLineReader(String pathname) {

        try {
            //读取文件并且使用BufferedReader逐行分析
            file = new FileInputStream(pathname);
            fileStream = new InputStreamReader(file, "UTF-8");
            reader = new BufferedReader(fileStream);
        } catch (IOException e) {
            //输出错误信息
            ErrorMessages.openFileError(pathname);
        } finally {
        }
    }
}
