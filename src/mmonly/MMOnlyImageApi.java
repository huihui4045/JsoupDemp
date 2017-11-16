package mmonly;

import huihui.MMImageBean;
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
 * Created by gavin on 2017/11/16.
 */
public class MMOnlyImageApi {

    //http://www.mmonly.cc/mmtp/

    private static final String BASE_URL = "http://www.mmonly.cc/mmtp/";


    private static final String MM_TYPE_SHOU_YE = "";
    private static final String MM_TYPE_XING_GAN = "xgmn";
    private static final String MM_TYPE_SI_WA = "swmn";
    private static final String MM_TYPE_NEI_YI = "nymn";
    private static final String MM_TYPE_BJNMN = "bjnmn";
    private static final String MM_TYPE_QING_CHUN = "qcmn";
    private static final String MM_TYPE_CHANG_TUI = "ctmn";
    private static final String MM_TYPE_JIE_PAI = "jpmn";
    private static final String MM_TYPE_HAN_GUO = "hgmn";
    private static final String MM_TYPE_WAI_GUO = "wgmv";

    public static final String[] MM_TYPE = {MM_TYPE_SHOU_YE, MM_TYPE_XING_GAN,
            MM_TYPE_SI_WA, MM_TYPE_NEI_YI, MM_TYPE_BJNMN,
            MM_TYPE_QING_CHUN, MM_TYPE_CHANG_TUI, MM_TYPE_JIE_PAI,
            MM_TYPE_HAN_GUO, MM_TYPE_WAI_GUO};
    public static final String[] MM_TYPE_NAME = {"首页美女", "性感美女", "丝袜美女", "内衣美女", "比基美女", "清纯美女",
            "长腿美女", "街拍美女", "韩国美女", "外国美女"};

    private static int getPageType(String flag) {

        switch (flag) {
            case MM_TYPE_SHOU_YE:

                return 9;
            case MM_TYPE_XING_GAN:

                return 10;
            case MM_TYPE_SI_WA:
                return 11;
            case MM_TYPE_NEI_YI:
                return 15;
            case MM_TYPE_BJNMN:
                return 14;
            case MM_TYPE_QING_CHUN:
                return 16;
            case MM_TYPE_CHANG_TUI:
                return 17;

            case MM_TYPE_JIE_PAI:
                return 19;
            case MM_TYPE_HAN_GUO:

                return 12;
            case MM_TYPE_WAI_GUO:
                return 13;

            default:

                return 9;

        }

    }


    public static List<MMImageBean> getImagesFromType(int page, String flag) throws IOException {


        String requset = "";

        if (page == 1) {

            requset = String.format("%s%s", BASE_URL, flag);
        } else {

            String item = String.format("list_%d_%d.html", getPageType(flag), page);

            requset = String.format("%s%s%s", BASE_URL, flag, item);
        }

        System.out.println("请求的URL：" + requset);


        Document document = Jsoup.connect(requset).get();


        return getImages(document);
    }


    private static final String regex = "\\d+-\\d+-\\d+\\s\\d+:\\d+:\\d+";

    private static final String START = "共";
    private static final String END = "张";


    private static final String pageRegex = String.format("%s\\d+%s", START, END);

    private static String RAGEX_IMAGE = ".html";

    /***
     * 获取各类型图片
     * @param document
     * @return
     */
    private static List<MMImageBean> getImages(Document document) throws IOException {

        // System.out.println("document = [" + document + "]");


        //http://t1.mmonly.cc/uploads/tu/201711/9999/29ab72a640.jpg      1
        //http://t1.mmonly.cc/uploads/tu/201711/9999/918a3a2645.jpg      2
        // http://t1.mmonly.cc/uploads/tu/201711/9999/73a17a04e2.jpg      3

        //http://t1.mmonly.cc/uploads/tu/201711/9999/7f00c9a691.jpg      1
        //http://t1.mmonly.cc/uploads/tu/201711/9999/090acc1b67.jpg      2
        //http://t1.mmonly.cc/uploads/tu/201711/9999/af6d66abbf.jpg      3
        //http://t1.mmonly.cc/uploads/tu/201711/9999/9e02a8a170.jpg

        Elements mainDiv = document.getElementsByClass("item_list infinite_scroll masonry");

        //  System.out.println("mainDiv = [" + mainDiv + "]");

        Elements itemDivs = mainDiv.get(0).select("div[class=item masonry_brick masonry-brick]");


        List<MMImageBean> datas = new ArrayList<>(itemDivs.size());

        for (Element itemDiv : itemDivs) {

            Elements item_t = itemDiv.getElementsByClass("item_t");

            Elements items_likes = itemDiv.getElementsByClass("items_likes");

            Element element = item_t.select("a[target=_blank]").get(0);


            String mNextUrl = element.attr("href");

            Elements img = element.getElementsByTag("img");

            String imageUrl = img.attr("src");

            String title = img.attr("alt");

            MMImageBean imageBean = new MMImageBean(imageUrl, title);


            String text = items_likes.text();

         //   System.out.println("text = [" + text + "]");

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {

                String updateTime = matcher.group(0);
                imageBean.setUpdateTime(updateTime);

            }


            Pattern mPattern = Pattern.compile(pageRegex);
            Matcher mMatcher = mPattern.matcher(text);

            int num = 0;

            if (mMatcher.find()) {

                String page = mMatcher.group(0).replace(START, "").replace(END, "");

                num = Integer.valueOf(page.trim()).intValue();
                imageBean.setPage(num);

            }


            List<String> htmls = new ArrayList<>(num);

            for (int i = 1; i <= num; i++) {

                if (i == 1)
                    htmls.add(mNextUrl);
                else {

                    String newstr = String.format("_%d.html", i);


                    htmls.add(mNextUrl.replace(RAGEX_IMAGE, newstr));

                }
            }

            imageBean.setItems(htmls);

            datas.add(imageBean);


        }


        return datas;
    }
}
