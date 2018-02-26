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
		 struct Tree *tree;};
         struct Tree{
         	int num;
         	char op;
         	struct Tree* parent;
         	struct Tree* rchild;
         	struct Tree* lchild;
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
         }}
        int main(){
         struct ReversePolish  Polish[100];
           struct OP *topOP,*headOP,*q;
        struct Output *topOUT,*headOUT,*r;
        struct Tree *t,*root;
          char array[100];
         int i,number=0,m=0,cnt=0;
          gets(array);//     puts(array);
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
                 Polish[m].op=EOF;
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
                             Polish[m].num=EOF;
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
                      Polish[m].num=EOF;
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
        //    printf("%d %c\n",cnt,topOP->op);
         for (q=topOP,i=cnt;i;i--){
              Polish[m].num=EOF;
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
        /*
         	for(i=0;i<m;i++){
		if(Polish[i].num!=EOF){
			printf("%d ",Polish[i].num);
		}
		if(Polish[i].op!=EOF){
			printf("%c ",Polish[i].op);
		}
		
		
	}
	
	*/
	/*
	if(headOP!=NULL){
		for(q=topOP;q!=NULL;q=q->last){
			Polish[m].num=0;
			Polish[m++].op=q->op;
		}
	}*/
	
	//用来打出逆波兰表达式用来检查 
	for(i=0;i<m;i++){//用逆波兰表达式生成树 
//		printf("num=%d,op=%d\n",Polish[i].num,Polish[i].op);
		if(Polish[i].num!=EOF){
			if(topOUT==NULL){
				headOUT=(struct Output*)malloc(sizeof(struct Output));
				topOUT=headOUT;
//				topOUT->num=Polish[i].num;		
				t=(struct Tree*)malloc(sizeof(struct Tree));
				t->num=Polish[i].num;
				t->op=EOF;
				t->lchild=NULL;
				t->rchild=NULL; 
				topOUT->tree=t;		
				topOUT->last=NULL;
			}
			else{
				r=(struct Output*)malloc(sizeof(struct Output));
//				r->num=Polish[i].num;
				t=(struct Tree*)malloc(sizeof(struct Tree));
				t->num=Polish[i].num;
				t->op=EOF;
				t->lchild=NULL;
				t->rchild=NULL;
				r->tree=t;
				r->last=topOUT;
				topOUT=r;
			}
		}
		if(Polish[i].op!=EOF){
			if(Polish[i].op=='+'){
				t=(struct Tree*)malloc(sizeof(struct Tree));
				t->op='+';
				t->num=EOF;
				t->rchild=topOUT->tree;
				topOUT->tree->parent=t;
				t->lchild=topOUT->last->tree;
				topOUT->last->tree->parent=t;
				root=t;
//				topOUT->last->num=topOUT->last->num+topOUT->num;
				r=topOUT;
				topOUT=topOUT->last;
				free(r);
				topOUT->tree=root;
			}
			if(Polish[i].op=='-'){
				t=(struct Tree*)malloc(sizeof(struct Tree));
				t->op='-';
				t->num=EOF;
				t->rchild=topOUT->tree;
				topOUT->tree->parent=t;
				t->lchild=topOUT->last->tree;
				topOUT->last->tree->parent=t;
				root=t;
//				topOUT->last->num=topOUT->last->num+topOUT->num;
				r=topOUT;
				topOUT=topOUT->last;
				free(r);
				topOUT->tree=root;
			}
			if(Polish[i].op=='*'){
				t=(struct Tree*)malloc(sizeof(struct Tree));
				t->op='*';
				t->num=EOF;
				t->rchild=topOUT->tree;
				topOUT->tree->parent=t;
				t->lchild=topOUT->last->tree;
				topOUT->last->tree->parent=t;
				root=t;
//				topOUT->last->num=topOUT->last->num+topOUT->num;
				r=topOUT;
				topOUT=topOUT->last;
				free(r);
				topOUT->tree=root;
			}
			if(Polish[i].op=='/'){
				t=(struct Tree*)malloc(sizeof(struct Tree));
				t->op='/';
				t->num=EOF;
				t->rchild=topOUT->tree;
				topOUT->tree->parent=t;
				t->lchild=topOUT->last->tree;
				topOUT->last->tree->parent=t;
				root=t;
//				topOUT->last->num=topOUT->last->num+topOUT->num;
				r=topOUT;
				topOUT=topOUT->last;
				free(r);
				topOUT->tree=root;
			}
		}
	}
//	printf("%d",headOUT->num);
//	puts(array);

	
 	root->num=EOF;
 	//打出三个要求节点的内容 
 	if(root->lchild->num==EOF&&root->rchild->num==EOF){
		printf("%c %c %c \n",root->op,root->lchild->op,root->rchild->op);
	}
	else if(root->lchild->num!=EOF&&root->rchild->num==EOF){
		printf("%c %d %c \n",root->op,root->lchild->num,root->rchild->op);		
	}
	else if(root->rchild->num!=EOF&&root->lchild->num==EOF){
		printf("%c %c %d \n",root->op,root->lchild->op,root->rchild->num);			
	}
	else if(root->rchild->num!=EOF&&root->lchild->num!=EOF){
		printf("%c %d %d \n",root->op,root->lchild->num,root->rchild->num);			
	} 
	
	//遍历计算得到最终结果，没用中序，用的是判断两个孩子是否同为数字，若不是则进入为符号的左孩子 
	for(t=root;t->lchild!=NULL;t=t->lchild){
		; 
	}
	 
    while(root->num==EOF){
    	if(t->num==EOF){
    		do{
			t=t->lchild;
			}while(t->num==EOF);
		}
    	if(t->parent->rchild->num==EOF){
    		t=t->parent->rchild->lchild;
		}
		else{
			if(t->parent->op=='+'){
			t->parent->num=t->parent->lchild->num+t->parent->rchild->num;
			t=t->parent;
			continue;				
			}
			if(t->parent->op=='-'){
			t->parent->num=t->parent->lchild->num-t->parent->rchild->num;
			t=t->parent;
			continue;				
			}
			if(t->parent->op=='*'){
			t->parent->num=t->parent->lchild->num*t->parent->rchild->num;
			t=t->parent;	
			continue;			
			}
			if(t->parent->op=='/'){
			t->parent->num=t->parent->lchild->num/t->parent->rchild->num;
			t=t->parent;
			continue;				
			}
		}
	} 
	printf("%d",root->num);
	return 0;
} 


