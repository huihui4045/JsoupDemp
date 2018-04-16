package jj20;

import download.DownLoadUtils;
import huihui.ImageItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.TimeUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavin on 2017/12/20.
 * http://www.jj20.com/bz/nxxz/nxmt/10558.html
 * http://pic.jj20.com/up/allimg/1011/12191G42Q6/1G219142Q6-4.jpg
 * http://pic.jj20.com/up/allimg/1011/12191G42Q6/1G219142Q6-2.jpg
 */
public class JJ20ImageTest {


    private static String url = JJ20ImageData.DATA[JJ20ImageData.DATA.length - 1];


    public static void main(String[] args) {


        try {
            getDownLoadImage();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private static void getDownLoadImage() throws IOException {

        for (int i = 0; i < JJ20ImageData.DATA.length - 1; i++) {

            if (JJ20ImageData.DATA[i].equals(url)) {

                System.out.println("数据已经重复");

                return;
            }

        }

        List<ImageItem> datas = getListImages(url);


        System.out.println("datas:\n"+datas.toString());


        //String url="C:\\Users\\gavin\\Desktop\\晨读\\今日头条\\%s\\%s";

       String url="C:\\Users\\molu_\\Desktop\\简书\\今日头条\\%s\\%s";

        System.out.println("url:"+url);


        File file = new File(String.format(url,
                TimeUtil.getTimeShort(), datas.get(0).getTitle()));


        if (!file.exists()) {

            file.mkdirs();
        }

        List<String> images = new ArrayList<>(datas.size());

        for (ImageItem data : datas) {

            images.add(data.getSrc());
        }

        DownLoadUtils.downLoadImageReferrer(images, file.getAbsolutePath(),"http://www.jj20.com");




    }


    private static List<ImageItem> getListImages(String url) {

        try {
            Document document = Jsoup.connect(url).get();


            Element photoElement = document.getElementsByClass("photo").get(0);

            Element numElement = document.getElementsByClass("wzfz tu-tit fix").get(0);

            String h1 = numElement.getElementsByTag("h1").get(0).text();

            int i = h1.indexOf('/');

            String substring = h1.substring(i + 1, h1.length() - 1);

            Element imgElement = photoElement.getElementsByTag("img").get(0);




            int num = Integer.valueOf(substring).intValue();


            String mImageUrl = imgElement.attr("src");
            String mImageTitle = imgElement.attr("alt");


            System.out.println("mImageUrl = [" + mImageUrl + "]");


            List<ImageItem> datas = new ArrayList<>(num);

            boolean contains = mImageUrl.contains("0.jpg");

            int j=0;

            if (!contains){

                j=1;
                num++;
            }





            for ( ; j <num; j++) {


                String newUrl = mImageUrl.replace(contains ?"0.jpg":"1.jpg", String.format("%d.jpg", j));

                ImageItem imageItem = new ImageItem(newUrl, mImageTitle);

                datas.add(imageItem);
            }


            return datas;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
