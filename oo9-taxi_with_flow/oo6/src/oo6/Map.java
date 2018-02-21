package oo6;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import oo6.Map;
import oo6.Point1;
//寻找最短路径
public class Map {
	boolean up=false;
	boolean down=false;
	boolean right=false;
	boolean left=false;

	static int maxofmap =80;
	//记得改成maxofmap
	static Map[][] map = new Map[maxofmap][maxofmap];

	
	
	public Map(boolean b, boolean c, boolean d, boolean e) {
		/* @Requires:boolean b,c,d,e;
		 * @Modifies:\this.up,\this.down,\this.right,\this.left;	
		 * @Effects:\this.up==b;\this.down==c;\this.right==d;\this.left==e;	
		 * */
		up=b;
		down=c;
		right=d;
		left=e;
	}
	
	public static synchronized void deletepath(Point1 a,Point1 b){
		/* @Requires:Point1 a,b;
		 * @Modifies:map;
		 * @Effects:if Point1.isDIR(a,b) then map[b.X][b.Y].dir==false;map[a.X][a.Y].oppositedir==false;	
		 * @THREAD_REQUIRES:\locked(map);
		 * @THREAD_EFFECTS:\locked();
		 * */
		 if(Point1.isUP(a, b)){
			 Map.map[b.X][b.Y].up=false;
			 Map.map[a.X][a.Y].down=false;
			 TaxiGUI.SetRoadStatus(new Point(a.X,a.Y), new Point(b.X,b.Y), 0);
		 }
		 else if(Point1.isDOWN(a, b)){
			 Map.map[b.X][b.Y].down=false;
			 Map.map[a.X][a.Y].up=false;
			 TaxiGUI.SetRoadStatus(new Point(a.X,a.Y), new Point(b.X,b.Y), 0);
		 }
		 else if(Point1.isRIGHT(a, b)){
			 Map.map[b.X][b.Y].right=false;
			 Map.map[a.X][a.Y].left=false;
			 TaxiGUI.SetRoadStatus(new Point(a.X,a.Y), new Point(b.X,b.Y), 0);
		 }
		 else if(Point1.isLEFT(a, b)){
			 Map.map[b.X][b.Y].left=false;
			 Map.map[a.X][a.Y].right=false;
			 TaxiGUI.SetRoadStatus(new Point(a.X,a.Y), new Point(b.X,b.Y), 0);
		 }
	 }
	
	public static synchronized void addpath(Point1 a,Point1 b){
		/* @Requires:Point1 a,b;
		 * @Modifies:map;
		 * @Effects:if Point1.isDIR(a,b) then map[b.X][b.Y].dir==ture;map[a.X][a.Y].oppositedir==true;	
		 * @THREAD_REQUIRES:\locked(map);
		 * @THREAD_EFFECTS:\locked();
		 * */
		 if(Point1.isUP(a, b)){
			 Map.map[b.X][b.Y].up=true;
			 Map.map[a.X][a.Y].down=true;
			 TaxiGUI.SetRoadStatus(new Point(a.X,a.Y), new Point(b.X,b.Y), 1);
		 }
		 else if(Point1.isDOWN(a, b)){
			 Map.map[b.X][b.Y].down=true;
			 Map.map[a.X][a.Y].up=true;
			 TaxiGUI.SetRoadStatus(new Point(a.X,a.Y), new Point(b.X,b.Y), 1);
		 }
		 else if(Point1.isRIGHT(a, b)){
			 Map.map[b.X][b.Y].right=true;
			 Map.map[a.X][a.Y].left=true;
			 TaxiGUI.SetRoadStatus(new Point(a.X,a.Y), new Point(b.X,b.Y), 1);
		 }
		 else if(Point1.isLEFT(a, b)){
			 Map.map[b.X][b.Y].left=true;
			 Map.map[a.X][a.Y].right=true;
			 TaxiGUI.SetRoadStatus(new Point(a.X,a.Y), new Point(b.X,b.Y), 1);
		 }
	 }
	
	public static void InitDirection(){
		/* @Requires:none;
		 * @Modifies:map;
		 * @Effects:\all int i,j;
		 * 			0<i&i<Map.maxofmap,0<j&j<Map.maxofmap;
		 * 			map[i][j]==Map(false,false,false,false);
		 * */
		for(int i=0;i<maxofmap;i++){
			for(int j=0;j<maxofmap;j++){
				map[i][j]=new Map(false,false,false,false);
			}
		}
	}
	public static void getmap(){
		/* @Requires:none;
		 * @Modifies:map;
		 * @Effects:\all int i,j;
		 * 			0<i&i<Map.maxofmap,0<j&j<Map.maxofmap;
		 * 			(file[i][j]==1) ==>(map[i][j].right==true),(file[i][j]==2) ==>(map[i][j].down==true),(file[i][j]==3) ==>(map[i][j].right==true&&map[i][j].down==true)
		 * */
		try {
            String encoding="GBK";
            File file=new File("E:\\map.txt");
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int m = 0;
                InitDirection();
                while((lineTxt = bufferedReader.readLine()) != null){
                    String[] linesplit = lineTxt.split(" ");
                	for(int i=0;i<linesplit.length;i++){
                		if(Integer.parseInt(linesplit[i])==1){
                			map[m][i].right=true;
                			map[m][i+1].left=true;
                		}
                		else if(Integer.parseInt(linesplit[i])==2){
                			map[m][i].down=true;
                			map[m+1][i].up=true;
                		}
                		else if(Integer.parseInt(linesplit[i])==3){
                			map[m][i].right=true;
                			map[m][i+1].left=true;
                			map[m][i].down=true;
                			map[m+1][i].up=true;
                		}
                	}
                	m++;
                }
                read.close();
            }
            else{
            	System.out.println("can't find map");
            }
		}catch (Exception e) {
			System.out.println("wrong map");
			e.printStackTrace();
		}
	}
	
	/**
	 * @param start
	 * @param end
	 * @return
	 */
	public int findpoint(Point1 p,ArrayList<NewPoint1> list){
		/* @Requires:Point1 p,ArrayList<NewPoint1> list;
		 * @Modifies:none;
		 * @Effects:\all int i,j;
		 * 			0<i&i<Map.maxofmap,0<j&j<Map.maxofmap;
		 * 			(file[i][j]==1) ==>(map[i][j].right==true),(file[i][j]==2) ==>(map[i][j].down==true),(file[i][j]==3) ==>(map[i][j].right==true&&map[i][j].down==true)
		 * */
		for(int i=0;i<list.size();i++){
			if(Point1.equal(list.get(i).point,p)){
				return i;
			}
		}
		return -1;
	}
	
	public int findLine(Point1 p,Point1 f,ArrayList<NewPoint1> list){
		for(int i=0;i<list.size();i++){
			if((Point1.equal(list.get(i).point,p)&&Point1.equal(list.get(i).father,f))||(Point1.equal(list.get(i).point,f)&&Point1.equal(list.get(i).father,p))){
				return i;
			}
		}
		return -1;
	}
	public synchronized ArrayList<Point1> shortway(Point1 start,Point1 end){
		/* @Requires:Point1 start,end;
		 * @Modifies:none;
		 * @Effects:if Point1.equals(start,end) \result==null;
		 * 			if !Point1.equals(start,end) \result==path;path.size==\min path.size;
		 * 			
		 * */
		
		ArrayList<Point1> path = new ArrayList<Point1>();	
		ArrayList<NewPoint1> FATHER = new ArrayList<NewPoint1>();
		ArrayList<NewPoint1> sign= new ArrayList<NewPoint1>();
		if(Point1.equal(start,end)){
			System.out.println("Start point euals end point!");
			return null;
		}
		else{
			int x=0;
			int y=0;		
			
		
			Point1 p = start;
			FATHER.add(new NewPoint1(p,p,0));
			ArrayList<Point1> plist =new ArrayList<Point1>();
			ArrayList<Point1> plist2 =new ArrayList<Point1>();
			plist.add(p);
			int i=0;
			while(true){
				if(Point1.equal(p,end)){
					break;
				}	
				for(i=0;i<plist.size();i++){
					p=plist.get(i);
					if(map[p.X][p.Y].down&&(findLine(Point1.DOWN(p),p,sign)==-1)){//!sign[p.X][p.Y].down
						if(findpoint(Point1.DOWN(p),FATHER)==-1){
							FATHER.add(new NewPoint1(Point1.DOWN(p),p,(Flow.flow[p.X][p.Y].down+FATHER.get(findpoint(p,FATHER)).last)));
			//				System.out.println("");
						}
						else{
							int a=findpoint(Point1.DOWN(p),FATHER);
							if(FATHER.get(a).last>(Flow.flow[p.X][p.Y].down+FATHER.get(findpoint(p, FATHER)).last)){
								FATHER.get(a).last=(Flow.flow[p.X][p.Y].down+FATHER.get(findpoint(p, FATHER)).last);
								FATHER.get(a).father=p;
							}							
						}
						sign.add(new NewPoint1(Point1.DOWN(p),p,0));
						
						plist2.add(Point1.DOWN(p));
					}
					if(map[p.X][p.Y].up&&(findLine(Point1.UP(p),p,sign)==-1)){
						if(findpoint(Point1.UP(p),FATHER)==-1){
							FATHER.add(new NewPoint1(Point1.UP(p),p,(Flow.flow[p.X][p.Y].up+FATHER.get(findpoint(p,FATHER)).last)));
		///					System.out.println("");
						}
						else{
							int a=findpoint(Point1.UP(p),FATHER);
				//			System.out.println(a+" "+p.X+" "+p.Y);
							
				//			System.out.println(Flow.flow[p.X][p.Y].up);
							if(FATHER.get(a).last>(Flow.flow[p.X][p.Y].up+FATHER.get(findpoint(p, FATHER)).last)){
								FATHER.get(a).last=(Flow.flow[p.X][p.Y].up+FATHER.get(findpoint(p, FATHER)).last);
								FATHER.get(a).father=p;
							}							
						}

						sign.add(new NewPoint1(Point1.UP(p),p,0));
						plist2.add(Point1.UP(p));
					}
					if(map[p.X][p.Y].right&&(findLine(Point1.RIGHT(p),p,sign)==-1)){
						if(findpoint(Point1.RIGHT(p),FATHER)==-1){
					//		System.out.println(Flow.flow[p.X][p.Y].right);
							FATHER.add(new NewPoint1(Point1.RIGHT(p),p,(Flow.flow[p.X][p.Y].right+FATHER.get(findpoint(p,FATHER)).last)));
		//					System.out.println("");
						}
						else{
							int a=findpoint(Point1.RIGHT(p),FATHER);
							if(FATHER.get(a).last>(Flow.flow[p.X][p.Y].right+FATHER.get(findpoint(p, FATHER)).last)){
								FATHER.get(a).last=(Flow.flow[p.X][p.Y].right+FATHER.get(findpoint(p, FATHER)).last);
								FATHER.get(a).father=p;
							}							
						}
						sign.add(new NewPoint1(Point1.RIGHT(p),p,0));
						plist2.add(Point1.RIGHT(p));
					}
					if(map[p.X][p.Y].left&&(findLine(Point1.LEFT(p),p,sign)==-1)){
						if(findpoint(Point1.LEFT(p),FATHER)==-1){
							FATHER.add(new NewPoint1(Point1.LEFT(p),p,(Flow.flow[p.X][p.Y].left+FATHER.get(findpoint(p,FATHER)).last)));
		//					System.out.println("");
						}
						else{
							int a=findpoint(Point1.LEFT(p),FATHER);
							if(FATHER.get(a).last>(Flow.flow[p.X][p.Y].left+FATHER.get(findpoint(p, FATHER)).last)){
								FATHER.get(a).last=(Flow.flow[p.X][p.Y].left+FATHER.get(findpoint(p, FATHER)).last);
								FATHER.get(a).father=p;
							}							
						}
						sign.add(new NewPoint1(Point1.LEFT(p),p,0));
						plist2.add(Point1.LEFT(p));
					}
					if(Point1.equal(p,end)){
						break;
					}
					
				}
				plist.clear();
				plist.addAll(plist2);
				plist2.clear();
				
			}
			if(Point1.equal(p,end)){
				while(!Point1.equal(p,start)){
		//			System.out.println("start:"+start.X+" "+start.Y+"end:"+end.X+" "+end.Y+" "+p.X+" "+p.Y);
					path.add(p);
//					System.out.println(getpointoffind(p));
					p=FATHER.get(findpoint(p,FATHER)).father;					
				}
				path.add(p);
				return path;
			}
									
		
		}
		return null;
		
		
	}
	
	
	
	
	
}
