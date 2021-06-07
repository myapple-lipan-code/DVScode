package com.ljs.callray.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ljs.callray.domain.BaseResponse;
import com.ljs.callray.domain.PageForm;
import com.ljs.callray.domain.PageInfoForm;
import com.ljs.callray.service.ScconfigService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author administrator
 * @date 2021/6/4 16:01
 */
@RestController
@RequestMapping("/scconfig")
@Api("录屏API")
public class ScConfigController {

    @Autowired
    ScconfigService scconfigService;

    @PostMapping("/list")
    public BaseResponse list(@RequestBody JSONObject accountParams) {
        Map<String, Object> params = JSONObject.parseObject(accountParams.toJSONString(), new TypeReference<Map<String, Object>>() {
        });
        int current = (int) params.get("current");
        int size = (int) params.get("size");
        String ext = (String) params.get("ext");
        PageInfoForm pageInfoForm = new PageInfoForm(current,size);
        try {
            PageForm page = scconfigService.getListByPage(ext, pageInfoForm);
            return BaseResponse.success("查询成功", page);
        } catch (Exception e) {
            return BaseResponse.success("查询失败", null);
        }
    }
}
