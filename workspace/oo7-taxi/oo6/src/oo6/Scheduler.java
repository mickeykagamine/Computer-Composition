package oo6;

import java.util.Random;

import oo6.FileWrite;

import java.util.ArrayList;

public class Scheduler extends Thread{
	Point1 A;
	Point1 B;
	long T;
	Taxi taxinull=new Taxi(-1,-1,"null",new Point1(-1,-1),null);
	
	public Scheduler(Point1 a,Point1 b,long t){
		A=a;
		B=b;
		T=t;
	}
	public boolean canpick(Point1 A,Taxi T){		
		if((T.spot.X>=A.X-2&&T.spot.X<=A.X+2)&&(T.spot.Y>=A.Y-2&&T.spot.Y<=A.Y+2)){
//			System.out.println("Taxi No."+T.num+": state:"+T.state+" honest:"+T.honest);
			if(T.state.equals("waiting")){
				return true;
			}
		}
		return false;
	}
	
	public void checktaxi(Point1 A,Taxi T){		
		if((T.spot.X>=A.X-2&&T.spot.X<=A.X+2)&&(T.spot.Y>=A.Y-2&&T.spot.Y<=A.Y+2)){
			String sss=("Taxi No."+T.num+": state:"+T.state+" honest:"+T.honest);
			FileWrite.tofile(sss);System.out.println(sss);	
			
		
		}

	}
	
	public void run(){
		//出租车数量要改啊
		int taxinumber=Taxi.taxinumber;
		ArrayList<Taxi> chosinglist= new ArrayList<Taxi>();
		boolean[] chosen =new boolean[taxinumber];
		int n = 0;
		
		for(int i=0;i<taxinumber;i++){
			chosen[i]=false;
		}
		for(int i=0;i<taxinumber;i++){
			checktaxi(A,Taxi.TAXILIST[i]);
		}
		while(
			  System.currentTimeMillis()-T<3000
		//		System.currentTimeMillis()-T<30000	
				){
			for(int i=0;i<taxinumber;i++){
				if(canpick(A,Taxi.TAXILIST[i])&&chosen[i]==false){					
					chosinglist.add(Taxi.TAXILIST[i]);
					chosen[i]=true;
				}
			}
		}
		if(chosinglist.isEmpty()){
			System.out.println("no taxi!");
			return;
		}
		else{
			for(int i=0;i<n;i++){
				String sss=("抢单：\n");
				FileWrite.tofile(sss);System.out.println(sss);
				System.out.println(sss);
				sss=("Taxi"+chosinglist.get(i).num+" ");
				FileWrite.tofile(sss);System.out.println(sss);	
				chosinglist.get(i).honest++;
			}
			for(int h=0;h<n;h++){
				if(chosinglist.get(h).state!="waiting"){
					chosinglist.remove(h);
				}
			}
			if(chosinglist.isEmpty()){
				System.out.println("no taxi!");
				return;
			}
			int max=chosinglist.get(0).honest;
			int maxi=0;
			for(int i=0;i<n;i++){
				if(chosinglist.get(i).honest>max){
					max=chosinglist.get(i).honest;
					maxi=i;
				}
			}	
			int count=0;
			for(int i=0;i<n;i++){
				if(chosinglist.get(i).honest==max){
					count++;
				}
			}
			if(count>1){
				int min=0;
				Map p =new Map(false, false, false, false, false);
				if(p.shortway(chosinglist.get(0).spot, A)!=null){
					min=p.shortway(chosinglist.get(0).spot, A).size();
				}
				else{
					min=0;
					maxi=0;
				}
				int mini=0;
				if(p.shortway(chosinglist.get(0).spot, A)!=null){
					for(int i=0;i<n;i++){
						if(p.shortway(chosinglist.get(i).spot, A).size()<min){
							min=p.shortway(chosinglist.get(i).spot, A).size();
							mini=i;
						}
					}
					
					maxi=mini;
				}
			}
			String sss=("taxi"+chosinglist.get(maxi).num+"get the request from("+A.X+","+A.Y+")to("+B.X+","+B.Y+")at honest"+chosinglist.get(maxi).honest);			
			FileWrite.tofile(sss);System.out.println(sss);	
			Taxi.addtask(chosinglist.get(maxi),A,B);
			chosinglist.get(maxi).state="pickingup";
		}
		
	}
}
