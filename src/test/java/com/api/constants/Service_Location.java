package com.api.constants;

public enum Service_Location {

	SERVICE_CENTRE_A(1),
	SERVICE_CENTRE_B(2),
	SERVICE_CENTRE_C(3);

	int code;

	Service_Location(int code) {
		this.code = code;

	}

	public int getCode() {
		return code;

	}

}
