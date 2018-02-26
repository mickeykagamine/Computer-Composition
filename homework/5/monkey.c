#include<stdio.h>
#include<stdlib.h>
struct Nodeptr{
	int n;
	struct Nodeptr *next;
	struct Nodeptr *last;
};
struct Nodeptr * creatList(int n){
	struct Nodeptr *q,*list=NULL;
	int i;
	q=list=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
	for(i=0;i<n;i++){
		q->n=i+1;
		q->next=malloc(sizeof(struct Nodeptr));
		q->next->last=q;
		q=q->next;
	}
	q->last->next=list;
	list->last=q->last;
	free(q);
	return list;
}
int main(){
	int n,m,f,i;
	struct Nodeptr *head,*q,*p;
	scanf("%d%d%d",&n,&m,&f);
	head=creatList(n);
	q=head;
	i=1;
	while(q->next!=q){
		if(i==m){
		//	printf("%d\n",q->n);
			q->last->next=q->next;
			q->next->last=q->last;
			p=q;
			q=q->next;
			free(p);
			i=1;
		}		
		else {
			i++;
			q=q->next;
		}
	}

	printf("%d",(q->n+f-1)%n);
}

