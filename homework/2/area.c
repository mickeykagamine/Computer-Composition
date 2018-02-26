#include<stdio.h>
int main() 
{
	int AX1,AX2,AY1,AY2,BX1,BX2,BY1,BY2,MAX_AX,MIN_AX,MAX_AY,MIN_AY,MAX_BX,MIN_BX,MAX_BY,MIN_BY,X1,X2,Y1,Y2;
	scanf("%d %d %d %d\n %d %d %d %d",&AX1,&AY1,&AX2,&AY2,&BX1,&BY1,&BX2,&BY2);
	if(AX1<AX2){
	MAX_AX=AX2;MIN_AX=AX1;
	}
	else{MAX_AX=AX1;MIN_AX=AX2;}
	if(AY1<AY2){
	MAX_AY=AY2;MIN_AY=AY1;
	}
	else{MAX_AY=AY1;MIN_AY=AY2;}
	if(BX1<BX2){
	MAX_BX=BX2;MIN_BX=BX1;
	}
	else{MAX_BX=BX1;MIN_BX=BX2;}
	if(BY1<BY2){
	MAX_BY=BY2;MIN_BY=BY1;
	}
	else{MAX_BY=BY1;MIN_BY=BY2;}
	
    if(MAX_AX>MAX_BX)X1=MAX_BX;
    else X1=MAX_AX;
    if(MIN_AX>MIN_BX)X2=MIN_AX;
    else X2=MIN_BX;
    
    if(MAX_AY>MAX_BY)Y1=MAX_BY;
    else Y1=MAX_AY;
    if(MIN_AY>MIN_BY)Y2=MIN_AY;
    else Y2=MIN_BY;
    
    if(X1-X2>0&&Y1-Y2>0)printf("%d",(X1-X2)*(Y1-Y2));
    else printf("%d",0);
    
    
    
} 

