package oo6;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Light extends Thread{
	/*Overview
	 * 存取各个路的红绿灯状况
	 * \all int i,j;
	 * 0<i&i<Map.maxofmap;j<0&j<Map.maxofmap;
	 * repOK<==>!(((light[i][j].leftright==light[i][j].updown)&(light[i][j].leftright==light[i][j].updown))
	 * 				||((light[i][j].sign==0)&(light[i][j].leftright==false||light[i][j].updown==false))
	 * 				||(light[i][j].time<50||light[i][j].time>100))==ture;
	 * 
	 * */
	boolean updown=true;
	boolean leftright=!updown;
	int sign =0;
	int time =50;
	Point1 spot=new Point1(-1,-1);
	static Light[][] light = new Light[Map.maxofmap][Map.maxofmap];
	public boolean repOK(){
		/* @Requires:none;
		 * @Modifies:none;
		 * @Effects:check light 
		 * 			if light is right return true 
		 *  */
		for(int i=0;i<Map.maxofmap;i++){
			for(int j=0;j<Map.maxofmap;j++){
				if(light[i][j].sign==1){
					if(light[i][j].leftright==light[i][j].updown){
						return false;
					}
				}
				else if(light[i][j].sign==0){
					if(light[i][j].leftright==false||light[i][j].updown==false){
						return false;
					}
				}
				
				if(light[i][j].time<50||light[i][j].time>100){
					return false;
				}
			}
		}
		return true;
		
	}
	public Light(boolean a,boolean b,int c,int d,int x,int y) {
		/* @Requires:boolean a, b,int c, d, x, y;
		 * @Modifies:\this.updown,\this.leftright,\this.sign,\this.time,\this.spot;
		 * @Effects:\this.updown==a,\this.leftright==b,\this.sign ==c,\this.time==d,\this.spot.X==x,\this.spot.Y==y;
		 * 			
		 *  */
		updown=a;
		leftright=b;
		sign =c;
		time=d;
		spot=new Point1(-1,-1);
		spot.X=x;
		spot.Y=y;
	}
	public static void getlight(){
		/* @Requires:none;
		 * @Modifies:map;
		 * @Effects:\all int i,j;
		 * 			0<i&i<Map.maxofmap,0<j&j<Map.maxofmap;
		 * 			(file[i][j]==1) ==>(light[i][j].sign==1,light[i][j].updown!=light[i][j].leftright),(file[i][j]==0) ==>(light[i][j].down==0,light[i][j].updown==light[i][j].leftright==true)
		 *  * */
		try {
            String encoding="GBK";
            File file=new File("E:\\light.txt");
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int m = 0;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String[] linesplit = lineTxt.split(" ");
                    
                	for(int i=0;i<linesplit.length;i++){
                		
                		//记得readme注明每个灯时间不同
                		if(Integer.parseInt(linesplit[i])==1){
                			light[m][i]=new Light(true,false,Integer.parseInt(linesplit[i]),50+new Random().nextInt(50),m,i);    
       
                		}
                		else if(Integer.parseInt(linesplit[i])==0){
                			light[m][i]=new Light(true,true,Integer.parseInt(linesplit[i]),50+new Random().nextInt(50),m,i);            
           
                		}
                		
                	}
                	m++;
                }
                read.close();
                light[0][0].start();
            }
            else{
            	System.out.println("can't find lightmap");
            }
		}catch (Exception e) {
			System.out.println("wrong lightmap");
			e.printStackTrace();
		}
	}
	public void run(){
		/* @Requires:none;
		 * @Modifies:light;
		 * @Effects:change light.updown light.leftright according to time;
		 * */
		int t=0;
		TaxiGUI aa =new TaxiGUI();
		while(true){
			for(int m=0;m<Map.maxofmap;m++){
				for(int i=0;i<Map.maxofmap;i++){
					if(light[m][i].sign==0){
						aa.SetLightStatus(new Point(spot.X,spot.Y),0);
					}
					
					else if(light[m][i].sign==1){
							if(light[m][i].updown==false){
								aa.SetLightStatus(new Point(spot.X,spot.Y),1);
							}
							else if(light[m][i].updown==true){
								aa.SetLightStatus(new Point(spot.X,spot.Y),2);
							}
							
							if(t%((light[m][i].time))==0){
						//		if(m==1&&i==1)System.out.println("aa");
								light[m][i].updown=!light[m][i].updown;
								light[m][i].leftright=!light[m][i].updown;
							}
							
							if(light[m][i].updown==false){
								aa.SetLightStatus(new Point(light[m][i].spot.X,light[m][i].spot.Y),1);
							}
							else if(light[m][i].updown==true){
								aa.SetLightStatus(new Point(light[m][i].spot.X,light[m][i].spot.Y),2);
							}
							
						
					}
				}
			}
			try{
				sleep(1);
			}catch (Exception e) {
				e.printStackTrace();
			}
			t=t+1;
		}
	}
}
