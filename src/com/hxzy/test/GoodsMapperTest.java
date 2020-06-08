package com.hxzy.test;

import com.hxzy.dao.GoodsDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class GoodsMapperTest {

//    @Test
    public void GoodsMapperTest_1() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = factory.openSession();
            GoodsDao goodsMapper = session.getMapper(GoodsDao.class);
//            Map<String ,Object> map=new HashMap<String,Object>();
//            map.put("pageNum",5);
//            map.put("pageSize",5);
            System.out.println(goodsMapper.selectByLimitInt(5, 5));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
