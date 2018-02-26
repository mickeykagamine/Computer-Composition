#include<stdio.h>
main()
{
int data1,data2;
char op;
scanf("%d %d %c",&data1,&data2,&op);
if(op=='+')printf("%d",data1+data2);
if(op=='-')printf("%d",data1-data2);
if(op=='*')printf("%d",data1*data2);
if(op=='/'){
            if(data1%data2==0){printf("%d",data1/data2);}
            if(data1%data2!=0){printf("%.2f",data1*1.0/data2);}
            }           
system("pause");
}


