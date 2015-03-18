package com.example.calendardemo.bean;

import java.io.Serializable;

public class CalendarBean implements Serializable {
	/**一年中哪一月份*/
	public int month_of_year;
	/**一月中哪一周*/
	public int week_of_month;
	/**一年中的第几周*/
	public int week_of_year;
	/**year*/
	public int year;
	/**一月中的几号*/
	public int day_of_month;
	/**一周中的第几天*/
	public int day_of_week;
	/** 上月 */
	public boolean isUpMonth;
	/** 下月 */
	public boolean isNextMonth;
	/** 当前月 */
	public boolean isCurrentMonth;
	/**是今天*/
	public boolean isToday;
	@Override
	public String toString() {
		return "CalendarBean [month_of_year=" + month_of_year
				+ ", week_of_month=" + week_of_month + ", week_of_year="
				+ week_of_year + ", year=" + year + ", day_of_month="
				+ day_of_month + ", day_of_week=" + day_of_week
				+ ", isUpMonth=" + isUpMonth + ", isNextMonth=" + isNextMonth
				+ ", isCurrentMonth=" + isCurrentMonth + ", isToday=" + isToday
				+ "]";
	}
}
