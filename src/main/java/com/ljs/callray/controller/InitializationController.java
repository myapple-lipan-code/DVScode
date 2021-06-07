package com.ljs.callray.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljs.callray.dao.RightItemRepository;
import com.ljs.callray.entity.Account;
import com.ljs.callray.entity.RightItem;
import com.ljs.callray.service.AccountService;
import com.ljs.callray.utils.AccountUtils;
import com.ljs.callray.utils.AppConfigXML;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.map.HashedMap;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author administrator
 * @date 2021/5/19 9:13
 */
@RestController
@RequestMapping("/init")
@Api("初始化配置")
public class InitializationController {

    private Logger log = LoggerFactory.getLogger(InitializationController.class);

    @Autowired
    private RightItemRepository rightItemRepository;

    @Autowired
    private AccountService accountService;



    @PostMapping("/initialize")
    @ApiOperation("初始化配置信息")
    public Map<String,Object> initialize(){
        Map<String,Object> initializationMap = new HashedMap<>();
        List<RightItem> rightItemList = rightItemRepository.selectList(new QueryWrapper<>());
        initializationMap.put("rightItemList",rightItemList);
        //log.info("---------------->已准备加载权限信息数据<---------");
        //log.info("------------> 开始加载配置文件信息 <------------");
        try {
            AppConfigXML config = new AppConfigXML();
            List<String> cardTypes = config.getCardType();
            //log.info("所有录音方案:"+cardTypes);
            List<String> channelTypes = config.getChannelType();
            //log.info("所有通道类型:"+channelTypes);
            List<String> ratioTypes = config.getRatioType();
            //log.info("所有压缩率:"+ratioTypes);

            initializationMap.put("cardTypes",cardTypes);
            initializationMap.put("channelTypes",channelTypes);
            initializationMap.put("ratioTypes",ratioTypes);

            //录音记录导出列 待定
            Map<String, Object> configMap = config.getConfig();
            initializationMap.put("appConfig",configMap);
            //初始化消息中心操作


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return initializationMap;

    }
    @PostMapping("/initializeDepartment")
    @ApiOperation("初始部门信息")
    public Map<String,Object> initializeDepartment(){
        Map<String,Object> initializationMap = new HashedMap<>();
        List<Account> departments = accountService.getByProperty(Account.DEPARTMENT);
        departments.add(AccountUtils.getDefaultDepartment());
        initializationMap.put("departments",departments);
        return initializationMap;
    }
}
