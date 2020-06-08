package com.hxzy.servlet;

import com.google.gson.Gson;
import com.hxzy.Resout.Status;
import com.hxzy.entity.ExceptionEnum;
import com.hxzy.entity.Goods;
import com.hxzy.Resout.PageBean;
import com.hxzy.service.BaseService;
import com.hxzy.service.impl.GoodsServiceimpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/shoping")
public class GoodsServlet extends BaseServlet {

    Goods goods;
    BaseService biz = new GoodsServiceimpl();
    List<Goods> list = new ArrayList();
    PageBean<Goods> pb;

    public String index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        if (req.getMethod().equalsIgnoreCase("get")) {
            return "/WEB-INF/goods/index.html";
        } else {
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            pb = selectByLimitPage(pageNum, pageSize);
            Status status = new Status(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMessage(), pb);
            resp.getWriter().print(gson.toJson(status));
            return "ajax";
        }
    }

    public String insert(HttpServletRequest req, HttpServletResponse resp) {
        doAdd(req);
        biz.insert(goods);
        return "/WEB-INF/goods/index.html";//请求显示表单页面
    }

    private PageBean<Goods> selectByLimitPage(int pageNum, int pageSize) {
        int totalRecord = biz.selectCount();
        pb = new PageBean(pageNum, pageSize, totalRecord);
        int starIndex = pb.getStartIndex();
        pb.setList(biz.selectByLimitInt(starIndex, pageSize));
        return pb;
    }

    private void doAdd(HttpServletRequest req) {
        goods = new Goods();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2 *********通过工厂获取servlet 上传的工具
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //3  ********通过servlet上传工具解析请求，获取（拿到）所有的表单域
            List<FileItem> items = upload.parseRequest(req);
            //每个item 就是一个表单域
            for (FileItem item : items) {
                //普通域
                if (item.isFormField()) {
                    if (item.getSize() == 0) {
                        throw new Exception(item.getFieldName() + "不能为空！");
                    }
                    String fieldValue = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
                    //用 BeanUtils 将 名称、值 拷贝到对象中去
                    BeanUtils.copyProperty(goods, item.getFieldName(), fieldValue);
                } else {
                    //首先获取上传的文件名称
                    String fileName = item.getName();
                    if (fileName == "") {
                        throw new Exception("上传文件不能为空！");
                    }
                    //拼装上传路径字符串
                    String uploadPath = "public/upload/images/";
                    String physicalPath = getServletContext().getRealPath("/" + uploadPath);

                    String uuid = UUID.randomUUID().toString();
                    physicalPath = physicalPath + uuid + fileName;

                    //将文件保存到 physicalPath
                    item.write(new File(physicalPath));//写磁盘
                    //将文件保存路径保存到icon
                    BeanUtils.copyProperty(goods, "productIcon", uploadPath + uuid + fileName);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
