package com.vc.jj.util;

public class LongUtil {
	  public static boolean isValid(Long param){
		  if(param == null || param <= 0){
			  return false;
		  }
		  return true;
	  }
}
