package com.hxzy.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    private String DIRECTION = "redirect:";

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        try {
            Method method = this.getClass().getMethod(req.getParameter("method"), HttpServletRequest.class, HttpServletResponse.class);
            String path = (String) method.invoke(this, req, resp);
            if (path.startsWith(DIRECTION)) {
                resp.sendRedirect(path.substring(DIRECTION.length() + 1));
            } else if (path.startsWith("ajax")) {
//                resp.sendRedirect(path.substring(DIRECTION.length()+1));
//                req.getRequestDispatcher(path).forward(req,resp);
            } else {
                req.getRequestDispatcher(path).forward(req, resp);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
