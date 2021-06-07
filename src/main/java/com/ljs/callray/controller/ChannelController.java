package com.ljs.callray.controller;

import com.ljs.callray.domain.BaseResponse;
import com.ljs.callray.domain.ChannelForm;
import com.ljs.callray.entity.Channel;
import com.ljs.callray.service.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author administrator
 * @date 2021/5/17 18:03
 */
@RestController
@RequestMapping("/channel")
@Api("分机API")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @PostMapping("/getListByPage")
    @ApiOperation("获取分机")
    public BaseResponse getListByPage(){

        return channelService.getListByPage();
    }
    @PostMapping("/get")
    @ApiOperation("根据id获取分机")
    public  BaseResponse get(String id){

        return channelService.get(id);
    }
    @PostMapping("/add")
    @ApiOperation("添加分机")
    public Map<String,Object> add(@RequestBody ChannelForm channelForm, BindingResult result){
        Map<String, Object> errors = new HashMap<String, Object>();
        errors = channelForm.validate();
        if (result.hasErrors())
        {
            List<FieldError> fes = result.getFieldErrors();
            for (FieldError fieldError : fes)
            {
                errors.put(fieldError.getField(), true);
            }
        }
        if (errors.size() > 0)
        {
            errors.put("result", false);
            return errors;
        }
        Set<String> changetExts = new HashSet<>();
        //添加单个分机
        if(channelForm.getEndExt() == null || channelForm.getEndExt().trim().isEmpty()){
               Channel old =  channelService.getByExtension(channelForm.getBeginExt().toString());
                if(old == null){
                    Channel channel = channelForm.toChannel();
                    channelService.add(channel);
                    if (channel.getCtiAppIPAddr() != null && !"".equals(channel.getCtiAppIPAddr().trim()))
                        changetExts.add(channel.getCtiAppIPAddr());
                }else{

                    errors.put("beginExist", true);
                    errors.put("result", false);
                    return errors;
                }
        }else{
            List<Channel> channels = new ArrayList<Channel>();
            List<Channel> exists = new ArrayList<Channel>();
            boolean ret = false;
            do
            {
                ret = channelForm.toChannelList(channels);
                channelService.add(channels, exists);
                if (channels.size() > 0)
                {
                    Channel newExt = channels.get(0);
                    if (newExt.getCtiAppIPAddr() != null && !"".equals(newExt.getCtiAppIPAddr().trim()))
                        changetExts.add(newExt.getCtiAppIPAddr());
                }
                channels.clear();
            }
            while (ret);
            if (exists.size() > 0)
            {
                errors.put("batchExist", exists);
            }

        }
        errors.put("result",true);
        return errors;

    }
    //进行数据的修改操作
    @PostMapping("/update")
    @ApiOperation("修改分机")
  public  Map<String,Object> update(@RequestBody ChannelForm channelForm,BindingResult result){
       Map<String, Object> errors = channelForm.validate();

          if(result.hasErrors()) {
              List<FieldError> fes = result.getFieldErrors();
              for (FieldError fieldError : fes) {
                  errors.put(fieldError.getField(), true);
              }
              if (errors.size() > 0) {
                  errors.put("result", false);
                  return errors;
              }
          }
          if (channelForm.getEndExt() == null || "".equals(channelForm.getEndExt()))
          {
              Channel old = channelService.getByExtension(channelForm.getBeginExt().toString());
              if (old == null || old.getId().longValue() == channelForm.getId().longValue())
              {
                  Set<String> changetExts = null;
                  changetExts = new HashSet<String>();
                  Channel newExt = channelForm.toChannel();
                  channelService.update(newExt);
                  if (old != null && old.getCtiAppIPAddr() != null && !"".equals(old.getCtiAppIPAddr().trim()))
                      changetExts.add(old.getCtiAppIPAddr());
                  if (newExt != null && newExt.getCtiAppIPAddr() != null && !"".equals(newExt.getCtiAppIPAddr().trim()))
                      changetExts.add(newExt.getCtiAppIPAddr());
                  errors.put("result", true);
              }
              else
              {
                  errors.put("exist", true);
                  errors.put("result", false);
                  return errors;
              }
          }
          errors.put("result",true);
          return errors;
      }
      @PostMapping("/delete")
      @ApiOperation("删除分机")
      public BaseResponse delete(Long id){
        Channel channel = channelService.deleteById(id);
        if(channel != null){
            return BaseResponse.success("删除分机"+channel.getExtension()+"成功");
        }else{
            return BaseResponse.success("删除分机失败");
        }

      }
    @PostMapping("/deletes")
    @ApiOperation("删除分机")
    public BaseResponse deletes(@RequestParam("ids") List<Long> ids){
        List<Channel> channel = channelService.deleteByIds(ids);
        if(channel != null){
            return BaseResponse.success("删除分机"+ids+"成功");
        }else{
            return BaseResponse.success("删除分机失败");
        }

    }


}
