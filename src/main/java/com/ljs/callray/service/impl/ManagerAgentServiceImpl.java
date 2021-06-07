package com.ljs.callray.service.impl;

import com.ljs.callray.dao.AccountRepository;
import com.ljs.callray.dao.ManagerAgentRepository;
import com.ljs.callray.entity.Account;
import com.ljs.callray.entity.ManagerAgent;
import com.ljs.callray.service.ManagerAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author administrator
 * @date 2021/6/4 11:30
 */
@Service
public class ManagerAgentServiceImpl implements ManagerAgentService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ManagerAgentRepository managerAgentRepository;

    @Override
    public void save(Long managerId, List<Long> agentIds) {
        List<ManagerAgent> managerAgentList  = new ArrayList<>();
        for (Long agentId : agentIds) {
            managerAgentList.add(new ManagerAgent(managerId,agentId));
            Account account = accountRepository.selectById(agentId);//工号的desp添加管理者
            String desp = account.getDesp();
            if(desp != null) {
                if (!desp.contains(managerId.toString()) )
                {
                    if ( desp.equals(""))
                    {
                        account.setDesp(managerId.toString());
                    }
                    else
                    {
                        String temp = desp + "," + managerId;
                        if (temp.length() < 50)
                            account.setDesp(desp + "," + managerId);
                    }
                    accountRepository.updateById(account);
                }
            }else {
                account.setDesp(managerId.toString());
                accountRepository.updateById(account);
            }


        }
        managerAgentRepository.deleteByManagerId(managerId);
        if( managerAgentList.size() > 0){
            managerAgentRepository.batchAdd(managerAgentList);
        }

    }
}
