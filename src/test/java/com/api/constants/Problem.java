package com.api.constants;

public enum Problem {

	    SMARTPHONE_IS_RUNNING_SLOW(1),
	    POOR_BATTERY_LIFE(2),
	    PHONE_OR_APP_CRASHES(3),
	    SYNC_ISSUE(4),
	    MICRO_SD_CARD_IS_NOT_WORKING_ON_YOUR_PHONE(5),
	    OVERHEATING(6),
	    CONNECTING_PROBLEM_WITH_BLUETOOTH_WIFI(7),
	    CRACKED_SCREEN(8),
	    OTHER(9),
	    CAMERA_ISSUE(10),
	    CHARGER_NOT_WORKING(11),
	    HEAD_PHONE_JACK_NOT_WORKING(12),
	    HEAD_PHONE_ISSUE(13),
	    RANDOM_PROBLEM(14),
	    FRONT_CAMERA_NOT_WORKING(15),
	    BATTERY_ISSUE(16),
	    SCREEN_DISPLAY_ISSUE(17),
	    APPS_NOT_DOWNLOADING(18),
	    UNRESPONSIVE_SCREEN(19),
	    BLUE_SCREEN_ERROR(20),
	    PK_TEST_PROB1(21);
	  
	    

	    int code;

	    private Problem(int code) {
	        this.code = code;
	    }

	    public int getCode() {
	        return code;
	    }
	
}
