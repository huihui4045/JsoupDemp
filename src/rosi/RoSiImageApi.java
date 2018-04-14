package rosi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoSiImageApi {

    public static  String BASE_URL="http://mmp.mmxyz.net/index/";

    public static List<RoSiBean> getRosiImages(int page){

        List<RoSiBean> list=new ArrayList<>();

        String item_url=String.format("%s%d.html",BASE_URL,page);


        try {
            Document document = Jsoup.connect(item_url).get();


            Elements elements = document.getElementsByClass("post-thumbnail");

            for (Element element : elements) {


                Element item = element.select("a").get(0);

                String url=  item.attr("href");

                String title=  item.attr("title");

                Elements img = item.getElementsByTag("img");

                String image_url= img.attr("src");


                RoSiBean siBean=new RoSiBean(url,image_url,title);

                list.add(siBean);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }
}
