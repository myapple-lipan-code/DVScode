package com.ljs.callray.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ljs.callray.domain.BaseResponse;
import com.ljs.callray.domain.OperatelogForm;
import com.ljs.callray.domain.PageForm;
import com.ljs.callray.entity.Operatelog;
import com.ljs.callray.service.OperatelogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author administrator
 * @date 2021/5/27 15:08
 */
@RestController
@RequestMapping("/operaterlog")
@Api("日志")
public class OperatelogController {

    @Autowired
    private OperatelogService operatelogService;

    @PostMapping("/get")
    @ApiOperation("获取日志")
    public BaseResponse getByPage(@RequestBody OperatelogForm form){
        try{
            List<Operatelog> operatelogs = operatelogService.getByPage(form);
            return  BaseResponse.success(operatelogs);
        }catch (Exception ee){
            ee.printStackTrace();
            return  BaseResponse.fail("查询日志异常");
        }
    }
    @PostMapping("/getByPage")
    @ApiOperation("获取日志")
    public  BaseResponse get(@RequestBody JSONObject operateParams){

        Map<String, Object> params = JSONObject.parseObject(operateParams.toJSONString(), new TypeReference<Map<String, Object>>(){});
        JSONObject operatelogFormjob = (JSONObject) params.get("operatelogForm");
        JSONObject pageFormjob = (JSONObject) params.get("pageForm");
        PageForm pageForm = new PageForm(pageFormjob.getInteger("current"),pageFormjob.getInteger("size"));
        OperatelogForm  form  = new OperatelogForm(operatelogFormjob.getDate("starttime"),operatelogFormjob.getDate("endtime"),
                operatelogFormjob.getInteger("operateType"),
                operatelogFormjob.getString("operator"));
        //OperatelogForm form = new OperatelogForm();
        try{
            PageForm page = operatelogService.get(form, pageForm);
            return  BaseResponse.success(page);
        }catch (Exception ee){
            ee.printStackTrace();
            return  BaseResponse.fail("查询日志异常");
        }
    }
}
