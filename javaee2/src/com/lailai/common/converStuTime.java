package com.lailai.common;

public class converStuTime {
	public static String converTime(String oldTime){
		String[] timeArr = oldTime.split("-");
		String timeShow="";
		switch (timeArr[0]) {
			case "1":
				timeShow = "星期一的";
				break;
			case "2":
				timeShow = "星期二的";
				break;
			case "3":
				timeShow = "星期三的";
				break;
			case "4":
				timeShow = "星期四的";
				break;
			case "5":
				timeShow = "星期五的";
				break;
			case "6":
				timeShow = "星期六的";
				break;
			case "7":
				timeShow = "星期天的";
				break;
			}
			switch (timeArr[1]) {
			case "1":
				timeShow += "8:30~9:30 ";
				break;
			case "2":
				timeShow += "10:00~11:00 ";
				break;
			case "3":
				timeShow += "14:30~15:30 ";
				break;
			case "4":
				timeShow += "16:00~17:00 ";
				break;
			}
		return timeShow;
	}

}
