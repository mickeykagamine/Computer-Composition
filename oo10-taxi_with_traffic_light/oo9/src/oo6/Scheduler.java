package oo6;

import java.util.Random;

import oo6.FileWrite;

import java.util.ArrayList;

public class Scheduler extends Thread{
	/*Overview
	 * 获得乘客请求，分配给Taxi
	 * */
	Point1 A;
	Point1 B;
	long T;
	Taxi taxinull=new Taxi(-1,-1,"null",new Point1(-1,-1),null);
	
	public boolean repOK(){
		/* @Requires:none;
		 * @Modifies:none;
		 * @Effects:check scheduler
		 * 			if scheduler is right return true 
		 *  */
		return true;
		
	}
	
	
	public Scheduler(Point1 a,Point1 b,long t){
		/* @Requires:Point1 a,b;long t;
		 * @Modifies:\this.A,\this.B,\this.T;
		 * @Effects:\this.A==a,\this.B==b,\this.T==t;
		 * */
		A=a;
		B=b;
		T=t;
	}
	public boolean canpick(Point1 A,Taxi T){
		/* @Requires:Point1 A;Taxi T;
		 * @Modifies:none;
		 * @Effects:\result==((T.spot.X>=A.X-2&&T.spot.X<=A.X+2)&&(T.spot.Y>=A.Y-2&&T.spot.Y<=A.Y+2));
		 * */
		if((T.spot.X>=A.X-2&&T.spot.X<=A.X+2)&&(T.spot.Y>=A.Y-2&&T.spot.Y<=A.Y+2)){
//			System.out.println("Taxi No."+T.num+": state:"+T.state+" honest:"+T.honest);
			if(T.state.equals("waiting")){
				return true;
			}
		}
		return false;
	}
	
	public void checktaxi(Point1 A,Taxi T){
		/* @Requires:Point1 A;Taxi T;
		 * @Modifies:File;
		 * @Effects:if ((T.spot.X>=A.X-2&&T.spot.X<=A.X+2)&&(T.spot.Y>=A.Y-2&&T.spot.Y<=A.Y+2)) Filewrite==("Taxi No."+T.num+": state:"+T.state+" honest:"+T.honest);
		 * */
		if((T.spot.X>=A.X-2&&T.spot.X<=A.X+2)&&(T.spot.Y>=A.Y-2&&T.spot.Y<=A.Y+2)){
			String sss=("Taxi No."+T.num+": state:"+T.state+" honest:"+T.honest);
			FileWrite.tofile(sss);System.out.println(sss);			
		}

	}
	
	public void run(){
		/* @Requires:none;
		 * @Modifies:TAXILIST;
		 * @Effects:int max==(\max int i;0<i&i<taxinumber;TAXILIST[i].honest);
		 * 			int min==(\min int i;TAIXILIST[i].honest==max;shortway(TAXILIST[i].spot,A).size);
		 * 			TAXILIST[Random(\all int i;shortway(TAXILIST[i].spot,A).size==min;)].waitinglis1.add(A),TAXILIST[Random(\all int i;shortway(TAXILIST[i].spot,A).size==min;)].waitinglis1.add(B);
		 * 		 * */
		//出租车数量要改啊
		try{
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
	//		System.out.println("chosinglist is not empty!");
			for(int i=0;i<n;i++){
				String sss=("抢单：\n");
				FileWrite.tofile(sss);System.out.println(sss);		
				sss=("Taxi"+chosinglist.get(i).num+" ");
				FileWrite.tofile(sss);System.out.println(sss);	
				chosinglist.get(i).addhonest(1);
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
			n=chosinglist.size();
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
		//	System.out.println("count:"+count);
			if(count>1){
			//	System.out.println("yeaddddd");
				int min=0;
				Map p =new Map(false, false, false, false);
				ArrayList<Point1> l =p.shortway(chosinglist.get(0).spot, A);
				if(l!=null){
					min=l.size();
				}
				else{
					min=0;
					maxi=0;
				}
				int mini=0;
				if(l!=null){
					for(int i=0;i<n;i++){
						ArrayList<Point1> l2=p.shortway(chosinglist.get(i).spot, A);
						if(l2!=null&&l2.size()<min){
							min=l2.size();
							mini=i;
						}
						else if(l2==null){
							min=0;
							mini=i;
							break;
						}
//						System.out.println("");
					}
					
					maxi=mini;
				}
			}
			String sss=("taxi"+chosinglist.get(maxi).num+"get the request from("+A.X+","+A.Y+")to("+B.X+","+B.Y+")at honest"+chosinglist.get(maxi).honest);			
			FileWrite.tofile(sss);
			System.out.println(sss);	
			chosinglist.get(maxi).addtask(A,B);
			chosinglist.get(maxi).state="pickingup";
	//		System.out.print("");
		}
	
	}catch (Exception e) {
		System.out.println("wrong map");
//		e.printStackTrace();
	}
	
	}
}
