package huihui;

import download.DownLoadUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by gavin on 2017/11/14.
 */
public class MMImageTest {

    public static void main(String[] args) {


        try {
            List<MMImageBean> xingGanImages = MMImageApi.getXingGanImages(1);






            MMImageBean mmImageBean = xingGanImages.get(4);

            File file=new File(String.format("D:\\image\\%s",mmImageBean.getTitle()));

            if (!file.exists()){
                file.mkdirs();
            }


            DownLoadUtils.downLoadImageReferrer(mmImageBean.getItems(),file.getAbsolutePath(),"http://www.mm131.com/xinggan");



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
