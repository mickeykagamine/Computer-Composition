package oo4;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class input extends Thread{
	static boolean first = true;
	private static long DELTA;
	static boolean flag = false;
	public void run(){
		DecimalFormat A = new DecimalFormat("0.0");
		long time;
		int i;
		boolean sign;
		Scanner s = new Scanner(System.in);
		while(true){	
			String requests = s.nextLine();
			time = System.currentTimeMillis();
			if(first){
				setDELTA(time-TEST.getT());	
				first=false;
			}
			
			String requestNEW = requests.replace(" ","");
			if(requests.equals("end")){
				System.out.println("complete");
				flag = true;
				break;
			}
			String[] request =requestNEW.split(";");
			for(i=0;i<request.length;i++){
				if (FormCheck(request[i]) == true){
					System.out.println("Formcheck ok:"+request[i]);
				
					String request2 = request[i].replace("(","");
					String request3 = request2.replace(")","");
					request3 = request3.replace("#", "");
					String[] NewRequest = request3.split(","); 
			//		NewRequest[3]=time-Scheduler.getT()+"";
					
					sign = true;
					if((Double.parseDouble(NewRequest[1]) == 0)
						|(Double.parseDouble(NewRequest[1]) > Floor.getfloormax())
						|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == 1 && NewRequest[2].equals("DOWN"))
						|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == Floor.getfloormax() && NewRequest[2].equals("UP"))){
						System.out.println(System.currentTimeMillis()+":INVALID[("+request3+","+A.format((time-TEST.getT()-getDELTA())*0.001)+")]");
						String sss=System.currentTimeMillis()+":INVALID[("+request3+","+A.format((time-TEST.getT()-getDELTA())*0.001)+")]";					
						FileWrite.tofile(sss);
						sign=false;

					}
					
					else{
						long[] NewRequest2=new long[1000];
						NewRequest2[0] = NewRequest[0].equals("FR")?1:2;
						NewRequest2[1] = Long.parseLong(NewRequest[1]);
						NewRequest2[2] = NewRequest[0].equals("UP")?1:2;
						NewRequest2[3] = time-TEST.getT();
						
						for(int j=0;j<Queue.getN();j++){
							if (Queue.getQueue()[j][0]==NewRequest2[0]&&Queue.getQueue()[j][1]==NewRequest2[1]&&Queue.getQueue()[j][2]==NewRequest2[2]&&Queue.getQueue()[j][3]==NewRequest2[3]){
								System.out.println(System.currentTimeMillis()+":SAME[("+Queue.getQueue()[j][0]+","+Queue.getQueue()[j][1]+","+Queue.getQueue()[j][2]+","+Queue.getQueue()[j][3]+")]");
								String sss=System.currentTimeMillis()+":SAME[("+Queue.getQueue()[j][0]+","+Queue.getQueue()[j][1]+","+Queue.getQueue()[j][2]+","+Queue.getQueue()[j][3]+")]";					
								FileWrite.tofile(sss);
								sign = false;

							}
						}
						
					}	
					
					if(sign==true){					
						
						if(NewRequest[0].equals("ER")){
							Queue.insert(2,Integer.parseInt(NewRequest[2]),0,Integer.parseInt(NewRequest[1]),(time-TEST.getT()));
							System.out.println("put:("+2+","+Integer.parseInt(NewRequest[2])+","+0+","+Integer.parseInt(NewRequest[1])+","+(time-TEST.getT())+")into the queue");	           	         
						    
		
						}
						if(NewRequest[0].equals("FR")){
							if(NewRequest[2].equals("UP")){
								Queue.insert(1,Integer.parseInt(NewRequest[1]),1,0,(time-TEST.getT()));
								
								System.out.println("put:("+1+","+Integer.parseInt(NewRequest[1])+","+1+","+0+","+(time-TEST.getT())+")into the queue");	

							}
							if(NewRequest[2].equals("DOWN")){
								Queue.insert(1,Integer.parseInt(NewRequest[1]),2,0,(time-TEST.getT()));
		//						System.out.println("put:("+1+","+Integer.parseInt(NewRequest[1])+","+2+","+0+","+(time-Scheduler.getT())+")into the queue");
	
							}
						}
					}
					
				}
				else {
					System.out.println(System.currentTimeMillis()+":INVALID["+request[i]+","+A.format((time-TEST.getT()-getDELTA())*0.001)+"]");
					String sss=System.currentTimeMillis()+":INVALID["+request[i]+","+A.format((time-TEST.getT()-getDELTA())*0.001)+"]";					
					FileWrite.tofile(sss);
				}
			}				
				
		}
		
	}
	
	static boolean FormCheck(String s){
		Pattern p0 = Pattern.compile("\\(FR,[+]?[0-9]{1,2},DOWN\\)");
		Matcher match0 = p0.matcher(s);
		Pattern p1 = Pattern.compile("\\(FR,[+]?[0-9]{1,2},UP\\)");
		Matcher match1 = p1.matcher(s);
	    Pattern p2 = Pattern.compile("\\(ER,#[1-3]{1},[+]?[0-9]{1,2}\\)");
	    //#1的表达方式？？？
	    Matcher match2 = p2.matcher(s);

		
		if(match0.matches()){
			return true;
		}
		else if(match1.matches()){
			return true;
		}
		else if(match2.matches()){
			return true;
		}
		else {
			return false;
		}	
		
	}
	public static long getDELTA() {
		return DELTA;
	}

	public static void setDELTA(long dELTA) {
		DELTA = dELTA;
	}

	
}
