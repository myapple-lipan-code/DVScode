package com.ljs.callray.service;

import com.ljs.callray.controller.model.Result;
import com.ljs.callray.controller.vo.LoginVo;
import com.ljs.callray.domain.PageForm;
import com.ljs.callray.domain.PageInfoForm;
import com.ljs.callray.entity.Account;

import java.util.List;

/**
 * @author administrator
 * @date 2021/5/17 10:40
 */
public interface AccountService {

    Result<LoginVo> login(Account account);

    PageForm getByProperty(int property,String name, PageInfoForm pageForm);

    List<Account>  getByProperty(int property);

    void batchAdd(List<Account> addAccounts, List<Account> existAccount);

    void add(Account toAccount);

    void update(Account newaccount);

      Account getById(Long id);

     List<Account> getAllDeps();

     void  delete(Long id);

      void  deletes(List<Long> ids);

    List<Account> getMgrAccountAgentOrExt(Long id, Integer accountProperty);

    List<Account> getAgentAndExtByDepartments(List<String> departments, Integer accountProperty);

    Account getByNameAndPassword(Account account);
}
