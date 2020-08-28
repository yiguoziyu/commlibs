package com.ljj.commonlib.kit.cache;

/**
 * Created by Administrator on 2017/5/16.
 */

public interface  ICache {
    void put(String key, Object value);
    Object get(String key);
    void remove(String key);
    boolean contains(String key);
    void clear();
}
