package oo12;

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
							|(Double.parseDouble(NewRequest[1]) > Floor.getfloormax())
							|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == 1 && NewRequest[2].equals("DOWN"))
							|(NewRequest[0].equals("FR") && Double.parseDouble(NewRequest[1]) == Floor.getfloormax() && NewRequest[2].equals("UP")))){
							if(NewRequest[0].equals("FR")){
							sign2 = SmarterController.judgefr(NewRequest[0],NewRequest[1],NewRequest[2],NewRequest[3],Queue.getQueue(),Floor.Max(Request.getLast()));
							i=Queue.insetfr(sign2 , NewRequest[0], NewRequest[1], NewRequest[2], NewRequest[3],Queue.getQueue());
							/*
							if(sign2){
								Elevator.run(i-1);
							}
							*/
						}
						else if(NewRequest[0].equals("ER")){
							sign2 =  SmarterController.judgeer(NewRequest[0],NewRequest[1],NewRequest[2],Queue.getQueue(),Floor.Max(Request.getLast()));
							i=Queue.inseter(sign2, NewRequest[0], NewRequest[1], NewRequest[2],Queue.getQueue());
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
					System.out.println("INVALID["+request+"]");
				}
			}
			NewElevator.run(Queue.getQueue());
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




