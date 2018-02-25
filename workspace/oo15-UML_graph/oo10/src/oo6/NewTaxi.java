package oo6;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;



public class NewTaxi extends Taxi{
	/*Overview
	 * 根据自身状态和waitinglist移动自身
	 *  * \all int i;
	 * 0<i&i<taxinumber;
	 * repOK<==>!((TAXILIST[i].num<0||TAXILIST[i].num>=taxinumber)
	 * 			||(TAXILIST[i].sign!=0&&TAXILIST[i].sign!=1)
	 *			||(TAXILIST[i].spot.X<0||TAXILIST[i].spot.X>=Map.maxofmap||TAXILIST[i].spot.Y<0||TAXILIST[i].spot.Y>=Map.maxofmap)
	 *			||(TAXILIST[i].honest<0)
	 *			||(!(TAXILIST[i].state.equals("waiting")||TAXILIST[i].state.equals("heading")||TAXILIST[i].state.equals("stop")||TAXILIST[i].state.equals("stop1")||TAXILIST[i].state.equals("stop2")||TAXILIST[i].state.equals("pickingup"))))==ture;
	 * 
	 * */
	public boolean repOK(){
		/* @Requires:none;
		 * @Modifies:none;
		 * @Effects:check TAXILIST
		 * 			if TAXILIST is right return true 
		 *  */
		for(int i=0;i<taxinumber;i++){
			if(TAXILIST[i].num<0||TAXILIST[i].num>=taxinumber){
				return false;
			}
			if(TAXILIST[i].spot.X<0||TAXILIST[i].spot.X>=Map.maxofmap||TAXILIST[i].spot.Y<0||TAXILIST[i].spot.Y>=Map.maxofmap){
				return false;
			}
			if(TAXILIST[i].honest<0){
				return false;
			}
			if(TAXILIST[i].sign!=0&&TAXILIST[i].sign!=1){
				return false;
			}
			if(!(TAXILIST[i].state.equals("waiting")||TAXILIST[i].state.equals("heading")||TAXILIST[i].state.equals("stop")||TAXILIST[i].state.equals("stop1")||TAXILIST[i].state.equals("stop2")||TAXILIST[i].state.equals("pickingup"))){
				return false;
			}
		}
		return true;
		
	}
	public NewTaxi(int i, int j, String a, Point1 A, TaxiGUI GUI, Map[][] Map,int Sign) {
		/* @Requires:int i,j;String a;Point1 A;TaxiGUI GUI;
		 * @Modifies:\this.num,\this.honest,\this.state,\this.spot,\this.gui,\this.sign;
		 * @Effects:\this.num==i;\this.honest==j;\this.state ==a;\this.spot ==A;\this.gui==GUI,\this.sign==Sign;
		 * */
		super(i, j, a, A, GUI, Map,Sign);
	}
	ArrayList<Message> message=new ArrayList<Message>();
	
	public void printiterList(){
		/* @Requires:none
		 * @Modifies:none
		 * @Effects:print message;
		 * 
		 *  */
		ListIterator<Message> iterList = message.listIterator();
		System.out.println("+++++++++++++++++++++++++++++++++++++++supertaxi"+num+"+++++++++++++++++++++++++++++++++++++++");
		while(iterList.hasNext()){
			Message m=iterList.next();
			System.out.println("Time:"+m.Time);
			System.out.println("Start:("+m.start.X+","+m.start.Y+") End:("+m.end.X+","+m.end.Y+")");
			System.out.println("Spot:("+m.spot.X+","+m.spot.Y+")");
			for(int h=0;h<m.route.size();h++){
				System.out.print("("+m.route.get(h).X+","+m.route.get(h).Y+")");
			}	
			System.out.println("");
		}
		System.out.println("\n+++++++++++++++++++++++++++++++++++++++supertaxi"+num+"+++++++++++++++++++++++++++++++++++++++");
	}
	
	public ListIterator<Message> returniterator(){
		/* @Requires:none
		 * @Modifies:none
		 * @Effects:return message;
		 * 
		 *  */
		ListIterator<Message> iterList = message.listIterator();
		return iterList;
	}
	
	
	public void run(){
			
			String olddir="UP";
			String newdir="";
			//readme 注明olddir初始
			
			/* @Requires:none
			 * @Modifies:\this.waitinglist1
			 * @Effects:if(state.equals(waiting)) then random walk
			 * 			if(state.equals(heading)||state.equals(pickingup)) then walk follow the waitinglist1
			 * 			if(state.equals(stop)||state.equals(stop1)||state.euals(stop2)) then stop
			 * */
			try{
			long time=0;
			while(true){			
				if(state.equals("waiting")){
					time=System.currentTimeMillis();
					/////////////////////////////////////////////////////////////////waiting状态
					while(state.equals("waiting")){
						
						int[] flow=new int[4];
						for(int i=0;i<4;i++){
							flow[i]=-1;
						}
						int add =0;
						if(map[spot.X][spot.Y].up){			
							flow[1]=Flow.flow[spot.X][spot.Y].up;
						}
						if(map[spot.X][spot.Y].down){	
							flow[0]=Flow.flow[spot.X][spot.Y].down;
						}
						if(map[spot.X][spot.Y].left){
							flow[2]=Flow.flow[spot.X][spot.Y].left;
						}
						if(map[spot.X][spot.Y].right){
				
							flow[3]=Flow.flow[spot.X][spot.Y].right;
						}
						if(!((map[spot.X][spot.Y].right)|(map[spot.X][spot.Y].left)|(map[spot.X][spot.Y].down)|(map[spot.X][spot.Y].up))){
							return;
						}
						int r3 = randomfindmini(flow);
					//	System.out.println(r3);
						//up
						
						if(r3==1){
							Flow.addflow(spot.X, spot.Y,"up");
							Flow.addflow(spot.X-1, spot.Y,"down");
							newdir="UP";
							waitinglight(newdir,olddir,spot.X,spot.Y);
							spot.X--;
							
						}
						//down
						else if(r3==0){
							Flow.addflow(spot.X, spot.Y,"down");
							Flow.addflow(spot.X+1, spot.Y,"up");
							newdir="DOWN";
							waitinglight(newdir,olddir,spot.X,spot.Y);
							spot.X++;
							
						}
						//right
						else if(r3==3){
							Flow.addflow(spot.X, spot.Y,"right");
							Flow.addflow(spot.X, spot.Y+1,"left");
							newdir="RIGHT";
							waitinglight(newdir,olddir,spot.X,spot.Y);
							spot.Y++;
							
						}
						//left
						else if(r3==2){
							Flow.addflow(spot.X, spot.Y,"left");
							Flow.addflow(spot.X, spot.Y-1,"right");
							newdir="LEFT";
							waitinglight(newdir,olddir,spot.X,spot.Y);
							spot.Y--;
							
						}
						
						
						try{
							sleep(200);
						}catch (InterruptedException e) {
							System.out.println("ERROR");
						}
						//up
						if(r3==1){
							Flow.subflow(spot.X+1, spot.Y,"up");
							Flow.subflow(spot.X, spot.Y,"down");
							olddir="UP";
						}
						//down
						else if(r3==0){
							Flow.subflow(spot.X-1, spot.Y,"down");
							Flow.subflow(spot.X, spot.Y,"up");
							olddir="DOWN";
						}
						//right
						else if(r3==3){
							Flow.subflow(spot.X, spot.Y-1,"right");
							Flow.subflow(spot.X, spot.Y,"left");
							olddir="RIGHT";
						}
						//left
						else if(r3==2){
							Flow.subflow(spot.X, spot.Y+1,"left");
							Flow.subflow(spot.X, spot.Y,"right");
							olddir="LEFT";
						}
						
						long time2=System.currentTimeMillis()-time;
						if(time2>=20000){
							state="stop";
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
						
						
					
						
					//	System.out.println(r3);
						//up
						
						
						
					
			
					}
				}	
				
				
				///////////////////////////////////////////////////////////////////pickingup 状态
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
						Map p =new Map(false, false, false, false);
						aa=p.shortway(map,waitinglist1.get(0),spot);
						
						message.add(new Message(System.currentTimeMillis(),waitinglist1.get(0),waitinglist1.get(1),spot,aa));
						
						if(aa==null){
							state="stop1";
						}else{
							while(i<=(aa.size()-1)){
								if(Point1.isDOWN(aa.get(i),spot)&&map[spot.X][spot.Y].down){
									i++;
									Flow.addflow(spot.X, spot.Y,"down");
									Flow.addflow(spot.X+1, spot.Y,"up");
									newdir="DOWN";
									
									waitinglight(newdir,olddir,spot.X,spot.Y);
									
									spot=Point1.DOWN(spot);
									String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
									FileWrite.tofile(sss);	
									gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
									
									
																									
									try{
										sleep(200);
									}catch (InterruptedException e) {
										System.out.println("ERROR");
									}
									olddir="DOWN";
									Flow.subflow(spot.X-1, spot.Y,"down");
									Flow.subflow(spot.X, spot.Y,"up");
								}
								else if(Point1.isUP(aa.get(i), spot)&&map[spot.X][spot.Y].up){
									i++;
									Flow.addflow(spot.X, spot.Y,"up");
									Flow.addflow(spot.X-1, spot.Y,"down");
									newdir="UP";
									waitinglight(newdir,olddir,spot.X,spot.Y);
									spot=Point1.UP(spot);
									String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
									FileWrite.tofile(sss);	
									gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
									
									try{
										sleep(200);
									}catch (InterruptedException e) {
										System.out.println("ERROR");
									}
									olddir="UP";
									Flow.subflow(spot.X+1, spot.Y,"up");
									Flow.subflow(spot.X, spot.Y,"down");
								}
								else if(Point1.isRIGHT(aa.get(i), spot)&&map[spot.X][spot.Y].right){
									i++;
									Flow.addflow(spot.X, spot.Y,"right");
									Flow.addflow(spot.X, spot.Y+1,"left");
									
									newdir="RIGHT";
									waitinglight(newdir,olddir,spot.X,spot.Y);
									spot=Point1.RIGHT(spot);
									String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
									FileWrite.tofile(sss);	
									gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
									
									try{
										sleep(200);
									}catch (InterruptedException e) {
										System.out.println("ERROR");
									}
									olddir="RIGHT";
									Flow.subflow(spot.X, spot.Y-1,"right");
									Flow.subflow(spot.X, spot.Y,"left");
								}
								else if(Point1.isLEFT(aa.get(i), spot)&&map[spot.X][spot.Y].left){
									i++;
									Flow.addflow(spot.X, spot.Y,"left");
									Flow.addflow(spot.X, spot.Y-1,"right");
									
									newdir="LEFT";
									waitinglight(newdir,olddir,spot.X,spot.Y);
									spot=Point1.LEFT(spot);
									String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
									FileWrite.tofile(sss);	
									gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
									
									try{
										sleep(200);
									}catch (InterruptedException e) {
										System.out.println("ERROR");
									}
									olddir="LEFT";
									Flow.subflow(spot.X, spot.Y+1,"left");
									Flow.subflow(spot.X, spot.Y,"right");
								}
								else{
									aa.clear();
									aa=p.shortway(map,waitinglist1.get(0),spot);
									i=1;
								}
							}					
							state="stop1";	
						}
					}
				}
				
				
				/////////////////////////////////////////////////////////////////stop状态
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
				
				///////////////////////////////////////////////////////////////////////heading状态
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
						Map p =new Map(false, false, false, false);
			//			System.out.println("Taxi:"+num+"from("+spot.X+","+spot.Y+")to("+waitinglist1.get(1).X+","+waitinglist1.get(1).Y+")");
						aa=p.shortway(map,waitinglist1.get(1),waitinglist1.get(0));
						
						message.get(message.size()-1).route.addAll(aa);
				//		printiterList();
						/*
						for(int j=0;j<aa.size();j++){
							System.out.println("Taxi:"+num+"("+aa.get(j).X+","+aa.get(j).Y+")");
						}
						*/
							while(i<=(aa.size()-1)){
								if(Point1.isDOWN(aa.get(i), spot)&&map[spot.X][spot.Y].down){
									i++;
									Flow.addflow(spot.X, spot.Y,"down");
									Flow.addflow(spot.X+1, spot.Y,"up");
									newdir="DOWN";
									waitinglight(newdir,olddir,spot.X,spot.Y);
									
									spot=Point1.DOWN(spot);
									String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
									FileWrite.tofile(sss);	
									gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
									
									try{
										sleep(200);
									}catch (InterruptedException e) {
										System.out.println("ERROR");
									}
									olddir="DOWN";
									Flow.subflow(spot.X-1, spot.Y,"down");
									Flow.subflow(spot.X, spot.Y,"up");
	
								}
								else if(Point1.isUP(aa.get(i), spot)&&map[spot.X][spot.Y].up){
									i++;
									Flow.addflow(spot.X, spot.Y,"up");
									Flow.addflow(spot.X-1, spot.Y,"down");
									newdir="UP";
									waitinglight(newdir,olddir,spot.X,spot.Y);
									spot=Point1.UP(spot);
									String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
									FileWrite.tofile(sss);	
									gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
									
									try{
										sleep(200);
									}catch (InterruptedException e) {
										System.out.println("ERROR");
									}
									olddir="UP";
									Flow.subflow(spot.X+1, spot.Y,"up");
									Flow.subflow(spot.X, spot.Y,"down");
								}
								else if(Point1.isRIGHT(aa.get(i), spot)&&map[spot.X][spot.Y].right){
									i++;
									Flow.addflow(spot.X, spot.Y,"right");
									Flow.addflow(spot.X, spot.Y+1,"left");
									newdir="RIGHT";
									waitinglight(newdir,olddir,spot.X,spot.Y);
									spot=Point1.RIGHT(spot);
									String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
									FileWrite.tofile(sss);	
									gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
									
									try{
										sleep(200);
									}catch (InterruptedException e) {
										System.out.println("ERROR");
									}
									olddir="RIGHT";
									Flow.subflow(spot.X, spot.Y-1,"right");
									Flow.subflow(spot.X, spot.Y,"left");
								}
								else if(Point1.isLEFT(aa.get(i), spot)&&map[spot.X][spot.Y].left){
									i++;
									Flow.addflow(spot.X, spot.Y,"left");
									Flow.addflow(spot.X, spot.Y-1,"right");
									newdir="LEFT";
									waitinglight(newdir,olddir,spot.X,spot.Y);	
									spot=Point1.LEFT(spot);
									String sss=("taxi"+num+"move to"+"("+spot.X+","+spot.Y+")");
									FileWrite.tofile(sss);	
									gui.SetTaxiStatus(num, new Point(spot.X,spot.Y), STATE);
																
									try{
										sleep(200);
									}catch (InterruptedException e) {
										System.out.println("ERROR");
									}
									olddir="LEFT";
									Flow.subflow(spot.X, spot.Y+1,"left");
									Flow.subflow(spot.X, spot.Y,"right");
								}
								else{
									aa.clear();
									aa=p.shortway(map,waitinglist1.get(1),spot);
									i=1;
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
		}catch (Exception e) {
				System.out.println("ERROR1");
			}
		}
}
