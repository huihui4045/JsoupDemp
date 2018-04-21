package rosi;

public class RoSiBean {

    private String  url;

    private String  imageurl;

    private String title;

    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public RoSiBean(String url, String imageurl, String title) {
        this.url = url;
        this.imageurl = imageurl;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RoSiBean{" +
                "url='" + url + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", title='" + title + '\'' +
                ", num='" + num + '\'' +
                '}'+
                "\n";
    }
}
