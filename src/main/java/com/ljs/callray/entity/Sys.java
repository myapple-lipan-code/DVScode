package com.ljs.callray.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;


@TableName("t_sys")
public class Sys implements Serializable
{

	public static String downFileType = "{1}";

	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO,value = "lsh")
	private Long id;
	@TableField(value = "uniqueguid")
	private String uniqueguid;
	@TableField(value = "uniqueid")
	private String uniqueid;
	@TableField(value = "agent")
	private String agent;
	@TableField("callid")
	private String callid;
	@TableField("calledid")
	private String calledid;
	@TableField("extention")
	private String extention;
	@TableField(value = "preid")
	private String preid;
	@TableField(value = "nextid")
	private String nextid;
	@TableField(value = "starttime")
	private Date starttime;
	@TableField(value = "endtime")
	private Date endtime;
	@TableField(value = "howtime")
	private Integer howtime;
	@TableField(value = "acd")
	private Integer acd;
	@TableField(value = "dins")
	private String dins; // account_alias 这个是帐号别名 话务员姓名
	@TableField(value = "inorout")
	private Integer inorout;
	@TableField(value = "ifback")
	private Integer ifback;
	private String backwhere;
	private String voicepath;
	private String datapath;
	private String files;
	private String backpath;
	private String whereplace;
	private Integer voicesize;
	private Integer datasize;
	private String restorepath;
	private String grade;
	private String remark;

	private String columnsOne;
	private String columnsTwo;
	private String columnsThree;
	private String columnsFour;
	private String columnsFive;
	private String columnsSix;
	private String columnsSeven;
	private String columnsEight;
	private String columnsNine;
	private String columnsTen;

	public static String fieldToDBField(String field)
	{
		if ("columnsOne".equalsIgnoreCase(field))
		{
			return "columns_1";
		} else if ("columnsTwo".equalsIgnoreCase(field))
		{
			return "columns_2";
		} else if ("columnsThree".equalsIgnoreCase(field))
		{
			return "columns_3";
		} else if ("columnsFour".equalsIgnoreCase(field))
		{
			return "columns_4";
		} else if ("columnsFive".equalsIgnoreCase(field))
		{
			return "columns_5";
		} else if ("columnsSix".equalsIgnoreCase(field))
		{
			return "columns_6";
		} else if ("columnsSeven".equalsIgnoreCase(field))
		{
			return "columns_7";
		} else if ("columnsEight".equalsIgnoreCase(field))
		{
			return "columns_8";
		} else if ("columnsNine".equalsIgnoreCase(field))
		{
			return "columns_9";
		} else if ("columnsTen".equalsIgnoreCase(field))
		{
			return "columns_10";
		}
		return field;
	}

	public String getDownFileName()
	{
		Calendar c = Calendar.getInstance();
		c.setTime(starttime);
		return MessageFormat.format(downFileType, uniqueguid, uniqueid, agent, callid, calledid, extention, howtime, c.get(Calendar.YEAR), (c.get(Calendar.MONTH) + 1), c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUniqueguid()
	{
		return uniqueguid;
	}

	public void setUniqueguid(String uniqueguid)
	{
		this.uniqueguid = uniqueguid;
	}

	public String getUniqueid()
	{
		return uniqueid;
	}

	public void setUniqueid(String uniqueid)
	{
		this.uniqueid = uniqueid;
	}

	public String getAgent()
	{
		return agent;
	}

	public void setAgent(String agent)
	{
		this.agent = agent;
	}

	public String getCallid()
	{
		return callid;
	}

	public void setCallid(String callid)
	{
		this.callid = callid;
	}

	public String getCalledid()
	{
		return calledid;
	}

	public void setCalledid(String calledid)
	{
		this.calledid = calledid;
	}

	public String getExtention()
	{
		return extention;
	}

	public void setExtention(String extention)
	{
		this.extention = extention;
	}

	public String getPreid()
	{
		return preid;
	}

	public void setPreid(String preid)
	{
		this.preid = preid;
	}

	public String getNextid()
	{
		return nextid;
	}

	public void setNextid(String nextid)
	{
		this.nextid = nextid;
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

	public int getHowtime()
	{
		return howtime;
	}

	public void setHowtime(int howtime)
	{
		this.howtime = howtime;
	}

	public int getAcd()
	{
		return acd;
	}

	public void setAcd(int acd)
	{
		this.acd = acd;
	}

	public String getDins()
	{
		return dins;
	}

	public void setDins(String dins)
	{
		this.dins = dins;
	}

	public Integer getInorout()
	{
		return inorout;
	}

	public void setInorout(Integer inorout)
	{
		this.inorout = inorout;
	}

	public Integer getIfback()
	{
		return ifback;
	}

	public void setIfback(Integer ifback)
	{
		this.ifback = ifback;
	}

	public String getBackwhere()
	{
		return backwhere;
	}

	public void setBackwhere(String backwhere)
	{
		this.backwhere = backwhere;
	}

	public String getVoicepath()
	{
		return voicepath;
	}

	public void setVoicepath(String voicepath)
	{
		this.voicepath = voicepath;
	}

	public String getDatapath()
	{
		return datapath;
	}

	public void setDatapath(String datapath)
	{
		this.datapath = datapath;
	}

	public String getFiles()
	{
		return files;
	}

	public void setFiles(String files)
	{
		this.files = files;
	}

	public String getBackpath()
	{
		return backpath;
	}

	public void setBackpath(String backpath)
	{
		this.backpath = backpath;
	}

	public String getWhereplace()
	{
		return whereplace;
	}

	public void setWhereplace(String whereplace)
	{
		this.whereplace = whereplace;
	}

	public int getVoicesize()
	{
		return voicesize;
	}

	public void setVoicesize(int voicesize)
	{
		this.voicesize = voicesize;
	}

	public int getDatasize()
	{
		return datasize;
	}

	public void setDatasize(int datasize)
	{
		this.datasize = datasize;
	}

	public String getRestorepath()
	{
		return restorepath;
	}

	public void setRestorepath(String restorepath)
	{
		this.restorepath = restorepath;
	}

	public String getGrade()
	{
		return grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getColumnsOne()
	{
		return columnsOne;
	}

	public void setColumnsOne(String columnsOne)
	{
		this.columnsOne = columnsOne;
	}

	public String getColumnsTwo()
	{
		return columnsTwo;
	}

	public void setColumnsTwo(String columnsTwo)
	{
		this.columnsTwo = columnsTwo;
	}

	public String getColumnsThree()
	{
		return columnsThree;
	}

	public void setColumnsThree(String columnsThree)
	{
		this.columnsThree = columnsThree;
	}

	public String getColumnsFour()
	{
		return columnsFour;
	}

	public void setColumnsFour(String columnsFour)
	{
		this.columnsFour = columnsFour;
	}

	public String getColumnsFive()
	{
		return columnsFive;
	}

	public void setColumnsFive(String columnsFive)
	{
		this.columnsFive = columnsFive;
	}

	public String getColumnsSix()
	{
		return columnsSix;
	}

	public void setColumnsSix(String columnsSix)
	{
		this.columnsSix = columnsSix;
	}

	public String getColumnsSeven()
	{
		return columnsSeven;
	}

	public void setColumnsSeven(String columnsSeven)
	{
		this.columnsSeven = columnsSeven;
	}

	public String getColumnsEight()
	{
		return columnsEight;
	}

	public void setColumnsEight(String columnsEight)
	{
		this.columnsEight = columnsEight;
	}

	public String getColumnsNine()
	{
		return columnsNine;
	}

	public void setColumnsNine(String columnsNine)
	{
		this.columnsNine = columnsNine;
	}

	public String getColumnsTen()
	{
		return columnsTen;
	}

	public void setColumnsTen(String columnsTen)
	{
		this.columnsTen = columnsTen;
	}

	@Override
	public String toString()
	{
		return "VSys [id=" + id + ", uniqueguid=" + uniqueguid + ", uniqueid=" + uniqueid + ", agent=" + agent + ", callid=" + callid + ", calledid=" + calledid + ", extention=" + extention + ", preid=" + preid + ", nextid=" + nextid + ", starttime=" + starttime + ", endtime=" + endtime + ", howtime=" + howtime + ", acd=" + acd + ", dins=" + dins + ", inorout=" + inorout + ", ifback=" + ifback + ", backwhere=" + backwhere + ", voicepath=" + voicepath + ", datapath=" + datapath + ", files=" + files + ", backpath=" + backpath + ", whereplace=" + whereplace + ", voicesize=" + voicesize + ", datasize=" + datasize + ", restorepath=" + restorepath + ", grade=" + grade + ", remark=" + remark + ", columnsOne=" + columnsOne + ", columnsTwo=" + columnsTwo + ", columnsThree=" + columnsThree
				+ ", columnsFour=" + columnsFour + ", columnsFive=" + columnsFive + ", columnsSix=" + columnsSix + ", columnsSeven=" + columnsSeven + ", columnsEight=" + columnsEight + ", columnsNine=" + columnsNine + ", columnsTen=" + columnsTen + "]";
	}
}
