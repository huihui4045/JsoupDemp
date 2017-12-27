package buluo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by gavin on 2017/12/26.
 */
public class QQBuluTest {

    public static void main(String[] args) {

        try {
            Document document = Jsoup.connect("https://buluo.qq.com/p/detail.html?bid=10440&pid=7430055-1514124587").get();


            System.out.println("document = [" + document + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
