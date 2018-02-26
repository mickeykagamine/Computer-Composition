#include<stdio.h>
#include<string.h>
#include<stdlib.h>
struct  Node{
	char word[100];
	int time;
	struct Node *next;
	struct Node *last;
};
void lowletter(char line[2000000]){
	int i;
	for(i=0;i<strlen(line);i++){
		if(line[i]>='A'&&line[i]<='Z'){
			line[i]=line[i]+'a'-'A';
		}
	}
}
struct Node* sort(struct Node *head){
	struct Node *p;
	int flag=1,tmp;
	char cmp[100];
	for(p=head;p->next!=NULL;p=p->next){
		if(p->time==EOF){
			if(p==head){
				head=p->next;
			}
			else{
				p->next->last=p->last;
				p->last->next=p->next;
			}
		}
	}
	if(p->next==NULL&&p->time==EOF){
		p->last->next=NULL;
	}
	for(p=head;flag;){
		flag=0;
		for(p=head;p->next!=NULL;p=p->next){
			if(p->time<p->next->time){
				tmp=p->time;
				p->time=p->next->time;
				p->next->time=tmp;
				strcpy(cmp,p->word);
				strcpy(p->word,p->next->word);
				strcpy(p->next->word,cmp);
				flag=1;
			}
		}
	}
	return head;
}
int main(){
	struct Node *head,*p,*q,*array[26];
	char word[100],line[2000000];
	int i,n,c,m,x;
	FILE *fp1,*fp2;
	fp1=fopen("article.txt","r");
	fp2=fopen("wordfreq.txt","w");
	head=(struct Node*)malloc(sizeof(struct Node));
	head->word[0]='a';
	head->word[1]='\0';
	head->time=EOF;
	p=head;
	for(i=1,n='b';i<26;i++){
		p->next=(struct Node*)malloc(sizeof(struct Node));
		p->next->word[0]=n++;
		p->next->word[1]='\0';
		p->next->time=EOF;
		p->next->last=p;
		p->next->next=NULL;
		p=p->next;
	}
        p->next=(struct Node*)malloc(sizeof(struct Node));
		strcpy(p->next->word,"zyogote\0");
		p->next->time=EOF;
		p->next->last=p;
		p->next->next=NULL;
		p=p->next; 	
	for(i=0,p=head;i<26;i++,p=p->next){
		array[i]=p;
	}
	while(fgets(line,1999999,fp1)!=NULL){
		lowletter(line);
		for(i=0;i<strlen(line);i++){
			for(m=0;line[i]>='a'&&line[i]<='z';m++,i++){
				word[m]=line[i];				
			}
			word[m]='\0';
			for(x=0;x<26;x++){
				if(array[x]->word[0]==word[0]){
					for(p=array[x];p!=NULL;p=p->next){
						if(strcmp(word,p->word)==0){
							if(p->time==EOF){
								p->time=1;
								break;
							}
							else{
								p->time++;
								break;
							}
						}						
						else if(strcmp(word,p->word)<0){
							break;
						}

					}
					if(strcmp(word,p->word)<0){
						q=(struct Node*)malloc(sizeof(struct Node));
						strcpy(q->word,word);
						q->time=1;
 						q->last=p->last;
						q->next=p;
						p->last->next=q;
						p->last=q;
						break;
					} 
					else if(strcmp(word,p->word)==0){
						break;
					}
					
				}
			}

		}
		
	}
	head=sort(head);
	for(q=head,i=1;q!=NULL&&i<=100;i++,q=q->next){
		printf("%s %d\n",q->word,q->time);
	}
	for(p=head;p!=NULL;p=p->next){
		fprintf(fp2,"%s %d\n",p->word,p->time);
	}
} 

