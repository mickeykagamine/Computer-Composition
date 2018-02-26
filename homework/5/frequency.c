#include <stdio.h> 
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
struct Nodeptr{
    char a[100];
	int x;
    struct Nodeptr*next;
    struct Nodeptr*last;
 };

int main(){
    FILE *fp;
    char c[100],b[100];
    int m,i,N;
    struct Nodeptr *head,*q,*p;
    fp=fopen("article.txt","r");
    head=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
    head->a[0]=0;
	head->x=0;
    head->next=head;
    head->last=head;
    while((fgets(c,99,fp))!=NULL){
    	N=strlen(c);
    	for(i=0;c[i]!='\0';i++){
    		c[i]=tolower(c[i]);
		}
        for(i=0;i<N;i++){
            if(c[i]>='a'&&c[i]<='z'){
                for(m=0;c[i]>='a'&&c[i]<='z';i++,m++){
                    b[m]=c[i];
                }
                b[m]='\0';
                q=(struct Nodeptr*)malloc(sizeof(struct Nodeptr));
                strcpy(q->a,b);
                q->x=1;
				p=head; 
                if(p->a[0]==0&&p->next==head){
                        strcpy(p->a,q->a);
						p->x++;
                    	continue;
					}
                if(p->a[0]!=0){
                	if(head->next==head){
							if(strcmp(p->a,q->a)>0){
        		        //        q->x++;
        		        		p->last=q;
        		        		p->next=q;
        		        		q->next=p;
        		        		q->last=p;
								head=q;        		        		
						//		p->last->next=q;
                    	  //      q->last=p->last;
                        	//    p->last=q;
                            //	q->next=p;
                        	}
							if(strcmp(p->a,q->a)==0){
								p->x++;
								free(q);
							} 
							if(strcmp(p->a,q->a)<0&&p->next==head){
	//							q->next=p->next;
	//							p->next->last=q;								
	//							q->last=p;								
	//							p->next=q;
								p->next=q;
								p->last=q;
								q->next=p;
								q->last=p;
							}                   		
					}
					else {					
						for(p=head;p->next!=head;p=p->next){
    	    	                if(strcmp(p->a,q->a)>0){
        				            //q->x++;
        				            if(p==head){
        				            	q->last=p->last;
        				            	p->last->next=q;
        				            	q->next=p;
        				            	p->last=q;
        				            	head=q;
        				            	break;
									}
									p->last->next=q;
                    	    	    q->last=p->last;
                        	    	p->last=q;
                            		q->next=p;
                            		break;
                        		}
								if(strcmp(p->a,q->a)==0){
									p->x++;
									free(q);
									break;
								} 						
           	   			}
           	   			if(p->next==head&&strcmp(p->a,q->a)<0){	
							p->next->last=q;
							q->last=p;
							q->next=p->next;
							p->next=q;
						}
						else if(p->next==head&&strcmp(p->a,q->a)>0){
									p->last->next=q;
                    	    	    q->last=p->last;
                        	    	p->last=q;
                            		q->next=p;                 	
							
						}
						else if(p->next==head&&strcmp(p->a,q->a)==0){
							p->x++;
							free(q);                								
						}
           	   		}
           	    } 
            }
        }
        
    }
	for(p=head;p->next!=head;p=p->next){
		for(i=0;p->a[i]!='\0';i++){
			printf("%c",p->a[i]);
		}
		printf(" %d\n",p->x);
	}
	for(i=0;p->a[i]!='\0';i++){
			printf("%c",p->a[i]);
	}
	printf(" %d",p->x);
    return 0;
}


