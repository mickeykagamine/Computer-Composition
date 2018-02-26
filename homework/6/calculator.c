#include<stdio.h>
#include<stdlib.h>
#include<string.h>

struct OP{
	char op;
	struct OP *last;
};
struct ReversePolish{
	int num;
	char op;
};
struct Output{
	int num;
	struct Output *last;
};
int judge(char op){
	if(op=='+'||op=='-'){
		return 1;
	}
	else if(op=='*'||op=='/'){
		return 2;
	}
	else if(op=='('){
		return 3;
	}
	else{
		return 0;
	}
}
void deleteSpace(char a[]){
	int i,N,j;
	N=strlen(a);
	for(i=0;i<N+1;i++){
		if(a[i]==' '){
			for(j=i;j<N;j++){
				a[j]=a[j+1];
			}
		i--;
		}
	}
}
int main(){
	struct ReversePolish  Polish[100];
	struct OP *topOP,*headOP,*q;
	struct Output *topOUT,*headOUT,*r;
	char array[100];
	int i,number=0,m=0,cnt=0;
	gets(array);
// 	puts(array);
	deleteSpace(array);
	topOUT=NULL;
	for(i=0;array[i]!='=';i++){
		if(array[i]<='9'&&array[i]>='0'){
			number=0;
			while(array[i]<='9'&&array[i]>='0'){
					number=array[i]-'0'+number*10;
					i++;
			}
			i--;
			Polish[m].op=0;
			Polish[m++].num=number;
		} 
		if(array[i]=='+'||array[i]=='-'||array[i]=='*'||array[i]=='/'||array[i]=='('){
			if(!cnt){
				headOP=(struct OP*)malloc(sizeof(struct OP));
				headOP->last=NULL;
				topOP=headOP;
				topOP->op=array[i];
				cnt=1;
			}
			else {
				if(judge(array[i])<=judge(topOP->op)&&topOP->op!='('){
					while(judge(array[i])<=judge(topOP->op)&&topOP->op!='('){
						Polish[m].num=0;
						Polish[m++].op=topOP->op;
						cnt--;
						if(topOP==headOP){
							break;
						}
						q=topOP;
						topOP=topOP->last;
						free(q);
					}
					q=(struct OP*)malloc(sizeof(struct OP));
			 		q->op=array[i];
			 		q->last=topOP;
					topOP=q;
					cnt++;
				}
				else{
				q=(struct OP*)malloc(sizeof(struct OP));
				q->op=array[i];
				q->last=topOP;
				topOP=q;
				cnt++;
				}
			}
		}
		
		if(array[i]==')'){
			while(topOP->op!='('){
				Polish[m].num=0;
				Polish[m++].op=topOP->op;
				cnt--;
				q=topOP;
				topOP=topOP->last;
				free(q);
				}
			cnt--;
			q=topOP;
	 		topOP=topOP->last;
			free(q);
		}		
	}
//	printf("%d %c\n",cnt,topOP->op);
	for (q=topOP,i=cnt;i;i--){
		Polish[m].num=0;
		Polish[m++].op=q->op;
		if (q->last!=NULL)
			q=q->last;
		else 
			break;
	}
	/*
	if(headOP!=NULL){
		for(q=topOP;q!=NULL;q=q->last){
			Polish[m].num=0;
			Polish[m++].op=q->op;
		}
	}*/
	for(i=0;i<m;i++){
//		printf("num=%d,op=%d\n",Polish[i].num,Polish[i].op);
		if(Polish[i].num!=0){
			if(topOUT==NULL){
				headOUT=(struct Output*)malloc(sizeof(struct Output));
				topOUT=headOUT;
				topOUT->num=Polish[i].num;		
				topOUT->last=NULL;		
			}
			else{
				r=(struct Output*)malloc(sizeof(struct Output));
				r->num=Polish[i].num;
				r->last=topOUT;
				topOUT=r;
			}
		}
		if(Polish[i].op!=0){
			if(Polish[i].op=='+'){
				topOUT->last->num=topOUT->last->num+topOUT->num;
				r=topOUT;
				topOUT=topOUT->last;
				free(r);
			}
			if(Polish[i].op=='-'){
				topOUT->last->num=topOUT->last->num-topOUT->num;
				r=topOUT;
				topOUT=topOUT->last;
				free(r);
			}
			if(Polish[i].op=='*'){
				topOUT->last->num=topOUT->last->num*topOUT->num;
				r=topOUT;
				topOUT=topOUT->last;
				free(r);
			}
			if(Polish[i].op=='/'){
				topOUT->last->num=topOUT->last->num/topOUT->num;
				r=topOUT;
				topOUT=topOUT->last;
				free(r);
			}
		}
	}
	printf("%d",headOUT->num);
//n	puts(array);
	return 0;
} 


