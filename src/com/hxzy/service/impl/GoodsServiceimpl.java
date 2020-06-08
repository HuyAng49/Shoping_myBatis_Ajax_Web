package com.hxzy.service.impl;

import com.hxzy.dao.GoodsDao;
import com.hxzy.entity.Goods;
import com.hxzy.service.GoodsService;
import com.hxzy.util.sqlSession_Helper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class GoodsServiceimpl implements GoodsService {

    Object temp;
    SqlSession session;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        temp = getDao().deleteByPrimaryKey(id);
        session.commit();
        return (int) temp;
    }

    @Override
    public int insert(Goods record) {
        temp = getDao().insert(record);
        session.commit();
        return (int) temp;
    }

    @Override
    public int insertSelective(Goods record) {
        temp = getDao().insertSelective(record);
        session.commit();
        return (int) temp;
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return getDao().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        temp = getDao().updateByPrimaryKeySelective(record);
        session.commit();
        return (int) temp;
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        temp = getDao().updateByPrimaryKey(record);
        session.commit();
        return (int) temp;
    }

    @Override
    public List<Goods> selectAll() {
        return getDao().selectAll();
    }

    @Override
    public List<Goods> selectByLimitInt(Integer pageNum, Integer pageSize) {
        return getDao().selectByLimitInt(pageNum, pageSize);
    }

    @Override
    public List<Goods> selectByLimitMap(Map<String, Object> map) {
        return getDao().selectByLimitMap(map);
    }

    @Override
    public int selectCount() {
        return getDao().selectCount();
    }

    private GoodsDao getDao() {
        session = sqlSession_Helper.getSession("mybatis.xml");
        return session.getMapper(GoodsDao.class);
    }
}
