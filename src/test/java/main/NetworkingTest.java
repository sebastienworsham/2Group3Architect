package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkingTest {

    public static void main(String[] args) {

    }

    @Test
    void testNetworking() {
        URL url;
        try {
            url = new URL("https://sebastienworsham.github.io/LeaderBoardInfo/leaderboardSaveInfo.csv");
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            Assertions.assertEquals("Sebastien,8", br.readLine());
        } catch (MalformedURLException e) {
            System.err.println("Couldn't make network connection");
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
