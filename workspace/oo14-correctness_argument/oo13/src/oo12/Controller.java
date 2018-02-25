package oo12;


public class Controller{

	static boolean first=true;
	
	/*Overview:调度器，选择fr er指令插入
	 * !((sign!=true&sign!=false)|(first!=true&first!=false))<==>repOK
	 * */
	public static boolean repOK(){
		/*
		 * @Require:none;
		 * @Modified:none;
		 * @Effects:\result==!((first!=true&first!=false));
		 * */
		if(first!=true&first!=false){
			return false;
		}
		return true;
	}
	
	
	
	static boolean judgefr(String s0,String s1,String s2,String s3,String[][] Queue,double Max){
	/*
	@Require:String s0,s1,s2,s3; s0.equals("FR"|"ER"),1=<s1&&s1<=Floor.floormax,s2.equals("UP"|"DOWN"),s3>=0;
	@Modified:first;
	@Effects:\result==!((Double.parseDouble(s3) < Max(Request.getLast()))||
			\all int m;0<=m&m<=1000;\exist m,s0.equals(Queue.getQueue()[m][0])&&s1.equals(Queue.getQueue()[m][1])&&s2.equals(Queue.getQueue()[m][2])&&s3.equals(Queue.getQueue()[m][3]))||
			(first&&!(Double.parseDouble(s1)==1&&s2.equals("UP")&&Double.parseDouble(s3)==0))；
		if sign first==false;
	*/
		boolean sign = true;
		String s=s1;
//		double Max=Max(Request.getLast());
		if(Double.parseDouble(s3) < Max){
			sign = false;
			System.out.println("INVALID[("+s0+","+s1+","+s2+","+s3+")]");
		}
		for(int m =0; m<1000 ;m++){
			if(s0.equals(Queue[m][0])&&s1.equals(Queue[m][1])&&s2.equals(Queue[m][2])&&s3.equals(Queue[m][3]))
			{
				sign=false;
				System.out.println("SAME[("+s0+","+s1+","+s2+","+s3+")]");
			}
		}
		if(first&&!(Double.parseDouble(s1)==1&&s2.equals("UP")&&Double.parseDouble(s3)==0)){
			sign = false; 
			System.out.println("INVALID[("+s0+","+s1+","+s2+","+s3+")]");
		}
		if(sign){
			first=false;
		}				
		return sign;
	}
	
	static boolean judgeer(String s0,String s1,String s2,String[][] Queue,double Max){
	/*
	@Require:String s0,s1,s2,s3; s0.equals("FR"|"ER"),1=<s1&&s1<=Floor.floormax,s2.equals("UP"|"DOWN"),s3>=0;
	@Modified:first;
	@Effects:\result==!((Double.parseDouble(s2) < Max)||
			\all int m;0<=m&m<=1000;\exist m,s0.equals(s0.equals(Queue.getQueue()[m][0])&&s1.equals(Queue.getQueue()[m][1])&&s2.equals(Queue.getQueue()[m][2]))||
			(first);
	*/
		
		String s=s1;
		boolean sign = true;
//		double Max=Max(Request.getLast());
		if(Double.parseDouble(s2) < Max){
			sign = false;
				System.out.println("INVALID[("+s0+","+s1+","+s2+")]");
		}
		for(int m =0; m<1000 ;m++){
			if(s0.equals(Queue[m][0])&&s1.equals(Queue[m][1])&&s2.equals(Queue[m][2]))
			{
				sign=false;
				System.out.println("SAME[("+s0+","+s1+","+s2+")]");
			}
		}
		if(first){
			sign = false;
			System.out.println("INVALID[("+s0+","+s1+","+s2+")]");
		}				
		return sign;
	}
}