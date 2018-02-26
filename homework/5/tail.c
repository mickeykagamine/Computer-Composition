#include<stdio.h>
#include<stdlib.h>
#include<string.h>
struct Nodeptr{
	char a[100];
	struct Nodeptr*next;
};
struct Nodeptr* creatChart(int line){
	struct Nodeptr *q,*p,*head=NULL;
	int i;
	for(i=0;i<line;i++){
		if(head==NULL){
			head=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
			head->next=NULL;
			p=head;
		}
		else{
			q=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
			p->next=q;
			q->next=NULL;
			p=q;
		}
	}
	return head;
}
int main(int argc,char *argv[]){
	int line=10,i;
	char c[100];
	struct Nodeptr *p,*head;
	FILE *fp;
//	scanf("%d",&line);
	fp=fopen(argv[2],"r");
	line=atoi(argv[1]+1);
	head=creatChart(line);
	p=head;
	while(fgets(c,99,fp)!=NULL){
//		puts(c);
		for(i=0;i<strlen(c);i++){
			if(c[i]=='\n'){
				c[i]='\0';
			}
		}	
		strcpy(p->a,c);
//		puts(p->a);
		p=p->next;
		if(p==NULL){
			p=head;
		}
	} 
	for(i=0;i<line;i++){
		puts(p->a);
		p=p->next;
		if(p==NULL){
			p=head;
		}
	}
	return 0;
}

