package oo4;

import java.nio.charset.Charset;
import java.text.DecimalFormat;

import java.io.FileOutputStream;
import java.io.IOException;

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

public class NewElevator extends Thread{
	private int floor;
	private int headingfloor;
	private int direction;
	private int momentum;
	private int id;
	private long t;
	private long[][] Waitinglist=new long [1000][1000];
	
	public NewElevator(int Floor,int Headingfloor,int Direction,long time,int Id) {
        floor=Floor;
        setHeadingfloor(Headingfloor);
        setDirection(Direction);
        t=time;
        id=Id;
    }	
	public boolean getnearestfloor(long[][] Waitinglist,int floor){
		for(int i=0;i<1000;i++){
			if(floor==Waitinglist[i][1]){
				return true;
			}
		}
		return false;
	}
	
	public void DeletWaitingList(int n,long[][] Waitinglist){
		for(int i=0;i<1000;i++){
			if(Waitinglist[i][1]==n){
				Waitinglist[i][0]=0;
				Waitinglist[i][1]=0;
				Waitinglist[i][2]=0;
				Waitinglist[i][3]=0;
				break;
			}
		}
		
	}
	public int getioflist(int n){
		for(int i=0;i<1000;i++){
			if(getWaitinglist()[i][1]==n){
				return i;
			}
		}
		return 0;
	}
	

	int i=0;
	public void run() {
	boolean sign = false;
	DecimalFormat A = new DecimalFormat("0.0");
	while(true){
//		System.out.println("");
		sign = false;
		
		long max = 0;	
		
//		System.out.println(!sign&input.flag);
		if(!sign&input.flag){
			break;
		}
		
		if(direction == 0){
			sign =false;			
			for(i=0;i<1000;i++){
				if(Waitinglist[i][0]!=0){
					sign = true;
					System.out.println("yes!");
					break;
				}
				
				
			}
			if(max!=0){
				sign = true;
			}
			if(sign){
				if(floor==Waitinglist[i][1]){												
						String frorer = null;
						String directioN =null;
						if(Waitinglist[i][0]==1){
							frorer = "FR";
						}
						else if(Waitinglist[i][0]==2){
							frorer = "ER";
						}
						if(Waitinglist[i][2]==1){
							directioN = "UP";
						}
						else if(Waitinglist[i][2]==2){
							directioN = "DOWN";
						}
							
						if(Waitinglist[i][0]==1){
							String sss=System.currentTimeMillis()+":[("+frorer+","+Waitinglist[i][1]+","+directioN+"),"+(Waitinglist[i][4]-input.getDELTA())*0.001+"](#"+id+","+floor+",STILL,"+momentum+","+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001)+")";
							FileWrite.tofile(sss);	
							System.out.println(System.currentTimeMillis()+":[("+frorer+","+Waitinglist[i][1]+","+directioN+"),"+(Waitinglist[i][4]-input.getDELTA())*0.001+"](#"+id+","+floor+",STILL,"+momentum+","+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001)+")");
							DeletWaitingList(floor,Waitinglist);
						}
						
						if(Waitinglist[i][0]==2){
							String sss=System.currentTimeMillis()+":[("+frorer+",#"+(int)Waitinglist[i][3]+","+Waitinglist[i][1]+"),"+(Waitinglist[i][4]-input.getDELTA())*0.001+"](#"+id+","+floor+",STILL,"+momentum+","+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001)+")";							
							FileWrite.tofile(sss);	
							System.out.println(System.currentTimeMillis()+":[("+frorer+",#"+(int)Waitinglist[i][3]+","+Waitinglist[i][1]+"),"+(Waitinglist[i][4]-input.getDELTA())*0.001+"](#"+id+","+floor+",STILL,"+momentum+","+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001)+")");
							DeletWaitingList(floor,Waitinglist);
						}
						try{
							Thread.sleep(6000);
						}catch(InterruptedException e){
							System.out.println("ERROR");
						}
						
						
				}
				else if(Waitinglist[i][1]>floor){
						direction=1;
						headingfloor=(int)Waitinglist[i][1];					
				}
				else if(Waitinglist[i][1]<floor){
					direction=2;
					headingfloor=(int)Waitinglist[i][1];
					
				}
			}
		}
		
		
		if(direction != 0){
			
			if(floor != headingfloor && !getnearestfloor(Waitinglist,floor)){
				if(direction==1){
					floor++;
					System.out.println(floor);
					
				}
				else if(direction==2){
					floor--;
					System.out.println(floor);
				}
		
				momentum=momentum+ 1;
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println("ERROR");
				}
				System.out.println("one floor"+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001));
					
			}
			else if(floor == headingfloor){					
				String frorer = null;
				String directioN = null ;
				if(Waitinglist[i][0]==1){
					frorer = "FR";
				}
				else if(Waitinglist[i][0]==2){
					frorer = "ER";
				}
				if(direction==1){
					directioN = "UP";
				}
				else if(direction==2){
					directioN = "DOWN";
				}
				if(Waitinglist[i][0]==1){
					System.out.println(System.currentTimeMillis()+":[("+frorer+","+headingfloor+","+directioN+"),"+A.format((Waitinglist[i][4]-input.getDELTA())*0.001)+"](#"+id+","+floor+","+directioN+","+momentum+","+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001)+")");					
					String sss=System.currentTimeMillis()+":[("+frorer+","+headingfloor+","+directioN+"),"+A.format((Waitinglist[i][4]-input.getDELTA())*0.001)+"](#"+id+","+floor+","+directioN+","+momentum+","+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001)+")";
					FileWrite.tofile(sss);
				}
				if(Waitinglist[i][0]==2){
					System.out.println(System.currentTimeMillis()+":[("+frorer+",#"+(int)Waitinglist[i][3]+","+headingfloor+"),"+A.format((Waitinglist[i][4]-input.getDELTA())*0.001)+"](#"+id+","+floor+","+directioN+","+momentum+","+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001)+")");						
					String sss=System.currentTimeMillis()+":[("+frorer+",#"+(int)Waitinglist[i][3]+","+headingfloor+"),"+A.format((Waitinglist[i][4]-input.getDELTA())*0.001)+"](#"+id+","+floor+","+directioN+","+momentum+","+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001)+")";
					FileWrite.tofile(sss);	
				}
				try{
					Thread.sleep(6000);
				}catch(InterruptedException e){
					System.out.println("ERROR");
				}
				System.out.println("open the door"+A.format((System.currentTimeMillis()-t)*0.001-input.getDELTA()*0.001));				
				DeletWaitingList(headingfloor,Waitinglist);
				boolean SIGN=false;
				for(int j=0;j<1000;j++){
					if(Waitinglist[j][0]!=0){
						SIGN=true;
						headingfloor=(int) Waitinglist[j][1];
						i=j;
						break;
					}
				}
				if(!SIGN){
					direction=0;
					headingfloor=0;
				}
				
				
			}
			else if(getnearestfloor(Waitinglist,floor)){
				int h = getioflist(floor);
				String frorer = null;
				String directioN = null ;
				if(Waitinglist[h][0]==1){
					frorer = "FR";
				}
				else if(Waitinglist[h][0]==2){
					frorer = "ER";
				}
				if(direction==1){
					directioN = "UP";
				}
				else if(direction==2){
					directioN = "DOWN";
				}
				else if(direction==0){
					directioN = "STILL";
				}
				if(Waitinglist[h][0]==1){
					System.out.println(System.currentTimeMillis()+":[("+frorer+","+headingfloor+","+directioN+"),"+A.format((Waitinglist[h][4]-input.getDELTA())*0.001)+"]"+"(#"+id+","+floor+","+directioN+","+momentum+","+A.format((System.currentTimeMillis()-t)*0.001)+")");						
					String sss=System.currentTimeMillis()+":[("+frorer+","+headingfloor+","+directioN+"),"+A.format((Waitinglist[h][4]-input.getDELTA())*0.001)+"]"+"(#"+id+","+floor+","+directioN+","+momentum+","+A.format((System.currentTimeMillis()-t)*0.001)+")";
					FileWrite.tofile(sss);
				}
				if(Waitinglist[h][0]==2){
					System.out.println(System.currentTimeMillis()+":[("+frorer+",#"+(int)Waitinglist[h][3]+","+headingfloor+"),"+A.format((Waitinglist[h][4]-input.getDELTA())*0.001)+"]"+"(#"+id+","+floor+","+directioN+","+momentum+","+A.format((System.currentTimeMillis()-t)*0.001)+")");
					String sss=System.currentTimeMillis()+":[("+frorer+",#"+(int)Waitinglist[h][3]+","+headingfloor+"),"+A.format((Waitinglist[h][4]-input.getDELTA())*0.001)+"]"+"(#"+id+","+floor+","+directioN+","+momentum+","+A.format((System.currentTimeMillis()-t)*0.001)+")";						
					FileWrite.tofile(sss);	
				}
				try{
					Thread.sleep(6000);
				}catch(InterruptedException e){
					System.out.println("ERROR");
				}
					
				DeletWaitingList(floor,Waitinglist);
					
			}
		}				
	}
         
    }
	public int getFloor() {
		return floor;
	}
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public long[][] getWaitinglist() {
		return Waitinglist;
	}
	public void setWaitinglist(int i,int j,long waitinglist) {
		Waitinglist[i][j] = waitinglist;
	}
	public int getHeadingfloor() {
		return headingfloor;
	}
	public void setHeadingfloor(int headingfloor) {
		this.headingfloor = headingfloor;
	}
	public int getMomentum() {
		return momentum;
	}
	public void setMomentum(int momentum) {
		this.momentum = momentum;
	}
	public int getID() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	
}
