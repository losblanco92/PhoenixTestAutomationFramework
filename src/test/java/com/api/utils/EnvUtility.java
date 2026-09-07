package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtility {

	private static Dotenv dotenv;

	static {

		dotenv = Dotenv.load();
	}

	private EnvUtility() {

	}

	public static String loadVariable(String var)

	{

		return dotenv.get(var);
	}

}
