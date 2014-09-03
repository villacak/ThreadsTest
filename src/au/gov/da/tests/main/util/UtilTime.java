package au.gov.da.tests.main.util;

import java.util.Date;

public class UtilTime {

	public static float getDifferenceInMills(Date start, Date end) {
		long startMills = start.getTime();
		long endMills = end.getTime();
		return endMills - startMills;
	}

	public static float getDifferenceInSeconds(Date start, Date end) {
		long startMills = start.getTime();
		long endMills = end.getTime();
		return (float) (endMills - startMills) / 1000;
	}
}
