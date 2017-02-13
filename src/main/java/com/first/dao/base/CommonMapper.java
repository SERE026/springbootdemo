package com.first.dao.base;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 被继承的Mapper，一般业务Mapper继承它
 *
 */
public interface CommonMapper<T> extends SelectWithLockMapper<T>,Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
