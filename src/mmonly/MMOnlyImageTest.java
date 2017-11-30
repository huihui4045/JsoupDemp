package mmonly;

import download.DownLoadUtils;
import huihui.ImageItem;
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
 * Created by gavin on 2017/11/16.
 */
public class MMOnlyImageTest {


    private static String url = MMOnlyData.DATA[MMOnlyData.DATA.length - 1];

    private static String RAGEX_IMAGE = ".html";


    //http://t1.mmonly.cc/uploads/tu/201602/147/lptq2rjz2i2.jpg

    public static void main(String[] args) {


        /*try {
            List<MMImageBean> imagesFromTypes = MMOnlyImageApi.getImagesFromType(1, MMOnlyImageApi.MM_TYPE[2]);

            System.out.println(imagesFromTypes.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }*/


        getDownLoadImage();


        //System.out.println(document.body());
    }

    public static void getDownLoadImage() {


        for (int i = 0; i < MMOnlyData.DATA.length - 1; i++) {

            if (MMOnlyData.DATA[i].equals(url)) {

                System.out.println("数据已经重复");

                return;
            }


        }


        Document document = null;
        try {
            document = Jsoup.connect(url).get();

            List<ImageItem> datas = getImage(document);


            File file = new File(String.format("C:\\Users\\gavin\\Desktop\\晨读\\今日头条\\%s\\%s",
                    TimeUtil.getTimeShort(), datas.get(0).getTitle()));


            if (!file.exists()) {

                file.mkdirs();
            }

            List<String> images = new ArrayList<>(datas.size());

            for (ImageItem data : datas) {

                images.add(data.getSrc());
            }

            DownLoadUtils.downLoadImageReferrer(images, file.getAbsolutePath(), "http://www.mmonly.cc");

        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("错误信息 = [" + e.toString() + "]");
        }

    }


    public static List<ImageItem> getImage(Document document) throws IOException {

        Elements divElements = document.select("div[class=pages]");
        Elements liElements = divElements.get(0).getElementsByTag("li");
        Element element = liElements.get(0);
        String page = element.text();
        String contentpage = page.substring(1, page.length() - 2);
        int mPage = Integer.valueOf(contentpage).intValue();

        String requsetUrl = url;

        List<ImageItem> imageItems = new ArrayList<>(mPage);
        for (int i = 1; i <= mPage; i++) {

            if (i > 1) {

                String newstr = String.format("_%d.html", i);
                requsetUrl = url.replace(RAGEX_IMAGE, newstr);
                imageItems.add(getImageItem(requsetUrl));
            } else {

                imageItems.add(getImageItem(requsetUrl));
            }

        }

        return imageItems;
    }


    private static ImageItem getImageItem(String url) throws IOException {


        Document document = Jsoup.connect(url).get();


        Elements divElements = document.select("div[class=big-pic]");

        Element imgElement = divElements.get(0).getElementsByTag("img").get(0);


        String imageUrl = imgElement.attr("src");

        String title = imgElement.attr("alt");


        ImageItem imageItem = new ImageItem(imageUrl, title);

        return imageItem;
    }


}
