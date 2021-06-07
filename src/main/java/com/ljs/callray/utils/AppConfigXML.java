package com.ljs.callray.utils;

import com.ljs.callray.domain.Column;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

@SuppressWarnings("rawtypes")
public class AppConfigXML
{

	private Document document;
	private Element root;

	public AppConfigXML() throws DocumentException
	{
		SAXReader reader = new SAXReader();
		document = reader.read(AppConfigXML.class.getClassLoader().getResourceAsStream("appConfig.xml"));
		root = document.getRootElement();
	}

	/**
	 * 获取系统配置信息
	 * 
	 * @return
	 */
	public Map<String, Object> getConfig()
	{
		Map<String, Object> configMap = new HashMap<String, Object>();
		Element config = root.element("config");
		Element msgCenterIP = config.element("msgCenterIP");
		configMap.put("msgCenterIP", msgCenterIP.getTextTrim());
		Element msgCenterPort = config.element("msgCenterPort");
		configMap.put("msgCenterPort", Integer.parseInt(msgCenterPort.getTextTrim()));
		Element dataCenterIP = config.element("dataCenterIP");
		configMap.put("dataCenterIP", dataCenterIP.getText());
		Element dataCenterPort = config.element("dataCenterPort");
		configMap.put("dataCenterPort", Integer.parseInt(dataCenterPort.getText()));
		Element radio = config.element("ratio");
		configMap.put("ratio", radio.getText());
		Element listenPort = config.element("listenPort");
		configMap.put("listenPort", listenPort.getText());

		Element downFileType = config.element("downFileType");
		configMap.put("downFileType", downFileType.getText());
		Element Iscallrayplay = config.element("Iscallrayplay");
		configMap.put("Iscallrayplay", Iscallrayplay.getText());
		Element httpServerIp = config.element("httpServerIp");
		configMap.put("httpServerIp", httpServerIp.getText());
		Element httpServerPort = config.element("httpServerPort");
		configMap.put("httpServerPort", httpServerPort.getText());
		Element lang = config.element("alarmLang");
		configMap.put("alarmLang", lang.getText());

		Element localPath = config.element("localPath");
		configMap.put("localPath", localPath.getText());
		Element maxSelectNumber = config.element("maxSelectNumber");
		configMap.put("maxSelectNumber", maxSelectNumber.getText());
		return configMap;
	}

	public List<String> getCardType()
	{
		List<String> cardTypes = new ArrayList<String>();
		Element cardNode = root.element("cardTypes");
		List cardTypeNodes = cardNode.elements("cardType");
		for (Iterator iterator = cardTypeNodes.iterator(); iterator.hasNext();)
		{
			Element node = (Element) iterator.next();
			String cardType = node.getTextTrim();
			if (cardType != null && !cardType.isEmpty())
			{
				cardTypes.add(cardType);
			}
		}
		return cardTypes;
	}

	public List<String> getChannelType()
	{
		List<String> channelTypes = new ArrayList<String>();
		Element channelNode = root.element("channelTypes");
		List channelTypeNodes = channelNode.elements("channelType");
		for (Iterator iterator = channelTypeNodes.iterator(); iterator.hasNext();)
		{
			Element node = (Element) iterator.next();
			String channelType = node.getTextTrim();
			if (channelType != null && !channelType.isEmpty())
			{
				channelTypes.add(channelType);
			}
		}
		return channelTypes;
	}

	public List<String> getRatioType()
	{
		List<String> ratios = new ArrayList<String>();
		Element channelNode = root.element("ratios");
		List channelTypeNodes = channelNode.elements("ratioType");
		for (Iterator iterator = channelTypeNodes.iterator(); iterator.hasNext();)
		{
			Element node = (Element) iterator.next();
			String ratioType = node.getTextTrim();
			if (ratioType != null && !ratioType.isEmpty())
			{
				ratios.add(ratioType);
			}
		}
		return ratios;
	}

	/**
	 * 加载sysColumns下的column节点 用于显示和导出
	 * 
	 * @return
	 */
	public List<Column> getSysColumns()
	{
		List<Column> sysColumns = new ArrayList<Column>();
		Element channelNode = root.element("sysColumns");
		List channelTypeNodes = channelNode.elements("column");
		for (Iterator iterator = channelTypeNodes.iterator(); iterator.hasNext();)
		{
			Element node = (Element) iterator.next();
			Column column = new Column();
			Attribute attribute = node.attribute("name");
			if (attribute != null)
			{
				column.setName(attribute.getValue());
			}
			else
			{
				throw new RuntimeException(new Throwable("column节点的那么属性不存在"));
			}
			attribute = node.attribute("value");
			if (attribute != null)
			{
				column.setValue(attribute.getValue());
			}
			else
			{
				column.setValue(column.getName());
			}
			attribute = node.attribute("show");
			if (attribute != null)
			{
				column.setShow(Boolean.parseBoolean(attribute.getValue()));
			}
			attribute = node.attribute("export");
			if (attribute != null)
			{
				column.setExport(Boolean.parseBoolean(attribute.getValue()));
			}
			attribute = node.attribute("width");
			if (attribute != null)
			{
				column.setWidth(Integer.parseInt(attribute.getValue()) * 32);
			}
			else
			{
				column.setWidth(60 * 32);
			}
			attribute = node.attribute("format");
			if (attribute != null)
			{
				column.setFormat(attribute.getValue());
			}
			sysColumns.add(column);
		}
		return sysColumns;
	}

	public synchronized void saveAppConfig(String _msgCenterIP, String _msgCenterPort, String _dataCenterIP, String _dataCenterPort, String _ratio, String _listenPort, String _downFileType, String _Iscallrayplay, String _httpServerIp, String _httpServerPort, String _alarmLang, String _localPath, String _maxSelectNumber)
	{
		Element config = root.element("config");
		Element msgCenterIP = config.element("msgCenterIP");
		msgCenterIP.setText(_msgCenterIP);
		Element msgCenterPort = config.element("msgCenterPort");
		msgCenterPort.setText(_msgCenterPort);
		Element dataCenterIP = config.element("dataCenterIP");
		dataCenterIP.setText(_dataCenterIP);
		Element dataCenterPort = config.element("dataCenterPort");
		dataCenterPort.setText(_dataCenterPort);
		Element ratio = config.element("ratio");
		ratio.setText(_ratio);
		Element listenPort = config.element("listenPort");
		listenPort.setText(_listenPort);

		Element downFileType = config.element("downFileType");
		downFileType.setText(_downFileType);
		Element Iscallrayplay = config.element("Iscallrayplay");
		Iscallrayplay.setText(_Iscallrayplay);
		Element httpServerIp = config.element("httpServerIp");
		httpServerIp.setText(_httpServerIp);
		Element httpServerPort = config.element("httpServerPort");
		httpServerPort.setText(_httpServerPort);
		Element alarmLang = config.element("alarmLang");
		alarmLang.setText(_alarmLang);
		Element maxSelectNumber = config.element("maxSelectNumber");
		maxSelectNumber.setText(_maxSelectNumber);
		Element localPath = config.element("localPath");
		localPath.setText(_localPath);	

		try
		{
			OutputFormat format = OutputFormat.createPrettyPrint();// 创建文件输出的时候，自动缩进的格式
			format.setEncoding("UTF-8");// 设置编码
			String path = AppConfigXML.class.getClassLoader().getResource("appConfig.xml").getPath();
			path = URLDecoder.decode(path, "UTF-8");
			XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
			// XMLWriter writer = new XMLWriter(new
			// FileWriter(AppConfigXML.class.getClassLoader().getResource("appConfig.xml").getPath()));
			writer.write(document);
			writer.close();

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws DocumentException {

		AppConfigXML config = new AppConfigXML();
		System.out.println(config.getConfig());

	}

}
