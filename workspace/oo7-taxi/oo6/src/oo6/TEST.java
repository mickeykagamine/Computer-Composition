package oo6;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TEST extends Thread{
	 int i;
	 
	 public void test(String ss){
		
			 	Pattern p0 = Pattern.compile("taxi [0-9]{1,3}");
				//                          
				Matcher match0 = p0.matcher(ss);
				
				if(match0.matches()){
					String[] sss =ss.split(" ");
					int num=Integer.parseInt(sss[1]);
					for(int j=0;j<Taxi.taxinumber;j++){
						if(j==num){
							System.out.println(System.currentTimeMillis()+":("+Taxi.TAXILIST[j].spot.X+","+Taxi.TAXILIST[j].spot.Y+")"+Taxi.TAXILIST[j].state);
						}
					}

				}
				else {
					Pattern p1 = Pattern.compile("waiting|pickingup|stop|heading");
					Matcher match1 = p1.matcher(ss);
					if(match1.matches()){
						System.out.println(ss+":\n");
							for(int j=0;j<Taxi.taxinumber;j++){
								if(Taxi.TAXILIST[j].state.equals(ss)){
									System.out.println(Taxi.TAXILIST[j].num+" ");
								}
							}
						
					}
					else{
						System.out.println("input invalid");
					}
				}	
		 }
	 
}
