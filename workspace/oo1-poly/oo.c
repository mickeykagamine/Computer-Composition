#include<stdio.h> 
#include<string.h>
char poly[1000];
void replace(char poly[1000]){
	int i,n;
	for(i=0;i<1000;i++){
		if(poly[i]==' '){
			for(n=i;n<1000;n++){
				poly[n]=poly[n+1];
			}
		}
	}
}
int GetNumberFromString(char poly[1000],int i){
	int n,sign=1;
	n = poly[i]-48;
	if(i != 0&&poly[i-1] == '-'){
		sign=0;
	}
	while((i+1) < strlen(poly)&&poly[i+1] >= '0'&&poly[i+1] <= '9' ){
		n = n*10 + poly[i+1]-48;
		i++;
	}
	if (sign==0){
		return 0-n;
	}
	else return n;
}
int GetNumberIndexFromString(char poly[1000],int i){
	int n;
	n = poly[i]-48;
	while((i+1) < strlen(poly)&&poly[i+1] >= '0'&&poly[i+1] <= '9'){
		n = n*10 + poly[i+1]-48;
		i++;
	}
	return i;
}
int number[1000001];
int main(){	
gets(poly);
replace(poly);		
char op[100];
int n1=0,n2=0,n=0,sign=1,errorrr = 0;
int i,m;
for( i = 0,m = 0;i < strlen(poly);i++){
	if(poly==NULL){
		break;
	}
	if(poly[i] == '{' ||poly[i] == '(' ){
		op[m++] = poly[i];
	}
			
	else if(poly[i] >= '0' && poly[i] <= '9'){
		n = 0;
		n = GetNumberFromString(poly,i);
		i = GetNumberIndexFromString(poly,i);
		if ((i+1) < strlen(poly)&&poly[i+1] == ','){
			if(sign == 1){
				n1 = n;
			}
			else {
				n1 = 0-n; 
			}
		}
		else if ((i+1) < strlen(poly)&&poly[i+1] == ')' ){
			n2 = n;
		}
	}
			
	else if (poly[i] == ')'){
		if (m!=0&&op[m-1] == '('&&0<=n2<=1000001){
		number[n2] = number[n2]+n1;
		m--;
		}	
		else {
			printf("input error");
			errorrr = 1;
			break;
		}
	}
			
	else if (poly[i] == '}'){
		if (m!=0&&op[m-1] == '{'){
		m--;
		}	
		else {
			printf("input error");
			errorrr = 1;
		}
	}	
			
	else if((i+1) < strlen(poly)&&poly[i] == '-'&& poly[i+1] == '{'){
		sign = 0;
	}
	else if((i+1) < strlen(poly)&&poly[i] == '+'&& poly[i+1] == '{'){
		sign = 1;
	}
}
	if (errorrr == 0){
		printf("{");
		for( i = 0;i < 1000001;i++){
			if(number[i] != 0){
				printf("(%d,%d)",number[i],i);
				for(m = i+1;m < 1000001 && i+1 < 1000001;m++){
					if(number[m] != 0){
						printf(",");
						break;
					}
				}
			}
		}
		printf("}");
	}

	}



