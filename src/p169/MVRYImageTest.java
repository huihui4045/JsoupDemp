package p169;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by gavin on 2017/11/15.
 */
public class MVRYImageTest {

    //http://www.169b.com/rentiyishu/

    static String rentiyishu="http://www.169b.com";

    public static void main(String[] args) {

        Document document = null;
        try {
            document = Jsoup.connect(rentiyishu).get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(document.toString());
    }
}
