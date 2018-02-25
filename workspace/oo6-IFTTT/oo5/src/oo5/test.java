package oo5;
import java.io.File;

public class test extends Thread{
	File F;
	public void run(){
		//deletefile(F);
		
	}
	public synchronized void rename(File f,String s){
		File ff = new File(s);
		if(f.isFile()){
			f.renameTo(ff);
		}
	}
	public synchronized void newfile(String s){
		File f= new File(s);
	}
	public synchronized void deletefile(File f){
		f.delete();
	}
}
