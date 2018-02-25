package oo6;

import oo6.Point1;

public class NewPoint1 {
	
	Point1 point;
	Point1 father;
	int last;
	public NewPoint1(Point1 p, Point1 f,int l) {
		/* @Requires:boolean b,c,d,e;
		 * @Modifies:\this.point,\this.down,\this.right,\this.left;	
		 * @Effects:\this.up==b;\this.down==c;\this.right==d;\this.left==e;	
		 * */
		point=p;
		father=f;
		last=l;
	}
}
