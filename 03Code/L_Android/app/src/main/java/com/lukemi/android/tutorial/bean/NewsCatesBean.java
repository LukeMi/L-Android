package com.lukemi.android.tutorial.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 新闻列表
 * Created by android on 2017/6/8.
 */

public class NewsCatesBean {

    /**
     * rsm : 1
     * data : {"lists":{"rows":[{"id":"55963","catid":"9","title":"美国Buffalo医疗集团临床实验中心主任、纽约州立大学Buffalo林琳教授来江苏省人民医院病理学部进行学术访问","thumb":"http://a1.cdn.91360.com/cms/uploadfile/2017/0607/thumb_160_120_20170607031201275.png","copyfrom":"江苏省人民医院医技在线","publish":"1天前","jump":"","url":"http://www.91360.com/201706/9/55963.html","desc":"2017年5月23日下午，江苏省人民医院病理学部邀请美国Buffalo医疗集团临床实验中心主任、纽约州立大学Buffalo皮肤科和病理科林琳教授开展皮肤病理的学术交流活动。","native":""},{"id":"55961","catid":"9","title":"结直肠病检报告规范推广","thumb":"http://a1.cdn.91360.com/cms/uploadfile/2017/0607/thumb_160_120_20170607015619954.png","copyfrom":"风云 高医病理","publish":"1天前","jump":"","url":"http://www.91360.com/201706/9/55961.html","desc":"2017年5月31日，天气炎热，在高州市人民医院病理科办公室里学习热情不输天气高温，为了更好的与国际规范接轨，由陈显文整理文献资料，对临床送检的标本处理切片及报告单的书写及规范化","native":""}],"total":"1251","curpage":1},"slides":{"rows":[{"id":"55984","catid":"9","title":"复旦大学基础医学院病理系党员讲上医精神 支持学生支部党建活动","thumb":"http://a1.cdn.91360.com/cms/uploadfile/2017/0608/thumb_410_220_20170608024817486.png","copyfrom":"基础医学院 复旦新闻文化网","publish":"5小时前","jump":"","url":"http://www.91360.com/201706/9/55984.html","desc":"应基础医学院学生党支部的邀请，病理系老党员郭慕依教授在5月20日周六上午冒着高温为30多位来自松江二中的学生讲解上医精神。郭老师介绍了自己几十年的人生经历，包括如何走上医学...","native":""}],"total":4,"curpage":1},"cat":{"catid":"9","catname":"行业资讯","url":"http://www.91360.com/blzx/hyzx/","description":"病理行业资讯尽在91360，病理行业最新资讯、行业动态、病理公告、病理招标、病理医生、医院、医疗设备厂家信息全面快速掌握。"}}
     */

    private int rsm;
    private DataBean data;

    public int getRsm() {
        return rsm;
    }

    public void setRsm(int rsm) {
        this.rsm = rsm;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lists : {"rows":[{"id":"55963","catid":"9","title":"美国Buffalo医疗集团临床实验中心主任、纽约州立大学Buffalo林琳教授来江苏省人民医院病理学部进行学术访问","thumb":"http://a1.cdn.91360.com/cms/uploadfile/2017/0607/thumb_160_120_20170607031201275.png","copyfrom":"江苏省人民医院医技在线","publish":"1天前","jump":"","url":"http://www.91360.com/201706/9/55963.html","desc":"2017年5月23日下午，江苏省人民医院病理学部邀请美国Buffalo医疗集团临床实验中心主任、纽约州立大学Buffalo皮肤科和病理科林琳教授开展皮肤病理的学术交流活动。","native":""},{"id":"55961","catid":"9","title":"结直肠病检报告规范推广","thumb":"http://a1.cdn.91360.com/cms/uploadfile/2017/0607/thumb_160_120_20170607015619954.png","copyfrom":"风云 高医病理","publish":"1天前","jump":"","url":"http://www.91360.com/201706/9/55961.html","desc":"2017年5月31日，天气炎热，在高州市人民医院病理科办公室里学习热情不输天气高温，为了更好的与国际规范接轨，由陈显文整理文献资料，对临床送检的标本处理切片及报告单的书写及规范化","native":""}],"total":"1251","curpage":1}
         * slides : {"rows":[{"id":"55984","catid":"9","title":"复旦大学基础医学院病理系党员讲上医精神 支持学生支部党建活动","thumb":"http://a1.cdn.91360.com/cms/uploadfile/2017/0608/thumb_410_220_20170608024817486.png","copyfrom":"基础医学院 复旦新闻文化网","publish":"5小时前","jump":"","url":"http://www.91360.com/201706/9/55984.html","desc":"应基础医学院学生党支部的邀请，病理系老党员郭慕依教授在5月20日周六上午冒着高温为30多位来自松江二中的学生讲解上医精神。郭老师介绍了自己几十年的人生经历，包括如何走上医学...","native":""}],"total":4,"curpage":1}
         * cat : {"catid":"9","catname":"行业资讯","url":"http://www.91360.com/blzx/hyzx/","description":"病理行业资讯尽在91360，病理行业最新资讯、行业动态、病理公告、病理招标、病理医生、医院、医疗设备厂家信息全面快速掌握。"}
         */

        private ListsBean lists;
        private SlidesBean slides;
        private CatBean cat;

        public ListsBean getLists() {
            return lists;
        }

        public void setLists(ListsBean lists) {
            this.lists = lists;
        }

        public SlidesBean getSlides() {
            return slides;
        }

        public void setSlides(SlidesBean slides) {
            this.slides = slides;
        }

        public CatBean getCat() {
            return cat;
        }

        public void setCat(CatBean cat) {
            this.cat = cat;
        }

        public static class ListsBean {
            /**
             * rows : [{"id":"55963","catid":"9","title":"美国Buffalo医疗集团临床实验中心主任、纽约州立大学Buffalo林琳教授来江苏省人民医院病理学部进行学术访问","thumb":"http://a1.cdn.91360.com/cms/uploadfile/2017/0607/thumb_160_120_20170607031201275.png","copyfrom":"江苏省人民医院医技在线","publish":"1天前","jump":"","url":"http://www.91360.com/201706/9/55963.html","desc":"2017年5月23日下午，江苏省人民医院病理学部邀请美国Buffalo医疗集团临床实验中心主任、纽约州立大学Buffalo皮肤科和病理科林琳教授开展皮肤病理的学术交流活动。","native":""},{"id":"55961","catid":"9","title":"结直肠病检报告规范推广","thumb":"http://a1.cdn.91360.com/cms/uploadfile/2017/0607/thumb_160_120_20170607015619954.png","copyfrom":"风云 高医病理","publish":"1天前","jump":"","url":"http://www.91360.com/201706/9/55961.html","desc":"2017年5月31日，天气炎热，在高州市人民医院病理科办公室里学习热情不输天气高温，为了更好的与国际规范接轨，由陈显文整理文献资料，对临床送检的标本处理切片及报告单的书写及规范化","native":""}]
             * total : 1251
             * curpage : 1
             */

            private String total;
            private int curpage;
            private List<RowsBean> rows;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public int getCurpage() {
                return curpage;
            }

            public void setCurpage(int curpage) {
                this.curpage = curpage;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * id : 55963
                 * catid : 9
                 * title : 美国Buffalo医疗集团临床实验中心主任、纽约州立大学Buffalo林琳教授来江苏省人民医院病理学部进行学术访问
                 * thumb : http://a1.cdn.91360.com/cms/uploadfile/2017/0607/thumb_160_120_20170607031201275.png
                 * copyfrom : 江苏省人民医院医技在线
                 * publish : 1天前
                 * jump :
                 * url : http://www.91360.com/201706/9/55963.html
                 * desc : 2017年5月23日下午，江苏省人民医院病理学部邀请美国Buffalo医疗集团临床实验中心主任、纽约州立大学Buffalo皮肤科和病理科林琳教授开展皮肤病理的学术交流活动。
                 * native :
                 */

                private String id;
                private String catid;
                private String title;
                private String thumb;
                private String copyfrom;
                private String publish;
                private String jump;
                private String url;
                private String desc;
                @SerializedName("native")
                private String nativeX;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCatid() {
                    return catid;
                }

                public void setCatid(String catid) {
                    this.catid = catid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getCopyfrom() {
                    return copyfrom;
                }

                public void setCopyfrom(String copyfrom) {
                    this.copyfrom = copyfrom;
                }

                public String getPublish() {
                    return publish;
                }

                public void setPublish(String publish) {
                    this.publish = publish;
                }

                public String getJump() {
                    return jump;
                }

                public void setJump(String jump) {
                    this.jump = jump;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getNativeX() {
                    return nativeX;
                }

                public void setNativeX(String nativeX) {
                    this.nativeX = nativeX;
                }
            }
        }

        public static class SlidesBean {
            /**
             * rows : [{"id":"55984","catid":"9","title":"复旦大学基础医学院病理系党员讲上医精神 支持学生支部党建活动","thumb":"http://a1.cdn.91360.com/cms/uploadfile/2017/0608/thumb_410_220_20170608024817486.png","copyfrom":"基础医学院 复旦新闻文化网","publish":"5小时前","jump":"","url":"http://www.91360.com/201706/9/55984.html","desc":"应基础医学院学生党支部的邀请，病理系老党员郭慕依教授在5月20日周六上午冒着高温为30多位来自松江二中的学生讲解上医精神。郭老师介绍了自己几十年的人生经历，包括如何走上医学...","native":""}]
             * total : 4
             * curpage : 1
             */

            private int total;
            private int curpage;
            private List<RowsBeanX> rows;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getCurpage() {
                return curpage;
            }

            public void setCurpage(int curpage) {
                this.curpage = curpage;
            }

            public List<RowsBeanX> getRows() {
                return rows;
            }

            public void setRows(List<RowsBeanX> rows) {
                this.rows = rows;
            }

            public static class RowsBeanX {
                /**
                 * id : 55984
                 * catid : 9
                 * title : 复旦大学基础医学院病理系党员讲上医精神 支持学生支部党建活动
                 * thumb : http://a1.cdn.91360.com/cms/uploadfile/2017/0608/thumb_410_220_20170608024817486.png
                 * copyfrom : 基础医学院 复旦新闻文化网
                 * publish : 5小时前
                 * jump :
                 * url : http://www.91360.com/201706/9/55984.html
                 * desc : 应基础医学院学生党支部的邀请，病理系老党员郭慕依教授在5月20日周六上午冒着高温为30多位来自松江二中的学生讲解上医精神。郭老师介绍了自己几十年的人生经历，包括如何走上医学...
                 * native :
                 */

                private String id;
                private String catid;
                private String title;
                private String thumb;
                private String copyfrom;
                private String publish;
                private String jump;
                private String url;
                private String desc;
                @SerializedName("native")
                private String nativeX;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCatid() {
                    return catid;
                }

                public void setCatid(String catid) {
                    this.catid = catid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getCopyfrom() {
                    return copyfrom;
                }

                public void setCopyfrom(String copyfrom) {
                    this.copyfrom = copyfrom;
                }

                public String getPublish() {
                    return publish;
                }

                public void setPublish(String publish) {
                    this.publish = publish;
                }

                public String getJump() {
                    return jump;
                }

                public void setJump(String jump) {
                    this.jump = jump;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getNativeX() {
                    return nativeX;
                }

                public void setNativeX(String nativeX) {
                    this.nativeX = nativeX;
                }
            }
        }

        public static class CatBean {
            /**
             * catid : 9
             * catname : 行业资讯
             * url : http://www.91360.com/blzx/hyzx/
             * description : 病理行业资讯尽在91360，病理行业最新资讯、行业动态、病理公告、病理招标、病理医生、医院、医疗设备厂家信息全面快速掌握。
             */

            private String catid;
            private String catname;
            private String url;
            private String description;

            public String getCatid() {
                return catid;
            }

            public void setCatid(String catid) {
                this.catid = catid;
            }

            public String getCatname() {
                return catname;
            }

            public void setCatname(String catname) {
                this.catname = catname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
