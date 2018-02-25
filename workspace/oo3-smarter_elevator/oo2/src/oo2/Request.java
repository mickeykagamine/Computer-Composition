package oo2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request{
	private static int i;
	private static int p;
	private static boolean sign2 = false;
	private static Scanner s;
	private static double[] last = new double[10000];
	private static double summ;
	
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
						|(Double.parseDouble(NewRequest[1]) > Floor.getfloormax())
						|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == 1 && NewRequest[2].equals("DOWN"))
						|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == Floor.getfloormax() && NewRequest[2].equals("UP"))){
						if(NewRequest[0].equals("FR")){
							NewRequest[3]="0";
						}
						else if(NewRequest[0].equals("ER")){
							NewRequest[2]="0";
						}
						System.out.println("INVALID[("+request3+")]");
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
							sign2 = SmarterController.judgefr(NewRequest[0],NewRequest[1],NewRequest[2],NewRequest[3]);
							i=Queue.insetfr(sign2 , NewRequest[0], NewRequest[1], NewRequest[2], NewRequest[3]);
							/*
							if(sign2){
								Elevator.run(i-1);
							}
							*/
						}
						else if(NewRequest[0].equals("ER")){
							sign2 =  SmarterController.judgeer(NewRequest[0],NewRequest[1],NewRequest[2]);
							i=Queue.inseter(sign2, NewRequest[0], NewRequest[1], NewRequest[2]);
							/*
							if(sign2){
								Elevator.run(i-1);
							}
							
							*/
						}
					}
				}
				else if(requests.equals("run")){
					break;
				}
				else {
					System.out.println("INVALID["+requests+"]");
				}
			}
			NewElevator.run();
			System.out.println("completed");
			
		}catch (Exception e){
			System.out.println("ERORR");
		}
	}
	static boolean FormCheck(String s){
		Pattern p0 = Pattern.compile("\\(FR,[+]?[0-9]{1,2},DOWN,[+]?[0-9]{1,31}\\)");
		Matcher match0 = p0.matcher(s);
		Pattern p1 = Pattern.compile("\\(FR,[+]?[0-9]{1,2},UP,[+]?[0-9]{1,31}\\)");
		Matcher match1 = p1.matcher(s);
	    Pattern p2 = Pattern.compile("\\(ER,[+]?[0-9]{1,2},[+]?[0-9]{1,31}\\)");
	    Matcher match2 = p2.matcher(s);
		Pattern p3 = Pattern.compile("\\(ER,[+]?[0-9]{1,2},[+]?[0-9]{1,31}\\)");
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
	public static double getSumm() {
		return summ;
	}
	public static void setSumm(double summ) {
		Request.summ = summ;
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
	static int findj(int m,int max,String[][] Queue){
		int j;
		int sign3 = 0;
		for(j=m;j<max+1;j++){
			if (Queue[j][0]!=null){
				sign3=1;
				break;
			}
			else {
				continue;
			}			
		}
		if(sign3==1){
			return j;
		}
		else return -1;
	}
	static double Sum(int m,String[][] Queue ){
		int j1 = 0;	
		int j2 = 0;
		j1=findj(0,m,Queue);
			
		if(Double.parseDouble(Queue[j1][1])-NewElevator.getFloor() < 0){
			D = NewElevator.getFloor()-Double.parseDouble(Queue[j1][1]);
		}
		else {
			D = Double.parseDouble(Queue[j1][1])-NewElevator.getFloor();
		}
		if(Queue[j1][0].equals("FR")){
			sum = D*0.5+1+Double.parseDouble(Queue[j1][3]);
		}
		else if(Queue[j1][0].equals("ER")){
			sum = D*0.5+1+Double.parseDouble(Queue[j1][2]);
		}
		
		
		for(j2=findj(j1+1,m,Queue);j2<=m;j2=findj(j2+1,m,Queue),j1=j2){
			if(j2==-1){
				break;
			}
			if(Double.parseDouble(Queue[j2][1])-Double.parseDouble(Queue[j1][1]) < 0){
				D = Double.parseDouble(Queue[j1][1])-Double.parseDouble(Queue[j2][1]);
			}
			else {
				D = Double.parseDouble(Queue[j2][1])-Double.parseDouble(Queue[j1][1]);
			}
			if(Queue[j2][0].equals("FR")){
				if(sum>Double.parseDouble(Queue[j2][3])){
					sum=sum+(D*0.5)+1;
				}
				if(sum<=Double.parseDouble(Queue[j2][3])){
					sum=Double.parseDouble(Queue[j2][3])+(D*0.5)+1;
				}
			}
			else if(Queue[j2][0].equals("ER")){
				if(sum>Double.parseDouble(Queue[j2][2])){
					sum=sum+(D*0.5)+1;
				}
				if(sum<=Double.parseDouble(Queue[j2][2])){
					sum=Double.parseDouble(Queue[j2][2])+(D*0.5)+1;
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
			System.out.println("INVALID[("+s0+","+s1+","+s2+","+s3+")]");
		}
		/*
		for(int m = 1000;m >= 0;m--){			
			if(s0.equals("FR")&&s0.equals(Queue.getQueue()[m][0])){		
				if(s0.equals(Queue.getQueue()[m][0])&&s1.equals(Queue.getQueue()[m][1])&&s2.equals(Queue.getQueue()[m][2])){
					if(Sum(m,s)>=Double.parseDouble(s3)){
						sign = false;
						System.out.println("repeated request");
					}
				}				
			}			
		}
		*/
		if(first&&!(Double.parseDouble(s1)==1&&s2.equals("UP")&&Double.parseDouble(s3)==0)){
			sign = false;
			System.out.println("INVALID[("+s0+","+s1+","+s2+","+s3+")]");
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
				System.out.println("INVALID[("+s0+","+s1+","+s2+")]");
		}
		/*
		for(int m = 1000;m >= 0;m--){
			if(s0.equals("ER")&&s0.equals(Queue.getQueue()[m][0])){
				if(s0.equals(Queue.getQueue()[m][0])&&s1.equals(Queue.getQueue()[m][1])){
					if(Sum(m,s)>=Double.parseDouble(s2)){						
						sign = false;						
						System.out.println("repeated request");
					}
				}
				
			}
		}
		*/
		if(first){
			sign = false;
			System.out.println("INVALID[("+s0+","+s1+","+s2+")]");
		}
		if(sign){
			first=false;
		}				
		return sign;
	}
}

class SmarterController extends Controller{
	
}
class Elevator{
	private static int floor=1;
	private static String direction;
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
			setDirection("UP");
			System.out.println("("+(int)(Double.parseDouble(Queue.getQueue()[i][1]))+",UP,"+(t-1)+")");
		}
		else if(Double.parseDouble(Queue.getQueue()[i][1])<floor){
			setDirection("DOWN");
			System.out.println("("+(int)(Double.parseDouble(Queue.getQueue()[i][1]))+",DOWN,"+(t-1)+")");
		}
		else if(Double.parseDouble(Queue.getQueue()[i][1]) == floor){
			setDirection("STLL");
			System.out.println("("+(int)(Double.parseDouble(Queue.getQueue()[i][1]))+",STILL,"+t+")");
		}
		floor =(int)(Double.parseDouble(Queue.getQueue()[i][1]));
		
	}
	public static String getDirection() {
		return direction;
	}
	public static void setDirection(String direction) {
		Elevator.direction = direction;
	}
}

class NewElevator{
	private static int floor;
	private static String direction;
	private static double headingfloor;
	private static String[][] Waitinglist = new String[1000][1000];
	static int run(){
		setFloor(1);
		direction = "NULL";
		headingfloor = 0;
		int i = 0;
		int m = 0;
		int x = 0;
		int counting = 0;
		int lastt = 0;
		double t = 0;
		boolean sign = true;
		boolean running = false;
		boolean[] first = new boolean[1000];
		int[] last = new int[1000];
		int[] Stoplist = new int [1000];
		int h = 0;		
		for(i=0;i<1000;i++){
			first[i]=true;
			last[i]=0;
			Stoplist[i]=0;
		}
		while(true){
			for(i=0;i<1000;i++){
				if(last[i]==0){
					lastt=i;
					break;
				}
			}
			
			for(m = lastt;m < 1000;m++){ 
				sign = true;
				
				if(Queue.getQueue()[m][0]==null){
					m=Controller.findj(m,1000,Queue.getQueue());
					if(m==-1){
						break;
					}
				}
				
				
				///////FR
				if (Queue.getQueue()[m][0].equals("FR")){
					if(Double.parseDouble(Queue.getQueue()[m][3]) <= t){
						//add the main request
						if(!running){
							if (Double.parseDouble(Queue.getQueue()[m][1]) == getFloor()){							
									direction="STLL";
									t++;
								
									System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+","+Queue.getQueue()[m][3]+")]/("+getFloor()+","+direction+","+t+")");
									Stoplist[h++]=floor;
									Queue.getQueue()[m][0] = null;
									Queue.getQueue()[m][1] = null;
									Queue.getQueue()[m][2] = null;
									Queue.getQueue()[m][3] = null;
									last[m] = 1;
								
							}
							else if (Double.parseDouble(Queue.getQueue()[m][1]) < getFloor()){
								direction = "DOWN";
								running = true;
								headingfloor = Double.parseDouble(Queue.getQueue()[m][1]);
								Waitinglist[x][0]=Queue.getQueue()[m][0];
								Waitinglist[x][1]=Queue.getQueue()[m][1];
								Waitinglist[x][2]=Queue.getQueue()[m][2];
								Waitinglist[x++][3]=Queue.getQueue()[m][3];
								counting++;
								Queue.getQueue()[m][0] = null;
								Queue.getQueue()[m][1] = null;
								Queue.getQueue()[m][2] = null;
								Queue.getQueue()[m][3] = null;
								last[m] = 1;
							//	break;
							}
							else if (Double.parseDouble(Queue.getQueue()[m][1]) > getFloor()){
								direction="UP";
								running = true;
								headingfloor = Double.parseDouble(Queue.getQueue()[m][1]);
								Waitinglist[x][0]=Queue.getQueue()[m][0];
								Waitinglist[x][1]=Queue.getQueue()[m][1];
								Waitinglist[x][2]=Queue.getQueue()[m][2];
								Waitinglist[x++][3]=Queue.getQueue()[m][3];
								counting++;
								Queue.getQueue()[m][0] = null;
								Queue.getQueue()[m][1] = null;
								Queue.getQueue()[m][2] = null;
								Queue.getQueue()[m][3] = null;
								last[m] = 1;
							//	break;
								
							}
						}
						//add other requests besides main request
						else if (running){
							/////UP
							if(Queue.getQueue()[m][2].equals(direction)&&direction.equals("UP")){
								if(getFloor() < Double.parseDouble(Queue.getQueue()[m][1])&&Double.parseDouble(Queue.getQueue()[m][1]) <= headingfloor){
									for(int n = 999;n >= 0;n--){			
												
											if(Queue.getQueue()[m][0].equals(Waitinglist[n][0])&&Queue.getQueue()[m][1].equals(Waitinglist[n][1])&&Queue.getQueue()[m][2].equals(Waitinglist[n][2])){
												if( SmarterController.Sum(n,Waitinglist)>=Double.parseDouble(Queue.getQueue()[m][3])){
													sign = false ;
													System.out.println("SAME[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+","+Queue.getQueue()[m][3]+")]");
													Queue.getQueue()[m][0] = null;
													Queue.getQueue()[m][1] = null;
													Queue.getQueue()[m][2] = null;
													Queue.getQueue()[m][3] = null;
													last[m] = 1;
													break;
												}
												else {
													break;
												}
											}				
													
									}	
									if(sign){
										Waitinglist[x][0]=Queue.getQueue()[m][0];
										Waitinglist[x][1]=Queue.getQueue()[m][1];
										Waitinglist[x][2]=Queue.getQueue()[m][2];
										Waitinglist[x++][3]=Queue.getQueue()[m][3];
										counting++;
										Queue.getQueue()[m][0] = null;
										Queue.getQueue()[m][1] = null;
										Queue.getQueue()[m][2] = null;
										Queue.getQueue()[m][3] = null;
										last[m] = 1;
									}
								}
								else if((getFloor() == Double.parseDouble(Queue.getQueue()[m][1])) && (Double.parseDouble(Queue.getQueue()[m][3]) < (t-1)) ){
									if(Stoplist[h-1]==floor){
										System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+","+Queue.getQueue()[m][3]+")]/("+getFloor()+",STILL,"+(t+1)+")");
										Stoplist[h++]=floor;
									}
									else{
										System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+","+Queue.getQueue()[m][3]+")]/("+getFloor()+","+direction+","+t+")");
										Stoplist[h++]=floor;
									}
									t++;
									Queue.getQueue()[m][0] = null;
									Queue.getQueue()[m][1] = null;
									Queue.getQueue()[m][2] = null;
									Queue.getQueue()[m][3] = null;
									last[m] = 1;
								}							
							}
							////DOWN
							else if(Queue.getQueue()[m][2].equals(direction)&&direction.equals("DOWN")){
							if((getFloor() > Double.parseDouble(Queue.getQueue()[m][1]))&&(Double.parseDouble(Queue.getQueue()[m][1]) >= headingfloor)){
									for(int n = 999;n >= 0;n--){			
												
											if(Queue.getQueue()[m][0].equals(Waitinglist[n][0])&&Queue.getQueue()[m][1].equals(Waitinglist[n][1])&&Queue.getQueue()[m][2].equals(Waitinglist[n][2])){
												if( SmarterController.Sum(n,Waitinglist)>=Double.parseDouble(Queue.getQueue()[m][3])){
													System.out.println("SAME[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+","+Queue.getQueue()[m][3]+")]");
													sign = false;
													Queue.getQueue()[m][0] = null;
													Queue.getQueue()[m][1] = null;
													Queue.getQueue()[m][2] = null;
													Queue.getQueue()[m][3] = null;
													last[m] = 1;	
													break;
												}
												else {
													break;
												}
											}				
													
									}
									
									if(sign){
										Waitinglist[x][0]=Queue.getQueue()[m][0];
										Waitinglist[x][1]=Queue.getQueue()[m][1];
										Waitinglist[x][2]=Queue.getQueue()[m][2];
										Waitinglist[x++][3]=Queue.getQueue()[m][3];
										counting++;
										Queue.getQueue()[m][0] = null;
										Queue.getQueue()[m][1] = null;
										Queue.getQueue()[m][2] = null;
										Queue.getQueue()[m][3] = null;
										last[m] = 1;
									}
								}
								else if((getFloor() == Double.parseDouble(Queue.getQueue()[m][1])) && (Double.parseDouble(Queue.getQueue()[m][3]) < (t-1)) ){
									if(Stoplist[h-1]==floor){
										System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+","+Queue.getQueue()[m][3]+")]/("+getFloor()+",STILL,"+(t+1)+")");
										Stoplist[h++]=floor;
									}
									else{
										System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+","+Queue.getQueue()[m][3]+")]/("+getFloor()+","+direction+","+t+")");
										Stoplist[h++]=floor;
									}
									t++;
									Queue.getQueue()[m][0] = null;
									Queue.getQueue()[m][1] = null;
									Queue.getQueue()[m][2] = null;
									Queue.getQueue()[m][3] = null;
									last[m] = 1;
								}							
							}
						}
					}
					else {
						break;
					}
				}
				////////ER
				else if (Queue.getQueue()[m][0].equals("ER")){
					if(Double.parseDouble(Queue.getQueue()[m][2]) <= t){
						//add the main request
						if(!running){
							if (Double.parseDouble(Queue.getQueue()[m][1]) == getFloor()){							
									direction="STLL";
									t++;
								
									System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+")]/("+getFloor()+","+direction+","+t+")");
									Stoplist[h++]=floor;
									Queue.getQueue()[m][0] = null;
									Queue.getQueue()[m][1] = null;
									Queue.getQueue()[m][2] = null;
									last[m] = 1;
								
							}
							else if (Double.parseDouble(Queue.getQueue()[m][1]) < getFloor()){
								direction = "DOWN";
								running = true;
								headingfloor = Double.parseDouble(Queue.getQueue()[m][1]);
								Waitinglist[x][0]=Queue.getQueue()[m][0];
								Waitinglist[x][1]=Queue.getQueue()[m][1];
								Waitinglist[x++][2]=Queue.getQueue()[m][2];
								counting++;
								Queue.getQueue()[m][0] = null;
								Queue.getQueue()[m][1] = null;
								Queue.getQueue()[m][2] = null;
								last[m] = 1;
							//	break;
							}
							else if (Double.parseDouble(Queue.getQueue()[m][1]) > getFloor()){
								direction="UP";
								running = true;
								headingfloor = Double.parseDouble(Queue.getQueue()[m][1]);
								Waitinglist[x][0]=Queue.getQueue()[m][0];
								Waitinglist[x][1]=Queue.getQueue()[m][1];
								Waitinglist[x++][2]=Queue.getQueue()[m][2];
								counting++;
								Queue.getQueue()[m][0] = null;
								Queue.getQueue()[m][1] = null;
								Queue.getQueue()[m][2] = null;
								last[m] = 1;
							//	break;
								
							}
						}
						//add other requests besides main request
						else if (running){
							if(direction.equals("UP")){
								if(getFloor() < Double.parseDouble(Queue.getQueue()[m][1])){
									if(Double.parseDouble(Queue.getQueue()[m][1])>headingfloor){
										headingfloor=Double.parseDouble(Queue.getQueue()[m][1]);
									}
									for(int n = 999;n >= 0;n--){			
									
											if(Queue.getQueue()[m][0].equals(Waitinglist[n][0])
												&&Queue.getQueue()[m][1].equals(Waitinglist[n][1])){
												if( SmarterController.Sum(n,Waitinglist)>=Double.parseDouble(Queue.getQueue()[m][2])){
													sign = false;
													System.out.println("SAME[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+")]");
													Queue.getQueue()[m][0] = null;
													Queue.getQueue()[m][1] = null;
													Queue.getQueue()[m][2] = null;
													last[m] = 1;
													break;
												}
												else {
													break;
												}
											}				
												
									}	
									if(sign){
										Waitinglist[x][0]=Queue.getQueue()[m][0];
										Waitinglist[x][1]=Queue.getQueue()[m][1];
										Waitinglist[x++][2]=Queue.getQueue()[m][2];
										counting++;
										Queue.getQueue()[m][0] = null;
										Queue.getQueue()[m][1] = null;
										Queue.getQueue()[m][2] = null;
										last[m] = 1;
									}
								}
								else if((getFloor() == Double.parseDouble(Queue.getQueue()[m][1])) && (Double.parseDouble(Queue.getQueue()[m][2]) < (t-1)) ){
									if(Stoplist[h-1]==floor){
										System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+")]/("+getFloor()+",STILL,"+(t+1)+")");
										Stoplist[h++]=floor;
									}
									else{
										System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+")]/("+getFloor()+","+direction+","+t+")");
										Stoplist[h++]=floor;
									}
									t++;
									Queue.getQueue()[m][0] = null;
									Queue.getQueue()[m][1] = null;
									Queue.getQueue()[m][2] = null;
									last[m] = 1;
								}							
							}
							else if(direction.equals("DOWN")){
								if(getFloor() > Double.parseDouble(Queue.getQueue()[m][1])){
									if(Double.parseDouble(Queue.getQueue()[m][1])<headingfloor){
										headingfloor=Double.parseDouble(Queue.getQueue()[m][1]);
									}
									for(int n = 999;n >= 0;n--){			
												
											if(Queue.getQueue()[m][0].equals(Waitinglist[n][0])&&Queue.getQueue()[m][1].equals(Waitinglist[n][1])){
												if( SmarterController.Sum(n,Waitinglist)>=Double.parseDouble(Queue.getQueue()[m][2])){
													sign = false;
													System.out.println("SAME[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+")]");
													Stoplist[h++]=floor;
													Queue.getQueue()[m][0] = null;
													Queue.getQueue()[m][1] = null;
													Queue.getQueue()[m][2] = null;
													last[m] = 1;
													break;
												}
												else {
													break;
												}
												
											}				
													
									}	
									if(sign){
										Waitinglist[x][0]=Queue.getQueue()[m][0];
										Waitinglist[x][1]=Queue.getQueue()[m][1];
										Waitinglist[x++][2]=Queue.getQueue()[m][2];
										counting++;
										Queue.getQueue()[m][0] = null;
										Queue.getQueue()[m][1] = null;
										Queue.getQueue()[m][2] = null;
										last[m] = 1;
									}
								}
								else if((getFloor() == Double.parseDouble(Queue.getQueue()[m][1])) && (Double.parseDouble(Queue.getQueue()[m][2]) < (t-1)) ){
									if(Stoplist[h-1]==floor){
										System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+")]/("+getFloor()+",STILL,"+(t+1)+")");
										Stoplist[h++]=floor;
									}
									else{
										System.out.println("[("+Queue.getQueue()[m][0]+","+Queue.getQueue()[m][1]+","+Queue.getQueue()[m][2]+")]/("+getFloor()+","+direction+","+t+")");
										Stoplist[h++]=floor;
									}
									t++;
									Queue.getQueue()[m][0] = null;
									Queue.getQueue()[m][1] = null;
									Queue.getQueue()[m][2] = null;	
									last[m] = 1;
								}							
							}
						}
					}
					else {
						break;
					}
				}
			}
			t = t + 0.5;
			if(direction.equals("UP")){
				setFloor(getFloor() + 1);
			}
			else if(direction.equals("DOWN")){
				setFloor(getFloor() - 1);
			}
			
			
			for(i = 0;i < x;i++){
				if(Waitinglist[i][1]==null){
					continue;
				}
				else{					
					if(getFloor() == Double.parseDouble(Waitinglist[i][1])){
						if(Waitinglist[i][0].equals("FR")){
							if(!first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								System.out.println("[("+Waitinglist[i][0]+","+Waitinglist[i][1]+","+Waitinglist[i][2]+","+Waitinglist[i][3]+")]/("+getFloor()+","+direction+","+(t-1)+")");
								Stoplist[h++]=floor;
							}
							if(first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								System.out.println("[("+Waitinglist[i][0]+","+Waitinglist[i][1]+","+Waitinglist[i][2]+","+Waitinglist[i][3]+")]/("+getFloor()+","+direction+","+t+")");
								Stoplist[h++]=floor;
							}
							
							if(first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								t++;
							}
							first[(int)(Double.parseDouble(Waitinglist[i][1]))] = false;
							Waitinglist[i][0]=null;
							Waitinglist[i][1]=null;
							Waitinglist[i][2]=null;
							Waitinglist[i][3]=null;
							counting--;
						}
						else if(Waitinglist[i][0].equals("ER")){
							if(!first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								System.out.println("[("+Waitinglist[i][0]+","+Waitinglist[i][1]+","+Waitinglist[i][2]+")]/("+getFloor()+","+direction+","+(t-1)+")");
								Stoplist[h++]=floor;
							}
							if(first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								System.out.println("[("+Waitinglist[i][0]+","+Waitinglist[i][1]+","+Waitinglist[i][2]+")]/("+getFloor()+","+direction+","+t+")");
								Stoplist[h++]=floor;
							}
							
							if(first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								t++;
							}
							first[(int)(Double.parseDouble(Waitinglist[i][1]))] = false;
							Waitinglist[i][0]=null;
							Waitinglist[i][1]=null;
							Waitinglist[i][2]=null;	
							counting--;
						}
						
					}
				}
				
			}
			if(counting==0){
				headingfloor=0;
				direction="NULL";
				running=false;
				for(i=0;i<1000;i++){
					first[i]=true;
				}
				boolean flag2 = false;
				for(i=0;i<1000;i++){
					if(Queue.getQueue()[i][0]!=null){
						flag2 = true;
						break;
					}
				}
				if(flag2==false){
					return 0;
				}
			}
			
		}
		
	}
	public static int getFloor() {
		return floor;
	}
	public static void setFloor(int floor) {
		NewElevator.floor = floor;
	}
}
class Floor{
	private static int floormax = 10;
	static int getfloormax(){
		return floormax;
	}
}
