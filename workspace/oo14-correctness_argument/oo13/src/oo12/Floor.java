package oo12;

class Floor{
	private static int floormax = 10;
	static double D;
	static double sum = 0;
	static int getfloormax(){
		return floormax;
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
}