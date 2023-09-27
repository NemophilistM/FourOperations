package zsc;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {

    /**
     * @param s       要写入文件的字符串
     * @param path    要写完入的路径
     * @param isAddTo 写入文件方式是否追加
     * @throws IOException 文件路径不存在
     */
    public static void writeFile(String s, String path, boolean isAddTo) throws IOException {
        FileOutputStream File = new FileOutputStream(path, isAddTo);
        byte[] byteList = s.getBytes();
        File.write(byteList);
        File.write("\n".getBytes());
        File.close();
    }

    /**
     * @param path 要读取的文件地址
     * @return 返回读取出来的集合
     */
    public static ArrayList<String> readFile(String path) {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    path));
            String line = reader.readLine();
            list.add(line);
            while (line != null) {
                line = reader.readLine();
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
