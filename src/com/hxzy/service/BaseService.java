package com.hxzy.service;

import com.hxzy.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectAll();

    List<Goods> selectByLimitInt(@Param(value = "pageNum") Integer pageNum, @Param(value = "pageSize") Integer pageSize);

    List<Goods> selectByLimitMap(Map<String, Object> map);

    int selectCount();
}
