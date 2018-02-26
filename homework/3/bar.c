#include<stdio.h>
/*
int max(int array[512]){ 
	int max,i=0;
	max=array[0];
	while(i<512){
		if(max<array[i])
		max=array[i];
		i++; 		
	}
	return max;
} */
int max(int a,int b)
{
	if(a>b)return a;return b;
}
int main()
{ int c,a[512]={0},i,MAX=-1,h=1,l='a';
while((c=getchar())!=EOF)
	if(c>='a'&&c<='z')
	{
			a[c]++;
	}
	for (i=0;i<512;i++)
    MAX=max(a[i],MAX);
while(h<=MAX){
	while(l<='z'){	
	if(MAX-a[l]-h>=0)printf(" ");
	if(MAX-a[l]-h<0)printf("*");
	l=l+1;
	}l='a';
	printf("\n");
	h++;
}
printf("abcdefghijklmnopqrstuvwxyz");
	return 0; 
}


