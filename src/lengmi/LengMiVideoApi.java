package lengmi;

import huihui.ImageItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by molu_ on 2018/4/22.
 */
public class LengMiVideoApi {

    static String baseUrl="http://www.lengmi.cc/page/";





    public static String  getVideoUrl(String url){


        System.out.println("url = [" + url + "]");

        try {
            Document document = Jsoup.connect(url).get();


            Elements script = document.select("script");


            for (Element element : script) {

                if (element.html().contains("var player = ")){

                    //element.toString()


                    String videourl = getSubUtilSimple(element.toString(), file);

                    System.out.println("file = [" + videourl + "]");


                    return  videourl;
                }


            }


        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("IOException = [" + e.toString() + "]");
        }


        return "";


    }


    public static List<ImageItem>  getVideoList(int page){

        String url=String.format("%s%d",baseUrl,page);


        List<ImageItem>  datas=new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();


            Elements elements = document.getElementsByClass("m-lp");


            for (Element element : elements) {


                Element imgElement = element.getElementsByClass("img").get(0);

                Elements a = imgElement.select("a");

                Elements img = imgElement.select("img");


                String nextPage=a.attr("href");



                String title=a.attr("title");

                String imageurl=img.attr("src");

                ImageItem imageItem=new ImageItem(getSubUtilSimple(imageurl,rgex),title);

                imageItem.setNextPage(nextPage);


                datas.add(imageItem);

            }




        } catch (IOException e) {
            e.printStackTrace();
        }



        return  datas;
    }

    static String rgex = "src=(.*?)&h=";

    static String file="file:(.*?),";

    //static String rgex_num = "\\[(.*?)P\\]";

    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }
}
