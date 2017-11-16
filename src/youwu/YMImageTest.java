package youwu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by gavin on 2017/11/14.
 */
public class YMImageTest {

    private static String home="http://www.ugirls.com/Index/Search/Magazine-77.html";

    private static String item="http://www.ugirls.com/Shop/Detail/Product-408.html";


    private static String list="http://www.ugirls.com/Content/List/Magazine-408.html";

    public static void main(String[] args) {

        //http://img.ugirls.tv/uploads/magazine/content/a71880b13f24e62c3be31b5b660fccd5_magazine_web_m.jpg


        Document document = null;
        try {
            document = Jsoup.connect(list).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( document.toString());


    }
}
