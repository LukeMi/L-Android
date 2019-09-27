package com.lukemi.android.common;

import com.alibaba.android.arouter.facade.Postcard;

/**
 * @author lukemi
 * @date 2018/12/22 17:23
 * @des Intent 跳转实体类
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class IntentJumpBean {

    /**
     * 控件的Tag
     */
    private String text;

    /**
     * 控件跳转对应的Acticity class
     */
    private Class<?> c;

    private int flag;

    private Postcard postcard;

    public IntentJumpBean() {
    }

    public IntentJumpBean(String text, Class<?> c) {
        this.text = text;
        this.c = c;
    }

    public IntentJumpBean(String text, Postcard c, Class<?> nul) {
        super();
        this.text = text;
        this.postcard = c;
    }

    public IntentJumpBean(String text, Class<?> c, int flag) {
        this.text = text;
        this.c = c;
        this.flag = flag;
    }

    public Class<?> getC() {
        return c;
    }

    public void setC(Class<?> c) {
        this.c = c;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setPostcard(Postcard postcard) {
        this.postcard = postcard;
    }

    public Postcard getPostcard() {
        return postcard;
    }

    @Override
    public String toString() {
        return "IntentJumpBean{" +
                ", c=" + c +
                ", text='" + text + '\'' +
                '}';
    }
}
