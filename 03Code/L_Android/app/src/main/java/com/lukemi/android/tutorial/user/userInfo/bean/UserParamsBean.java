package com.lukemi.android.tutorial.user.userInfo.bean;

/**
 * 用户参数类
 */
public class UserParamsBean {
    /**
     * 多布局 类型一
     */
    public static final int ITEM_TYPE_1 = 0x000001;
    /**
     * 多布局 类型二
     */
    public static final int ITEM_TYPE_2 = 0x000002;
    /**
     * 多布局 类型三
     */
    public static final int ITEM_TYPE_3 = 0x000003;
    private String key;
    private String value;
    private int itemType = ITEM_TYPE_1;

    public UserParamsBean() {
    }

    public UserParamsBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @param key
     * @param value
     * @param itemType One of {@link #ITEM_TYPE_1},{@link #ITEM_TYPE_2},{@link #ITEM_TYPE_3},
     */
    public UserParamsBean(String key, String value, int itemType) {
        this.key = key;
        this.value = value;
        this.itemType = itemType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "UserParamsBean{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", itemType=" + itemType +
                '}';
    }
}
