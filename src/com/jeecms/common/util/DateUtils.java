package com.jeecms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.jasper.tagplugins.jstl.core.Param;

/**
 * @author Tom
 */
public class DateUtils {
	private StringBuffer buffer = new StringBuffer();
	private static String ZERO = "0";
	private static DateUtils date;
	public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat format1 = new SimpleDateFormat(
	"yyyyMMdd HH:mm:ss");
	public static SimpleDateFormat format2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public final static String TODAY="今天";
	public final static String TOMORROW="明天";
	public final static String YESTERDAY="过去";
	public final static String TWO_DAYS_AGO="两天后";
	public final static String THREE_DAYS_AGO="三天后";
	public final static String A_WEEK_AGO="一周后";

	public String getNowString() {
		Calendar calendar = getCalendar();
		buffer.delete(0, buffer.capacity());
		buffer.append(getYear(calendar));

		if (getMonth(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMonth(calendar));

		if (getDate(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getDate(calendar));
		if (getHour(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getHour(calendar));
		if (getMinute(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMinute(calendar));
		if (getSecond(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getSecond(calendar));
		return buffer.toString();
	}

	private static int getDateField(Date date,int field) {
		Calendar c = getCalendar();
		c.setTime(date);
		return c.get(field);
	}
	public static int getYearsBetweenDate(Date begin,Date end){
		int bYear=getDateField(begin,Calendar.YEAR);
		int eYear=getDateField(end,Calendar.YEAR);
		return eYear-bYear;
	}
	
	public static int getMonthsBetweenDate(Date begin,Date end){
		int bMonth=getDateField(begin,Calendar.MONTH);
		int eMonth=getDateField(end,Calendar.MONTH);
		return eMonth-bMonth;
	}
	public static int getWeeksBetweenDate(Date begin,Date end){
		int bWeek=getDateField(begin,Calendar.WEEK_OF_YEAR);
		int eWeek=getDateField(end,Calendar.WEEK_OF_YEAR);
		return eWeek-bWeek;
	}
	public static int getDaysBetweenDate(Date begin,Date end){
		int bDay=getDateField(begin,Calendar.DAY_OF_YEAR);
		int eDay=getDateField(end,Calendar.DAY_OF_YEAR);
		System.out.println(bDay);
		System.out.println(eDay);
		return eDay-bDay;
	}

	 /**  
     * @param args  
     * @throws ParseException   
     */  
    public static void main(String[] args) throws ParseException {   
        // TODO Auto-generated method stub   
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
//        Date d1=sdf.parse("2012-09-08 10:10:10");   
//        Date d2=sdf.parse("2012-09-15 00:00:00");   
//        System.out.println(daysBetween(d1,d2));   
//  
//        System.out.println(daysBetween("2012-9-08 10:10:10","2012-09-08 24:10:50"));   
    	Date date=getStartDate(new Date());
    	String strdate=format2.format(date);
    	System.out.println(strdate);
    }   
       
    /**   
     * 计算两个日期之间相差的天数   
     * @param smdate 较小的时间  
     * @param bdate  较大的时间  
     * @return 相差天数  
     * @throws ParseException   
     */     
    public static int daysBetween(Date smdate,Date bdate) throws ParseException     
    {     
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
        smdate=sdf.parse(sdf.format(smdate));   
        bdate=sdf.parse(sdf.format(bdate));   
        Calendar cal = Calendar.getInstance();     
        cal.setTime(smdate);     
        long time1 = cal.getTimeInMillis();                  
        cal.setTime(bdate);     
        long time2 = cal.getTimeInMillis();          
        long between_days=(time2-time1)/(1000*3600*24);   
             
       return Integer.parseInt(String.valueOf(between_days));            
    }     
    /**   
     * 计算两个日期之间相差的天数   
     * @param date 所代表的数字  
     *  
     * @return 数字代表的中午翻译 
     */  
    public static String getDateTranslate(int date){
    	if(date<0){
    		return YESTERDAY;
    	}else if(date==0){
    		return TODAY;
    	}else if(date==1){
    		return TOMORROW;
    	}else if(date==2){
    		return TWO_DAYS_AGO;
    	}else if(date>3&&date<7){
    		return THREE_DAYS_AGO;
    	}else{
    		return A_WEEK_AGO;
    	}
    }
    
       
	/**  
	*字符串的日期格式的计算  
	*/  
    public static int daysBetween(String smdate,String bdate) throws ParseException{   
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
        Calendar cal = Calendar.getInstance();     
        cal.setTime(sdf.parse(smdate));     
        long time1 = cal.getTimeInMillis();                  
        cal.setTime(sdf.parse(bdate));     
        long time2 = cal.getTimeInMillis();          
        long between_days=(time2-time1)/(1000*3600*24);   
             
       return Integer.parseInt(String.valueOf(between_days));      
    }   
	/**
	 * 获取date年后的amount年的第一天的开始时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficYearStart(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, amount);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date年后的amount年的最后一天的终止时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficYearEnd(Date date,int amount) {
		Date temp = getStartDate(getSpecficYearStart(date,amount + 1));
		Calendar cal = Calendar.getInstance();
		cal.setTime(temp);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 获取date月后的amount月的第一天的开始时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficMonthStart(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, amount);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取当前自然月后的amount月的最后一天的终止时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficMonthEnd(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getSpecficMonthStart(date,amount + 1));
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 获取date周后的第amount周的开始时间（这里星期一为一周的开始）
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficWeekStart(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		cal.add(Calendar.WEEK_OF_MONTH, amount);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date周后的第amount周的最后时间（这里星期日为一周的最后一天）
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficWeekEnd(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		cal.add(Calendar.WEEK_OF_MONTH, amount);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return getFinallyDate(cal.getTime());
	}
	
	public static Date getSpecficDateStart(Date date,int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, amount);
		return getStartDate(cal.getTime());
	}

	/**
	 * 得到指定日期的一天的的最后时刻23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFinallyDate(Date date) {
		String temp = format.format(date);
		temp += " 23:59:59";

		try {
			return format1.parse(temp);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 得到指定日期的一天的开始时刻00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {
		String temp = format.format(date);
		temp += " 00:00:00";

		try {
			return format1.parse(temp);
		} catch (Exception e) {
			return null;
		}
	}

	private int getYear(Calendar calendar) {
		return calendar.get(Calendar.YEAR);
	}

	private int getMonth(Calendar calendar) {
		return calendar.get(Calendar.MONDAY) + 1;
	}

	private int getDate(Calendar calendar) {
		return calendar.get(Calendar.DATE);
	}

	private int getHour(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	private int getMinute(Calendar calendar) {
		return calendar.get(Calendar.MINUTE);
	}

	private int getSecond(Calendar calendar) {
		return calendar.get(Calendar.SECOND);
	}

	private static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public static DateUtils getDateInstance() {
		if (date == null) {
			date = new DateUtils();
		}
		return date;
	}

}
