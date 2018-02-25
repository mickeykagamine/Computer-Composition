package oo4;

import java.text.DecimalFormat;


import oo4.Queue;
import oo4.NewElevator;

public class Scheduler extends Thread{
	
	
	static int[] last = new int[1000];
	static int[] Stoplist = new int [1000];
	static int h = 0;	
	static int counting = 0;
	static double t = 0;
	static int[] x = new int [3];
	private static double D;
	
	private static double sum = 0;
	
	static int findj(int m,int max,long[][] Queue){
		int j;
		int sign3 = 0;
		for(j=m;j<max+1;j++){
			if (Queue[j][0]!=0){
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
	
	static double Sum(int m,long[][] Queue ,NewElevator e){
		int j1 = 0;	
		int j2 = 0;
		j1=findj(0,m,Queue);
			
		if(Queue[j1][1]-e.getFloor() < 0){
			D = e.getFloor()-Queue[j1][1];
		}
		else {
			D = Queue[j1][1]-e.getFloor();
		}
		
			sum = D*3+6+Queue[j1][3];
		

		
		
		for(j2=findj(j1+1,m,Queue);j2<=m;j2=findj(j2+1,m,Queue),j1=j2){
			if(j2==-1){
				break;
			}
			if(Queue[j2][1]-Queue[j1][1] < 0){
				D = Queue[j1][1]-Queue[j2][1];
			}
			else {
				D = Queue[j2][1]-Queue[j1][1];
			}
			
				if(sum>Queue[j2][4]){
					sum=sum+(D*3)+6;
				}
				if(sum<=Queue[j2][4]){
					sum=Queue[j2][4]+(D*3)+6;
				}
			
			
			
		}											
		return sum;
	}
	
	
	public boolean canyoupickup(NewElevator e,int i){
	
	boolean sign=false;
	
		
	////锟斤拷锟叫碉拷4锟姐，突然锟斤拷锟斤拷4锟斤拷锟斤拷锟斤拷锟斤拷锟接︼拷牟锟斤拷锟�
			/////UP
			if((Queue.getQueue()[i][0]==1&&Queue.getQueue()[i][2]==1&&Queue.getQueue()[i][2]==e.getDirection())
					|(Queue.getQueue()[i][0]==2)){
				if((Queue.getQueue()[i][0]==1&&(e.getFloor() < Queue.getQueue()[i][1]&&Queue.getQueue()[i][1] <= e.getHeadingfloor()))
					|(Queue.getQueue()[i][0]==2&&(e.getFloor() < Queue.getQueue()[i][1]))){
					sign=true;
					if(Queue.getQueue()[i][0]==2&&((e.getFloor() > Queue.getQueue()[i][1]))){
						Queue.setQueue(i,0,e.getDirection());
					}					
				}
				
			}
			////DOWN
			else if(Queue.getQueue()[i][2]==2&&Queue.getQueue()[i][2]==e.getDirection()||(Queue.getQueue()[i][0]==2)){
				if((Queue.getQueue()[i][0]==1&&((e.getFloor() > Queue.getQueue()[i][1])&&(Queue.getQueue()[i][1]) >= e.getHeadingfloor()))
					|(Queue.getQueue()[i][0]==2&&((e.getFloor() > Queue.getQueue()[i][1])))){
				sign=true;
				if(Queue.getQueue()[i][0]==2&&((e.getFloor() > Queue.getQueue()[i][1]))){
					Queue.setQueue(i,0,e.getDirection());
				}
				
				}										
			}	
		return sign;		
	}
	
	
	public void clearwaitinglist(NewElevator e,int i,int eleid){
			e.setWaitinglist(x[eleid-1],0,Queue.getQueue()[i][0]);
			e.setWaitinglist(x[eleid-1],1,Queue.getQueue()[i][1]);
			e.setWaitinglist(x[eleid-1],2,Queue.getQueue()[i][2]);
			e.setWaitinglist(x[eleid-1],3,Queue.getQueue()[i][3]);
			e.setWaitinglist(x[eleid-1]++,4,Queue.getQueue()[i][4]);
//			System.out.println("("+TEST.e2.getWaitinglist()[x-1][0]+","+TEST.e2.getWaitinglist()[x-1][1]+","+TEST.e2.getWaitinglist()[x-1][2]+")");
			System.out.println("elevator#"+e.getID()+"get the request("+Queue.getQueue()[i][0]+","+Queue.getQueue()[i][1]+","+Queue.getQueue()[i][2]+","+Queue.getQueue()[i][3]+","+Queue.getQueue()[i][4]+")");
			Queue.delete(i);
	
			
	//		System.out.println(i);
			
	}
	
	public NewElevator findminn(NewElevator ee1,NewElevator ee2){
		if(ee1.getMomentum()<=ee2.getMomentum()){
			return ee1;
		}
		else return ee2;
	}
	
	public int findminnnum(NewElevator ee1,NewElevator ee2){
		if(ee1.getMomentum()<=ee2.getMomentum()){
			return ee1.getID();
		}
		else return ee2.getID();
	}
	
	public NewElevator findminn(NewElevator ee1,NewElevator ee2,NewElevator ee3){
		NewElevator e = findminn(ee1,ee2);		
		return findminn(e,ee3);
	}
	public int findminnnum(NewElevator ee1,NewElevator ee2,NewElevator ee3){
		NewElevator e = findminn(ee1,ee2);		
		return findminn(e,ee3).getID();
	}
	
	public boolean checkforsame(NewElevator e,int i){
		DecimalFormat A = new DecimalFormat("0.0");
		for(int j=0;j<1000;j++){
			if((e.getWaitinglist()[j][0]==1&&e.getWaitinglist()[j][0]==Queue.getQueue()[i][0]&&e.getWaitinglist()[j][1]==Queue.getQueue()[i][1]&&e.getWaitinglist()[j][2]==Queue.getQueue()[i][2])
				|(e.getWaitinglist()[j][0]==2&&e.getWaitinglist()[j][0]==Queue.getQueue()[i][0]&&e.getWaitinglist()[j][1]==Queue.getQueue()[i][1]&&e.getWaitinglist()[j][3]==Queue.getQueue()[i][3])){
				String frerjudge=null;
				String directionnn=null;
				if(Queue.getQueue()[i][0]==1){
					frerjudge="FR";
				}
				else if(Queue.getQueue()[i][0]==2){
					frerjudge="ER";
				}
				if(Queue.getQueue()[i][2]==1){
					directionnn="UP";
				}
				else if(Queue.getQueue()[i][2]==2){
					directionnn="DOWN";
				}	
				if(Queue.getQueue()[i][0]==1){
					System.out.println(System.currentTimeMillis()+":SAME[("+frerjudge+","+Queue.getQueue()[i][1]+","+directionnn+","+A.format((Queue.getQueue()[i][4]-input.getDELTA())*0.001)+")]");
					String sss=System.currentTimeMillis()+":SAME[("+frerjudge+","+Queue.getQueue()[i][1]+","+directionnn+","+A.format((Queue.getQueue()[i][4]-input.getDELTA())*0.001)+")]";					
					FileWrite.tofile(sss);
				}
				else if(Queue.getQueue()[i][0]==2){
					System.out.println(System.currentTimeMillis()+":SAME[("+frerjudge+",#"+Queue.getQueue()[i][3]+","+Queue.getQueue()[i][1]+","+A.format((Queue.getQueue()[i][4]-input.getDELTA())*0.001)+")]");
					String sss=System.currentTimeMillis()+":SAME[("+frerjudge+" ,#"+Queue.getQueue()[i][3]+","+Queue.getQueue()[i][1]+","+A.format((Queue.getQueue()[i][4]-input.getDELTA())*0.001)+")]";					
					FileWrite.tofile(sss);
				}
				Queue.delete(i);
				return true;
			}
		}
		return false;
	}
	
	public void run(){
		
		boolean sign = false;
		boolean sign1 = false;
		boolean sign2 = false;
		boolean sign3 = false;
		while (true){
	//		System.out.println("scheduler is running!");
			sign = false;
			int i;
			for(i=0;i<1000;i++){
				if(Queue.getQueue()[i][0]!=0){
					sign = true;
					break;
				}
			}			
			if(!sign&&input.flag) {
				break;
			}
			if(sign){
				if(Queue.getQueue()[i][0]==1){
					if(checkforsame(TEST.e1,i)){continue;}
					if(checkforsame(TEST.e2,i)){continue;}
					if(checkforsame(TEST.e3,i)){continue;}
					 sign1 = canyoupickup(TEST.e1,i);
					 sign2 = canyoupickup(TEST.e2,i);
					 sign3 = canyoupickup(TEST.e3,i);
							 
					 if(!sign1&!sign2&sign3){
						 clearwaitinglist(TEST.e3,i,3);
					 }
					 else if(!sign1&sign2&!sign3){
						 clearwaitinglist(TEST.e2,i,2);
					 }
					 else if(sign1&!sign2&!sign3){
						 clearwaitinglist(TEST.e1,i,1);
					 }
					 else if(!sign1&sign2&sign3){
						 clearwaitinglist(findminn(TEST.e2,TEST.e3),i,findminnnum(TEST.e2,TEST.e3));
					 }
					 else if(sign1&!sign2&sign3){
						 clearwaitinglist(findminn(TEST.e1,TEST.e3),i,findminnnum(TEST.e1,TEST.e3));
					 }
					 else if(sign1&sign2&!sign3){
						 clearwaitinglist(findminn(TEST.e1,TEST.e2),i,findminnnum(TEST.e1,TEST.e2));
					 }
					 else if(sign1&sign2&sign3){
						 clearwaitinglist(findminn(TEST.e1,TEST.e2,TEST.e3),i,findminnnum(TEST.e1,TEST.e2,TEST.e3));
					 }
					 
					 else if(!sign1&!sign2&!sign3){
						 if(TEST.e1.getDirection()!=0&TEST.e2.getDirection()!=0&TEST.e3.getDirection()==0){
							 clearwaitinglist(TEST.e3,i,3);
						 }
						 else if(TEST.e1.getDirection()!=0&TEST.e2.getDirection()==0&TEST.e3.getDirection()!=0){
							 clearwaitinglist(TEST.e2,i,2);
						 }
						 else if(TEST.e1.getDirection()==0&TEST.e2.getDirection()!=0&TEST.e3.getDirection()!=0){
							 clearwaitinglist(TEST.e1,i,1);
						 }
						 else if(TEST.e1.getDirection()!=0&TEST.e2.getDirection()==0&TEST.e3.getDirection()==0){
							 clearwaitinglist(findminn(TEST.e2,TEST.e3),i,findminnnum(TEST.e2,TEST.e3));
						 }
						 else if(TEST.e1.getDirection()==0&TEST.e2.getDirection()!=0&TEST.e3.getDirection()==0){
							 clearwaitinglist(findminn(TEST.e1,TEST.e3),i,findminnnum(TEST.e1,TEST.e3));
						 }
						 else if(TEST.e1.getDirection()==0&TEST.e2.getDirection()==0&TEST.e3.getDirection()!=0){
							 clearwaitinglist(findminn(TEST.e1,TEST.e2),i,findminnnum(TEST.e1,TEST.e2));
						 }
						 else if(TEST.e1.getDirection()==0&TEST.e2.getDirection()==0&TEST.e3.getDirection()==0){
							 clearwaitinglist(findminn(TEST.e1,TEST.e2,TEST.e3),i,findminnnum(TEST.e1,TEST.e2,TEST.e3));
						 }					 
					 }	
				}
				
				else if(Queue.getQueue()[i][0]==2){
					if(Queue.getQueue()[i][3]==1){
						if(canyoupickup(TEST.e1,i)|TEST.e1.getDirection()==0){
							clearwaitinglist(TEST.e1,i,1);
						}
					}
					if(Queue.getQueue()[i][3]==2){
						if(canyoupickup(TEST.e2,i)|TEST.e2.getDirection()==0){
							clearwaitinglist(TEST.e2,i,2);
						}
					}
					if(Queue.getQueue()[i][3]==3){
						if(canyoupickup(TEST.e3,i)|TEST.e3.getDirection()==0){
							clearwaitinglist(TEST.e3,i,3);
						}
					}
				}
			}
			
		}
	}
	
	
	
}
