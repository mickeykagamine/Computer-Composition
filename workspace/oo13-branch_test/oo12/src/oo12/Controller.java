package oo12;


public class Controller{
	private static boolean sign=true;
	static boolean first=true;
	static double D;
	static double sum = 0;
	/*Overview:调度器，选择fr er指令插入，并且有一个能计算sum的函数
	 * !((D<0)|(sum<0))<==>repOK
	 * */
	public static boolean repOK(){
		/*
		 * @Require:none;
		 * @Modified:none;
		 * @Effects:\result==!((D<0)|(sum<0));
		 * */
		if(D<0){
			return false;
		}
		if(sum<0){
			return false;
		}
		return true;
	}
	static double Max(double[] last){
		/*
		@Require:double[] last;
		@Modified:None;
		@Effects:\all int h;0<=h&&h<=10000;\result==\max(last[h]);
		*/
		double max = last[0];
		for(int h = 0;h < 10000;h++){
			if(max < last[h]){
				max = last[h];
			}
		}		
		return max;
	}
	static int findj(int m,int max,String[][] Queue){
	/*
	@Require:int m,max;String[][] Queue;
	@Modified:None;
	@Effects:\all int j;m<=j&&j<=max;if \exist j;Queue[j][0]!=null;\result==j;else \result==-1;
	*/
		int j;
		int sign3 = 0;
		for(j=m;j<max+1;j++){
			if (Queue[j][0]!=null){
				sign3=1;
				break;
			}
			else {
				continue;
			}			
		}
		if(sign3==1){
			return j;
		}
		else return -1;
	}
	static double Sum(int m,String[][] Queue ){
	/*
	@Require:int m;String[][] Queue;
	@Modified:None;
	@Effects:int D=|NewElevator.floor-Queue[j].floor|,int sum=0;\all int j;Queue[j]!=null,0<=j&&j<=Queue.length;sum=\max(Queue[j].time,sum)+D*0.5+1;
		\result==sum;
	*/	
		
		int j1 = 0;	
		int j2 = 0;
		j1=findj(0,m,Queue);
			
		if(Double.parseDouble(Queue[j1][1])-NewElevator.getFloor() < 0){
			D = NewElevator.getFloor()-Double.parseDouble(Queue[j1][1]);
		}
		else {
			D = Double.parseDouble(Queue[j1][1])-NewElevator.getFloor();
		}
		if(Queue[j1][0].equals("FR")){
			sum = D*0.5+1+Double.parseDouble(Queue[j1][3]);
		}
		else if(Queue[j1][0].equals("ER")){
			sum = D*0.5+1+Double.parseDouble(Queue[j1][2]);
		}
		
		
		for(j2=findj(j1+1,m,Queue);j2<=m;j2=findj(j2+1,m,Queue),j1=j2){
			if(j2==-1){
				break;
			}
			if(Double.parseDouble(Queue[j2][1])-Double.parseDouble(Queue[j1][1]) < 0){
				D = Double.parseDouble(Queue[j1][1])-Double.parseDouble(Queue[j2][1]);
			}
			else {
				D = Double.parseDouble(Queue[j2][1])-Double.parseDouble(Queue[j1][1]);
			}
			if(Queue[j2][0].equals("FR")){
				if(sum>Double.parseDouble(Queue[j2][3])){
					sum=sum+(D*0.5)+1;
				}
				if(sum<=Double.parseDouble(Queue[j2][3])){
					sum=Double.parseDouble(Queue[j2][3])+(D*0.5)+1;
				}
			}
			else if(Queue[j2][0].equals("ER")){
				if(sum>Double.parseDouble(Queue[j2][2])){
					sum=sum+(D*0.5)+1;
				}
				if(sum<=Double.parseDouble(Queue[j2][2])){
					sum=Double.parseDouble(Queue[j2][2])+(D*0.5)+1;
				}
			}
			
		}																			
		return sum;
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
		sign = true;
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
	@Effects:\result==!((Double.parseDouble(s2) < Max(Request.getLast()))||
			\all int m;0<=m&m<=1000;\exist m,s0.equals(s0.equals(Queue.getQueue()[m][0])&&s1.equals(Queue.getQueue()[m][1])&&s2.equals(Queue.getQueue()[m][2]))||
			(first)；
		if sign first==false;
	*/
		
		String s=s1;
		sign = true;
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