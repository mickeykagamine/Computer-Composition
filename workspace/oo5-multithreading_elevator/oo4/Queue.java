package oo4;

class Queue{
	private static long[][] queue = new long[1000][1000];
	private static int n = 0;
	
	public synchronized static void insert(int s0,int s1,int s2,int s3,long s4){					
			
			queue[n][0]=s0;
			queue[n][1]=s1;
			queue[n][2]=s2;
			queue[n][3]=s3;
			queue[n++][4]=s4;
	System.out.println("Success insert into the Queue:("+s1+","+s2+","+s3+","+s4+")");		
	}
	
	public synchronized static void delete(int n){
		//synchronizedµ•∂¿”√∑®= =		
		queue[n][0] = 0;
		queue[n][1] = 0;
		queue[n][2] = 0;
		queue[n][3] = 0;
		queue[n][4] = 0;			
}
	public static long[][] getQueue() {
		return queue;
	}
	public static void setQueue(int i ,int j ,int a) {
		Queue.queue[i][j]= a;
	}

	public static int getN() {
		return n;
	}

	public static void setN(int n) {
		Queue.n = n;
	}

	
}