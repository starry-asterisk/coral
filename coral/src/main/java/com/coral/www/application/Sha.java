/* 
 * Sha.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www.application;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
public class Sha {
	/* sha 암호화를 제공하는 메소드 */
	
	/**
	 * sha-256 암호화 + 솔팅처리
	 * 
	 * @param input
	 * @param salt
	 * @return
	 */
	public static String get256(String input, String salt) {
		return get256(input + salt);
	}

	/**
	 * 기본 sha-256 암호화
	 * 
	 * @param input
	 * @return
	 */
	public static String get256(String input) {

		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
	}

	/**
	 * sha-512 방식 + 솔팅처리
	 * 
	 * @param input
	 * @param salt
	 * @return
	 */
	public static String get512(String input, String salt) {
		return get512(input + salt);
	}

	/**
	 * 기본 sha-512 암호화
	 * 
	 * @param input
	 * @return
	 */
	public static String get512(String input) {

		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
	}
}