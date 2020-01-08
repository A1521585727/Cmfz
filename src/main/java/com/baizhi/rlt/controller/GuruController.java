package com.baizhi.rlt.controller;

import com.baizhi.rlt.entity.Guru;
import com.baizhi.rlt.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    GuruService guruService;

    @RequestMapping("/selectAllGuru")
    public List<Guru> selectAllGuru(){
        List<Guru> gurus = guruService.selectAll();
        return gurus;
    }
}
