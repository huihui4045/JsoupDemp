package huihui;

/**
 * Created by gavin on 2017/10/26.
 */
public class ImageItem {

    private String src;

    private String  title;

    private String nextPage;

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

    @Override
    public String toString() {
        return "ImageItem{" +
                "src='" + src + '\'' +
                ", title='" + title + '\'' +
                ", nextPage='" + nextPage + '\'' +
                '}'+"\n";
    }
}
