#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define LENGTH 81
void addLInt(char s1[],char s2[]);
void reverse(char s[]);
void deleted(char s[]);
int main(){
	char intstr1[LENGTH],intstr2[LENGTH];
	scanf("%s %s",intstr1,intstr2);
	addLInt(intstr1,intstr2);
	printf("%s",intstr1);
	return 0;
} 

void addLInt(char s1[],char s2[])
{
	int i=0,j;
	char s[LENGTH];
	deleted(s1);
	deleted(s2); 
	//printf("%s %s\n",s1,s2);
	if(strlen(s1)<strlen(s2)){
		strcpy(s,s1);
		strcpy(s1,s2);
		strcpy(s2,s);
		printf("-");
	}
	else if(strlen(s1)==strlen(s2)&&(strcmp(s1,s2)<0))
	{
		strcpy(s,s1);
		strcpy(s1,s2);
		strcpy(s2,s);
		printf("-");
	}
	//printf("%s %s\n",s1,s2);
	reverse(s1);reverse(s2);
	//printf("%s %s\n",s1,s2);
	while(s2[i]!='\0'){		
		if(s1[i]-s2[i]>=0)
		{
			s1[i]=s1[i]-s2[i]+'0';
			i++;continue;
		}
		if(s1[i]-s2[i]<0){
			s1[i]=s1[i]-s2[i]+10+'0';
			j=i+1;
			while(s1[j]=='0'){
				s1[j]='9';
				j++;
			}
			
			s1[j]=s1[j]-1;
		}		
		i++;
						
	}
	reverse(s1);

	deleted(s1);
}
void reverse(char s[])
{
	int i,j,c;
	for(i=0,j=strlen(s)-1;i<j;i++,j--){
	c=s[i];
	s[i]=s[j];
	s[j]=c; 
	}		
}


void deleted(char s[])
{
	int i=0,j;
	while(s[i]=='0')
	{
		for(j=0;s[j]!='\0';j++)
		{
			s[j]=s[j+1];
		}
	}
	if(s[0]=='\0'){
		s[1]='\0';
		s[0]='0';
	}

}


