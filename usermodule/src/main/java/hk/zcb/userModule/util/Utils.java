package hk.zcb.userModule.util;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {
	public static String findMessage(String key) {
		Properties properties = new Properties();
		InputStream inStream = Utils.class.getClassLoader().getResourceAsStream("message.properties");
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

	/**
	 * 创建颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	public static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static synchronized String getNowTime() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String nowtime;
		System.setProperty("user.timezone", "Asia/Shanghai");
		long time = System.currentTimeMillis();
		nowtime = String.valueOf(time);
		return nowtime;
	}

	/**
	 * 获取当前时间
	 * 
	 * @param str
	 *            例：yyyy-MM-dd HH:mm:ss 指定的日期格式
	 * @return
	 */
	public static String getNowTime(String str) {
		System.setProperty("user.timezone", "Asia/Shanghai");
		SimpleDateFormat formatter = new SimpleDateFormat(str);
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		String formatStr = formatter.format(new Date());
		return formatStr;
	}

	/**
	 * string类型转换为date类型 strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd
	 * HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒，strTime的时间格式必须要与formatType的时间格式相同
	 * 
	 * @param strTime
	 * @param formatType
	 * @return
	 * @throws Exception
	 */
	public static Date stringToDate(String strTime, String formatType) throws Exception {
		System.setProperty("user.timezone", "Asia/Shanghai");
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Date date = null;
		date = formatter.parse(strTime);
		return date;
	}

	/**
	 * date类型转换为long类型 date要转换的date类型的时间
	 * 
	 * @param date
	 * @return
	 */
	public static long dateToLong(Date date) {
		System.setProperty("user.timezone", "Asia/Shanghai");
		return date.getTime();
	}

	/**
	 * string类型转换为long类型 strTime要转换的String类型的时间 formatType时间格式
	 * strTime的时间格式和formatType的时间格式必须相同
	 * 
	 * @param strTime
	 * @param formatType
	 * @return
	 * @throws Exception
	 */
	public static long stringToLong(String strTime, String formatType) throws Exception {
		System.setProperty("user.timezone", "Asia/Shanghai");
		Date date = stringToDate(strTime, formatType); // String类型转成date类型
		if (date == null) {
			return 0;
		} else {
			long currentTime = dateToLong(date); // date类型转成long类型
			return currentTime;
		}
	}

	/**
	 * 将long值转换成对应格式的字符串的时间
	 * 
	 * @param time
	 * @param str
	 *            例：yyyy-MM-dd HH:mm:ss 指定的日期格式
	 * @return
	 */
	public static String longTimeToString(Long time, String str) {
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat(str);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		tsStr = sdf.format(time);
		return tsStr;
	}
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uuid);
		return  uuid;
	}

}
