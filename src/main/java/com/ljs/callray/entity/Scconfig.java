package com.ljs.callray.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author
 * 
 */
@TableName("t_Scconfig")
public class Scconfig implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394459132739544209L;

	@TableId(type = IdType.AUTO,value = "num")
	private Long num;
	@TableField(value = "ext")
	private String ext;
	@TableField(value = "ip")
	private String ip;
	@TableField(value = "cancap")
	private Integer cancap;
	@TableField(value = "captype")
	private Integer captype;
	@TableField(value = "fileport")
	private Integer fileport;
	@TableField(value = "loadtime")
	private String loadtime;
	@TableField(value = "agentid")
	private String agentid;
	@TableField(value = "maxsctime")
	private Integer maxsctime;
	@TableField(value = "showform")
	private Integer showform;
	@TableField(value = "windowsuser")
	private String windowsuser;

	public Long getNum()
	{
		return num;
	}

	public void setNum(Long num)
	{
		this.num = num;
	}

	public String getExt()
	{
		return ext;
	}

	public void setExt(String ext)
	{
		this.ext = ext;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Integer getCancap()
	{
		return cancap;
	}

	public void setCancap(Integer cancap)
	{
		this.cancap = cancap;
	}

	public Integer getCaptype()
	{
		return captype;
	}

	public void setCaptype(Integer captype)
	{
		this.captype = captype;
	}

	public Integer getFileport()
	{
		return fileport;
	}

	public void setFileport(Integer fileport)
	{
		this.fileport = fileport;
	}

	public String getLoadtime()
	{
		return loadtime;
	}

	public void setLoadtime(String loadtime)
	{
		this.loadtime = loadtime;
	}

	public String getAgentid()
	{
		return agentid;
	}

	public void setAgentid(String agentid)
	{
		this.agentid = agentid;
	}

	public Integer getMaxsctime()
	{
		return maxsctime;
	}

	public void setMaxsctime(Integer maxsctime)
	{
		this.maxsctime = maxsctime;
	}

	public Integer getShowform()
	{
		return showform;
	}

	public void setShowform(Integer i)
	{
		this.showform = i;
	}

	public String getWindowsuser()
	{
		return windowsuser;
	}

	public void setWindowsuser(String windowsuser)
	{
		this.windowsuser = windowsuser;
	}

}
