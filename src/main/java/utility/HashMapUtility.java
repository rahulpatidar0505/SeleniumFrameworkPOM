package utility;

import java.util.HashMap;

/*Some good Example of HashMap in Selenium:
1. HashMap of Days (Monday to Sunday)
2. HashMap of Months (Jan to Dec)
3. HashMap of Users (admin, customer, etc..)
4. HashMap of cities code in any flight booking site*/

public class HashMapUtility {

	public HashMap<String,String> getUserLoginInfo()
	{
		HashMap<String, String> userMap=new HashMap<String,String>();
		userMap.put("customer", "rahulgpatidar_Rahualgoru@123");
		userMap.put("admin", "admin_test@123");
		return userMap;
	}
	
	public HashMap<Integer,String> monthMap()
	{
		HashMap<Integer, String> monthMap=new HashMap<Integer,String>();
		monthMap.put(1, "January");
		monthMap.put(2, "February");
		monthMap.put(3, "March");
		monthMap.put(4, "April");
		monthMap.put(5, "May");
		monthMap.put(6, "June");
		monthMap.put(7, "July");
		monthMap.put(8, "August");
		monthMap.put(9, "September");
		monthMap.put(10, "October");
		monthMap.put(11, "November");
		monthMap.put(12, "December");
		return monthMap;
	}
	
	public HashMap<Integer,String> dayMap()
	{
		HashMap<Integer, String> dayMap=new HashMap<Integer,String>();
		dayMap.put(1, "Monday");
		dayMap.put(1, "Tuesday");
		dayMap.put(1, "Wednesday");
		dayMap.put(1, "Thursday");
		dayMap.put(1, "Friday");
		dayMap.put(1, "Saturday");
		dayMap.put(1, "Sunday");
		return dayMap;
	}
	
}
