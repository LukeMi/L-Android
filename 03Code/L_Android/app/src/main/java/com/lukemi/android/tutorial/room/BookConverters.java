package com.lukemi.android.tutorial.room;

import android.arch.persistence.room.TypeConverter;

import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.List;

public class BookConverters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static List<AuthorEntity> fromStringToList(String value) {
        return JSON.parseArray(value, AuthorEntity.class);
    }

    @TypeConverter
    public static String fromListToString(List<AuthorEntity> list) {
        return JSON.toJSONString(list);
    }
}
