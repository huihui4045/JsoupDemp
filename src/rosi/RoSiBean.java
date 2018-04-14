package rosi;

public class RoSiBean {

    private String  url;

    private String  imageurl;

    private String title;

    public RoSiBean(String url, String imageurl, String title) {
        this.url = url;
        this.imageurl = imageurl;
        this.title = title;
    }

    @Override
    public String toString() {
        return "RoSiBean{" +
                "url='" + url + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", title='" + title + '\'' +
                '}'+
                "\n";
    }
}
