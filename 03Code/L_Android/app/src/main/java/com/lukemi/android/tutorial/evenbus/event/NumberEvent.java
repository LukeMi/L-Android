package com.lukemi.android.tutorial.evenbus.event;

/**
 * 发送数字
 */
public class NumberEvent {
    private int num;

    public NumberEvent() {
    }

    public NumberEvent(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "NumberEvent{" +
                "num=" + num +
                '}';
    }
}
