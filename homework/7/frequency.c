#include<stdio.h>
#include<string.h>
#include<stdlib.h>
void lower(char a[100]){
	int i;
	for(i=0;a[i]!='\0';i++){
		if(a[i]>='A'&&a[i]<='Z'){
			a[i]=a[i]-'A'+'a';
		}
	}
}

struct Tree{
	char word[100];
	int n;
	struct Tree *lchild;
	struct Tree *rchild;
	struct Tree *parent;
}; 
void preorder(struct Tree *q){
//	printf("sdfghj");
	if(q!=NULL){
		preorder(q->lchild);
		printf("%s %d\n",q->word,q->n);
		preorder(q->rchild);
	}
}
int main(){
	FILE *fp;
	char line[100]={0},word[100]={0};
	int i=0,m,c,judge;
	struct Tree *root=NULL,*p,*q;
	fp=fopen("article.txt","r");
	while(fgets(line,99,fp)!=NULL){
//		puts(line);
		lower(line);
		for(i=0;line[i]!='\0';i++){
			for(m=0;line[i]>='a'&&line[i]<='z';m++,i++){
				word[m]=line[i];
			}
			word[m]='\0';
			if(word[0]=='\0'){
				continue;
			}
			if(root==NULL){
				root=(struct Tree*)malloc(sizeof(struct Tree));
				strcpy(root->word,word);
				root->n=1;
				root->rchild=NULL;
				root->lchild=NULL;
				root->parent=NULL; 
			}
		    else{
		    	p=root;
		    	while(1){
 		    		if(strcmp(word,p->word)<0){
		    			if(p->lchild!=NULL){
							p=p->lchild;
						}
						else{
							judge=1;
							break;
						}
					} 
					if(strcmp(word,p->word)>0){
						if(p->rchild!=NULL){
							p=p->rchild;
						}
						else{
							judge=2;
							break;
						}				
					}
					if(strcmp(word,p->word)==0){
						judge=3;
						p->n++;
						break;
					}
				}
				if(judge==1){
					q=(struct Tree*)malloc(sizeof(struct Tree));
					q->lchild=NULL;
					q->rchild=NULL;
					q->parent=p;
					q->n=1;
					strcpy(q->word,word);
					p->lchild=q;
				}
				if(judge==2){
					q=(struct Tree*)malloc(sizeof(struct Tree));
					q->lchild=NULL;
					q->rchild=NULL;
					q->parent=p;
					q->n=1;
					strcpy(q->word,word);
					p->rchild=q;
				}
			}
				
		}
	} 
	if(root->rchild->rchild!=NULL)		
	printf("%s %s %s\n",root->word,root->rchild->word,root->rchild->rchild->word);
	else
		printf("%s %s\n",root->word,root->rchild->word);
//	for(q=root;q->lchild!=NULL;q=q->lchild){
//		;
//	}
//	printf("aaaaa");
	preorder(root);	
//	printf("bbbbb")
	return 0;
	}


