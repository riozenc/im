package ims.common.security.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;

/**
 * 用户令牌工具
 * 
 * @author riozenc
 *
 */
public class UserTokenUtils {
	private static final String encryModel = "MD5";

	public static String createToken(String userId) {
		LocalTime localTime = LocalTime.now();

		MessageDigest md;
		try {
			md = MessageDigest.getInstance(encryModel);
			md.update(userId.getBytes());
			byte[] b1 = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b1.length; i++) {
				sb.append(Integer.toHexString(b1[i] & 0xFF));
			}
			md.reset();
			md.update(localTime.toString().getBytes());
			byte[] b2 = md.digest();
			for (int i = 0; i < b1.length; i++) {
				sb.append(Integer.toHexString(b2[i] & 0xFF));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {

		System.out.println(UserTokenUtils.createToken("czy"));
		System.out.println(UserTokenUtils.createToken("tff"));
	}
}
