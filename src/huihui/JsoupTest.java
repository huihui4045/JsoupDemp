package huihui;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by gavin on 2017/10/25.
 */
public class JsoupTest {

    static int page=1;
    static boolean isStop=false;
    public static void main(String[] args) {

       // http://www.7160.com/xiaohua/

        //http://www.mzitu.com/  http://i.meizitu.net/pfiles/img/lazy.png" 妹子图
        //http://www.94xmn.com/xingganmeinv/

       // http://img.mmjpg.com/small/2017/1154.jpg   http://www.mmjpg.com/mm/1153/2


        //图片本地化(破解防盗链) http://img1.mm131.com/pic/3354.jpg
       // http://img.mmjpg.com/2017/1153/2.jpg
       /* try {
            HttpHelper.connect("http://img.mmjpg.com/2017/1153/2.jpg")
                    .referrer("http://www.mmjpg.com").timeout(3000).get().toFile("D://image/101.jpg");




        } catch (IOException e) {
            e.printStackTrace();
        }*/


       // getImageType();


       /* try {

            huihui.MMImageApi.getDetailList();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        try {
            Document document = Jsoup.connect("http://www.mzitu.com").get();

            System.out.println("document:"+document.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    private static void getImageType() {
        while (!isStop){
            try {
                //抓取页面数据
                //List<huihui.MMImageBean> xingGanImages = huihui.MMImageApi.getXingGanImages(page);
                //List<huihui.MMImageBean> xingGanImages = huihui.MMImageApi.getQingChunImages(page);

                List<MMImageBean> xingGanImages = MMImageApi.getXiaoHuaImages(page);

                System.out.println( xingGanImages.toString());
                Thread.sleep(1000);
                page++;
                if (xingGanImages==null)
                    isStop=true;
            } catch (Exception e) {
                e.printStackTrace();
                isStop=true;
            }


            break;
        }


        System.out.println("数据抓取完毕");
    }
}
