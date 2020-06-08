package com.hxzy.test;

import com.hxzy.entity.Goods;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class test {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = factory.openSession();
            Goods goods = session.selectOne("com.hxzy.entity.Goods", 1);
            System.out.println(goods);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
