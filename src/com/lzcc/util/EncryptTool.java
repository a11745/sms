package com.lzcc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EncryptTool {
	public static String SHADigest(String input) {
		return encrypt("SHA-1", input.getBytes());
	}

	/**
	 * 采用MD5对输入数据进行摘要算法。
	 * 
	 * @param input
	 *            要加密的数据
	 * @return
	 */
	public static String MD5(String input) {
		return encrypt("MD5", input.getBytes());
	}

	private static String encrypt(String algorithm, byte[] input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(algorithm + "： 该加密算法不可用!");
		}

		md.update(input);
		byte[] buf = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			int v = buf[i] & 0xFF;
			if (v < 16)
				sb.append("0");
			sb.append(Integer.toHexString(v).toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 针对动网论坛用户密码的MD5加密，截取加密字符串的中间16位
	 * 
	 * @param input
	 * @return
	 */
	public static String legacyMD5(String input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		md.update(input.getBytes());
		byte[] buf = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			int v = buf[i] & 0xFF;
			if (v < 16)
				sb.append("0");
			sb.append(Integer.toHexString(v).toUpperCase());
		}
		return sb.toString().substring(8, 24).toLowerCase();
	}
}
