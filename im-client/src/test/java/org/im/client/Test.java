package org.im.client;

public class Test {
	public static void main(String[] args) {
		String s = "-2 -2 -2 1 0 0 6 -121 0 0 3 -24 0 0 0 49 60 114 101 103 105 115 116 101 114 98 101 97 110 62 60 117 115 101 114 73 100 62 54 56 54 60 47 117 115 101 114 73 100 62 60 47 114 101 103 105 115 116 101 114 98 101 97 110 62 17 10 27 15 3 51 0 0 0 0 22";
		String[] ss = s.split(" ");

		StringBuilder builder = new StringBuilder();

		for (String temp : ss) {
			temp = Integer.toHexString(Byte.parseByte(temp) & 0xFF);
			builder.append(" ").append(temp.length()>1?temp:"0"+temp);
		}
		System.out.println(builder);
		
	}
}
