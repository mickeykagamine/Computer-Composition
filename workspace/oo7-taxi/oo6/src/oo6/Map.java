package oo6;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
//寻找最短路径
public class Map {
	boolean up=false;
	boolean down=false;
	boolean right=false;
	boolean left=false;
	boolean sign=false;
	static int maxofmap =80;
	//记得改成80
	static Map[][] map = new Map[maxofmap][maxofmap];
	Point1[][] Find= new Point1[2][6400];
	
	public Map(boolean b, boolean c, boolean d, boolean e,boolean f) {
		up=b;
		down=c;
		right=d;
		left=e;
		sign=f;
	}
	public static void InitSign(){
		for(int i=0;i<maxofmap;i++){
			for(int j=0;j<maxofmap;j++){
				map[i][j].sign=false;
			}
		}
	}
	public static void InitDirection(){
		for(int i=0;i<maxofmap;i++){
			for(int j=0;j<maxofmap;j++){
				map[i][j]=new Map(false,false,false,false,false);
			}
		}
	}
	public static void getmap(){
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
	
	public ArrayList<Point1> shortway(Point1 start,Point1 end){
		ArrayList<Point1> path = new ArrayList<Point1>();		
		if(Point1.equal(start,end)){
			System.out.println("Start point euals end point!");
			return null;
		}
		else{
			int x=0;
			int y=0;		
			initFinding();
			InitSign();
			map[start.X][start.Y].sign=true;			
			Point1 p = start;
			ArrayList<Point1> plist =new ArrayList<Point1>();
			ArrayList<Point1> plist2 =new ArrayList<Point1>();
			plist.add(p);
			int i=0;
			while(true){
				for(i=0;i<plist.size();i++){
					p=plist.get(i);
					if(map[p.X][p.Y].down&&(!map[Point1.DOWN(p).X][Point1.DOWN(p).Y].sign)){
						Find[1][getpointoffind(Point1.DOWN(p))]=p;
						map[Point1.DOWN(p).X][Point1.DOWN(p).Y].sign=true;
						plist2.add(Point1.DOWN(p));
					}
					if(map[p.X][p.Y].up&&(!map[Point1.UP(p).X][Point1.UP(p).Y].sign)){
						Find[1][getpointoffind(Point1.UP(p))]=p;
						map[Point1.UP(p).X][Point1.UP(p).Y].sign=true;
						plist2.add(Point1.UP(p));
					}
					if(map[p.X][p.Y].right&&(!map[Point1.RIGHT(p).X][Point1.RIGHT(p).Y].sign)){
						Find[1][getpointoffind(Point1.RIGHT(p))]=p;
						map[Point1.RIGHT(p).X][Point1.RIGHT(p).Y].sign=true;
						plist2.add(Point1.RIGHT(p));
					}
					if(map[p.X][p.Y].left&&(!map[Point1.LEFT(p).X][Point1.LEFT(p).Y].sign)){
						Find[1][getpointoffind(Point1.LEFT(p))]=p;
						map[Point1.LEFT(p).X][Point1.LEFT(p).Y].sign=true;
						plist2.add(Point1.LEFT(p));
					}
					if(Point1.equal(p,end)){
						break;
					}
					
				}
				plist.addAll(plist2);
				plist2.clear();
				if(Point1.equal(p,end)){
					break;
				}	
			}
			if(Point1.equal(p,end)){
				while(!Point1.equal(p,start)){
					path.add(p);
//					System.out.println(getpointoffind(p));
					p=Find[1][getpointoffind(p)];						
				}
				path.add(p);
				return path;
			}
					
				
				
		
		}
		return null;
	}
	
	/*
	static int maxofnewpoint=100;
	//记得改。。
	static NewPoint1[][] Find =new NewPoint1[maxofnewpoint][maxofnewpoint];
	//1000大小是否合适
	public static void initFinding(){
		for(int i=0;i<maxofnewpoint;i++){
			for(int j=0;j<maxofnewpoint;j++){
				Point1 p = new Point1(0,0);
				Find[i][j]=new NewPoint1(p,-1);
			}
		}
	}
	*/
	
	
	public void initFinding(){
		for(int i=0;i<6400;){
			for(int j=0;j<80;j++){
				for(int h=0;h<80;h++){				
					Find[0][i++]=new Point1(j,h);
				}
			}
		}
		for(int i=0;i<6400;i++){
			Find[1][i]=new Point1(-1,-1);
		}
	}
	public int getpointoffind(Point1 p){
		for(int i=0;i<6400;i++){
			if(Point1.equal(Find[0][i],p)){
				return i;
			}
		}
		return -1;
	}
	
	public Point1 father(Point1 p){
		for(int i=0;i<6400;i++){
			if(Point1.equal(Find[0][i],p)){
				return Find[1][i];
			}
		}
		return new Point1(-1,-1);
	}
}
