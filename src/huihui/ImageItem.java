package huihui;

/**
 * Created by gavin on 2017/10/26.
 */
public class ImageItem {

    private String src;

    private String  title;

    private String nextPage;


    private String videoUrl;


    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public ImageItem(String src, String title) {
        this.src = src;
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    @Override
    public String toString() {
        return "ImageItem{" +
                "src='" + src + '\'' +
                ", title='" + title + '\'' +
                ", nextPage='" + nextPage + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}'+
                "\n";
    }
}
