package com.jeferry.android.widget.bean;

public class SoftInputModeBean {
    private String name;
    private int mode;

    public SoftInputModeBean() {
    }

    public SoftInputModeBean(String name, int mode) {
        this.name = name;
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "SoftInputModeBean{" +
                "name='" + name + '\'' +
                ", mode=" + mode +
                '}';
    }
}
