package com.ljs.callray.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author administrator
 * @date 2021/5/19 9:14
 */
@TableName("tx_RightItemList")
public class RightItem  implements Serializable {


    @TableId(type = IdType.AUTO,value = "right_id")
    private  Long id;
    @TableField(value = "right_desc")
    private String descp;
    @TableField(value = "right_type")
    private String  type;
    @TableField(value = "right_code")
    private String code;
    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
