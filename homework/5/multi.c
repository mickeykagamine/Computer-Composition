#include<stdio.h>
#include<stdlib.h>
struct Nodeptr{
	int n;
	int m;
	struct Nodeptr*next; 
}; 
int getexp(int a[]){
	int i=0;
	char c;
	do{
		scanf("%d%d%c",&a[i],&a[i+1],&c);
		i+=2;
	}while(c!='\n');
	return i;
}

int main(){
	struct Nodeptr *p,*q,*x,*y,*head1=NULL,*head2=NULL,*head3=NULL,*s,*r,*w;
	int i,N1,N2,temp1,temp2,exchange1,exchange2,mi,xi;
	int a[100],b[100];
	N1=getexp(a);
	N2=getexp(b);
	for(i=0;i<N1;i+=2){
		if(head1==NULL){
			head1=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
			head1->n=a[i];
			head1->m=a[i+1];
			head1->next=NULL;
			p=head1;
		}
		else{
			q=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
			q->n=a[i];
			q->m=a[i+1];
			p->next=q;
			q->next=NULL;
			p=q; 
		}
	}
	for(i=0;i<N2;i+=2){
		if(head2==NULL){
			head2=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
			head2->n=b[i];
			head2->m=b[i+1];
			head2->next=NULL;
			p=head2;
		}
		else{
			q=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
			q->n=b[i];
			q->m=b[i+1];
			p->next=q;
			q->next=NULL;
			p=q; 
		}
	}
/*	for(q=head1;q!=NULL;q=q->next){
		printf("%d %d ",q->n,q->m);
	}
	*/
	for(q=head1;q!=NULL;q=q->next){
		for(p=head2;p!=NULL;p=p->next){
			if(head3==NULL){
				head3=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
				head3->n=(q->n)*(p->n);
				head3->m=(q->m)+(p->m);
				head3->next=NULL;	
//				printf("1\n");			
			}
			
			else{
				mi=(p->m)+(q->m);
				xi=(p->n)*(q->n);
				for(s=head3;s!=NULL;s=s->next){
					if(mi==s->m){
						s->n=(s->n)+xi;
	//									printf("2\n");
						break;

					}
				}

				if(s==NULL){
					x=head3;
					for(s=head3;s!=NULL;s=s->next){
						if(mi>(s->m)){
							break;
						}
						else{
							x=s;
						}
//						printf("3\n");
					}
					
					y=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
					y->n=xi;
					y->m=mi;
					y->next=x->next;
					x->next=y;
			//		printf("%d",y->m);					
				}										
			}
		}
	}
	
	for(q=head3;q!=NULL;q=q->next){
		printf("%d %d ",q->n,q->m);
	}

/*
	s=head3=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
	head3->n=(q->n)*(p->n);
	head3->m=(q->m)+(p->m);
	head3->next=NULL;
	if(N1==2&&N2==2){
		printf("%d %d",head3->n,head3->m);
		return 0;
	}
	while(q!=NULL){
		for(p=head2;p!=NULL;p=p->next){
			temp1=(q->n)*(p->n);
			temp2=(q->m)+(p->m);
			s=head3;
			while(s!=NULL){
				if(temp2==s->m){
					s->n=s->n+temp1;
					break;
				}
				else{
					s=s->next;
				}
			}
			if(s==NULL){
				s=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
				s->n=temp1;
				s->m=temp2;
				s->next=NULL;
			}
		}
		q=q->next;		
	}
	for(q=head3;q!=NULL;q=q->next){
		for(p=q;p!=NULL;p=p->next){
			if((p->m)>(q->m)){
				exchange1=q->m;
				exchange2=q->n;
				q->m=p->m;
				q->n=p->n;
				p->m=exchange1;
				q->n=exchange2;
			}
		}
	}

*/
	return 0;
}



