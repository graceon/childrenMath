package controller;

public class ErrorMessages {
    /**
     *
     * @param path 打开文件路径
     */
    public static final void openFileError(String path){
        System.out.println("打开文件出错，路径："+path);
    }

    /**
     *
     * @param path 输出文件路径
     */
    public static final void writeFileError(String path){
        System.out.println("输出文件出错，路径："+path);
    }
}
