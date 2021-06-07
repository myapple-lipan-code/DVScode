package com.ljs.callray.service;

import com.ljs.callray.domain.PageForm;
import com.ljs.callray.domain.PageInfoForm;

/**
 * @author administrator
 * @date 2021/6/4 16:03
 */
public interface ScconfigService {

    PageForm getListByPage(String ext, PageInfoForm pageInfoForm);

}
