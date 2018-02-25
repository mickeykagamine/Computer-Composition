package oo6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oo6.TaxiGUI;
import oo6.mapInfo;

class mapInfo{
	int[][] map=new int[80][80];
	public void readmap(String path){//读入地图信息
		//Requires:String类型的地图路径,System.in
		//Modifies:System.out,map[][]
		//Effects:从文件中读入地图信息，储存在map[][]中
		Scanner scan=null;
		File file=new File(path);
		if(file.exists()==false){
			System.out.println("地图文件不存在,程序退出");
			System.exit(1);
			return;
		}
		try {
			scan = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			
		}
		for(int i=0;i<80;i++){
			String[] strArray = null;
			try{
				strArray=scan.nextLine().split("");
			}catch(Exception e){
				System.out.println("地图文件信息有误，程序退出");
				System.exit(1);
			}
			for(int j=0;j<80;j++){
				try{
					this.map[i][j]=Integer.parseInt(strArray[j]);
				}catch(Exception e){
					System.out.println("地图文件信息有误，程序退出");
					System.exit(1);
				}
			}
		}
		scan.close();
	}
}


public class Customer {
	static boolean flag =false;	
	public static void main(String args[]){
		
		TaxiGUI gui=new TaxiGUI();
		mapInfo mi=new mapInfo();
		mi.readmap("map.txt");//在这里设置地图文件路径
		gui.LoadMap(mi.map, 80);
		Map.getmap();	
		Taxi.InitTaxiMap(gui);
		Scanner s = new Scanner(System.in);
		while(true){
			String request=s.nextLine();
				if(FormCheck(request)){
					request=request.replace("(", "");
					request=request.replace(")", "");
					String[] S=request.split(",");
					Point1 A = new Point1(Integer.parseInt(S[1]),Integer.parseInt(S[2]));
					Point1 B = new Point1(Integer.parseInt(S[3]),Integer.parseInt(S[4]));
					if(Point1.equal(A, B)){
						System.out.println("Same spot!");
					}
					else if(A.X<0|A.X>80|A.Y<0|A.Y>80|B.X<0|B.X>80|B.Y<0|B.Y>80){
						System.out.println("Invalid sopt!");
					}
					else{
						long T=System.currentTimeMillis();
						Scheduler sch =new Scheduler(A,B,T);
						sch.start();
					}
				}
				else System.out.println("Input Invalid!");
			
		}
		
	}
	static boolean FormCheck(String s){
		Pattern p0 = Pattern.compile("\\(CR,\\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\),\\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)\\)");
		//                           (CR,(1,1),(2,3))
		Matcher match0 = p0.matcher(s);
		
		if(match0.matches()){
			return true;
		}
		else {
			return false;
		}	
	}
	
}
