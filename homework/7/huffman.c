#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<math.h>
struct Tree{
	char letter;
	int time;
	char num[100];
	struct Tree *parent;
	struct Tree *rchild;
	struct Tree *lchild;
	struct Tree *next;
	struct Tree *last;
};
char huffman[2000];
//struct Tree num[100]={0};
void preorder(struct Tree *p){
//	char a[100];
//	char a0[1]={0};
//	char a1[1]={1};
	if(p!=NULL){
		if(p->parent==p){
			strcpy(p->num,"\0");
		}
		else if(p==p->parent->lchild){
			strcpy(p->num,p->parent->num);
			strcat(p->num,"0\0");
		}
		else if(p==p->parent->rchild){
			strcpy(p->num,p->parent->num);
			strcat(p->num,"1\0");
		}
//		printf("%c:   %s\n",p->letter,p->num);
		preorder(p->lchild);
		preorder(p->rchild);
	}
}
void preorder1(struct Tree *p,char c){
//	char a[100];
//	char a0[1]={0};
//	char a1[1]={1};
	if(p!=NULL){
		if(p->letter==c){
			strcat(huffman,p->num);
//			printf("%s\n",p->num);
		}
//		printf("%c:   %s\n",p->letter,p->num);
		preorder1(p->lchild,c);
		preorder1(p->rchild,c);
	}
}
int FindMin(int a[512],int N){
	int i=0,min,mini,max=0,maxi=0;
	for(i=0;i<=N;i++) {
		if(max<a[i]){
			max=a[i];
			maxi=i;
		}
	}
	for(i=0;a[i]==0;i++){
		;
	}
	mini=maxi;
	min=max;
	for(i=0;i<=N;i++){
		if(min>a[i]&&a[i]!=0){
			min=a[i];
			mini=i;
		}		
	}
	if(a[mini]!=0){
		return mini;
	}
	else{
		return -1;
	}
}
int main(){
	FILE *fp1,*fp2;
	char c,article[1000];
	int i,N=0,a[512]={0},min,mini,m=0,cnt=0,e,result=0,n;
	huffman[0]='\0';
	
	struct Tree *root,*p,array[100],*head,*top,*q,*q1,*q2;
	fp1=fopen("input.txt","r");
	fp2=fopen("output.txt","w");
	while((c=fgetc(fp1))!=EOF){
		article[e++]=c;
		if(c=='\n'){
			;
		}
		else{
			a[c]++;
			if(N<(int)c){
				N=(int)c;
			}
			cnt++;
		}
	}
	a[0]++;
	cnt++;
	article[e++]='\0';
	for(;cnt>0;cnt--){
		mini=FindMin(a,N);
		if(mini!=-1){
			array[m].letter=(char)mini;
			array[m++].time=a[mini];
			a[mini]=0;
		}
	}
	i=0;
	head=(struct Tree*)malloc(sizeof(struct Tree));
	head->letter=array[i].letter;
	head->time=array[i++].time;
	head->lchild=NULL;
	head->rchild=NULL;
	(head->num)[0]='\0';
	top=head;
	for(;i<m;){
		p=(struct Tree*)malloc(sizeof(struct Tree));
		p->letter=array[i].letter;
		p->time=array[i++].time;	
		(p->num)[0]='\0';
		p->lchild=NULL;
		p->rchild=NULL;
		top->next=p;
		p->last=top;
		top=p;
		top->next=NULL;
	}
	while(head->next!=NULL){
		p=(struct Tree*)malloc(sizeof(struct Tree));
		p->time=head->time+head->next->time;
		p->letter=EOF;
		p->lchild=head;
		p->lchild->parent=p;
		p->rchild=head->next;
		p->rchild->parent=p;
		(p->num)[0]='\0';
		q=head;
		if(p->time>=top->time){
			top->next=p;
			p->last=top;
			top=p;
			top->next=NULL;
			q1=head;
			q2=head->next;
			head=head->next->next;
//			free(q1);
//			free(q2);
		}
		else{
			for(;q->time<=p->time;q=q->next){
				;
			}
			q=q->last;
			p->next=q->next;
			q->next=p;
			p->next->last=p;
			p->last=q;
			q1=head;
			q2=head->next;
			head=head->next->next;
//			free(q1);
//			free(q2);
		}		
	}
	root=head;
	root->parent=root;
	preorder(root);
	cnt=0;
	for(cnt=0;article[cnt]!='\0';cnt++){
		preorder1(root,article[cnt]);
	}
	preorder1(root,article[cnt]);
	strcat(huffman,"0000000\0");
	for(i=0;i<strlen(huffman)-8;i+=8){
		result=0;
		for(n=i;n<i+8;n++){
			result=result+pow(2,7-n%8)*(huffman[n]-'0');
		}
		printf("%x",result);
	}
//	printf("%s\n",huffman);

	return 0;
}


