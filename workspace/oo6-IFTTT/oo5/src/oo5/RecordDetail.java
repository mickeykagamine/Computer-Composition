package oo5;
import java.io.File;

import java.nio.charset.Charset;

import oo5.FileWrite;

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

public class RecordDetail extends Thread{
	
	public synchronized static void addwatchingdetail(List f,long a,long b,String type){		
		if(type.equals("Modified")){
			String sss="Detail:"+f.Path+" Modified:"+"Old: "+a+" New:"+b;
			FileWrite.tofile(sss);
			System.out.println("Detail:"+f.Path+" Modified:"+"Old: "+a+" New:"+b);
		}
	
		else if(type.equals("SizeChanged")){
			String sss="Detail:"+f.Path+" Sizechanged:"+"Old: "+a+" New:"+b;
			FileWrite.tofile(sss);
			System.out.println("Detail:"+f.Path+" Sizechanged:"+"Old: "+a+" New:"+b);
		}		
	}
	public synchronized static void addwatchingdetail(List f,String a,String b,String type){		
		if(type.equals("Renamed")){
			String sss="Detail:"+f.Path+" Renamed:"+"Old: "+a+" New:"+b;
			FileWrite.tofile(sss);
			System.out.println("Detail:"+f.Path+" Renamed:"+"Old: "+a+" New:"+b);
		}
		else if(type.equals("PathChanged")){
			String sss="Detail:"+f.Path+" Pathchanged:"+"Old: "+a+" New:"+b;
			FileWrite.tofile(sss);
			System.out.println("Detail:"+f.Path+" Pathchanged:"+"Old: "+a+" New:"+b);
		}
	
				
	}
	
}
