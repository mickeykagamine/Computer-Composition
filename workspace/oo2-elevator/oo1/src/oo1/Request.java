package oo1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request{
	private static int i;
	private static int p;
	private static boolean sign2 = false;
	private static Scanner s;
	private static double[] last = new double[10000];
	
	public static void main(String args[]){
		try{
			s = new Scanner(System.in);
			while(true){			
				String requests = s.nextLine();
				String request = requests.replace(" ","");
				if (FormCheck(request) == true){
					String request2 = request.replace("(","");
					String request3 = request2.replace(")","");
					String[] NewRequest = request3.split(",");
					
					if((Double.parseDouble(NewRequest[1]) == 0)
						|(Double.parseDouble(NewRequest[1]) > 10)
						|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == 1 && NewRequest[2].equals("DOWN"))
						|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == Floor.getfloormax() && NewRequest[2].equals("UP"))){
						if(NewRequest[0].equals("FR")){
							NewRequest[3]="0";
						}
						else if(NewRequest[0].equals("ER")){
							NewRequest[2]="0";
						}
						System.out.println("invalid input");
					}
					if(NewRequest[0].equals("FR")){
						getLast()[p++] = Double.parseDouble(NewRequest[3]);
					}
					else if(NewRequest[0].equals("ER")){
						getLast()[p++] = Double.parseDouble(NewRequest[2]);
					}
					
					if(!((Double.parseDouble(NewRequest[1]) == 0)
							|(Double.parseDouble(NewRequest[1]) > 10)
							|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == 1 && NewRequest[2].equals("DOWN"))
							|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == Floor.getfloormax() && NewRequest[2].equals("UP")))){
							if(NewRequest[0].equals("FR")){
							sign2 = Controller.judgefr(NewRequest[0],NewRequest[1],NewRequest[2],NewRequest[3]);
							i=Queue.insetfr(sign2, NewRequest[0], NewRequest[1], NewRequest[2], NewRequest[3]);
							if(sign2){
								Elevator.run(i-1);
							}
						}
						else if(NewRequest[0].equals("ER")){
							sign2 = Controller.judgeer(NewRequest[0],NewRequest[1],NewRequest[2]);
							i=Queue.inseter(sign2, NewRequest[0], NewRequest[1], NewRequest[2]);
							if(sign2){
								Elevator.run(i-1);
							}
							
						}
					}
				}
				else if(requests.equals("run")){
					break;
				}
				else {
					System.out.println("invalid input");
				}
			}
		}catch (Exception e){
			System.out.println("ERORR");
		}
	}
	static boolean FormCheck(String s){
		Pattern p0 = Pattern.compile("\\(FR,[0-9]{1,2},DOWN,[0-9]{1,5}\\)");
		Matcher match0 = p0.matcher(s);
		Pattern p1 = Pattern.compile("\\(FR,[0-9]{1,2},UP,[0-9]{1,5}\\)");
		Matcher match1 = p1.matcher(s);
	    Pattern p2 = Pattern.compile("\\(ER,[0-9]{1,2},[0-9]{1,5}\\)");
	    Matcher match2 = p2.matcher(s);
		Pattern p3 = Pattern.compile("\\(ER,[0-9]{1,2},[0-9]{1,5}\\)");
		Matcher match3 = p3.matcher(s);
		
		if(match0.matches()){
			return true;
		}
		else if(match1.matches()){
			return true;
		}
		else if(match2.matches()){
			return true;
		}
		else if(match3.matches()){
			return true;
		}
		else {
			return false;
		}	
		
	}
	public static double[] getLast() {
		return last;
	}
	public static void setLast(double[] last) {
		Request.last = last;
	}		
}

class Queue{
	private static String[][] queue = new String[10000][10000];
	private static int n = 0;
	static int insetfr(boolean sign,String s0,String s1,String s2,String s3){
		if(sign == true){
		getQueue()[n][0] = s0;
		getQueue()[n][1] = s1;
		getQueue()[n][2] = s2;
		getQueue()[n][3] = s3;
		n++;
		}
		return n;
	}
	static int inseter(boolean sign,String s0,String s1,String s2){
		if(sign == true){
		getQueue()[n][0] = s0;
		getQueue()[n][1] = s1;
		getQueue()[n][2] = s2;
		n++;
		}
		return n;
	}
	public static String[][] getQueue() {
		return queue;
	}
	public static void setQueue(String[][] queue) {
		Queue.queue = queue;
	}

}

class Controller{
	private static boolean sign=true;
	private static boolean first=true;
	private static double D;
	private static double sum = 0;
	static double Max(double[] last){
		double max = last[0];
		for(int h = 0;h < 10000;h++){
			if(max < last[h]){
				max = last[h];
			}
		}		
		return max;
	}	
	static double Sum(int m,String s1){
		sum=(Double.parseDouble(Queue.getQueue()[0][1])-1)*0.5+1;
		
		int j;				
		for(j=1;j<=m;j++){
			if(Double.parseDouble(Queue.getQueue()[j][1])-Double.parseDouble(Queue.getQueue()[j-1][1]) < 0){
				D = Double.parseDouble(Queue.getQueue()[j-1][1])-Double.parseDouble(Queue.getQueue()[j][1]);
			}
			else {
				D = Double.parseDouble(Queue.getQueue()[j][1])-Double.parseDouble(Queue.getQueue()[j-1][1]);
			}
			if(Queue.getQueue()[j][0].equals("FR")){
				if(sum>Double.parseDouble(Queue.getQueue()[j][3])){
					sum=sum+(D*0.5)+1;
				}
				if(sum<=Double.parseDouble(Queue.getQueue()[j][3])){
					sum=Double.parseDouble(Queue.getQueue()[j][3])+(D*0.5)+1;
				}
			}
			else if(Queue.getQueue()[j][0].equals("ER")){
				if(sum>Double.parseDouble(Queue.getQueue()[j][2])){
					sum=sum+(D*0.5)+1;
				}
				if(sum<=Double.parseDouble(Queue.getQueue()[j][2])){
					sum=Double.parseDouble(Queue.getQueue()[j][2])+(D*0.5)+1;
				}
			}
		}											
		return sum;
	}
	static boolean judgefr(String s0,String s1,String s2,String s3){
		sign = true;
		String s=s1;
		if(Double.parseDouble(s3) < Max(Request.getLast())&&Double.parseDouble(s3)!=0){
			sign = false;
			System.out.println("input error");
		}
		for(int m = 1000;m >0;m--){			
			if(s0.equals("FR")&&s0.equals(Queue.getQueue()[m][0])){		
				if(s0.equals(Queue.getQueue()[m][0])&&s1.equals(Queue.getQueue()[m][1])&&s2.equals(Queue.getQueue()[m][2])){
					if(Sum(m,s)>=Double.parseDouble(s3)){
						sign = false;
						System.out.println("repeated request");
					}
				}				
			}			
		}
		if(first&&Double.parseDouble(s3)!=0){
			sign = false;
			System.out.println("input error");
		}
		if(sign){
			first=false;
		}				
		return sign;
	}
	static boolean judgeer(String s0,String s1,String s2){
		String s=s1;
		sign = true;
		if(Double.parseDouble(s2) < Max(Request.getLast())&&Double.parseDouble(s2)!=0){
			sign = false;
				System.out.println("input error");
		}
		for(int m = 1000;m > 0;m--){
			if(s0.equals("ER")&&s0.equals(Queue.getQueue()[m][0])){
				if(s0.equals(Queue.getQueue()[m][0])&&s1.equals(Queue.getQueue()[m][1])){
					if(Sum(m,s)>=Double.parseDouble(s2)){						
						sign = false;						
						System.out.println("repeated request");
					}
				}
				
			}
		}
		if(first&&Double.parseDouble(s2)!=0){
			sign = false;
			System.out.println("input error");
		}
		if(sign){
			first=false;
		}				
		return sign;
	}
}


class Elevator{
	private static int floor=1;
	private static double t=0;
	private static double Delta;
	static void run(int i){
		if((Double.parseDouble(Queue.getQueue()[i][1])-floor)>=0){
			Delta=Double.parseDouble(Queue.getQueue()[i][1])-floor;
		}
		else if((Double.parseDouble(Queue.getQueue()[i][1])-floor)<0){
			Delta=floor-Double.parseDouble(Queue.getQueue()[i][1]);
		}
		if(Queue.getQueue()[i][0].equals("FR")){
			if(Double.parseDouble(Queue.getQueue()[i][3]) <= t){
				t=Delta*0.5+1+t;
			}
			else if(Double.parseDouble(Queue.getQueue()[i][3])>t){
				t=Delta*0.5+1+Double.parseDouble(Queue.getQueue()[i][3]);
			}
		}
		else if(Queue.getQueue()[i][0].equals("ER")){
			if(Double.parseDouble(Queue.getQueue()[i][2]) <= t){
				t=Delta*0.5+1+t;
			}
			else if(Double.parseDouble(Queue.getQueue()[i][2])>t){
				t=Delta*0.5+1+Double.parseDouble(Queue.getQueue()[i][2]);
			}
		}		
		if(Double.parseDouble(Queue.getQueue()[i][1])>floor){
			System.out.println("("+(int)(Double.parseDouble(Queue.getQueue()[i][1]))+",UP,"+(t-1)+")");
		}
		else if(Double.parseDouble(Queue.getQueue()[i][1])<floor){
			System.out.println("("+(int)(Double.parseDouble(Queue.getQueue()[i][1]))+",DOWN,"+(t-1)+")");
		}
		else if(Double.parseDouble(Queue.getQueue()[i][1]) == floor){
			System.out.println("("+(int)(Double.parseDouble(Queue.getQueue()[i][1]))+",STILL,"+t+")");
		}
		floor =(int)(Double.parseDouble(Queue.getQueue()[i][1]));
		
	}
}
class Floor{
	private static int floormax = 10;
	static int getfloormax(){
		return floormax;
	}
}
