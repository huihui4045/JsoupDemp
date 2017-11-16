package huihui;

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
 * Created by gavin on 2017/10/26.
 */
public class MMImageApi {


    private static final String BASEURL="http://www.mm131.com/";

   private static String RAGEX_IMAGE = "0.jpg";

    private static final String regex="\\d+-\\d+-\\d+\\s\\d+:\\d+:\\d+";


    private static final  String MM_TYPE_XING_GAN="xinggan";
    private static final  String MM_TYPE_QING_CHUN="qingchun";
    private static final  String MM_TYPE_XIAO_HUA="xiaohua";
    private static final  String MM_TYPE_CHE_MO="chemo";
    private static final  String MM_TYPE_QI_PAO="qipao";
    private static final  String MM_TYPE_MING_XING="mingxing";

    public static final String [] MM_TYPE={MM_TYPE_XING_GAN,MM_TYPE_QING_CHUN,MM_TYPE_XIAO_HUA,MM_TYPE_CHE_MO,MM_TYPE_QI_PAO,MM_TYPE_MING_XING};
    public static final String [] MM_TYPE_NAME={"性感美女","清纯美眉","美女校花","性感车模","旗袍美女","明星写真"};


    private static int getPageType(String flag){

        switch (flag){
            case MM_TYPE_XING_GAN:

                return 6;
            case MM_TYPE_QING_CHUN:

                return 1;
            case MM_TYPE_XIAO_HUA:
                return 2;
            case MM_TYPE_CHE_MO:
                return 3;
            case MM_TYPE_QI_PAO:
                return 4;

            case MM_TYPE_MING_XING:
                return 5;

        }

        return 6;
    }

    /***
     * 获取性感妹妹图片数据
     * @param page
     * @return
     * @throws IOException
     */
    public static List<MMImageBean> getXingGanImages(int page) throws IOException {


        return  getImagesFromType(page,MM_TYPE_XING_GAN);
    }


    /****
     * 获取清纯妹妹图片数据
     * @param page
     * @return
     * @throws IOException
     */
    public static List<MMImageBean> getQingChunImages(int page) throws IOException {

        return  getImagesFromType(page,MM_TYPE_QING_CHUN);
    }

    /****
     * 获取校花妹妹图片数据
     * @param page
     * @return
     * @throws IOException
     */
    public static List<MMImageBean> getXiaoHuaImages(int page) throws IOException {

        return  getImagesFromType(page,MM_TYPE_XIAO_HUA);
    }


    /****
     * 获取车模妹妹图片数据
     * @param page
     * @return
     * @throws IOException
     */
    public static List<MMImageBean> getCheMoImages(int page) throws IOException {

        return  getImagesFromType(page,MM_TYPE_CHE_MO);
    }


    /****
     * 获取旗袍妹妹图片数据
     * @param page
     * @return
     * @throws IOException
     */
    public static List<MMImageBean> getQiPaoImages(int page) throws IOException {

        return  getImagesFromType(page,MM_TYPE_QI_PAO);
    }


    /****
     * 获取明星妹妹图片数据
     * @param page
     * @return
     * @throws IOException
     */
    public static List<MMImageBean> getMingXingImages(int page) throws IOException {

        return  getImagesFromType(page,MM_TYPE_MING_XING);
    }








    public static List<MMImageBean>  getImagesFromType(int page, String flag) throws IOException {


        String url="";
        if (page==1){

            url=String.format("%s%s",BASEURL,flag);
        }else {

            String item=String.format("list_%d_%d.html",getPageType(flag),page);

            url=String.format("%s%s/%s",BASEURL,flag,item);
        }

        System.out.println("请求的URL："+url);


        Document document = Jsoup.connect(url).get();

        return getImages(document);
    }


    /***
     * 获取各类型图片
     * @param document
     * @return
     */
    private static List<MMImageBean>  getImages(Document document) throws IOException {

        Elements publicElements = document.getElementsByClass("list-left public-box");

            Element publicElement = publicElements.get(0);

            Elements selects = publicElement.select("a[target='_blank']");

            List<MMImageBean> datas=new ArrayList<>(selects.size());

            for (Element element : selects) {

                String url=  element.attr("href");
                element.text();


                Elements img = element.getElementsByTag("img");
                String imageUrl= img.attr("src");

                String title=img.attr("alt");

                MMImageBean MMImageBean =new MMImageBean(imageUrl,title);


                Document ItemDocument = Jsoup.connect(url).get();


                Elements elements = ItemDocument.select("span[class=page-ch]");

                String  allpage= elements.get(0).text();

                String contentpage=allpage.substring(1,allpage.length()-1);


                int num = Integer.valueOf(contentpage);


                Elements updateTimeElements = ItemDocument.select("div[class=content-msg]");

               String update= updateTimeElements.first().text();


                //String updateTime=update.split(regex);

                Pattern pattern =Pattern.compile(regex);
                Matcher matcher = pattern.matcher(update);

                if (matcher.find()){

                    String updateTime = matcher.group(0);
                    MMImageBean.setUpdateTime(updateTime);
                }


                List<String> itemUrls=new ArrayList<>(num);


                for (int i = 0; i < num; i++) {

                    String newstr = String.format("%d.jpg",i);

                  String s=  imageUrl.replace(RAGEX_IMAGE,newstr);

                    itemUrls.add(s);
                }

                MMImageBean.setPage(num);
                MMImageBean.setItems(itemUrls);


                datas.add(MMImageBean);
            }


        return  datas;
    }

    //http://img1.mm131.com/pic/3137/1.jpg





}
