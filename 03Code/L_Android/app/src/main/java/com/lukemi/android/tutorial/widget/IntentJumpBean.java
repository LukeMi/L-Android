package com.lukemi.android.tutorial.widget;

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

    public IntentJumpBean() {
    }

    public IntentJumpBean(String text, Class<?> c) {
        this.text = text;
        this.c = c;
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

    @Override
    public String toString() {
        return "IntentJumpBean{" +
                ", c=" + c +
                ", text='" + text + '\'' +
                '}';
    }
}
