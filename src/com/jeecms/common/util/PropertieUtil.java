package com.jeecms.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils; 
public class PropertieUtil {

	/**
	 * 指定property文件
	 */
	public String getPath(String str,HttpServletRequest request) {
		String path=request.getSession().getServletContext().getRealPath("/");
		String temp = path+"/WEB-INF/"+ str;
		// System.out.println(temp);
		return temp;
	}
	
	public String getPath() {
		String path=this.getClass().getClassLoader().getResource("/").getPath();
		path=path.substring(0,path.length()-9);
		String temp = path+"/";
		// System.out.println(temp);
		return temp;
	}

	/**
	 * 根据Key 读取Value
	 * 
	 * @param key
	 * @return
	 */
	public String readData(String fileName, String key,HttpServletRequest request) {
		Properties props = new Properties();
		try {
			String path=getPath(fileName,request);
			InputStream in = new BufferedInputStream(new FileInputStream(
					getPath(fileName,request)));
			props.load(in);
			in.close();
			String value = props.getProperty(key);
			
			value=new String(value.getBytes("ISO8859-1"),"UTF-8");
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 修改或添加键值对 如果key存在，修改 反之，添加。
	 * 
	 * @param key
	 * @param value
	 */
	public void writeData(String fileName, String key, String value,HttpServletRequest request) {
		Properties prop = new Properties();
		value=convertToISO(value);
		if(value!=null){
			try {
				File file = new File(getPath(fileName,request));
				if (!file.exists())
					file.createNewFile();
				InputStream fis = new FileInputStream(file);
				prop.load(fis);
				fis.close();// 一定要在修改值之前关闭fis
				OutputStream fos = new FileOutputStream(getPath(fileName,request));
				prop.setProperty(key, value);
				prop.store(fos, "Update '" + key + "' value");
				fos.close();
			} catch (IOException e) {
				System.err.println("Visit " + fileName + " for updating " + value
						+ " value error");
			}
		}
	}
	/**
	 * 修改或添加键值对 如果key存在，修改 反之，添加。
	 * 
	 * @param map
	 */
	public void writeDataByMap(String fileName, Map<String, String> map,HttpServletRequest request) {
		Properties prop = new Properties();
		
		try {
				File file = new File(getPath(fileName,request));
				if (!file.exists())
					file.createNewFile();
				InputStream fis = new FileInputStream(file);
				prop.load(fis);
				fis.close();// 一定要在修改值之前关闭fis

				Set<String> keyset=map.keySet();
				Iterator<String> keys=keyset.iterator();
				while(keys.hasNext()){
					String key=keys.next();
					String value=map.get(key);
					value=convertToISO(value);
					if(value!=null)
						prop.setProperty(key, value);
				}
				OutputStream fos = new FileOutputStream(getPath(fileName,request));
				prop.store(fos, "");
		} catch (IOException e) {
		}
	}
	
	//转换中文字符为ISO8859-1
	public String convertToISO(String str){
		try {
			String newstr=new String(str.getBytes("UTF-8"),"ISO8859-1");
			return newstr;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) { 
	}
}
