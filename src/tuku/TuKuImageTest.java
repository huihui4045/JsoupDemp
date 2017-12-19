package tuku;

import download.DownLoadUtils;
import huihui.ImageItem;
import mmonly.MMOnlyData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.TimeUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavin on 2017/12/19.
 */
public class TuKuImageTest {

    private static String url = TuKuData.DATA[TuKuData.DATA.length - 1];


    public static void main(String[] args) {


        try {
            getDownLoadImage();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void getDownLoadImage() throws IOException {

        for (int i = 0; i < TuKuData.DATA.length - 1; i++) {

            if (MMOnlyData.DATA[i].equals(url)) {

                System.out.println("数据已经重复");

                return;
            }

        }

        List<ImageItem> datas = getListImages(url);

        String url="C:\\Users\\gavin\\Desktop\\晨读\\今日头条\\%s\\%s";


        File file = new File(String.format(url,
                TimeUtil.getTimeShort(), datas.get(0).getTitle()));


        if (!file.exists()) {

            file.mkdirs();
        }

        List<String> images = new ArrayList<>(datas.size());

        for (ImageItem data : datas) {

            images.add(data.getSrc());
        }

        DownLoadUtils.downLoadImage(images, file.getAbsolutePath());




    }

    private static List<ImageItem> getListImages(String url) throws IOException {

        List<ImageItem> datas=new ArrayList<>();

        ImageItem firstItem = getImageItem(url);

        int index=1;

        datas.add(firstItem);

        ImageItem imageItem=firstItem;


        while (imageItem.getNextPage().contains("http")&&(index++)<=15){

           ImageItem   indexItem = getImageItem(imageItem.getNextPage());

            imageItem=indexItem;

           datas.add(indexItem);



        }



        return  datas;
    }







    private static ImageItem getImageItem(String url) throws IOException {


        Document document = Jsoup.connect(url).get();


        // System.out.println("document = [" + document + "]");


        Elements center = document.select("div[class=img_box]");

        Element element = center.get(0);

        Element a = element.select("a").first();

        Element imgElement = a.getElementsByTag("img").get(0);


        String text = a.attr("href");




        String imageUrl= imgElement.attr("src");

        String title=imgElement.attr("alt");


        ImageItem imageItem=new ImageItem(imageUrl,title);

        imageItem.setNextPage(text);

        return imageItem;
    }
}
