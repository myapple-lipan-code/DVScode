package com.ljs.callray.domain;

import org.dom4j.Element;

/**
 * 配置文件中 column列
 * 
 * @author Administrator
 *
 */
public class Column
{

	public static final String INOROUT = "inoroutToStr";
	public static final String IFBACK = "ifbackToStr";
	public static final String HOWTIME = "howtimeToStr";
	public static final String DATATETIME = "datetimeToStr";

	private Element element;
	private int index;
	private String name; // 字段名称
	private String defaultValue;
	private String value; // 字段含义、显示用
	private String en_value; // 字段含义、显示用 英语
	private boolean show; // 字段是否显示为表格中的一列
	private boolean export; // 字段是否作为导出excel中的一列
	private int width; // 字段宽度
	private String format; // 格式化

	private boolean selectd; // 是否属于查询条件中的一项
	private boolean like; // 是否作为模糊匹配

	public Column()
	{
		super();
	}

	public Column(String name, String value, String envalue, boolean export, int width, String format)
	{
		super();
		this.name = name;
		this.value = value;
		this.en_value = envalue;
		this.export = export;
		this.width = width;
		this.format = format;
	}

	public Column(int index, String name, String value, String en_value, boolean show, boolean export)
	{
		this.index = index;
		this.name = name;
		this.value = value;
		this.en_value = en_value;
		this.show = show;
		this.export = export;
	}

	public Element getElement()
	{
		return element;
	}

	public void setElement(Element element)
	{
		this.element = element;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public String getValue()
	{
		if (value != null && !value.trim().isEmpty())
			return value;
		return defaultValue;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public boolean isShow()
	{
		return show;
	}

	public void setShow(boolean show)
	{
		this.show = show;
	}

	public boolean isExport()
	{
		return export;
	}

	public void setExport(boolean export)
	{
		this.export = export;
	}

	public boolean isSelectd()
	{
		return selectd;
	}

	public void setSelectd(boolean selectd)
	{
		this.selectd = selectd;
	}

	public boolean isLike()
	{
		return like;
	}

	public void setLike(boolean like)
	{
		this.like = like;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public String getFormat()
	{
		return format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	public String getEn_value()
	{
		return en_value;
	}

	public void setEn_value(String en_value)
	{
		this.en_value = en_value;
	}

	@Override
	public String toString()
	{
		return "Column [name=" + name + ", value=" + value + ", en_value=" + en_value + ", show=" + show + ", export=" + export + ", width=" + width + ", format=" + format + ", selectd=" + selectd + ", like=" + like + "]";
	}
}
