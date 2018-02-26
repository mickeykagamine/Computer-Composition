#include<stdio.h>
void replace(int n,char a[100]){
	int i=n;
//	while(a[i]!='/0'){
		while(i<100){
			a[i]=a[i+1];
			i++;
			//printf("%d ",i);
		}//if(i==100)break;
//	}
}
int main()
{
	char s[100],op[100];
	int i=0,op1=0,op2=0,b[100]={0},m=0,n=0,result=0;
	gets(s);//printf("%s",s);
	//s[99]='/0';
	while (s[i]!='='){
		if(s[i]==' '){
			replace(i,s);
			i--;
		}
		i++;if(s[i]=='='||i==99)break;
		//	printf("@@%s ",s);
	}
//	printf("%s",s);OK
	i=0;
	while(s[i]!='='){
		if(s[i]=='+'||s[i]=='-'){
		op1++;
		op[n]=s[i];
		n++;
		}
		if(s[i]=='/'||s[i]=='*'){
		op2++;
		op[n]=s[i];
		n++;
		}
		i++;		
	}
	op[n]='\0';
	//printf("%s",s);
	i=0;
	while(s[i]!='='){
		while(s[i]>='0'&&s[i]<='9'){
			b[m]=b[m]*10+(s[i]-'0');
			i++;
		}
		if(s[i]!='=')i++;
		m++;
	//	printf("%d ",b[m-1]);
	}//printf("%s",op);
	m=0;
	while(m<=99){
	if(op[m]=='-')b[m+1]=0-b[m+1];
//	printf("%d ~ %c ",m,op[m]);
	m++;
	}	
	m=0;
	n=0;
	while(op2>0){		
		if(op[n]=='*'){
		b[n+1]=b[n]*b[n+1];
		b[n]=0;op2--;
		}
		if(op[n]=='/'){
		b[n+1]=b[n]/b[n+1];
		b[n]=0;op2--;
		}
	//	printf("%d\n",b[n+1]);
		n++;		
	}
	m=0;
	while(m<=99){
	result=b[m]+result;
	//printf("%d ",b[m]);
	m++;}
	printf("%d",result);
    return 0;
}


