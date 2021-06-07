package com.ljs.callray.controller;

import com.ljs.callray.domain.BaseResponse;
import com.ljs.callray.utils.AppConfigXML;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author administrator
 * @date 2021/5/20 16:02
 */
@RestController
@Api("配置API")
@RequestMapping("/config")
public class ConfigController {

    @PostMapping("/saveAppConfig")
    @ApiOperation("保存配置")
    public BaseResponse saveAppConfig(@RequestBody Map<String,String> appParams){
        try {
            AppConfigXML configXML = new AppConfigXML();
            String msgCenterIP = appParams.get("msgCenterIP");
            String msgCenterPort = appParams.get("msgCenterPort");
            String dataCenterIP = appParams.get("dataCenterIP");
            String dataCenterPort = appParams.get("dataCenterPort");
            String ratio = appParams.get("ratio");
            String listenPort = appParams.get("listenPort");
            String downFileType = appParams.get("downFileType");
            String Iscallrayplay = appParams.get("Iscallrayplay");
            String httpServerIp = appParams.get("httpServerIp");
            String httpServerPort = appParams.get("httpServerPort");
            String alarmLang = appParams.get("alarmLang");
            String localPath = StringEscapeUtils.escapeXml10(appParams.get("localPath"));
            if (localPath == null)
                localPath = "";
            String maxSelectNumber = appParams.get("maxSelectNumber");
            configXML.saveAppConfig(msgCenterIP,msgCenterPort,dataCenterIP,dataCenterPort,ratio,listenPort,downFileType,Iscallrayplay,httpServerIp,httpServerPort,alarmLang,localPath,maxSelectNumber);
            return BaseResponse.success("保存配置成功",null);
        } catch (DocumentException e) {
            e.printStackTrace();
            return BaseResponse.success("保存配置失败",null);
        }


    }
}
