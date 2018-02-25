package oo12;


class NewElevator{
	/*Overview:电梯，自带小调度，判断选择哪些指令入队
	 * 
	 * repOK<==>!((floor<=0||floor>10)|(headingfloor<=0||headingfloor>10)|(!(direction.equals("UP")|(direction.equals("DOWN")|(direction.equals("STILL")))))
	 * */
	static int floor;
	static String direction;
	static double headingfloor;
	static String[][] Waitinglist = new String[1000][1000];
	public static boolean repOK(){
		/*
		 * @Require:none;
		 * @Modified:none;
		 * @Effects:\result==!((floor<=0||floor>10)|(headingfloor<=0||headingfloor>10)|(!(direction.equals("UP")|(direction.equals("DOWN")|(direction.equals("STILL")))));
		 * */
		if(floor<=0||floor>10){
			return false;
		}
		if(headingfloor<=0||headingfloor>10){
			return false;
		}
		if(!(direction.equals("UP")|(direction.equals("DOWN")|(direction.equals("STILL"))))){
			return false;
		}
		return true;
	}
	static int run(String[][] Queue){
		/*
		 * @Require:String[][] Queue;
		 * @Modified:Queue.queue,int floor,String diection,double headinglfloor,String[][] Waitinglist;
		 * @Effects:move the elevator according to the require;
		 * */
		setFloor(1);
		direction = "NULL";
		headingfloor = 0;
		int i = 0;
		int m = 0;
		int x = 0;
		int counting = 0;
		int lastt = 0;
		double t = 0;
		boolean sign = true;
		boolean running = false;
		boolean[] first = new boolean[1000];
		int[] last = new int[1000];
		int[] Stoplist = new int [1000];
		int h = 0;		
		for(i=0;i<1000;i++){
			first[i]=true;
			last[i]=0;
			Stoplist[i]=0;
		}
		while(true){
			for(i=0;i<1000;i++){
				if(last[i]==0){
					lastt=i;
					break;
				}
			}
			
			for(m = lastt;m < 1000;m++){ 
				sign = true;
				
				if(Queue[m][0]==null){
					m=Controller.findj(m,1000,Queue);
					if(m==-1){
						break;
					}
				}
				
				
				///////FR
				if (Queue[m][0].equals("FR")){
					if(Double.parseDouble(Queue[m][3]) <= t){
						//add the main request
						if(!running){
							if (Double.parseDouble(Queue[m][1]) == getFloor()){							
									direction="STLL";
									t++;
								
									System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+","+Queue[m][3]+")]/("+getFloor()+","+direction+","+t+")");
									Stoplist[h++]=floor;
									Queue[m][0] = null;
									Queue[m][1] = null;
									Queue[m][2] = null;
									Queue[m][3] = null;
									last[m] = 1;
								
							}
							else if (Double.parseDouble(Queue[m][1]) < getFloor()){
								direction = "DOWN";
								running = true;
								headingfloor = Double.parseDouble(Queue[m][1]);
								Waitinglist[x][0]=Queue[m][0];
								Waitinglist[x][1]=Queue[m][1];
								Waitinglist[x][2]=Queue[m][2];
								Waitinglist[x++][3]=Queue[m][3];
								counting++;
								Queue[m][0] = null;
								Queue[m][1] = null;
								Queue[m][2] = null;
								Queue[m][3] = null;
								last[m] = 1;
							//	break;
							}
							else if (Double.parseDouble(Queue[m][1]) > getFloor()){
								direction="UP";
								running = true;
								headingfloor = Double.parseDouble(Queue[m][1]);
								Waitinglist[x][0]=Queue[m][0];
								Waitinglist[x][1]=Queue[m][1];
								Waitinglist[x][2]=Queue[m][2];
								Waitinglist[x++][3]=Queue[m][3];
								counting++;
								Queue[m][0] = null;
								Queue[m][1] = null;
								Queue[m][2] = null;
								Queue[m][3] = null;
								last[m] = 1;
							//	break;
								
							}
						}
						//add other requests besides main request
						else if (running){
							/////UP
							if(Queue[m][2].equals(direction)&&direction.equals("UP")){
								if(getFloor() < Double.parseDouble(Queue[m][1])&&Double.parseDouble(Queue[m][1]) <= headingfloor){
									for(int n = 999;n >= 0;n--){			
												
											if(Queue[m][0].equals(Waitinglist[n][0])&&Queue[m][1].equals(Waitinglist[n][1])&&Queue[m][2].equals(Waitinglist[n][2])){
												if( SmarterController.Sum(n,Waitinglist)>=Double.parseDouble(Queue[m][3])){
													sign = false ;
													System.out.println("SAME[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+","+Queue[m][3]+")]");
													Queue[m][0] = null;
													Queue[m][1] = null;
													Queue[m][2] = null;
													Queue[m][3] = null;
													last[m] = 1;
													break;
												}
												else {
													break;
												}
											}				
													
									}	
									if(sign){
										Waitinglist[x][0]=Queue[m][0];
										Waitinglist[x][1]=Queue[m][1];
										Waitinglist[x][2]=Queue[m][2];
										Waitinglist[x++][3]=Queue[m][3];
										counting++;
										Queue[m][0] = null;
										Queue[m][1] = null;
										Queue[m][2] = null;
										Queue[m][3] = null;
										last[m] = 1;
									}
								}
								else if((getFloor() == Double.parseDouble(Queue[m][1])) && (Double.parseDouble(Queue[m][3]) < (t-1)) ){
									if(Stoplist[h-1]==floor){
										System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+","+Queue[m][3]+")]/("+getFloor()+",STILL,"+(t+1)+")");
										Stoplist[h++]=floor;
									}
									
									
									else{
										System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+","+Queue[m][3]+")]/("+getFloor()+","+direction+","+t+")");
										Stoplist[h++]=floor;
									}
									
									t++;
									Queue[m][0] = null;
									Queue[m][1] = null;
									Queue[m][2] = null;
									Queue[m][3] = null;
									last[m] = 1;
								}							
							}
							////DOWN
							else if(Queue[m][2].equals(direction)&&direction.equals("DOWN")){
							if((getFloor() > Double.parseDouble(Queue[m][1]))&&(Double.parseDouble(Queue[m][1]) >= headingfloor)){
									for(int n = 999;n >= 0;n--){			
												
											if(Queue[m][0].equals(Waitinglist[n][0])&&Queue[m][1].equals(Waitinglist[n][1])&&Queue[m][2].equals(Waitinglist[n][2])){
												if( SmarterController.Sum(n,Waitinglist)>=Double.parseDouble(Queue[m][3])){
													System.out.println("SAME[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+","+Queue[m][3]+")]");
													sign = false;
													Queue[m][0] = null;
													Queue[m][1] = null;
													Queue[m][2] = null;
													Queue[m][3] = null;
													last[m] = 1;	
													break;
												}
												else {
													break;
												}
											}				
													
									}
									
									if(sign){
										Waitinglist[x][0]=Queue[m][0];
										Waitinglist[x][1]=Queue[m][1];
										Waitinglist[x][2]=Queue[m][2];
										Waitinglist[x++][3]=Queue[m][3];
										counting++;
										Queue[m][0] = null;
										Queue[m][1] = null;
										Queue[m][2] = null;
										Queue[m][3] = null;
										last[m] = 1;
									}
								}
								else if((getFloor() == Double.parseDouble(Queue[m][1])) && (Double.parseDouble(Queue[m][3]) < (t-1)) ){
									if(Stoplist[h-1]==floor){
										System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+","+Queue[m][3]+")]/("+getFloor()+",STILL,"+(t+1)+")");
										Stoplist[h++]=floor;
									}
									/*
									else{
										System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+","+Queue[m][3]+")]/("+getFloor()+","+direction+","+t+")");
										Stoplist[h++]=floor;
									}
									*/
									t++;
									Queue[m][0] = null;
									Queue[m][1] = null;
									Queue[m][2] = null;
									Queue[m][3] = null;
									last[m] = 1;
								}							
							}
						}
					}
					else {
						break;
					}
				}
				////////ER
				else if (Queue[m][0].equals("ER")){
					if(Double.parseDouble(Queue[m][2]) <= t){
						//add the main request
						if(!running){
							if (Double.parseDouble(Queue[m][1]) == getFloor()){							
									direction="STLL";
									t++;
								
									System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+")]/("+getFloor()+","+direction+","+t+")");
									Stoplist[h++]=floor;
									Queue[m][0] = null;
									Queue[m][1] = null;
									Queue[m][2] = null;
									last[m] = 1;
								
							}
							else if (Double.parseDouble(Queue[m][1]) < getFloor()){
								direction = "DOWN";
								running = true;
								headingfloor = Double.parseDouble(Queue[m][1]);
								Waitinglist[x][0]=Queue[m][0];
								Waitinglist[x][1]=Queue[m][1];
								Waitinglist[x++][2]=Queue[m][2];
								counting++;
								Queue[m][0] = null;
								Queue[m][1] = null;
								Queue[m][2] = null;
								last[m] = 1;
							//	break;
							}
							else if (Double.parseDouble(Queue[m][1]) > getFloor()){
								direction="UP";
								running = true;
								headingfloor = Double.parseDouble(Queue[m][1]);
								Waitinglist[x][0]=Queue[m][0];
								Waitinglist[x][1]=Queue[m][1];
								Waitinglist[x++][2]=Queue[m][2];
								counting++;
								Queue[m][0] = null;
								Queue[m][1] = null;
								Queue[m][2] = null;
								last[m] = 1;
							//	break;
								
							}
						}
						//add other requests besides main request
						else if (running){
							if(direction.equals("UP")){
								if(getFloor() < Double.parseDouble(Queue[m][1])){
									if(Double.parseDouble(Queue[m][1])>headingfloor){
										headingfloor=Double.parseDouble(Queue[m][1]);
									}
									for(int n = 999;n >= 0;n--){			
									
											if(Queue[m][0].equals(Waitinglist[n][0])
												&&Queue[m][1].equals(Waitinglist[n][1])){
												if( SmarterController.Sum(n,Waitinglist)>=Double.parseDouble(Queue[m][2])){
													sign = false;
													System.out.println("SAME[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+")]");
													Queue[m][0] = null;
													Queue[m][1] = null;
													Queue[m][2] = null;
													last[m] = 1;
													break;
												}
												else {
													break;
												}
											}				
												
									}	
									if(sign){
										Waitinglist[x][0]=Queue[m][0];
										Waitinglist[x][1]=Queue[m][1];
										Waitinglist[x++][2]=Queue[m][2];
										counting++;
										Queue[m][0] = null;
										Queue[m][1] = null;
										Queue[m][2] = null;
										last[m] = 1;
									}
								}
								else if((getFloor() == Double.parseDouble(Queue[m][1])) && (Double.parseDouble(Queue[m][2]) < (t-1)) ){
								
									if(Stoplist[h-1]==floor){
										System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+")]/("+getFloor()+",STILL,"+(t+1)+")");
										Stoplist[h++]=floor;
									}
									else{
										System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+")]/("+getFloor()+","+direction+","+t+")");
										Stoplist[h++]=floor;
									}
									t++;
									Queue[m][0] = null;
									Queue[m][1] = null;
									Queue[m][2] = null;
									last[m] = 1;
								
								}	
														
							}
							else if(direction.equals("DOWN")){
								if(getFloor() > Double.parseDouble(Queue[m][1])){
									if(Double.parseDouble(Queue[m][1])<headingfloor){
										headingfloor=Double.parseDouble(Queue[m][1]);
									}
									for(int n = 999;n >= 0;n--){			
												
											if(Queue[m][0].equals(Waitinglist[n][0])&&Queue[m][1].equals(Waitinglist[n][1])){
												if( SmarterController.Sum(n,Waitinglist)>=Double.parseDouble(Queue[m][2])){
													sign = false;
													System.out.println("SAME[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+")]");
													Stoplist[h++]=floor;
													Queue[m][0] = null;
													Queue[m][1] = null;
													Queue[m][2] = null;
													last[m] = 1;
													break;
												}
												else {
													break;
												}
												
											}				
													
									}	
									if(sign){
										Waitinglist[x][0]=Queue[m][0];
										Waitinglist[x][1]=Queue[m][1];
										Waitinglist[x++][2]=Queue[m][2];
										counting++;
										Queue[m][0] = null;
										Queue[m][1] = null;
										Queue[m][2] = null;
										last[m] = 1;
									}
								}
								else if((getFloor() == Double.parseDouble(Queue[m][1])) && (Double.parseDouble(Queue[m][2]) < (t-1)) ){
									if(Stoplist[h-1]==floor){
										System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+")]/("+getFloor()+",STILL,"+(t+1)+")");
										Stoplist[h++]=floor;
									}
									/*
									else{
										System.out.println("[("+Queue[m][0]+","+Queue[m][1]+","+Queue[m][2]+")]/("+getFloor()+","+direction+","+t+")");
										Stoplist[h++]=floor;
									}
									*/
									t++;
									Queue[m][0] = null;
									Queue[m][1] = null;
									Queue[m][2] = null;	
									last[m] = 1;
								}							
							}
						}
					}
					else {
						break;
					}
				}
			}
			t = t + 0.5;
			if(direction.equals("UP")){
				setFloor(getFloor() + 1);
			}
			else if(direction.equals("DOWN")){
				setFloor(getFloor() - 1);
			}
			
			
			for(i = 0;i < x;i++){
				if(Waitinglist[i][1]==null){
					continue;
				}
				else{					
					if(getFloor() == Double.parseDouble(Waitinglist[i][1])){
						if(Waitinglist[i][0].equals("FR")){
							if(!first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								System.out.println("[("+Waitinglist[i][0]+","+Waitinglist[i][1]+","+Waitinglist[i][2]+","+Waitinglist[i][3]+")]/("+getFloor()+","+direction+","+(t-1)+")");
								Stoplist[h++]=floor;
							}
							if(first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								System.out.println("[("+Waitinglist[i][0]+","+Waitinglist[i][1]+","+Waitinglist[i][2]+","+Waitinglist[i][3]+")]/("+getFloor()+","+direction+","+t+")");
								Stoplist[h++]=floor;
							}
							
							if(first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								t++;
							}
							first[(int)(Double.parseDouble(Waitinglist[i][1]))] = false;
							Waitinglist[i][0]=null;
							Waitinglist[i][1]=null;
							Waitinglist[i][2]=null;
							Waitinglist[i][3]=null;
							counting--;
						}
						else if(Waitinglist[i][0].equals("ER")){
							if(!first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								System.out.println("[("+Waitinglist[i][0]+","+Waitinglist[i][1]+","+Waitinglist[i][2]+")]/("+getFloor()+","+direction+","+(t-1)+")");
								Stoplist[h++]=floor;
							}
							if(first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								System.out.println("[("+Waitinglist[i][0]+","+Waitinglist[i][1]+","+Waitinglist[i][2]+")]/("+getFloor()+","+direction+","+t+")");
								Stoplist[h++]=floor;
							}
							
							if(first[(int)(Double.parseDouble(Waitinglist[i][1]))]){
								t++;
							}
							first[(int)(Double.parseDouble(Waitinglist[i][1]))] = false;
							Waitinglist[i][0]=null;
							Waitinglist[i][1]=null;
							Waitinglist[i][2]=null;	
							counting--;
						}
						
					}
				}
				
			}
			if(counting==0){
				headingfloor=0;
				direction="NULL";
				running=false;
				for(i=0;i<1000;i++){
					first[i]=true;
				}
				boolean flag2 = false;
				for(i=0;i<1000;i++){
					if(Queue[i][0]!=null){
						flag2 = true;
						break;
					}
				}
				if(flag2==false){
					return 0;
				}
			}
			
		}
		
	}
	public static int getFloor() {
	/*
	@Require:none;
	@Modified:none;
	@Effects:\result==floor;
	*/
		return floor;
	}
	public static void setFloor(int floor) {
	/*
	@Require:int floor;
	@Modified:NewElevator.floor;
	@Effects:NewElevator.floor==floor;
	*/
		NewElevator.floor = floor;
	}
	
}
