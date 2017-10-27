package com.lukemi.myandroid.news_today.model;

import java.util.List;

/**
 * 国际频道
 * Created by android on 2017/7/20.
 */

public class JNewsWorldBean implements JNewsBaseBean {

    /**
     * media_avatar_url : http://p2.pstatp.com/large/6cb0002b772ef9ad43d
     * article_genre : article
     * is_diversion_page : false
     * title : 美国安检X线太牛，任何人都没有隐私可言！
     * middle_mode : false
     * gallary_image_count : 11
     * image_list : [{"url":"http://p3.pstatp.com/list/2ebb00001256bac63e39"},{"url":"http://p3.pstatp.com/list/2eb900033fad9dc3a50a"},{"url":"http://p9.pstatp.com/list/2ebe0002eabba0ae5440"}]
     * more_mode : true
     * behot_time : 1500519915
     * source_url : /group/6441870642227839233/
     * source : 鹿人三千
     * hot : 1
     * is_feed_ad : false
     * image_url : http://p3.pstatp.com/list/204x140/2ebb00001256bac63e39
     * single_mode : true
     * has_gallery : false
     * group_id : 6441870642227839233
     * media_url : http://toutiao.com/m6594278595/
     */

    private String media_avatar_url;
    private String article_genre;
    private boolean is_diversion_page;
    private String title;
    private boolean middle_mode;
    private int gallary_image_count;
    private boolean more_mode;
    private int behot_time;
    private String source_url;
    private String source;
    private int hot;
    private boolean is_feed_ad;
    private String image_url;
    private boolean single_mode;
    private boolean has_gallery;
    private String group_id;
    private String media_url;
    private List<ImageListBean> image_list;

    public String getMedia_avatar_url() {
        return media_avatar_url;
    }

    public void setMedia_avatar_url(String media_avatar_url) {
        this.media_avatar_url = media_avatar_url;
    }

    public String getArticle_genre() {
        return article_genre;
    }

    public void setArticle_genre(String article_genre) {
        this.article_genre = article_genre;
    }

    public boolean isIs_diversion_page() {
        return is_diversion_page;
    }

    public void setIs_diversion_page(boolean is_diversion_page) {
        this.is_diversion_page = is_diversion_page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMiddle_mode() {
        return middle_mode;
    }

    public void setMiddle_mode(boolean middle_mode) {
        this.middle_mode = middle_mode;
    }

    public int getGallary_image_count() {
        return gallary_image_count;
    }

    public void setGallary_image_count(int gallary_image_count) {
        this.gallary_image_count = gallary_image_count;
    }

    public boolean isMore_mode() {
        return more_mode;
    }

    public void setMore_mode(boolean more_mode) {
        this.more_mode = more_mode;
    }

    public int getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(int behot_time) {
        this.behot_time = behot_time;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public boolean isIs_feed_ad() {
        return is_feed_ad;
    }

    public void setIs_feed_ad(boolean is_feed_ad) {
        this.is_feed_ad = is_feed_ad;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isSingle_mode() {
        return single_mode;
    }

    public void setSingle_mode(boolean single_mode) {
        this.single_mode = single_mode;
    }

    public boolean isHas_gallery() {
        return has_gallery;
    }

    public void setHas_gallery(boolean has_gallery) {
        this.has_gallery = has_gallery;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public List<ImageListBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ImageListBean> image_list) {
        this.image_list = image_list;
    }

    @Override
    public String toString() {
        return "JNewsWorldBean{" +
                       "media_avatar_url='" + media_avatar_url + '\'' +
                       ", article_genre='" + article_genre + '\'' +
                       ", is_diversion_page=" + is_diversion_page +
                       ", title='" + title + '\'' +
                       ", middle_mode=" + middle_mode +
                       ", gallary_image_count=" + gallary_image_count +
                       ", more_mode=" + more_mode +
                       ", behot_time=" + behot_time +
                       ", source_url='" + source_url + '\'' +
                       ", source='" + source + '\'' +
                       ", hot=" + hot +
                       ", is_feed_ad=" + is_feed_ad +
                       ", image_url='" + image_url + '\'' +
                       ", single_mode=" + single_mode +
                       ", has_gallery=" + has_gallery +
                       ", group_id='" + group_id + '\'' +
                       ", media_url='" + media_url + '\'' +
                       ", image_list=" + image_list +
                       '}';
    }

    public static class ImageListBean {
        /**
         * url : http://p3.pstatp.com/list/2ebb00001256bac63e39
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ImageListBean{" +
                           "url='" + url + '\'' +
                           '}';
        }
    }
}
