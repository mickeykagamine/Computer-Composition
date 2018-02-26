#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void reverse(char a[]){
	int i,n,m;
	char b[1000];
	n=strlen(a);
	for(i=n-1,m=0;i!=-1;i--,m++){
		b[m]=a[i];
	}
	for(i=0;i<n;i++){
		a[i]=b[i];
	}
}
struct Node{
	char a;
	int line;
	struct Node *last;
};
int main(){
	FILE *fp;
	int l=1,i,judge=1;
	char c,d,array[1000]={};
	struct Node *top1,*top2,*q,*head1,*head2; 
	fp=fopen("example.c","r");
	head1=(struct Node*)malloc(sizeof(struct Node));
	head2=(struct Node*)malloc(sizeof(struct Node));
	top1=head1;
	top2=head2;
	head1->last=NULL;
	head2->last=NULL;
	while((c=fgetc(fp))!=EOF){
		if(c=='\n'){
			l++;
		}
		
		else if(c=='('||c=='{'){
			q=(struct Node*)malloc(sizeof(struct Node));
			q->a=c;
			q->line=l;
			q->last=top1;
			top1=q;
			q=(struct Node*)malloc(sizeof(struct Node));
			q->a=c;
			q->line=l;
			q->last=top2;
			top2=q;
		}
		else if(c=='/'){
			if((c=fgetc(fp))=='/'){
				do{
					c=fgetc(fp);
				}while(c!='\n');
				l++;
			}
			else if(c=='*'){
					do{
					d=c;
					c=fgetc(fp);
					if(c=='\n')l++;
				}while(!(c=='/'&&d=='*'));				
			}					
		}
		else if(c=='"'){
			do{
				c=fgetc(fp);
			}while(c!='"');
		}
		else if(c==')'){
			q=(struct Node*)malloc(sizeof(struct Node));
			q->a=c;
			q->line=l;
			q->last=top2;
			top2=q;
			if(top1->a=='('){
				q=top1;
				top1=top1->last;
				free(q);								
			}
			else
			{
				printf("without maching '%c' at line %d",c,l);
				judge=0;
				break;
			}
		}
		else if(c=='}'){
			q=(struct Node*)malloc(sizeof(struct Node));
			q->a=c;
			q->line=l;
			q->last=top2;
			top2=q;
			if(top1->a=='{'){
				q=top1;
				top1=top1->last;
				free(q);				
			}
			else
			{
				printf("without maching '%c' at line %d",c,l);
				judge=0;
				break;
			}
		}
	}
	for(q=top2,i=0;q!=head2;q=q->last,i++){
 		array[i]=q->a;
	}

	reverse(array);
	if(top1!=head1&&judge){
		printf("without maching '%c' at line %d",top1->a,top1->line);
		judge=0;
	}
	if(judge){
		puts(array);
	}
	fclose(fp);
	return 0;
}

