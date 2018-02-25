package oo6;

import java.util.ArrayList;
import java.util.ListIterator;

public class Message {
	/*Overview
	 * newtaxi´¢´æµÄÐÅÏ¢
	 * \all Time;
	 * Time>0;
	 * */
	long Time;
	Point1 start;
	Point1 end;
	Point1 spot;
	ArrayList<Point1> route;
	public boolean repOK(){
		/* @Requires:none;
		 * @Modifies:none;
		 * @Effects:check Message 
		 * 			if Message is right return true 
		 *  */
		if(Time<0){
			return false;
		}
		return true;
		
	}
	public Message(long t,Point1 s,Point1 e,Point1 sp,ArrayList<Point1> p){
		/* @Requires:long t,Point1 s,e,sp,ArrayList<Point1> p
		 * @Modifies:\this.Time,\this.start,\this.end,\this.spot,\this.route;
		 * @Effects:\this.Time==t,\this.start.equals(s),\this.end.equals(e),\this.spot.equals(sp),\this.route.equals(p);
		 * */
		Time=t;
		start=new Point1(s.X,s.Y);
		end=new Point1(e.X,e.Y);
		spot=new Point1(sp.X,sp.Y);
		route=new ArrayList<Point1>();
		route.addAll(p);
	}
	public ListIterator<Point1> returnroute(int i){
		/* @Requires:none
		 * @Modifies:none
		 * @Effects:return route;
		 * 
		 *  */
		ListIterator<Point1> iterList = route.listIterator();
		return iterList;
	}
	
}
