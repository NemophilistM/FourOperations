package zsc;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @Test
    void writeFile() {
        try {
            FileUtil.writeFile("jdf","D:\\youranswer.txt",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readFile() {
        FileUtil.readFile("D:\\youranswer.txt");
    }
}