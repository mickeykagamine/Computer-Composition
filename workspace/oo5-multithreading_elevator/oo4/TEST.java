package oo4;

public class TEST {
	private static long T=(System.currentTimeMillis());	
	static NewElevator e1 = new NewElevator(1,0,0,T,1);
	static NewElevator e2 = new NewElevator(1,0,0,T,2);
	static NewElevator e3 = new NewElevator(1,0,0,T,3);
	static Scheduler SS = new Scheduler();
	public static void main(String args[]){				    			
			input INPUT = new input();				
			INPUT.start();
			e1.start();
			e2.start();
			e3.start();	
			SS.start();
			
	}
	public static long getT() {
		return T;
	}
	public static void setT(long t) {
		T = t;
	}
}
