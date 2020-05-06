package com.iscas.service.sample.controller;

import com.iscas.service.sample.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lvxianjin
 * @Date: 2020/5/5 10:44
 * @Description:
 */
@RestController
@CrossOrigin("*")
public class ProduceController {
    @Autowired
    private ProduceService produceService;
    @GetMapping("/test")
    public void produce(){
        produceService.createSample();
    }
}
