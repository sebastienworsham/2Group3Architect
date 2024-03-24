package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;


public class IOStreamsTest {
    public static final String SAVEPATH = "src/main/java/main/save.csv";

    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter(SAVEPATH);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("testing");
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testIOStreams() {
        try {
            FileReader fr = new FileReader(SAVEPATH);
            BufferedReader br = new BufferedReader(fr);

            Assertions.assertEquals("testing", br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
