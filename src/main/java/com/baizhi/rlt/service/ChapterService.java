package com.baizhi.rlt.service;


import com.baizhi.rlt.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    //分页查
    Map selectPage(Integer curPage, Integer pageSize,String albumId);
    //添加
    Map insert(Chapter chapter,String albumId);
    //修改
    void update(Chapter chapter);
    //删除
    void delete(List ids,String albumId);
}
