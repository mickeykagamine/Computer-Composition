package oo5;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

public class Monitor {
	static boolean end=false;
	static boolean[][] List = new boolean[100][16];
	public static void InitList(){
		for(int i = 0;i < 100;i++){
			for(int j = 0;j < 6;j++){
				List[i][j]=false;
			}
		}
	}
	static String[] FileList =new String[100];
	public static int GetNumOfList(String s){
		for(int i=0;i<100;i++){
			if(s.equals(FileList[i])){
				return i;
			}
		}
		return -1;
	}
		
	static int num = 0;
	public static void main(String args[]){
		RecordSummary summary = new RecordSummary();
		summary.start();
		
		RecordDetail detail = new RecordDetail();
		detail.start();
		
		InitList();
		
		
		Scanner s = new Scanner(System.in);	
		while(true){
			String task = s.nextLine();
			if(task.equals("end")){
				break;
			}
			if(FormCheck(task) == true){
				System.out.println("Formcheck ok:"+task);
				String[] Split = task.split(" ");
				Split[1] = Split[1].replace("[", "");
				Split[1] = Split[1].replace("]", "");
				File f = new File(Split[1]);					
				//文件地址为文件夹
				if(f.isFile()||f.isDirectory()){					
					WorkSpace space = new WorkSpace(f,Split[4],Split[2]); 
					space.start();
				}									
															
				//文件地址无效时
				else{
					System.out.println("File Path Invalid");
				}					
					
				
			}
			else{
				System.out.println("Invalid Input");
			}
		}
		
	}
	static boolean FormCheck(String s){
		Pattern p0 = Pattern.compile("IF \\[.{1,}\\] (renamed|modified|path-changed|size-changed) THEN (recover|record-detail|record-summary)");
		//                            IF [D:\test\demo.cpp] renamed THEN recover
		Matcher match0 = p0.matcher(s);
		
		if(match0.matches()){
			return true;
		}
		else {
			return false;
		}	
	}
	
}

