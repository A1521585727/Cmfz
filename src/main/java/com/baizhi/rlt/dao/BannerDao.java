package com.baizhi.rlt.dao;

import com.baizhi.rlt.entity.Banner;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface BannerDao extends InsertListMapper<Banner>, Mapper<Banner>, DeleteByIdListMapper<Banner,String> {
}
