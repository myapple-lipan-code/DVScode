package com.ljs.callray.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 查询操作日志表单
 * 
 * @author Administrator
 *
 */
public class OperatelogForm implements Serializable
{

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date starttime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endtime;
	private Integer operateType;
	private String operator;
	public OperatelogForm() {
	}
	public OperatelogForm( Integer operateType, String operator) {
		this.operateType = operateType;
		this.operator = operator;

	}
	public OperatelogForm(Date starttime, Date endtime, Integer operateType, String operator) {
		this.starttime = starttime;
		this.endtime = endtime;
		this.operateType = operateType;
		this.operator = operator;
	}

	public Date getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Date starttime)
	{
		this.starttime = starttime;
	}

	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}

	public Integer getOperateType()
	{
		return operateType;
	}

	public void setOperateType(Integer operateType)
	{
		this.operateType = operateType;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "OperatelogForm{" +
				"starttime=" + starttime +
				", endtime=" + endtime +
				", operateType=" + operateType +
				", operator='" + operator + '\'' +
				'}';
	}
}
