package com.hxzy.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class sqlSession_Helper {

    public static SqlSession getSession(String myBatisConFig) {
        SqlSession session = null;
        try {
            InputStream is = Resources.getResourceAsStream(myBatisConFig);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            session = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

//    public static void closeSession(SqlSession session){
//        session.close();
//    }

}
