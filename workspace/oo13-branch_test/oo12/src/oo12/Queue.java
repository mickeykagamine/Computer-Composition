package oo12;

class Queue{
	/*Overview:请求队列，储存请求
	 * !(n<0)<==>repOK
	 * */
	private static String[][] queue = new String[10000][10000];
	static int n = 0;
	public static boolean repOK(){
		/*
		 * @Require:none;
		 * @Modified:none;
		 * @Effects:\result==!(n<0);
		 * */
		if(n<0){
			return false;
		}
		return true;
	}
	static int insetfr(boolean sign,String s0,String s1,String s2,String s3,String[][] Queue){
		/*
		 * @Require:boolean sign,String s0,s1,s2,s3;
		 * @Modified:Queue;
		 * @Effects:if sign,getQueue()[n][0] == s0,getQueue()[n][1] == s1,getQueue()[n][2] == s2,getQueue()[n][3] == s3;\result==n;
		 * */
		if(sign == true){
		Queue[n][0] = s0;
		Queue[n][1] = s1;
		Queue[n][2] = s2;
		Queue[n][3] = s3;
	//	System.out.println("Queue.insetfr(true,\""+s0+"\",\""+s1+"\",\""+s2+"\",\""+s3+"\",Queue.getQueue());");
		n++;
		}
		return n;
	}
	static int inseter(boolean sign,String s0,String s1,String s2,String[][] Queue){
		/*
		 * @Require:boolean sign,String s0,s1,s2;
		 * @Modified:Queue;
		 * @Effects:if sign,getQueue()[n][0] == s0,getQueue()[n][1] == s1,getQueue()[n][2] == s2;\result==n;
		 * */
		if(sign == true){
		Queue[n][0] = s0;
		Queue[n][1] = s1;
		Queue[n][2] = s2;
	//	System.out.println("Queue.inseter(true,\""+s0+"\",\""+s1+"\",\""+s2+"\",Queue.getQueue());");
		n++;
		}
		return n;
	}
	public static String[][] getQueue() {
		/*
		@Require:none;
		@Modified:none;
		@Effects:\result==queue;
		*/
		return queue;
	}

}