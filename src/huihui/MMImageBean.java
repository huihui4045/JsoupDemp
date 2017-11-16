package huihui;

import java.util.List;

/**
 * Created by gavin on 2017/10/26.
 */
public class MMImageBean {

    private String imageUrl;

    private String title;


    private String updateTime;

    private List<String>  items;

    private int page;

    public MMImageBean(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    @Override
    public String toString() {
        return "\nhuihui.MMImageBean{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", items=" + items +
                ", page=" + page +
                '}';
    }
}
