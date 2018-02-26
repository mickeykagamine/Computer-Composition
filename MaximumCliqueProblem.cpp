#include<stdio.h>
#include<stdlib.h> 
#include<time.h>
int array[4000][4000],resultt[4000][4000],arrayN,result[4000];
double array2[4000],posibility[4000];
int pos(int N,int random,double p[]){//choose number
	int j,result=0;
	double k=0,kk=0;
	for(j=0;j<N;j++){
		result=result+p[j];
	}
	for(j=0;j<N;j++){
		k=k+(double)p[j];
		if(j==0){
			kk=0;
		}
		else{
			kk=k-(double)p[j];
		}
		if(random>=(kk*4000/result)&&random<(k*4000/result)){
			return j;
		}
	}
}

int antfindresult(int ant,int N){
	int i,ant2,cnt=0,j,r=0,Random,temp=0,newant;
	double ants[4000]={0};
	for(i=0;i<N;i++){
		result[i]=0;			
	}
	result[ant]=1;

	while(1){
		cnt=0;
		for(i=0;i<N;i++){
			ants[i]=0;
			
		}
		for(j=0;j<N;j++){
			for(i=0;i<N;i++){
				if(result[i]==1){
					if(array[j][i]==0||i==j){
					cnt=0;
					break;
					}
					if(array[j][i]==1&&i!=j){
						cnt=1;
					}
				}
			}
			if(cnt){
				ants[j]=1;
			}
		}
		temp=0;
		for(i=0;i<N;i++){
			temp=ants[i]+temp;
		}
		if(temp){
//			srand(time(0));
			Random=rand()%4000;
			newant=pos(N,Random,ants);
			result[newant]=1;	
		}
		if(!temp){

			for(i=0;i<N;i++){
				r=r+result[i];
			}
			return r;
		}
	}
}

void changeposibility(int N,int maxi){
	int i;
	for(i=0;i<N;i++){
		if(resultt[maxi][i]==1){
			posibility[i]=posibility[i]*1.01;
		}
	}
}

int findmax(int N,int array[]){
	int i,max=0;
	for(i=0;i<N;i++){
		if(max<array[i]){
			max=array[i];
		}
	}
	return max;
}

int findmaxi(int N,int array[]){
	int i,max=0,maxi;
	for(i=0;i<N;i++){
		if(max<array[i]){
			max=array[i];
			maxi=i;
		}
	}
	return maxi;
}
int main(){
	int i,j,n,m,N,k,line,ant[4000],random[1000]={0},Random,RESULT[4000]={0},findresult[4000],REESULT,maxi,nothing;
	FILE *fp;
	fp=fopen("test.txt","r");
	fscanf(fp,"%d%d",&N,&line);
//	scanf("%d%d",&N,&line);
	//initial posibility
	for(i=0;i<N;i++){
		posibility[i]=1000;
	}
	//get graph
	for(i=0;i<line;i++){
		fscanf(fp,"%x%d%d",&nothing,&n,&m);
	//	scanf("%d%d",&n,&m);
		array[n][m]=1;
		array[m][n]=1;
		array[m][m]=1;
		array[n][n]=1;
		posibility[n]++;
		posibility[m]++;
	}
	// get random number
	//	srand(time(0));
		Random=rand()%4000;
		j=pos(N,Random,posibility);
	i=j;
	k=j;
	array2[j]=1;
	arrayN=0;			
	while(i>0){
		if(array[i][j]==1){
			array2[i]=1;
			arrayN++;
		}
	i--;
	}
	while(k<N){
		if(array[k][j]==1){
			array2[k]=1;
			arrayN++;
		}
		k++;
	}
	for(i=0;i<5000;i++){//add ant
		for(j=0;j<N;j++){
			posibility[j]=posibility[i]*0.9;
		} 
		for(j=0;j<100;j++){
		//	srand(time(0));
			Random=rand()%4000;
			ant[j]=pos(N,Random,array2);
			findresult[j]=antfindresult(ant[j],N);
			for(k=0;k<N;k++){
				resultt[j][k]=result[k];
			}
		}
		RESULT[i]=findmax(N,findresult);
		printf("%d ",RESULT[i]);
//		maxi=findmaxi(N,findresult);
 //		for(j=0;j<N;j++){
//			if(resultt[maxi][j]!=0){
//				printf("%d ",j);
//			}
//		} 
//		printf("\n");
	//	printf("%d\n",maxi);
		changeposibility(N,maxi);
	}
	REESULT=findmax(N,RESULT);
	printf("%d",REESULT);
} 
