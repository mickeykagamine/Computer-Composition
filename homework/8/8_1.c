#include<stdio.h>
#include<string.h>
#include<stdlib.h>
struct graph{
	int point1;
	int point2;
	int line;
	struct graph* r;
	struct graph* l;
	};
struct graph* array[1000];	
struct graph* chart[1000];
int m=0,judge=1;
int flag[1000]={0};

void visit(struct graph *p,int point){

	int i=0;
while(1){
	flag[p->point1]=1;
	if(p!=NULL&&flag[p->point2]==0&&flag[point-1]==0){

		chart[m++]=p;
		p=array[p->point2]->r;
			
		visit(p,point);	
		
	}
	
	if(judge==0)
	{
		return;
	}
	
	
	if(flag[p->point2]==1||chart[m-1]->point2==point-1){
	 
		if(chart[m-1]->point2==point-1){
			for(i=0;i<m;i++){
				printf("%d ",chart[i]->line);
			}
			printf("\n");
	 		 //弹出点 
			while(m>0){

				
				
				if(chart[m-1]->r!=NULL&&flag[p->point2]==0&&flag[point-1]==0){
	
					break;
				}
				if(p!=NULL&&flag[point-1]==0){
				
					break;
				} 
				
				flag[chart[m-1]->point2]=0;
	//			flag[chart[m-1]->point1]=0;
				
				p=chart[m-1]->r;
				m--;
				
				if(p==NULL){
					p=chart[m-1]->r;
				 	flag[chart[m-1]->point2]=0;	
					m--;
					
				}

				
				if(p==NULL&&m>0){
					p=chart[m-1]->r;
			//		flag[p->point2]=0;	
					flag[chart[m-1]->point2]=0;
					m--;
					
				}
				
				

			}
		
		}
	
		else{
			
				flag[chart[m-1]->point2]=0;
				if(p->r!=NULL){
					p=p->r;
				}
				else{
					if(chart[m-1]->r!=NULL){
					flag[chart[m-1]->point1]=0;
					p=chart[--m]->r;
					
					}
					else judge=0;
				}
					
					
		}
		if(p==NULL){
		judge=0;
		return;
	}
	
		}
		


} 
	
}



int main(){
	int edge,point,i,a,b,c,m=0,n;
	struct graph *p,*q,*branch;

	struct graph* top[1000];
	scanf("%d%d",&point,&edge);
	for(i=0;i<point;i++){
		p=(struct graph*)malloc(sizeof(struct graph));
		p->point1=i;
		p->line=0;
		p->r=NULL;
		p->l=p;
		array[i]=p;
		top[i]=array[i];
	}
	for(i=0;i<edge;i++){
		scanf("%d%d%d",&a,&b,&c);
		//建立一个方向 
		p=(struct graph*)malloc(sizeof(struct graph));
		p->line=a;
		p->point1=b;
		p->point2=c;
		p->r=NULL;
		q=array[b];
		if(q->r==NULL){
			q->r=p;
			p->l=array[b];
			top[b]=p;
		}
		else{
			for(q=array[b];q->r!=NULL;q=q->r){
				if(q->r->line>p->line){
					p->r=q;
					q->l->r=p;
					p->l=q->l;
					q->l=p;					
				}
			}
			if(q->line<p->line){
				q->r=p;
				p->l=q;
				top[b]=p;
			}
		}	 
		//建立另一个方向 
		p=(struct graph*)malloc(sizeof(struct graph));
		p->line=a;
		p->point1=c;
		p->point2=b;
		p->r=NULL;
		q=array[c];
		if(q->r==NULL){
			q->r=p;
			p->l=array[c];
//			top[b]=p;
		}
		else{
			for(q=array[c];q->r!=NULL;q=q->r){
				if(q->r->line>p->line){
					p->r=q;
					q->l->r=p;
					p->l=q->l;
					q->l=p;					
				}
			}
			if(q->line<p->line){
				q->r=p;
				p->l=q;
//				top[b]=p;
			}
		}	 
		
	}
	
	
 	visit(array[0]->r,point);

	
	return 0;
}


