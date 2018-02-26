#include<stdio.h>
int main(){
	FILE *fp1,*fp2;
	fp1=fopen("encrypt.txt","r");
	fp2=fopen("output.txt","w");
	char A[100]={},B[100],c[100]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	char Q;
	int a[512]={},N2=0;
	int i=0,m=0,j;
    gets(A);
	//puts(A); 
    while(i<100){
    //c=(int)A[i];	
	a[A[i]]++;	
		if (a[A[i]]>=2)A[i]=' ';	
	i++;
	}
	i=0;
	while(m<26) {
    if(A[i]!=' '){
	B[m]=A[i];
	m++;}
	i++;
	}
// 
i=0;
while(B[i]!='\0'){
N2++;
i++;
}

	m=122;
	j=0;
	i=0;
	while(m>='a'){
		while(B[i]!='\0'){
			if(B[i]!=m){
			j=1;
			i++;}
			if(B[i]==m){
			j=0;
			break;}
		}
		//printf("%d",j);
		i=0;
		if(j){
		B[N2]=m;
		N2++;}
		m--;		
	}

	
	//puts(c);
	//puts(B);

//puts(A);
	
	i=0;
while((Q=fgetc(fp1))!=EOF){
	if(!(Q>='a'&&Q<='z'))fputc(Q,fp2);
	else {
		while(i<26){	
			if(Q!=c[i])i++;
			if(Q==c[i]){
				Q=B[i];
				fputc(Q,fp2);
				break;
			}		
		}
	i=0;
	
	}

//fputs(A,fp2);
	}
	
	fclose(fp1);
	fclose(fp2);
	return 0;
	
}


