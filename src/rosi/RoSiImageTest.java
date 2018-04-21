package rosi;

import download.DownLoadUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.TimeUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoSiImageTest {


    //http://mmp.mmxyz.net/index/2.html


    public static void main(String[] args) throws IOException {

        //download();

        //System.out.println(elements.get(0).toString());

        List<String> roSiImages = RoSiImageApi.
                getRoSiImages("http://mmp.mmxyz.net/rosi/rosi-2319.html");

        System.out.println(roSiImages);

    }

    private static void download() {
        String url="C:\\Users\\Y\\Desktop\\今日头条\\%s\\%s";
        List<RoSiBean> rosiImages = RoSiImageApi.getRosiImages(1);


        File file = new File(String.format(url,
                TimeUtil.getTimeShort(), rosiImages.get(0).getTitle()));


        if (!file.exists()) {

            file.mkdirs();
        }


        if (rosiImages.size()>=12){

            rosiImages=rosiImages.subList(0,12);
        }

        System.out.println(rosiImages);

        List<String>  datas=new ArrayList<>();

        for (RoSiBean rosiImage : rosiImages) {

            datas.add(rosiImage.getImageurl());

            DownLoadUtils.downLoadImage(datas,file.getAbsolutePath());
        }
    }

    //http://mmp.mmxyz.net/rosi/rosi-2314.html
    //http://img1.mmxyz.net/2018/04/2314/rosi-2314-001.jpg
}
