package com.utils;

import java.math.BigDecimal;

public class ArithUtil {

	/** * 加法运算 * @param v1 * @param v2 * @return */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/*** 减法运算* @param v1* @param v2* @return */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/***
	 * 乘法运算 * @param v1 * @param v2 * @return
	 * */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/*** 除法运算* @param v1 被除数* @param v2 除数* @return 商 */
	public static double div(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,3).doubleValue();
	}

	/*** 除法运算* @param v1 被除数* @param v2 除数* @return 商和余数 */
	public static BigDecimal[] divideAndRemainder(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		BigDecimal[] arr = b1.divideAndRemainder(b2);
		return arr;
	}

	/*** 求商（向下舍入）* @param v1* @param v2* @return */
	public static BigDecimal divideToIntegralValue(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		System.out.println("------------");
		return b1.divideToIntegralValue(b2);
	}
}