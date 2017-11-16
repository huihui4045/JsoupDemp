package download;

import huihui.HttpHelper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gavin on 2017/11/14.
 */
public class DownLoadUtils {


    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    /***
     *
     * @param list
     * @param filepath  保存的文件夹路径
     */

    public static void downLoadImage (List<String> list, String filepath){


        downLoadImageReferrer(list,filepath,null);


    }


    /****
     *
     * @param list
     * @param filepath
     * @param Referrer  防盗链
     */
    public static void downLoadImageReferrer (List<String> list, String filepath,String Referrer){


        for (int i = 0; i < list.size(); i++) {

            String url=list.get(i);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int finalI = i;
            fixedThreadPool.execute(() -> {


                String fileName=String.format("%s\\%d.jpg",filepath, finalI);

                try {

                    if (Referrer!=null){
                        HttpHelper.connect(url)
                                .referrer(Referrer).timeout(3000).get().toFile(fileName);
                    }else {
                        HttpHelper.connect(url).timeout(3000).get().toFile(fileName);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
