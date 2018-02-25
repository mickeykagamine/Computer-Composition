package oo5;
import java.io.File;

public class WorkSpace extends Thread{
	private File F;	
	private File F2;
	private boolean filesign=false;	
	private String type;
	private String touch;
	int num = 0;
	private List[] l = new List[100];
	private List[] l2 = new List[100];
	public WorkSpace(File f,String n,String sign){
		F = f;
		F2 = f;
		if(f.isFile()){
			filesign=true;
		}
		type = n;
		touch = sign;
		for(int i=0;i<100;i++){
			l[i] = new List();
		}
		for(int i=0;i<100;i++){
			l2[i] = new List();
		}
	}
	public void getfile(File f,List[] l){
		l[num].Name=f.getName();
		l[num].Modified=f.lastModified();
		l[num].Path=f.getAbsolutePath();
		l[num].Size=f.length();
		num++;
	}
	
	public long contentlength(File f){
		File[] files=f.listFiles();
		long length=0;
		for(int i=0;i<files.length;i++){
			if(files[i].isFile()){
				length=files[i].length()+length;
			}
		}
		return length;
	}
	public void getcontent(File f,List[] l){
		l[num].Name=f.getName();
		l[num].Modified=f.lastModified();
		l[num].Path=f.getAbsolutePath();
		l[num++].Size=contentlength(f);
		File[] files=f.listFiles(); 
		for(int i=0;i<files.length;i++){
			if(files[i].isFile()){
				getfile(files[i],l);
			}
			else if(files[i].isDirectory()){
				getcontent(files[i],l);
			}
		}
	}
	
	public void copy(List[] a,List[] b,int num){
		for(int i=0;i<num;i++){
			b[i].Name=a[i].Name;
			b[i].Modified=a[i].Modified;
			b[i].Path=a[i].Path;
			b[i].Size=a[i].Size;
		}
	}
	
	
	public void clear(List a){
		a.Name=null;
		a.Modified=0;
		a.Path=null;
		a.Size=0;
	}
	public void compare(List[] a,List[] b){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				if(a[i].Name==b[j].Name&&a[i].Modified==b[j].Modified&&a[i].Path==b[j].Path&&a[i].Size==b[j].Size){
					clear(a[i]);
					clear(b[i]);
				}
			}
		}
	}
	public void run(){
		
		if(F.isFile()){
			
			F=F.getParentFile();
			//不能直接弄成parent
		}
		getcontent(F,l);
		
		copy(l,l2,num);
		while(true){
			num=0;
		
			
			getcontent(F,l);
			
			//compare l ,l2;
			for(int i=0;i<num;i++){
				for(int j=0;j<num;j++){
					File f1=new File(l[i].Path);
			//		f1.getParent();
					
					if(l[i].Name.equals(l2[j].Name)&&l[i].Modified==l2[j].Modified&&l[i].Path.equals(l2[j].Path)&&l[i].Size==l2[j].Size){
						continue;
					}
					else if(!l[i].Name.equals(l2[j].Name)&&l[i].Modified!=l2[j].Modified&&!l[i].Path.equals(l2[j].Path)&&l[i].Size!=l2[j].Size||
							l[i].Name==""||l2[j].Name==""){
						continue;
					}
					//renamed					
					else if((!filesign|(filesign&&f1.getParent().equals(F2.getParent())))&&touch.equals("renamed")&&(!l[i].Name.equals(l2[j].Name))&&l[i].Modified==l2[j].Modified&&l[i].Size==l2[j].Size){
						System.out.println("");
						if(type.equals("record-summary")){
							RecordSummary.addwatchingsummary(l[i],"Renmaed");
						}
						else if(type.equals("record-detail")){
							RecordDetail.addwatchingdetail(l[i],l2[j].Name,l[i].Name,"Renamed");
						}
						else if(type.equals("recover")){
							//重命名文件
							File nf =new File(l[i].Path);
							if(nf.exists()){
								nf.renameTo(new File(l2[i].Path));
							}
							l[i].Path=l2[i].Path;
						}
					}
					//modified
					else if((!filesign|(filesign&&l[i].Path.equals(F2.getAbsolutePath())))&&touch.equals("modified")&&l[i].Name.equals(l2[j].Name)&&l[i].Modified!=l2[j].Modified&&l[i].Path.equals(l2[j].Path)){
						if(type.equals("record-summary")){
							System.out.println("");
							RecordSummary.addwatchingsummary(l[i],"Modified");
						}
						else if(type.equals("record-detail")){
							RecordDetail.addwatchingdetail(l[i],l2[j].Modified,l[i].Modified,"Modified");
						}
						else if(type.equals("recover")){
							System.out.println("Cannot recover when Modified!");
						}
					}				
					//Pathchanged
					else if((!filesign|(filesign&&l[i].Name.equals(F2.getName())))&&touch.equals("path-changed")&&l[i].Name.equals(l2[j].Name)&&l[i].Modified==l2[j].Modified&&(!l[i].Path.equals(l2[j].Path))&&l[i].Size==l2[j].Size){
						if(type.equals("record-summary")){
							RecordSummary.addwatchingsummary(l[i],"PathChanged");
						}
						else if(type.equals("record-detail")){
							RecordDetail.addwatchingdetail(l[i],l2[j].Path,l[i].Path,"PathChanged");
						}
						else if(type.equals("recover")){
							//原地址新加，新地址删除							
							File oldf =new File(l2[j].Path);
							
							File newf =new File(l[i].Path);
							newf.renameTo(oldf);
					//		newf.delete();
							//表单更新问题
							l[i].Path=l2[i].Path;
						}
					}
					//sizechanged
					else if((!filesign|(filesign&&l[i].Path.equals(F2.getAbsolutePath())))&&touch.equals("size-changed")&&l[i].Name.equals(l2[j].Name)&&l[i].Path.equals(l2[j].Path)&&l[i].Size!=l2[j].Size){
						if(type.equals("record-summary")){
							RecordSummary.addwatchingsummary(l[i],"SizeChanged");
						}
						else if(type.equals("record-detail")){
							RecordDetail.addwatchingdetail(l[i],l2[j].Size,l[i].Size,"SizeChanged");
						}
						else if(type.equals("recover")){
							System.out.println("Cannot recover when Sizechanged!");
						}
					}
				}
			}			
			copy(l,l2,num);
		}
		
	}
	

	
	
	
	
}
