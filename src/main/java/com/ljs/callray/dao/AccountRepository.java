package com.ljs.callray.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljs.callray.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author administrator
 * @date 2021/5/17 10:39
 */
@Repository
public interface AccountRepository extends BaseMapper<Account> {

    Account getByNameAndProperty(Account queryParam);

    List<Account> getByIds(List<Long> ids);

    List<Account> getByProperty(int property);

    List<Account> getByPropertyAndDepartments(Map<String, Object> params);

    List<Account> getByDepartment(String departmentIds);

    List<Account> getAgentAndExtByDepartments(Map<String, Object> paramters);

    List<Account> getMgrAccountAgentOrExt(Map<String, Object> paramters);

    Account getByNameAndPassword(Account account);

    //获取管理的账户信息
    List<Account> getMgrAccount(Long id);
}
