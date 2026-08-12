package com.api.constants;

public enum Products {

	NEXUS_2(1), PIXEL(2);

	int code;

	 Products(int code) {

		this.code = code;

	}

	public int getCode() {
		return code;
	}

}
