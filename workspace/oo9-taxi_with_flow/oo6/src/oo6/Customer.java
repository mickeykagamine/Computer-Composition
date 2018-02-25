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
	int[][] map=new int[Map.maxofmap][Map.maxofmap];
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
		for(int i=0;i<Map.maxofmap;i++){
			String[] strArray = null;
			try{
				strArray=scan.nextLine().split("");
			}catch(Exception e){
				System.out.println("地图文件信息有误，程序退出");
				System.exit(1);
			}
			for(int j=0;j<Map.maxofmap;j++){
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
		/* @Requires:none
		 * @Modifies:Flow.flow,Map.map,TAXILIST;
		 * @Effects:init everything
		 *  */
		try{
		TaxiGUI gui=new TaxiGUI();
		mapInfo mi=new mapInfo();
		mi.readmap("map.txt");//在这里设置地图文件路径
		gui.LoadMap(mi.map, Map.maxofmap);
		Map.getmap();	
		Flow.initflow();
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
					else if(A.X<0|A.X>=Map.maxofmap|A.Y<0|A.Y>=Map.maxofmap|B.X<0|B.X>=Map.maxofmap|B.Y<0|B.Y>=Map.maxofmap){
						System.out.println("Invalid sopt!");
					}
					else{
						long T=System.currentTimeMillis();
						Scheduler sch =new Scheduler(A,B,T);
						sch.start();
					}
				}
				else if(FormCheck2(request)){
					
					request=request.replace("delete", "");
					request=request.replace("(", "");
					request=request.replace(")", " ");
					request=request.replace(",", " ");
					String[] S=request.split(" ");
					Point1 A = new Point1(Integer.parseInt(S[1]),Integer.parseInt(S[2]));
					Point1 B = new Point1(Integer.parseInt(S[3]),Integer.parseInt(S[4]));
					if(Point1.isDOWN(A, B)||Point1.isLEFT(A, B)||Point1.isRIGHT(A, B)||Point1.isUP(A, B))
					Map.deletepath(A,B);
					else System.out.println("A must be neighbor of B");
				}
				else if(FormCheck3(request)){
					request=request.replace("add", "");
					request=request.replace("(", "");
					request=request.replace(")", " ");
					request=request.replace(",", " ");
					String[] S=request.split(" ");
					Point1 A = new Point1(Integer.parseInt(S[1]),Integer.parseInt(S[2]));
					Point1 B = new Point1(Integer.parseInt(S[3]),Integer.parseInt(S[4]));
					if(Point1.isDOWN(A, B)||Point1.isLEFT(A, B)||Point1.isRIGHT(A, B)||Point1.isUP(A, B))
					Map.addpath(A,B);
					else System.out.println("A must be neighbor of B");
				}	
				else System.out.println("Input Invalid!");
			
		}
		}catch (Exception e) {
			System.out.println("ERROR");
		}
	}
	static boolean FormCheck(String s){
		/* @Requires:String s;
		 * @Modifies:none
		 * @Effects:\result==(s matches("\\(CR,\\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\),\\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)\\)"))		
		 * */
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
	static boolean FormCheck2(String s){
		/* @Requires:String s;
		 * @Modifies:none
		 * @Effects:\result==(s matches("delete \\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)\\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)"))		
		 * */
		Pattern p1 = Pattern.compile("delete \\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)\\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)");
		//                           detele (0,0)(1,1)
		Matcher match1 = p1.matcher(s);
		
		if(match1.matches()){
			return true;
		}
		else {
			return false;
		}	
	}
	
	static boolean FormCheck3(String s){
		/* @Requires:String s;
		 * @Modifies:none
		 * @Effects:\result==(s matches("add \\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)\\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)"))		
		 * */
		Pattern p3 = Pattern.compile("add \\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)\\([+]?[0-9]{1,2},[+]?[0-9]{1,2}\\)");
		//                           add (0,0)(1,1)
		Matcher match3 = p3.matcher(s);
		
		if(match3.matches()){
			return true;
		}
		else {
			return false;
		}	
	}
}
