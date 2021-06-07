package com.ljs.callray.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljs.callray.dao.AccountRepository;
import com.ljs.callray.dao.ChannelRepository;
import com.ljs.callray.dao.ManagerAgentRepository;
import com.ljs.callray.domain.BaseResponse;
import com.ljs.callray.entity.Account;
import com.ljs.callray.entity.Channel;
import com.ljs.callray.entity.ManagerAgent;
import com.ljs.callray.service.ChannelService;
import com.ljs.callray.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author administrator
 * @date 2021/5/17 17:56
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ManagerAgentRepository managerAgentRepository;

    @Override
    public BaseResponse getListByPage() {
        try{
            List<Channel> channelList = channelRepository.selectList(new QueryWrapper<Channel>());
            return  BaseResponse.success(channelList);
        }catch (Exception e){
            return BaseResponse.fail("获取分机失败");

        }


    }

    @Override
    public BaseResponse get(String id) {
        try{

            Long lsh = (id == null || "".equals(id)) ? 0: Long.parseLong(id);
            Channel channel = channelRepository.selectById(lsh);
            if(channel != null){
                Account queryParam = new Account();
                queryParam.setName(channel.getExtension());
                queryParam.setProperty(Account.EXTENSION);
                Account account = accountRepository.getByNameAndProperty(queryParam);
               if(account != null){
                   String dpts = account.getDesp();
                   if (dpts != null && !dpts.trim().isEmpty())
                   {
                       List<Account> oldDepartments = accountRepository.getByIds(CommonUtils.StrArrayToLongList(dpts.split(",")));
                       List<Account> departments = new ArrayList<>();
                       List<String> departmentIds = new ArrayList<>();
                       //做一个筛选
                       if(oldDepartments != null && oldDepartments.size() > 0){
                           for (int i = 0; i < oldDepartments.size(); i++) {
                                Account department = oldDepartments.get(i);
                               Integer property = department.getProperty();
                               if(property == Account.DEPARTMENT){
                                   departments.add(department);
                                   departmentIds.add(department.getId().toString());
                               }
                           }
                       }
                       String[] departmentIdList = new String[departmentIds.size()];
                       departmentIdList = departmentIds.toArray(departmentIdList);
                       channel.setDepartmentIds(departmentIdList);
                       channel.setDepartments(departments);
                   }
               }
            }
            return  BaseResponse.success(channel);
        }catch (Exception e){
            return BaseResponse.fail("根据id获取分机失败");

        }
    }

    @Override
    public Channel getByExtension(String extension) {
        //根据分机号 获取开始分机
        QueryWrapper<Channel> wrapper = new QueryWrapper<>();
        wrapper.eq("extension",extension);
        List<Channel> channels = channelRepository.selectList(wrapper);
        if(channels == null || channels.size() <= 0){
            return null;
        }else{
            return channels.get(0);
        }

    }

    @Override
    public void add(Channel channel) {
        // 设置通道号
        Integer chNoInCard = channelRepository.getMaxChNoInCard();
        if (chNoInCard == null)
        {
            chNoInCard = 0;
        }
        channel.setChNoInCard(chNoInCard + 1);
        channelRepository.add(channel);
        // 在帐号表也添加分机
        Account queryParam = new Account();
        queryParam.setName(channel.getExtension());
        queryParam.setProperty(Account.EXTENSION);
        Account account = accountRepository.getByNameAndProperty(queryParam);
        if (account == null)
        {
            account = new Account();
            account.setName(channel.getExtension());
            account.setAlias(channel.getExtension());
            account.setProperty(Account.EXTENSION);
            account.setDesp(CommonUtils.toString(channel.getDepartmentIds(), ","));
            accountRepository.insert(account);
            if (channel.getDepartmentIds() != null && channel.getDepartmentIds().length > 0)
            {
                // 把分机添加到所属部门的班长的管理表中
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("property", Account.MONITOR);
                params.put("departments", channel.getDepartmentIds());
                //获取到部门下的班长
                List<Account> monitors = accountRepository.getByPropertyAndDepartments(params);
                List<ManagerAgent> agents = new ArrayList<ManagerAgent>();
                if (monitors != null && monitors.size() > 0)
                {
                    for (Account monitor : monitors)
                    {
                        if (account.getId() != null && account.getId() > -1)
                        {
                            agents.add(new ManagerAgent(monitor.getId(), account.getId()));
                        }
                    }
                    if (agents.size() > 0)
                    {
                        managerAgentRepository.batchAdd(agents);
                    }
                }
            }
        }
    }
    public void add(List<Channel> channels, List<Channel> exists)
    {
        for (Channel channel : channels)
        {
            Channel old = this.getByExtension(channel.getExtension());
            if (old == null)
            {
                this.add(channel);
            }
            else
            {
                exists.add(channel);
            }
        }
    }
    @Override
    public void update(Channel newExt) {
        Channel old = channelRepository.selectById(newExt.getId());
        if(old != null){
            old.setCardType(newExt.getCardType());
            old.setChannelIPAddr(newExt.getChannelIPAddr());
            old.setChannelMac(newExt.getChannelMac());
            old.setChannelPacketSize(newExt.getChannelPacketSize());
            old.setChannelSBCServer(newExt.getChannelSBCServer());
            old.setChannelType(newExt.getChannelType());
            old.setChannelVirtualExt(newExt.getChannelVirtualExt());
            old.setComputer(newExt.getComputer());
            old.setCtiAppIPAddr(newExt.getCtiAppIPAddr());
            old.setEnable(newExt.getEnable());
            old.setExtension(newExt.getExtension());
            channelRepository.updateById(old);
        }
    }

    @Override
    public Channel deleteById(Long id) {
        //进行删除 需要删除的就是 分机  账号表分机   管理范围内被管理的分机
        Channel channel = channelRepository.selectById(id);
        if(channel != null){
            String extension = channel.getExtension();
            Account queryParam = new Account();
            queryParam.setProperty(Account.EXTENSION);
            queryParam.setName(extension);
            Account account = accountRepository.getByNameAndProperty(queryParam);//删除分机账号
            if(account != null){
                accountRepository.deleteById(account.getId());
                //删除被管理者分机
                managerAgentRepository.deleteByAgentId(account.getId());
            }
            //删除通道
            channelRepository.deleteById(id);

        }
        return channel;
    }

    //删除分机数据
    @Override
    public List<Channel> deleteByIds(List<Long> ids) {
      List<Channel> channels = new ArrayList<>();
        for (Long id : ids) {
            Channel channel = this.deleteById(id);
            if(channel != null){
                channels.add(channel);
            }
        }
        return channels;
    }
}
