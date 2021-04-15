package com.deeconsulting.desafiostoom.data.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	public static String decode(String texto) {
		try {
			return URLDecoder.decode(texto, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			return "";
		}
	}
	
	
}
