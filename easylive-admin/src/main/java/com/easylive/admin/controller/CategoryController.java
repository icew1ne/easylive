package com.easylive.admin.controller;

import com.easylive.entity.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CategoryController extends ABaseController{
    @RequestMapping("/loadCategory")
    public ResponseVO loadDataList() {
        return getSuccessResponseVO(null);
    }
}
