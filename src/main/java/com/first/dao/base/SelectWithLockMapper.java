package com.first.dao.base;

import org.apache.ibatis.annotations.SelectProvider;

import com.first.dao.provider.SelectWithLockProvider;


public interface SelectWithLockMapper<T> {

    /**
     * 
     * @param key
     * @return
     */
    @SelectProvider(type = SelectWithLockProvider.class, method = "dynamicSQL")
    T selectByPrimaryKeyWithLock(Object key);

}
