package oo5;
import java.io.File;

import java.nio.charset.Charset;
import java.text.DecimalFormat;

import java.io.FileOutputStream;
import java.io.IOException;


public class RecordSummary extends Thread{
	static int n0 = 0;
	static int n1 = 0;
	static int n2 = 0;
	static int n3 = 0;
	public void run(){
		
	}
	
	public synchronized static void addwatchingsummary(List f,String type){
		if(type.equals("Renamed")){
			String sss="Summary:"+f.Path+" Renamed "+(++n0);
			FileWrite.tofile(sss);
			System.out.println("Summary:"+f.Path+" Renamed "+(n0));
		}
		else if(type.equals("Modified")){
			String sss="Summary:"+f.Path+" Modified "+(++n1);
			FileWrite.tofile(sss);
			System.out.println("Summary:"+f.Path+" Modified "+(n1));
		}
		else if(type.equals("PathChanged")){
			String sss="Summary:"+f.Path+" Path-changed "+(++n2);
			FileWrite.tofile(sss);
			System.out.println("Summary:"+f.Path+" Path-changed "+(n2));
		}
		else if(type.equals("SizeChanged")){
			String sss="Summary:"+f.Path+" Size-changed "+(++n3);
			FileWrite.tofile(sss);
			System.out.println("Summary:"+f.Path+" Size-changed "+(n3));
		}		
	}
}
