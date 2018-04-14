package rosi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class RoSiImageTest {


    //http://mmp.mmxyz.net/index/2.html


    public static void main(String[] args) throws IOException {


        List<RoSiBean> rosiImages = RoSiImageApi.getRosiImages(1);

        System.out.println(rosiImages);

        //System.out.println(elements.get(0).toString());

    }
}
