package com.ljs.callray.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljs.callray.entity.Channel;
import org.springframework.stereotype.Repository;

/**
 * @author administrator
 * @date 2021/5/17 17:57
 */
@Repository
public interface ChannelRepository extends BaseMapper<Channel> {
    //获取最大分机号
    Integer getMaxChNoInCard();

    /**
     * 添加通道
     *
     * @param channel
     */
    void add(Channel channel);

}
