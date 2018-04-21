package lengmi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class LengMiTest {

    public static void main(String[] args) {

        Document document = null;
        try {
            document = Jsoup.connect("http://www.lengmi.cc/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(document.toString());
    }
}
