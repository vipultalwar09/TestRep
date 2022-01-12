package com.utility.logger;

import org.testng.Assert;
import org.testng.Reporter;

public class Logger {
	public static boolean flag = true;

	public static void log(String message) {
		if (flag) {
			Reporter.log(message, true);
		}

	}

	public static void log(int message) {
		if (flag) {
			Reporter.log(message + "", true);

		}

	}

	public static void log(boolean message) {
		if (flag) {
			Reporter.log(message + "", true);

		}

	}

	public static void log(double message) {
		if (flag) {
			Reporter.log(message + "", true);

		}

	}

	public static void log(int[] message) {
		if (flag) {
			for (int i : message) {
				Reporter.log(i + "", true);

			}

		}

	}

	public static void log(double[] message) {
		if (flag) {
			for (double d : message) {
				Reporter.log(d + "", true);

			}

		}

	}

	public static void log(String[] message) {
		if (flag) {
			for (String s : message) {
				Reporter.log(s + "", true);

			}

		}

	}

	public static void fail(String message) {
		log(message);
		Assert.fail(message);
	}
}