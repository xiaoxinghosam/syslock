package com.fzz.cloud.fzzcloudlockingSystem.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * 密码算法工具类
 * @author Administrator
 *
 */
public class ArithmeticUtils2 {
	
	public static final List<List<Integer>> baseYear = baseYear();

	public static final List<Integer> baseNumber = getBaseNumber2();// 长度为1080，数值为:例如:1, 8, 15,22, 29,36, 43,50...

	public static final List<List<Integer>> listAndList = getBaseListAndList();// 长度为1080，数值为：{{1,1,1},{1,1,2},{1,1,3}....}
	
	public static final int[] arrInt = {11, 15, 22, 29, 36, 43, 47, 50, 57, 64, 71, 78, 85, 92, 99, 55, 83, 27, 62, 76};
	/**
	 * 获取当个密码(没有具体结束时间要求)
	 * 
	 * @param startTime
	 * @param allow
	 * @param type  0:永久密码。1：单次密码。2：清空密码
	 * @return
	 */
	public static String permanentPwd(String startTime, String allow, String type) {
		
		String startYear = dateFormat(startTime).substring(0, 4);// 获取年

		Integer startY = getStartYearFlag(startYear, null);// 开始时间年位标识

		List<List<Integer>> timeList = getTimeList(new String[] { dateFormat(startTime) });// 封装开始时间{{3,4,5},{3,4,6}}

		List<List<Integer>> startListAndList = getMinusPositive(timeList.get(0));

		List<Integer> minusPositiveStartTimeList = startListAndList.get(0);

		List<Integer> startSum = startListAndList.get(1);// 如果时间数组里面有负数有值判断情况

		Integer startTimeIndex = getIndex(minusPositiveStartTimeList);// 根据在时间数组中不同组合有相同的数组在角标的位置

		if (startTimeIndex != -1) {

			Integer startValue = baseNumber.get(startTimeIndex);// 根据角标在1080数组中获取数值

			String value = getValue(startValue, startSum);// 判断数组中负数情况
			// - - - 都为负数的情况下1080对应的值+1
			// + - - 正负负情况下1080对应的值+2
			// - + - +3
			// - - + +4
			// + + - +5
			// - + + +6
			// + - + +7

			String startVal = alterationStr(value);// 如果或者1080中的值不够4位高位补0例如:45==》0045
			
			String strValue = "";
			
			if("0".equals(type))
				strValue = "0";
			else if("1".equals(type)) 
				strValue = "4";
			else if("2".equals(type)) 
				strValue = "3";
			
			String substring = startVal.substring(0, startVal.length() - 3);
			
			int parseInt = Integer.parseInt(startVal.substring(1, startVal.length()));
			
//			strValue = strValue + startY + String.valueOf(Integer.parseInt(allow) + (Integer.parseInt(startVal)));// 拼接标识并且返回
			
			String nextInt = String.valueOf(arrInt[new Random().nextInt(20)]);
			
			strValue = strValue + startY + substring + String.valueOf(Integer.parseInt(allow) + Integer.parseInt(parseInt + nextInt));// 拼接标识并且返回

			System.out.println("没有位移之前的数据:" + strValue);

			String changePlace = changePlace(strValue);

			return changePlace;

		}

		return null;
	}

	/**
	 * 获取限时密码or循环密码
	 * 
	 * 
	 * 标识位（2位）:第一位为密码标识位。第二位为年标识位0表示18年依次类推..循环密码没有年取1-9为循环方式 00：永久密码 10：限时密码
	 * 2{循环方式}：循环密码 30：清空密码 40：单次密码
	 * 
	 * 
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param forway 循环密码有的循环方式
	 * @return
	 */
	public static String getTimePasswordOrForPwd(String startTime, String endTime, String allow, String forway) {
		
		String startDate = dateFormat(startTime);
		
		String endDate = dateFormat(endTime);

		String startYear = startDate.substring(0, 4);

		String endYear = endDate.substring(0, 4);
		
		String hrTime = endDate.substring(endDate.length() - 4,endDate.length() - 2);
		
		Integer yearFlag = getStartYearFlag(startYear,endYear);// 开始年和结束年求平方的和

		List<List<Integer>> timeList = getTimeList(new String[] { dateFormat(startTime), dateFormat(endTime) });// 封装开始时间，结束时间切割{{3,4,5},{3,4,6}}

		System.out.println("timeList输入的时间为:" + timeList);

		List<List<Integer>> startListAndList = getMinusPositive(timeList.get(0));// 获取结束时间超过6 - 15-12的负数表现形式例如:7=1(-1)，16=1(-1)
																		
		List<List<Integer>> endListAndList = getMinusPositive(timeList.get(1));// 获取结束时间超过6 - 15-12的负数表现形式例如:7=1(-1)，16=1(-1)

		List<Integer> minusPositiveStartTimeList = startListAndList.get(0);

		List<Integer> minusPositiveEndTimeList = endListAndList.get(0);

		List<Integer> startSum = startListAndList.get(1);// 如果时间数组里面有负数有值判断情况

		List<Integer> endSum = endListAndList.get(1);// 如果时间数组里面有负数有值判断情况

		Integer startTimeIndex = getIndex(minusPositiveStartTimeList);// 根据在时间数组中不同组合有相同的数组在角标的位置

		Integer endTimeIndex = getIndex(minusPositiveEndTimeList);// 根据在时间数组中不同组合有相同的数组在角标的位置

		if (startTimeIndex != -1 && endTimeIndex != -1) {

			Integer startValue = baseNumber.get(startTimeIndex);// 根据角标在1080数组中获取数值

			Integer endValue = baseNumber.get(endTimeIndex);// 根据角标在1080数组中获取数值

			String value = getValue(startValue, startSum);// 判断数组中负数情况

			String value2 = getValue(endValue, endSum);// 判断数组中负数情况

			String startVal = alterationStr(value);// 如果或者1080中的值不够4位高位补0例如:45==》0045

			String endVal = alterationStr(value2);
			
			String startOne = startVal.substring(0, startVal.length() - 3);//值前面第一个数值
			
			String endOne = endVal.substring(0, endVal.length() - 3);//值前面第一个数值
			
			String sum;
			
			if(StringUtils.isBlank(forway)) {
				sum = startVal.substring(1, startVal.length()) + endVal.substring(1, endVal.length());//值后面的数值拼接
				
			} else {
				sum = startVal.substring(1, startVal.length()) + hrTime;
			}
					
			
			String valueOf = String.valueOf(Integer.parseInt(allow) + (Integer.parseInt(sum)));//与唯一标识相加之后
			
			String substring = startOne + valueOf.substring(0 , valueOf.length() / 2);//从中间切割拼接完整的字符串
			
			String substring2 = endOne + valueOf.substring(valueOf.length() / 2 , valueOf.length());//从中间切割拼接完整的字符串
			
			String strValue;
			
			if(StringUtils.isBlank(forway)) {
				strValue = "1" + yearFlag + substring + substring2;// 拼接标识并且返回1：限时密码
			} else {
				strValue = "2" + yearFlag + forway + startOne + valueOf;// 拼接标识并且返回 2：循环密码
			}

			System.out.println("没有位移之前的数据:" + strValue);

			String changePlace = changePlace(strValue);

			System.out.println("位移之后的数据：" + changePlace);

			return changePlace;
		}

		return null;
	}
	
	
	public static Integer getStartYearFlag(String startYear, String endYear) {
		
		List<Integer> list = new ArrayList<>();
		
		if(StringUtils.isNotBlank(endYear) && StringUtils.isNotBlank(startYear)) {
			list.add(Integer.parseInt(startYear));
			list.add(Integer.parseInt(endYear));
		} else 
			list.add(Integer.parseInt(startYear));
			list.add(Integer.parseInt(startYear));
		
		Integer index = -1;

		for (int i = 0; i < baseYear.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < baseYear.get(i).size(); j++) {
				if (!(baseYear.get(i).get(j)).equals(list.get(j))) {
					flag = false;
				}
			}

			if (flag) {
				System.out.println("listAndList中的小list的值：" + baseYear.get(i));
				index = i;
			}
		}
		
		return index;
	}

	/**
	 * 换位置
	 * @param str
	 * @return
	 */
	public static String changePlace(String str) {
		
		char[] charArray = str.toCharArray();
    	
		if(charArray[charArray.length - 1] == '0') {
			
			return str;
			
		} else {
			
			return new StringBuilder(str).reverse().toString();
		}
	}
	

	/**
	 * 位移字符创加密
	 * 
	 * @param value
	 * @return
	 */
	public static String displacement(String value) {

		String substring = value.substring(0, value.length() / 2 - 1);

		System.out.println("位移之前的数据：" + substring);

		String binaryString = toBinaryString(Integer.parseInt(substring));

		LinkedList<String> revers = revers(binaryString);// 高位为0地位有值的时候也应该转换

		String strVal = "";

		for (String string : revers) {
			strVal += string;
		}

		System.out.println("拼接反转二进制字符串为:" + strVal);

		String frontVal = Integer.valueOf(strVal, 2).toString();// 前面一半的数据

		System.out.println("计算出二进制的十进制表现形式为:" + frontVal);

		String centreVal = value.substring(value.length() / 2 - 1, value.length() / 2);// 中间值得数据

		System.out.println("计算出中间值为:" + centreVal);

		String substring2 = value.substring((value.length() / 2), value.length());

		String binaryString2 = toBinaryString(Integer.parseInt(substring2));

		LinkedList<String> revers2 = revers(binaryString2);

		String strVal2 = "";

		for (String string : revers2) {
			strVal2 += string;
		}

		System.out.println("拼接反转二进制字符串为后面的值:" + strVal2);

		String latterVal = Integer.valueOf(strVal2, 2).toString();// 后面一半的数据

		System.out.println("计算出二进制的值为:" + latterVal);

		return frontVal + centreVal + latterVal;
	}

	/**
	 * 二进制完整数组切割并位移
	 * 
	 * @param binaryString
	 * @return
	 */
	public static LinkedList<String> revers(String binaryString) {

		LinkedList<String> linkedList = new LinkedList<>();

		for (int i = 1; i <= binaryString.length(); i++) {
			if (i % 4 == 0) {
				String substring = binaryString.substring(i - 4, i);
				linkedList.add(substring);
			}
		}

		System.out.println("没有转换之前的数：" + linkedList);

		linkedList.addFirst(linkedList.getLast());// 1111011111111111111111111111

		linkedList.removeLast();

		System.out.println("转换之后的数值：" + linkedList);

		return linkedList;
	}

	/**
	 * 获取数字完整的二进制表现形式
	 * 
	 * @param i
	 * @return
	 */
	final static char[] DIGITS = { '0', '1' };

	public static String toBinaryString(int i) {
		char[] buf = new char[16];
		int pos = 16;
		int mask = 1;
		do {
			buf[--pos] = DIGITS[i & mask];
			i >>>= 1;
		} while (pos > 0);

		return new String(buf, pos, 16);
	}

	public static Date getDate(String time) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			date = sdf.parse(time);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 获取到的1080值为当个数值补0
	 * @param password
	 * @return
	 */
	public static String alterationStr(String password) {

		char[] charArrays = new char[4];

		StringBuilder builder = new StringBuilder();

		if (password.length() >= 4) {
			builder.append(password);
		} else {
			char[] charArray = password.toCharArray();
			System.arraycopy(charArray, 0, charArrays, charArrays.length - charArray.length, charArray.length);
			for (int i = 0; i < charArrays.length; i++) {
				if (charArrays[i] == '\u0000') {
					charArrays[i] = '0';
				}
			}
		}

		for (char c : charArrays) {
			builder.append(c);
		}

		return builder.toString().substring(0, 4);
	}

	private static String getValue(Integer indexValue, List<Integer> sumList) {

		Integer one = sumList.get(0);
		Integer two = sumList.get(1);
		Integer three = sumList.get(2);

		System.out.println("sumList中的值:" + sumList);

		//MM-dd HH
		if (one == 7 && two == 16 && three == 12) { // - - - ..				.
			
			indexValue += 7;//,1
			
		} else if (one != 7 && two == 16 && three == 12) {// + - - ..
			
			indexValue += 3;//,2
			
		} else if (one == 7 && two != 16 && three == 12) {// - + - ..
			
			indexValue += 5;//,3
			
		} else if (one == 7 && two == 16 && three != 12) {// - - +
			
			indexValue += 6;//,4
			
		} else if (one != 7 && two != 16 && three == 12) {// + + - ..
			
			indexValue += 1;//,5
			
		} else if (one == 7 && two != 16 && three != 12) {// - + + ..		.
			
			indexValue += 4;//,6
			
		} else if (one != 7 && two == 16 && three != 12) {// + - + ..
			
			indexValue += 2;//,7
		}
		
		if(indexValue >= 10000) {
			String valueOf = String.valueOf(indexValue);
			String substring = valueOf.substring(2, valueOf.length());
			String value = "#" + substring;
			
			return value;
			
		} else {
			
			return indexValue.toString();
		}
	}

	/**
	 * 时间切割
	 * 
	 * @param time
	 * @return
	 */
	private static List<List<Integer>> getTimeList(String[] timeArr) {
		List<List<Integer>> lists = new ArrayList<>();
		for (String time : timeArr) {
			List<Integer> list = new ArrayList<>();
			for (int i = 1; i <= time.length() - 2; i++) {
				if (i % 2 == 0 && i > 4) {
					String substring = time.substring(i - 2, i);
					list.add(Integer.parseInt(substring));
				}
			}
			lists.add(list);
		}

		return lists;
	}

	/**
	 * 获取基础的1-8中1080个不同数据
	 * 
	 * @return
	 */
	public static List<Integer> getBaseNumber() {

		List<Integer> list = new ArrayList<>();

		Integer count = 0;

		f: for (int i = 1;;) {
			for (int j = 1;; j++) {
				if (j - i == 7) {
					if (count != 1456) {//1456 1628
						count++;
						list.add(i);
						i = j;
					} else {
						break f;
					}
				}
			}
		}

		return list;
	}
	
	/**
	 * 获取基础的1-8中1456个不同数据 其中大于等于9900并且小于等于10000的数挖空，用后面的值补
	 * 
	 * @return
	 */
	public static List<Integer> getBaseNumber2() {

		List<Integer> list = new ArrayList<>();

		Integer count = 0;

		f: for (int i = 1;;) {
			for (int j = 1;; j++) {
				if (j - i == 7) {
					if (count != 1470) {//1456  1628 1628
						count++;
						if(i == 1) {
							list.add(i);
						}
						i = j;
						if(i >= 9900 && i <= 10000 ) {
							continue f;
						} else {
						/*	if(i % 2 == 0 && i > 9800) {
								list.add(i);
							} else if(i < 9800){
								list.add(i);
							} else {
								continue f;
							}*/
							if(i != 10291) {
								list.add(i);
							} 
						}
					} else {
						break f;
					}
				}
			}
		}
		
		return list;
	}
	
	public static String addWell(String startValue, String endValue) {
		
		
		
		return null;
	}
	
	public static void main(String[] args) {
		String timePasswordOrForPwd = getTimePasswordOrForPwd("2018-06-02-09-49", "2019-01-01-00-00", "78945", null);
//		String permanentPwd = permanentPwd("2018-06-02-09-49", "78945", "0");
//		System.out.println(permanentPwd);
//		System.out.println(baseNumber.size());
//		List<Integer> baseNumber2 = getBaseNumber2();
//		System.out.println("修改后的baseNumber值为：" + baseNumber2 + "\r\n" + "长度为：" + baseNumber2.size() + "\r\n" + "数组中最大值为:" + baseNumber2.get(baseNumber2.size() -1));
		
//		 LocalDate d = LocalDate.parse("2008-06-07", DateTimeFormatter.ISO_LOCAL_DATE);
//		 System.out.println("这是当年的第"+d.getDayOfYear()+"天.");
		
		System.out.println(timePasswordOrForPwd);
		 
		
	}	
	

	/**
	 * 获取基本的组合不相同数据
	 * 
	 * @return
	 */
	public static List<List<Integer>> getBaseListAndList() {

		List<List<Integer>> listAndList = new ArrayList<>();
		List<Integer> arrList = null;

		for (int j = 1; j <= 7; j++) {
			for (int x = 1; x <= 16; x++) {
				for (int y = 0; y < 13; y++) {
					arrList = new ArrayList<>();
					arrList.add(j);
					arrList.add(x);
					arrList.add(y);
					listAndList.add(arrList);
				}
			}
		}
		return listAndList;
	}
	
	
	public static List<List<Integer>> baseYear() {
		
		List<List<Integer>> listAndList = new ArrayList<>();
		List<Integer> yearList = null;
		
		for (int i = 2018; i <= 2021; i++) {
			for (int j = 2018; j <= 2021; j++) {
				if(i > j) {
					continue;
				}
				yearList = new ArrayList<>();
				yearList.add(i);
				yearList.add(j);
				listAndList.add(yearList);
			}
		}
		
		return listAndList;
	}
	

	/**
	 * 获取list中list相同数组角标出现的位置
	 * 
	 * @param listAndList
	 * @param list
	 * @return
	 */
	public static Integer getIndex(List<Integer> list) {

		Integer index = -1;

		for (int i = 0; i < listAndList.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < listAndList.get(i).size(); j++) {
				if (!(listAndList.get(i).get(j)).equals(list.get(j))) {
					flag = false;
				}
			}

			if (flag) {
				System.out.println("listAndList中的小list的值：" + listAndList.get(i));
				index = i;
			}
		}

		return index;
	}

	/**
	 * 获取负数的正数形式相加的角标
	 * 
	 * @param minus
	 * @return
	 */
	public static Integer getPositive(List<Integer> minus) {

		Integer index = 0;

		for (int i = 0; i < minus.size(); i++) {
			if (i == 0) {
				if (minus.get(i) < 0) {
					Integer number = Math.abs(minus.get(i)) + 6;
					System.out.println("list中月的值:" + number);
					index += number;
				} else {
					index += minus.get(i);
				}

			} else if (i == 1) {
				if (minus.get(i) < 0) {
					Integer number = Math.abs(minus.get(i)) + 16;
					System.out.println("list中日的值:" + number);
					index += number;
				} else {
					index += minus.get(i);
				}

			} else {
				if (minus.get(i) < 0) {
					Integer number = Math.abs(minus.get(i)) + 12;
					System.out.println("list中时的值:" + number);
					index += number;// 77 5 12
				} else {
					index += minus.get(i);
				}
			}
		}

		return index;

	}

	/**
	 * 获取时间的负数的正数表现形式
	 * 
	 * @param list
	 * @return
	 */
	public static List<List<Integer>> getMinusPositive(List<Integer> list) {

		List<List<Integer>> listAndList = new ArrayList<>();

		List<Integer> minusList = new ArrayList<>();

		List<Integer> sumList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				if (list.get(i) > 7) {
					Integer number = list.get(i) - 7;
					minusList.add(number);
					sumList.add(7);

				} else {
					minusList.add(list.get(i));
					sumList.add(1);
				}

			} else if (i == 1) {
				if (list.get(i) > 16) {
					Integer number = list.get(i) - 16;
					minusList.add(number);
					sumList.add(16);
				} else {
					minusList.add(list.get(i));
					sumList.add(2);
				}

			} else {
				if (list.get(i) > 12) {
					Integer number = list.get(i) - 12;
					minusList.add(number);
					sumList.add(12);
				} else {
					minusList.add(list.get(i));
					sumList.add(3);
				}
			}
		}

		listAndList.add(minusList);
		listAndList.add(sumList);

		return listAndList;
	}

	public static List<Integer> getMinus(List<Integer> list) {

		List<Integer> minusList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				if (list.get(i) > 7) {
					Integer number = list.get(i) - 7;
					minusList.add(-number);
				} else {
					minusList.add(list.get(i));
				}
			} else if (i == 1) {
				if (list.get(i) > 16) {
					Integer number = list.get(i) - 16;
					minusList.add(-number);
				} else {
					minusList.add(list.get(i));
				}

			} else {
				if (list.get(i) > 12) {
					Integer number = list.get(i) - 12;
					minusList.add(-number);
				} else {
					minusList.add(list.get(i));
				}
			}
		}

		return minusList;
	}
	
	/**
	 * 返回时间
	 * @param time 格式2018-12-21-14
	 * @return
	 */
	public static String dateFormat(String time) {
		StringBuilder stringBuilder = new StringBuilder();
		String[] split = time.split("-");
		for (String string : split) {
			stringBuilder.append(string);
		}
		
		return stringBuilder.toString();
	}
	
    
    /**
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * 待定方法================================================================================================================
     * @param startValue
     * @param endValue
     * @return
     */
	
    /** 
     * （相对）精确的除法运算,当发生除不尽的情况时， 
     * 由scale参数指定精度，以后的数字四舍五入。 
     * @param d1 被除数 
     * @param d2 除数 
     * @param scale 需要精确到小数点以后几位 
     * @return 两个参数的商 
     */  
	
	
    public static double div(double d1, double d2, int scale) {  
        if (scale < 0) {  
            throw new IllegalArgumentException(  
                    "The scale must be a positive integer or zero");  
        }  
        BigDecimal b1 = new BigDecimal(Double.toString(d1));  
        BigDecimal b2 = new BigDecimal(Double.toString(d2));  
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
    }  
    
    
	public static String changeValue(String startValue, String endValue) {
		
		List<String> strList = new ArrayList<>();
		
		strList.add(startValue);
		
		if(StringUtils.isNotBlank(endValue)) {
			strList.add(endValue);
		}
		
		for (String string : strList) {
			
			if(Integer.parseInt(string) >= 9800 && Integer.parseInt(string) < 10000) {
				
				String substring = string.substring(0,string.length() - 1) + "0";
				
				int parseInt = Integer.parseInt(string.substring(string.length() - 2,string.length()));
				
				Integer integer = (int)div(Double.valueOf(substring), 2, 0);
				
				if(parseInt % 2 == 0) {
					/**0 2 4 6 8     
					   0 6 7 8 9 */
					switch (parseInt) {
					case 0:
						
						break;
					case 2:
						
						break;
					case 4:
						
						break;
					case 6:
						
						break;
					case 8:
						
						break;
					default:
						break;
					}
				} else {
					
					int i = (int)div(parseInt, 2, 0);
					integer += i;
					
				}
			} else {
				return string;
			}
		}
		
		return null;
	}
	
	
    public static void updateBaseList() {
    	
    	
    	List<Integer> list = new ArrayList<>();
    	
    	for (int i = 0; i < baseNumber.size(); i++) {
    		if(baseNumber.get(i) >= 9900 && baseNumber.get(i) <= 10000) {
    			list.add(baseNumber.get(i));
    		}
		}
    	
    	System.out.println("大于等于9900并且小于等于10000的值有:" + list + "   数量有：" + list.size());
    }

}
