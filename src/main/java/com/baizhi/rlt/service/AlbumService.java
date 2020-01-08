package com.baizhi.rlt.service;


import com.baizhi.rlt.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    //分页查
    Map selectPage(Integer curPage, Integer pageSize);
    //添加
    Map insert(Album album);
    //修改
    Map update(Album album);
    //删除
    void delete(List ids);
}
