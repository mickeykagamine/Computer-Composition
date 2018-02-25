package oo6;

import java.util.Random;

import oo6.FileWrite;

import java.awt.Point;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

class FileWrite{
	public static void tofile(String str){
		Charset charset = Charset.forName("US-ASCII");
		try{
			FileOutputStream out=new FileOutputStream("result.txt",true);
			str=str+"\r\n";
			out.write(str.getBytes(charset));
			out.close();
		}catch (IOException x ){
			System.err.format("IOException:%s%n", x);
		}
	}
}
public class Taxi extends Thread {	
	Point1 spot;
	int num = 0;
	int honest =0;
	TaxiGUI gui;
	String state;
	ArrayList<Point1> waitinglist1 = new ArrayList<Point1>();
	static int taxinumber=50;
	//记得要改啊啊啊啊
	static Taxi[] TAXILIST = new Taxi[taxinumber];
	
		
	
	public synchronized static void addtask(Taxi t,Point1 A, Point1 B){
		t.waitinglist1.add(A);
		t.waitinglist1.add(B);
	}
	public static void InitTaxiMap(TaxiGUI gui){
		for(int i=0;i<taxinumber;i++){
			
			int r1 = new Random().nextInt(Map.maxofmap);
			int r2 = new Random().nextInt(Map.maxofmap);	
			/*
			int r1=10;
			int r2=10;
			*/
	//		System.out.println("taxi"+i+"START AT("+r1+","+r2+")");
			TAXILIST[i]=new Taxi(i,0,"waiting",new Point1(r1,r2),gui);		
			/*
			if(i==0){
				TAXILIST[i]=new Taxi(i,0,"waiting",new Point1(r1,r2),gui);
			}
			
			if(i==1){
				TAXILIST[i]=new Taxi(i,0,"waiting",new Point1(9,9),gui);
			}
			*/
			TAXILIST[i].start();
			
		
		}
	}
	public Taxi(int i, int j,String a,Point1 A,TaxiGUI GUI) {
		num=i;
		honest=j;
		state =a;
		spot =A;
		gui=GUI;
	}
	public void run(){
		int time=0;
		while(true){			
			if(state.equals("waiting")){
				time=0;
				while(state.equals("waiting")){
					String[] dir =new String[4];
					for(int i=0;i<4;i++){
						dir[i]=null;
					}
					int add =0;
					if(Map.map[spot.X][spot.Y].up){
						dir[add++]="up";
					}
					if(Map.map[spot.X][spot.Y].down){
						dir[add++]="down";
					}
					if(Map.map[spot.X][spot.Y].left){
						dir[add++]="left";
					}
					if(Map.map[spot.X][spot.Y].right){
						dir[add++]="right";
					}
					
					int r3 = new Random().nextInt(add);
				
					if(dir[r3].equals("up")){
						spot.X--;
					}
					else if(dir[r3].equals("down")){
						spot.X++;
					}
					else if(dir[r3].equals("right")){
						spot.Y++;
					}
					else if(dir[r3].equals("left")){
						spot.Y--;
					}
					
//					System.out.println("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
					int STATE=-1;
					if(state.equals("waiting")){
						STATE =2;
					}
					else if(state.equals("pickingup")){
						STATE =3;
					}
					else if(state.equals("stop")){
						STATE =0;
					}
					else if(state.equals("heading")){
						STATE =1;
					}
					gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
					try{
						sleep(200);
					}catch (InterruptedException e) {
						System.out.println("ERROR");
					}
					time+=200;
					if(time==20000){
						state="stop";
					}
				}
			}			
			else if(state.equals("pickingup")){
				int STATE=-1;
				if(state.equals("waiting")){
					STATE =2;
				}
				else if(state.equals("pickingup")){
					STATE =3;
				}
				else if(state.equals("stop")){
					STATE =0;
				}
				else if(state.equals("heading")){
					STATE =1;
				}
				gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);			
				if(waitinglist1.isEmpty()){
					System.out.println("picking up error!");					
				}else{
					int i=1;
					ArrayList<Point1> aa=new ArrayList<Point1>();
					Map p =new Map(false, false, false, false, false);
					aa=p.shortway(waitinglist1.get(0),spot);
					if(aa==null){
						state="stop1";
					}else{
						while(i<aa.size()){
							if(Point1.isDOWN(aa.get(i),spot)){
								i++;
								spot=Point1.DOWN(spot);
								String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
								FileWrite.tofile(sss);	
								gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
								try{
									sleep(200);
								}catch (InterruptedException e) {
									System.out.println("ERROR");
								}
							}
							else if(Point1.isUP(aa.get(i), spot)){
								i++;
								spot=Point1.UP(spot);
								String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
								FileWrite.tofile(sss);	
								gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
								try{
									sleep(200);
								}catch (InterruptedException e) {
									System.out.println("ERROR");
								}
							}
							else if(Point1.isRIGHT(aa.get(i), spot)){
								i++;
								spot=Point1.RIGHT(spot);
								String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
								FileWrite.tofile(sss);	
								gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
								try{
									sleep(200);
								}catch (InterruptedException e) {
									System.out.println("ERROR");
								}
							}
							else if(Point1.isLEFT(aa.get(i), spot)){
								i++;
								spot=Point1.LEFT(spot);
								String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
								FileWrite.tofile(sss);	
								gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
								try{
									sleep(200);
								}catch (InterruptedException e) {
									System.out.println("ERROR");
								}
							}
						}					
						state="stop1";	
					}
				}
			}
			else if(state.equals("stop")){
				int STATE=-1;
				if(state.equals("waiting")){
					STATE =2;
				}
				else if(state.equals("pickingup")){
					STATE =3;
				}
				else if(state.equals("stop")){
					STATE =0;
				}
				else if(state.equals("heading")){
					STATE =1;
				}
				gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
				try{
					sleep(1000);
				}catch (InterruptedException e) {
					System.out.println("ERROR");
				}			
				state="waiting";
								
			}
			else if(state.equals("stop2")){
				int STATE=-1;
				if(state.equals("waiting")){
					STATE =2;
				}
				else if(state.equals("pickingup")){
					STATE =3;
				}
				else if(state.equals("stop")||state.equals("stop2")){
					STATE =0;
				}
				else if(state.equals("heading")){
					STATE =1;
				}
				gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
				try{
					sleep(1000);
				}catch (InterruptedException e) {
					System.out.println("ERROR");
				}
				state="pickingup";				
			}
			else if(state.equals("stop1")){
				int STATE=-1;
				if(state.equals("waiting")){
					STATE =2;
				}
				else if(state.equals("pickingup")){
					STATE =3;
				}
				else if(state.equals("stop1")||state.equals("stop")||state.equals("stop2")){
					STATE =0;
				}
				else if(state.equals("heading")){
					STATE =1;
				}
				gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
				try{
					sleep(1000);
				}catch (InterruptedException e) {
					System.out.println("ERROR");
				}											
				state="heading";				
			}
			else if(state.equals("heading")){ 
				int STATE=-1;
				if(state.equals("waiting")){
					STATE =2;
				}
				else if(state.equals("pickingup")){
					STATE =3;
				}
				else if(state.equals("stop")){
					STATE =0;
				}
				else if(state.equals("heading")){
					STATE =1;
				}
				int i=1;
				gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
				
				if(waitinglist1.isEmpty()){
					System.out.println("heading error!");
				}else{	
					ArrayList<Point1> aa =new ArrayList<Point1>();
					Map p =new Map(false, false, false, false, false);
					aa=p.shortway(waitinglist1.get(1), waitinglist1.get(0));
						while(i<aa.size()){
							if(Point1.isDOWN(aa.get(i), spot)){
								i++;
								spot=Point1.DOWN(spot);
								String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
								FileWrite.tofile(sss);	
								gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
								try{
									sleep(200);
								}catch (InterruptedException e) {
									System.out.println("ERROR");
								}
							}
							else if(Point1.isUP(aa.get(i), spot)){
								i++;
								spot=Point1.UP(spot);
								String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
								FileWrite.tofile(sss);	
								gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
								try{
									sleep(200);
								}catch (InterruptedException e) {
									System.out.println("ERROR");
								}
							}
							else if(Point1.isRIGHT(aa.get(i), spot)){
								i++;
								spot=Point1.RIGHT(spot);
								String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
								FileWrite.tofile(sss);	
								gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
								try{
									sleep(200);
								}catch (InterruptedException e) {
									System.out.println("ERROR");
								}
							}
							else if(Point1.isLEFT(aa.get(i), spot)){
								i++;
								spot=Point1.LEFT(spot);
								String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
								FileWrite.tofile(sss);	
								gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
								try{
									sleep(200);
								}catch (InterruptedException e) {
									System.out.println("ERROR");
								}
							}
						}
						honest+=3;
						//read me 标明是执行完毕后信誉才+3
						waitinglist1.remove(0);
						waitinglist1.remove(0);
						if(waitinglist1.isEmpty()){
							state="stop";
						}
						else{
							state="stop2";
						}
						
					
				}
			}
		}
	}
}
