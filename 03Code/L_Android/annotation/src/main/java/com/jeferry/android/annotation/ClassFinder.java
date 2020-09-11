package com.jeferry.android.annotation;

public class ClassFinder {
    public static final String SUFFIX = "$$Impl";
    private ClassFinder() {}
    public static <T> T findClass(Class<T> clazz) {
        T t = null;
        try {
            @SuppressWarnings("unchecked")
            Class<T> realClazz = (Class<T>) Class.forName(clazz.getCanonicalName() + SUFFIX);
            if (realClazz != null) {
                t = realClazz.newInstance();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return t;
    }
}
