package com.ljj.commonlib.kit.cache;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
@SuppressWarnings("serial")
public class CacheList <T> implements Serializable {
    public List<T> list;
}
