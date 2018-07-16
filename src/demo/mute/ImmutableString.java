package demo.mute;

import java.time.LocalTime;

public class ImmutableString {

	public static void main(String[] args) {


		StringBuilder strB = new StringBuilder();
		String str = new String();

		long timeDifference = LocalTime.now().toNanoOfDay();
		//StringBuilder
		for(int i=0;i<190000; i++) {
			strB.append(i);
		}
		
		timeDifference = LocalTime.now().toNanoOfDay() - timeDifference;
		//System.out.println("StringBuilder : "+strB.toString()+"\n took time "+timeDifference);
		System.out.println("StringBuilder : \n took time "+timeDifference);

		long strBtime = timeDifference;
		timeDifference = LocalTime.now().toNanoOfDay();
		//String
		for(int i=0;i<190000; i++) {
			str+=i;
		}
		
		timeDifference = LocalTime.now().toNanoOfDay() - timeDifference;
		//System.out.println("String : "+str+"\n took time "+timeDifference);
		System.out.println("String : \n took time "+timeDifference);
		long strtime = timeDifference;
		
		System.out.println("Time difference for the same string between String+ and StringBuilder.append is "+(strtime-strBtime)/10E9+" seconds");
	}
	
	/*
SAMPLE OUTPUT:

StringBuilder : 
 took time 46910900
String : 
 took time 36065985000
Time difference for the same string between String+ and StringBuilder.append is 3.60190741 seconds

SO DO NOT USE STRING IN LOGGERS FOR CRITICAL APPLICATIONS
*/

}
