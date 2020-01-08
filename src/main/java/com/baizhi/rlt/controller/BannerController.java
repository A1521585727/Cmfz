package com.baizhi.rlt.controller;

import com.alibaba.excel.EasyExcel;
import com.baizhi.rlt.entity.Banner;
import com.baizhi.rlt.entity.BannerDataListener;
import com.baizhi.rlt.entity.BannerPageDto;
import com.baizhi.rlt.service.BannerService;
import com.baizhi.rlt.util.HttpUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;

    //查所有
    @ResponseBody
    @RequestMapping("/selectAll")
    public List<Banner> selectAll(){
        List<Banner> banners = bannerService.selectAll();
        return banners;
    }
    //分页查
    @ResponseBody
    @RequestMapping("/selectPageBanner")
    public BannerPageDto selectPageBanner(Integer page, Integer rows){
        BannerPageDto bannerPageDto = bannerService.selectPage(page, rows);
        return bannerPageDto;
    }

    //编辑判断是修改/删除/增加
    @RequestMapping("saveBanner")
    @ResponseBody
    public Map saveBanner(Banner banner,String oper,String[] id){
        HashMap hashMap = new HashMap();
        if ("add".equals(oper)){
            bannerService.insert(banner);
            hashMap.put("bannerId",banner.getId());
        } else if ("edit".equals(oper)){
            banner.setUrl(null);
            bannerService.update(banner);
            hashMap.put("bannerId",banner.getId());
        } else {
            bannerService.delete(Arrays.asList(id));
        }
        return hashMap;
    }

    //文件上传
    @RequestMapping("/uploadBanner")
    @ResponseBody
    public Map uploadBanner(MultipartFile url, String bannerId,HttpServletRequest request){

        String http = HttpUtil.getHttp(url, request, "/upload/img/");
        //将文件存放到指定目录
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setUrl(http);
        bannerService.update(banner);

        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        return hashMap;
    }


    //EasyExcel导出轮播图信息
    @RequestMapping("/outBannerInformation")
    @ResponseBody
    public Map outBannerInformation(){
        List<Banner> banners = bannerService.selectAll();
        for (Banner banner : banners) {
            String[] split = banner.getUrl().split("/");
            String url = split[split.length-1];
            banner.setUrl(url);
        }
        String fileName = "F:\\资料\\后期项目\\day7-poiEasyExcel\\示例\\"+new Date().getTime()+".xls";
        EasyExcel.write(fileName,Banner.class).sheet("banner").doWrite(banners);
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        return hashMap;
    }
    //Excel模板下载
    @RequestMapping("/outBannerModel")
    @ResponseBody
    public Map outBannerModel(){
        List<Banner> banners = bannerService.selectAll();
        //创建excel表格对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //工作簿对象
        HSSFSheet sheet = workbook.createSheet();
        //行对象
        HSSFRow row = sheet.createRow(0);
        //表格的头部分
        String[] str = {"ID","标题","图片","超链接","创建时间","描述","状态"};
        for (int i = 0; i < str.length; i++) {
            String s = str[i];
            //单元格对象
            row.createCell(i).setCellValue(s);
        }

        try {
            workbook.write(new File("F:\\资料\\后期项目\\day7-poiEasyExcel\\示例\\"+new Date().getTime()+".xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        return hashMap;
    }
    //EasyExcel导入轮播图信息
    @RequestMapping("/inBannerInformation")
    @ResponseBody
    public Map inBannerInformation(MultipartFile inputBanner,HttpServletRequest request){
        String http = HttpUtil.getHttp(inputBanner, request, "/upload/inputBanner/");
        String realPath = request.getSession().getServletContext().getRealPath("/upload/inputBanner/");
        String[] split = http.split("/");
        String name = realPath + split[split.length-1];
        EasyExcel.read(name,Banner.class,new BannerDataListener()).sheet().doRead();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        return hashMap;
    }
}
