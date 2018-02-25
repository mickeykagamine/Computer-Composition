package oo6;

public class Point1 {
	int X=0;
	int Y=0;
	public static boolean equal(Point1 A,Point1 B){
		/* @Requires:Point1 A,B;
		 * @Modifies:none
		 * @Effects:\result==(A.X==B.X&&A.Y==B.Y)		
		 * */
		if(A.X==B.X&&A.Y==B.Y){
			return true;
		}
		else return false;
	}
	public Point1(int A,int B){		
		X=A;
		Y=B;
	}
	public static Point1 DOWN(Point1 A){
		/* @Requires:Point1 A;
		 * @Modifies:none
		 * @Effects:\result==((\old(A).X+1),\old(A).Y)	
		 * */
		Point1 B = new Point1(A.X+1,A.Y);
		return B;
	}
	public static Point1 UP(Point1 A){
		/* @Requires:Point1 A;
		 * @Modifies:none
		 * @Effects:\result==((\old(A).X-1),\old(A).Y)	
		 * */
		Point1 B = new Point1(A.X-1,A.Y);
		return B;
	}
	public static Point1 RIGHT(Point1 A){
		/* @Requires:Point1 A;
		 * @Modifies:none
		 * @Effects:\result==((\old(A).X),\old(A).Y+1)	
		 * */
		Point1 B = new Point1(A.X,A.Y+1);
		return B;
	}
	public static Point1 LEFT(Point1 A){
		/* @Requires:Point1 A;
		 * @Modifies:none
		 * @Effects:\result==((\old(A).X),\old(A).Y-1)	
		 * */
		Point1 B = new Point1(A.X,A.Y-1);
		return B;
	}
	
	public static boolean isUP(Point1 A,Point1 B){
		/* @Requires:Point1 A,B;
		 * @Modifies:none
		 * @Effects:\result==(A.X==B.X-1&&A.Y==B.Y)	
		 * */
		if(A.X==B.X-1&&A.Y==B.Y){
			return true;
		}
		return false;
	}
	public static boolean isDOWN(Point1 A,Point1 B){
		/* @Requires:Point1 A,B;
		 * @Modifies:none
		 * @Effects:\result==(A.X==B.X+1&&A.Y==B.Y)	
		 * */
		if(A.X==B.X+1&&A.Y==B.Y){
			return true;
		}
		return false;
	}
	public static boolean isRIGHT(Point1 A,Point1 B){
		/* @Requires:Point1 A,B;
		 * @Modifies:none
		 * @Effects:\result==(A.X==B.X&&A.Y==B.Y+1)
		 * */
		if(A.X==B.X&&A.Y==B.Y+1){
			return true;
		}
		return false;
	}
	public static boolean isLEFT(Point1 A,Point1 B){
		/* @Requires:Point1 A,B;
		 * @Modifies:none
		 * @Effects:\result==(A.X==B.X&&A.Y==B.Y-1)
		 * */
		if(A.X==B.X&&A.Y==B.Y-1){
			return true;
		}
		return false;
	}
}
