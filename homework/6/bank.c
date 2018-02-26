#include<stdio.h>
#include<stdlib.h>
#include<string.h>
struct customer{
	 int id;
	 int waitime;
	 int n;
	 struct customer *next;
	 struct customer *last;
};
int main(){
	struct customer *head,*tail,*p,*q;
	int n,i,people,id=1,window=3,Waiting=0,Waitingn[100]={0},N,clock,cnt,m,cmp=0,Wn;
	head=NULL;
	scanf("%d",&N);
	for(i=1;i<=N;i++){
		scanf("%d",&people);
		Waitingn[i]=people;
		Waiting=Waiting+people;
		for(;id<=Waiting;id++){
			if(head==NULL){
				head=(struct customer*)malloc(sizeof(struct customer));
				head->id=id;
				head->n=i;
				q=(struct customer*)malloc(sizeof(struct customer));
				head->last=q;
				head->last->n=1;
				tail=head;
				tail->next=NULL;
				cnt=1;
			}
			else{
				p=(struct customer*)malloc(sizeof(struct customer));
				p->id=id;
				p->n=i;
				tail->next=p;
				p->last=tail;
				tail=p;
				tail->next=NULL;
				cnt++;
			}
		}
	}
	for(clock=1;Waiting>0;clock++){
	
		if(Waitingn[clock-1]!=0){	
			Waitingn[clock]=Waitingn[clock]+Waitingn[clock-1];
		}		
		if(clock<=N){
		while((Waitingn[clock]-(window*7))>0&&window<5){
			window++;
			}
		}
		Wn=Waitingn[clock];
		for(i=1,p=head;i<=window&&i<=Wn&&p!=NULL;i++){
//			if(p->last->n!=p->n&&Waitingn[clock-1]!=0){
//				break;
//			}
			printf("%d : %d\n",p->id,clock-p->n);
			p=p->next;
			head=p;	
			if(p==NULL){
				return 0;
			}
			Waitingn[clock]--;
			if(Waitingn[clock]<0){
				Waitingn[clock]=0;
			}
			
		}
/*		if(p->last->n!=p->n&&Waitingn[clock-1]!=0){ 
			for(;i<=window&&i<=Wn;i++){
				printf("%d:%d\n",p->id,clock-(p->n));
				p=p->next;
				head=p;
				if(cnt==0){
					return 0;
				}
				Waitingn[clock]--;
				if(Waitingn[clock]<0){
					Waitingn[clock]=0;
				}
				if(Waitingn[clock]/window<7&&window>3){
					window--;
				}			
			}
		
		}
		*/
		if((Waitingn[clock]-(window*7))<0&&window>3){
			window--;
		}

	}	
	return 0;
}


