#include<stdio.h>
main()
{
int year,month,day;
scanf("%d %d %d",&year,&month,&day);
if ((year%4==0&&year%100!=0)||year%400==0){
                                         if(month==1)printf("%d",day);
                                         if(month==2)printf("%d",day+31);
                                         if(month==3)printf("%d",day+31+29);
                                         if(month==4)printf("%d",day+31+29+31);
                                         if(month==5)printf("%d",day+31+29+31+30);
                                         if(month==6)printf("%d",day+31+29+31+30+31);
                                         if(month==7)printf("%d",day+31+29+31+30+31+30);
                                         if(month==8)printf("%d",day+31+29+31+30+31+30+31);
                                         if(month==9)printf("%d",day+31+29+31+30+31+30+31+31);
                                         if(month==10)printf("%d",day+31+29+31+30+31+30+31+31+30);
                                         if(month==11)printf("%d",day+31+29+31+30+31+30+31+31+30+31);
                                         if(month==12)printf("%d",day+31+29+31+30+31+30+31+31+30+31+30);                                        
                                         }
if((year%4!=0||year%100==0)&&year%400!=0){
                                         if(month==1)printf("%d",day);
                                         if(month==2)printf("%d",day+31);
                                         if(month==3)printf("%d",day+31+28);
                                         if(month==4)printf("%d",day+31+28+31);
                                         if(month==5)printf("%d",day+31+28+31+30);
                                         if(month==6)printf("%d",day+31+28+31+30+31);
                                         if(month==7)printf("%d",day+31+28+31+30+31+30);
                                         if(month==8)printf("%d",day+31+28+31+30+31+30+31);
                                         if(month==9)printf("%d",day+31+28+31+30+31+30+31+31);
                                         if(month==10)printf("%d",day+31+28+31+30+31+30+31+31+30);
                                         if(month==11)printf("%d",day+31+28+31+30+31+30+31+31+30+31);
                                         if(month==12)printf("%d",day+31+28+31+30+31+30+31+31+30+31+30);                                        
                                         }
system("pause");
}                            


