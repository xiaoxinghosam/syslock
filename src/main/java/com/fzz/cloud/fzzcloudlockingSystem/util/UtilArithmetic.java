package com.fzz.cloud.fzzcloudlockingSystem.util;

import java.math.BigDecimal;

/** 
 * 精确的加减乘除运算
 * 
 */  
public class UtilArithmetic {  
      
    /** 
     * 精确的加法运算 
     * @param d1 被加数 
     * @param d2 加数 
     * @return 两个参数的和 
     */  
    public static double add(double d1, double d2) {  
        BigDecimal b1 = new BigDecimal(Double.toString(d1));  
        BigDecimal b2 = new BigDecimal(Double.toString(d2));  
        return b1.add(b2).doubleValue();  
    }  
      
    /** 
     * 精确的减法运算 
     * @param d1 被减数 
     * @param d2 减数 
     * @return 两个参数的差 
     */  
    public static double sub(double d1, double d2) {  
        BigDecimal b1 = new BigDecimal(Double.toString(d1));  
        BigDecimal b2 = new BigDecimal(Double.toString(d2));  
        return b1.subtract(b2).doubleValue();  
    }  
      
    /** 
     * 精确的乘法运算 
     * @param d1 被乘数 
     * @param d2 乘数 
     * @return 两个参数的积 
     */  
    public static double mul(double d1, double d2) {  
        BigDecimal b1 = new BigDecimal(Double.toString(d1));  
        BigDecimal b2 = new BigDecimal(Double.toString(d2));  
        return b1.multiply(b2).doubleValue();  
    }  
      
    /** 
     * 精确的乘法运算,由scale参数指定精度，以后的数字四舍五入. 
     * @param d1 被乘数 
     * @param d2 乘数 
     * @param scale 需要精确到小数点以后几位 
     * @return 两个参数的积 
     */  
    public static double mul2(double d1, double d2, int scale){  
        BigDecimal b1 = new BigDecimal(Double.toString(d1));  
        BigDecimal b2 = new BigDecimal(Double.toString(d2));  
        return round(b1.multiply(b2).doubleValue(), scale);  
    }  
  
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
      
    /** 
     * 判断d1是否大于d2 
     * @param d1 实数1 
     * @param d2 实数2 
     * @return true-大于;false-小于 
     */  
    public static boolean isDoubleGreater(double d1, double d2) {  
        boolean bn = false;  
        BigDecimal b1 = new BigDecimal(Double.toString(d1));  
        BigDecimal b2 = new BigDecimal(Double.toString(d2));  
        if (b1.compareTo(b2) > 0) {  
            bn = true;  
        } else {  
            bn = false;  
        }  
        return bn;  
    }  
      
    /** 
     * 判断d1是否小于d2 
     * @param d1 实数1 
     * @param d2 实数2 
     * @return true-小于;false-大于 
     */  
    public static boolean isDoubleLess(double d1, double d2) {  
        boolean bn = false;  
        BigDecimal b1 = new BigDecimal(Double.toString(d1));  
        BigDecimal b2 = new BigDecimal(Double.toString(d2));  
        if (b1.compareTo(b2) < 0) {  
            bn = true;  
        } else {  
            bn = false;  
        }  
        return bn;  
    }  
      
    /** 
     * 判断d1是否等于d2 
     * @param d1 实数1 
     * @param d2 实数2 
     * @return true-等于;false-不等于 
     */  
    public static boolean isDoubleEqual(double d1, double d2){  
        boolean bn = false;  
        BigDecimal b1 = new BigDecimal(Double.toString(d1));  
        BigDecimal b2 = new BigDecimal(Double.toString(d2));  
        if (b1.compareTo(b2) == 0) {  
            bn = true;  
        } else {  
            bn = false;  
        }  
        return bn;  
    }  
      
    /** 
     * 小数位四舍五入处理 
     * @param d 需要四舍五入的数字 
     * @param scale 小数点后保留几位 
     * @return 四舍五入后的结果 
     */  
    public static double round(double d, int scale) {  
        if (scale < 0) {  
            throw new IllegalArgumentException(  
                    "The scale must be a positive integer or zero");  
        }  
        BigDecimal b = new BigDecimal(Double.toString(d));  
        BigDecimal one = new BigDecimal("1");  
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
    }  
    /**
     *  9710, 9717, 9724, 9731, 9738, 9745, 9752, 9759, 9766, 9773, 9780, 9787, 9794, 9801, 9808, 9815, 9822, 9829, 9836, 9843, 9850,
     *  9857, 9864, 9871, 9878, 9885, 9892, 9899, 9906, 9913, 9920, 9927, 9934, 9941, 9948, 9955, 9962, 9969, 9976, 9983, 9990, 9997, 
     *  10004, 10011, 10018, 10025, 10032, 10039, 10046, 10053, 10060, 10067, 10074, 10081, 10088, 10095, 10102, 10109, 10116, 10123, 
     *  10130, 10137, 10144, 10151, 10158, 10165, 10172, 10179, 10186
     * @param args
     */
    public static void main(String[] args) {
		
    	System.out.println((int)div(3, 2, 0));
    	
	}
}  