package com.ljs.callray.service;

import com.ljs.callray.domain.BaseResponse;
import com.ljs.callray.entity.Channel;

import java.util.List;

/**
 * @author administrator
 * @date 2021/5/17 17:46
 */
public interface ChannelService {

    BaseResponse getListByPage();

    BaseResponse get(String id);

    Channel getByExtension(String extension);

    void add(Channel channel);

    void add(List<Channel> channels, List<Channel> exists);

    void update(Channel newExt);

    Channel deleteById(Long id);

    List<Channel> deleteByIds(List<Long> ids);
}
