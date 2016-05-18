package com.tentinet.healthy.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 资讯数据模型.
 *
 * @author paladin.
 */
public class NewsBean extends BaseBean {

    /**
     * 资讯id.
     */
    private String id;
    /**
     * 资讯图片.
     */
    private String image;
    /**
     * 资讯标题.
     */
    private String title;
    /**
     * 发布时间.
     */
    private String time;
    /**
     * 资讯内容.
     */
    private String content;

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    protected void setParams(JSONObject json) {
        try {
            setId(json.getString("hid"));
            setImage(json.getString("preimg"));
            setTitle(json.getString("title"));
            setTime(json.getString("postTime"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
