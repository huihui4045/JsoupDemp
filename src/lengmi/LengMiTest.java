package lengmi;

import huihui.ImageItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class LengMiTest {

    public static void main(String[] args) {


        List<ImageItem> videoList = LengMiVideoApi.getVideoList(1);





        System.out.println(videoList.toString());


       // String videoUrl = LengMiVideoApi.getVideoUrl("http://www.lengmi.cc/v/9753.html");
    }
}
