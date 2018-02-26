#include <stdio.h> 

void filecopy(FILE *ifp,FILE *ofp)
{
	char c;
	char c1;
	while((c=fgetc(ifp))!= EOF){         
		if(c==' '&&(c1!=' '&&c1!='\t'))
		 	fputc(c,ofp);
		if(c=='\t'&&(c1!=' '&&c1!='\t'))
			fputc(' ',ofp);
		if(c!=' '&&c!='\t')
			fputc(c,ofp);
		c1=c;
	}
}

 
int main() 
{     
    
    FILE *fp1, *fp2;        
	fp1 = fopen("fcopy.in","r");     
	fp2 = fopen("fcopy.out","w");     

	filecopy(fp1,fp2);
	fclose(fp1);     
	fclose(fp2);     
	return 0; 
} 


