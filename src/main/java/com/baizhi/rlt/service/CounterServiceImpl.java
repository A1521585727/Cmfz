package com.baizhi.rlt.service;

import com.baizhi.rlt.dao.CounterDao;
import com.baizhi.rlt.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("counterService")
@Transactional
public class CounterServiceImpl implements CounterService {
    @Autowired
    CounterDao counterDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Counter> selectByUid(String uid, String cid) {
        Counter counter = new Counter();
        counter.setUserId(uid);
        counter.setCourseId(cid);
        List<Counter> select = counterDao.select(counter);
        return select;
    }

    @Override
    public void insert(String uid, String title,String cid) {
        Counter counter = new Counter();
        counter.setUserId(uid);
        counter.setTitle(title);
        counter.setId(UUID.randomUUID().toString());
        counter.setCreateDate(new Date());
        counter.setCount(0);
        counter.setCourseId(cid);
        counterDao.insertSelective(counter);
    }

    @Override
    public void delete(String id, String uid,String cid) {
        Counter counter = new Counter();
        counter.setUserId(uid);
        counter.setId(id);
        counter.setCourseId(cid);
        counterDao.delete(counter);
    }

    @Override
    public void update(String uid, String id, long count,String cid) {
        Counter counter = new Counter();
        counter.setUserId(uid);
        counter.setCount(count);
        counter.setId(id);
        counter.setCourseId(cid);
        counterDao.updateByPrimaryKeySelective(counter);
    }
}
