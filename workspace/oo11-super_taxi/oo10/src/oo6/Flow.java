package oo6;

import oo6.Flow;
import oo6.Map;

public class Flow {
	/*Overview
	 * 存取各个路的流量
	 * \all int i,j;
	 * 0<i&i<Map.maxofmap;j<0&j<Map.maxofmap;
	 * repOK<==>!(flow[i][j].up<0||flow[i][j].down<0||flow[i][j].right<0||flow[i][j].left<0)==ture;
	 * 
	 * */
	int up=0;
	int down=0;
	int right=0;
	int left=0;
	static Flow[][] flow=new Flow[Map.maxofmap][Map.maxofmap];
	public boolean repOK(){
		/* @Requires:none;
		 * @Modifies:none;
		 * @Effects:check flow 
		 * 			if flow is right return true 
		 *  */
		for(int i=0;i<Map.maxofmap;i++){
			for(int j=0;j<Map.maxofmap;j++){
				if(flow[i][j].up<0||flow[i][j].down<0||flow[i][j].right<0||flow[i][j].left<0){
					return false;
				}
			}
		}
		return true;
		
	}
	
	public Flow(int b, int c, int d, int e) {
		/* @Requires:int b,c,d,e;
		 * @Modifies:\this.down,\this.up,\this.left,\this.right;
		 * @Effects:\this.down==b;\this.up==c;\this.left==d;\this.right==e;
		 * */
		down=b;
		up=c;
		left=d;
		right=e;
	}
	static void initflow(){
		/* @Requires:none;
		 * @Modifies:flow;
		 * @Effects:\all int i,j;
		 * 			0<i&i<Map.maxofmap,0<j&j<Map.maxofmap;
		 * 			flow[i][j]==Flow(0,0,0,0);
		 * */
		for(int i=0;i<Map.maxofmap;i++){
			for(int j=0;j<Map.maxofmap;j++){
				flow[i][j]=new Flow(0,0,0,0);
			}
		}
//		System.out.println("init flow sucess!!");
		/*
		int i=0;
		int j=0;
		//down up left right
		
		flow[0][0]=new Flow(2,0,0,0);
		flow[0][1]=new Flow(1,0,0,1);
		flow[0][2]=new Flow(1,0,1,0);
		flow[1][0]=new Flow(0,2,0,0);
		flow[1][1]=new Flow(0,1,0,0);
		flow[1][2]=new Flow(1,1,0,0);
		flow[2][0]=new Flow(0,0,0,0);
		flow[2][1]=new Flow(0,0,0,0);
		flow[2][1]=new Flow(0,1,0,0);
		*/
	}
	static synchronized void addflow(int i,int j,String dir){
		/* @Requires:int i,j;String dir;
		 * @Modifies:flow;
		 * @Effects:flow[i][j].dir==\old\flow[i][j].dir+1;	
		 * @THREAD_REQUIRES:\locked(flow);
		 * @THREAD_EFFECTS:\locked();
		 * */
	//	System.out.println("flow "+i+" "+j);
		if(dir.equals("up")&&Map.map[i][j].up){
			flow[i][j].up++;
		}
		else if(dir.equals("down")&&Map.map[i][j].down){
			flow[i][j].down++;
		}
		else if(dir.equals("right")&&Map.map[i][j].right){
			flow[i][j].right++;
		}
		else if(dir.equals("left")&&Map.map[i][j].left){
			flow[i][j].left++;
		}
	}
	static synchronized void subflow(int i,int j,String dir){
		/* @Requires:int i,j;String dir;
		 * @Modifies:flow;	
		 * @Effects:flow[i][j].dir==\old\flow[i][j].dir-1;	
		 * @THREAD_REQUIRES:\locked(flow);
		 * @THREAD_EFFECTS:\locked();
		 * */
		if(dir.equals("up")){
			flow[i][j].up--;
		}
		else if(dir.equals("down")){
			flow[i][j].down--;
		}
		else if(dir.equals("right")){
			flow[i][j].right--;
		}
		else if(dir.equals("left")){
			flow[i][j].left--;
		}
	}
}
