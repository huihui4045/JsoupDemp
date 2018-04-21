package rosi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoSiImageApi {

    public static  String BASE_URL="http://mmp.mmxyz.net/index/";




    public static List<String>  getRoSiImages(String url){

        List<String>  datas=new ArrayList<>();

        try {
            Document mItemDocument = Jsoup.connect(url).get();


            Elements photoThum = mItemDocument.getElementsByClass("photoThum");


            for (Element element : photoThum) {

                Elements a = element.select("a");

                String imageUrl = a.attr("href");

                datas.add(imageUrl);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return datas;
    }

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

                String simple = getSubUtilSimple(image_url, rgex);

                RoSiBean siBean=new RoSiBean(url,simple,title);

                String num = getSubUtilSimple(title,rgex_num);

                siBean.setNum(num);


                list.add(siBean);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }

   static String rgex = "src=(.*?)&w=";

    static String rgex_num = "\\[(.*?)P\\]";

    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }
}
