#include<stdio.h>
int main(){     
	FILE *fp1, *fp2;        
    fp1 = fopen("filein.txt","r");     
    fp2 = fopen("fileout.txt","w");     
    char a[100]={},b[100]={},c,d[100]={};
	int i=0,n=0,N1=0,N2=0,t;
	gets(a);
    gets(b);
    while(a[i]!='\0'){
        N1++;
        i++;
    }
    while((c=fgetc(fp1))!= EOF){
        i=0;
        t=0;
        d[i]=c;
        d[i+1]='\0';
        if(c!=a[i]&&c!=a[i]+'A'-'a')
            fputc(c,fp2);
        if(c==a[i]||c==a[i]+'A'-'a'){
            while(i<N1-1){
                i++;                   
                c=fgetc(fp1);            
                if(c==a[i]||c==a[i]+'A'-'a'){
                    t=1;
                    d[i]=c;
                    d[i+1]='\0';
                }
                if(c!=a[i]&&c!=a[i]+'A'-'a'){
                    t=0;
                    d[i]=c;
                    d[i+1]='\0';
                    break;
                }
            }
        if(t)
			fputs(b,fp2);
        if(t==0&&i!=0)  
           fputs(d,fp2);  
        }  
    } 
	if(t==0&&i==0)   
    fputs(d,fp2);	
	fclose(fp1);     
    fclose(fp2);     
	return 0; 
    }

