package com.baizhi.rlt.controller;

import com.baizhi.rlt.entity.Album;
import com.baizhi.rlt.service.AlbumService;
import com.baizhi.rlt.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    //分页查
    @RequestMapping("/selectPageAlbum")
    public Map selectPageAlbum(Integer page, Integer rows){
        Map map = albumService.selectPage(page, rows);
        return map;
    }
    //编辑判断是修改/删除/增加
    @RequestMapping("/saveAlbum")
    public Map saveAlbum(Album album, String oper, String[] id){
        Map map  = null;
        if ("add".equals(oper)){
            map= albumService.insert(album);
        } else if ("edit".equals(oper)){
            album.setPicture(null);
            map = albumService.update(album);
        } else {
            albumService.delete(Arrays.asList(id));
        }
        return map;
    }
    //文件上传
    @RequestMapping("/uploadAlbum")
    public Map uploadAlbum(MultipartFile picture, String albumId,HttpServletRequest request){

        String http = HttpUtil.getHttp(picture, request, "/upload/pic/");
        //将文件存放到指定目录
        Album album = new Album();
        album.setId(albumId);
        album.setPicture(http);
        albumService.update(album);

        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        return hashMap;
    }
}
