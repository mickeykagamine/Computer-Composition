package oo6;

import oo6.Point1;

public class NewPoint1 {
	/*Overview
	 * 新点类，用于计算最短路径记录父亲节点
	 * \all NewPoint1;
	 * repOK<==>!(last<0)==ture; 
	 * */
	Point1 point;
	Point1 father;
	int last;
	
	public boolean repOK(){
		/* @Requires:none;
		 * @Modifies:none;
		 * @Effects:check NewPoint1 
		 * 			if NewPoint1 is right return true 
		 *  */
		if(last<0){
			return false;
		}
		return true;
		
	}
	public NewPoint1(Point1 p, Point1 f,int l) {
		/* @Requires:Point1 p,f,int l;
		 * @Modifies:\this.point,\this.father,\this.last;	
		 * @Effects:\this.point==p;\this.father==f;\this.last==l;	
		 * */
		point=p;
		father=f;
		last=l;
	}
}
