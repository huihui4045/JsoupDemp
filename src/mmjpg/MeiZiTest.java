package mmjpg;

import huihui.HttpHelper;
import huihui.MMImageBean;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gavin on 2017/10/30.
 */
public class MeiZiTest {
  static   int page=1;

   private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);


    private static void getData(String type) throws IOException, InterruptedException {




        List<MMImageBean> mzImagesFromType = MeiZiImageApi.getMZImagesFromType(page, type);

        if (mzImagesFromType!=null &&mzImagesFromType.size()>0&&page<3){

            System.out.println("当前的页数 = [" + page + "]");

            System.out.println(mzImagesFromType.toString());


            if (mzImagesFromType!=null && mzImagesFromType.size()>0){



                for (MMImageBean mmImageBean : mzImagesFromType) {

                    String title = mmImageBean.getTitle();


                    List<String> items = mmImageBean.getItems();


                   /* File file=new File("D:\\image\\",title);

                    if (!file.exists()){

                        file.mkdirs();
                    }*/

                    for (int i = 0; i < items.size(); i++) {

                        String item=items.get(i);
                        int finalI = i;
                        fixedThreadPool.execute(() -> {

                            try {
                                String a=type.equals("")?"shouye":type;
                                String fileName=String.format("D:\\image\\%s\\%s\\%d.jpg",a,title, finalI);
                                System.out.println("FileName:"+fileName);
                                HttpHelper.connect(item)
                                        .referrer("http://www.mmjpg.com").timeout(3000).get().toFile(fileName);
                                Thread.sleep(1000);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        });
                    }






                }
            }



            page++;

            Thread.sleep(2000);

            System.out.println("********************************");

            getData(type);
        }


    }


    private static String URL="http://www.mmjpg.com/mm/289";





    public static void main(String[] args) throws IOException, InterruptedException {


        MeiZiImageApi.getDownLoadImages(URL);





        //MeiZiImageApi.getHome();

     //   getData(MeiZiImageApi.MZ_IMAGE_TYPE[0]);


       /* for (String s : MeiZiImageApi.MZ_IMAGE_TYPE) {

            page=1;



            getData(s);

            System.out.println("=========================");




        }*/



        //MeiZiImageApi.getMZImagesFromType(1,MeiZiImageApi.MZ_IMAGE_TYPE[0]);


        //List<MMImageBean> homeImages = MeiZiImageApi.getHomeImages();


       // System.out.println("homeImages:"+homeImages.toString());

        // MeiZiImageApi.getHome();

/*
        try {
            Document ItemDocument = Jsoup.connect("http://www.mmjpg.com/mm/1152").get();

            Element page = ItemDocument.getElementById("page");

            System.out.println("page:"+page);

            Elements aElements = page.select("a");



            System.out.println("aElements:"+aElements);

            System.out.println("aElements.size():"+aElements.size());

            Element element = aElements.get(aElements.size()-2);

            System.out.println("element:"+element.text());


        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
