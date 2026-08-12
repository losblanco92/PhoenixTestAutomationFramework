package com.api.constants;

public enum Platform {
	
	FRONT_DESK(2);
	
   int code;
	
	Platform (int code)
	{
		this.code= code;
		
	}
	
	public int getCode()
	{
		return code;
		
	}
	

}
