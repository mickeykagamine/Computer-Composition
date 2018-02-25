package oo;

import java.util.Scanner;

public class poly {
	static int []number = new int[1000001];
	private static Scanner s;
	public static void main(String [] args){
		s = new Scanner(System.in);
		String poly =s.nextLine();
		String poly2 = poly.replaceAll(" ", "");
		char []op = new char[100];
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		boolean sign = true;
		boolean errorrr = false;
		for(int index = 0,m = 0;index < poly2.length();index++){
			if(poly2==null){
				break;
			}
			if(poly2.charAt(index) == '{' 
			||poly2.charAt(index) == '(' ){
				op[m++] = poly2.charAt(index);
			}
			
			else if(poly2.charAt(index) >= '0' && poly2.charAt(index) <= '9'){
				n = 0;
				n = GetNumberFromString(poly2,index);
				index = GetNumberIndexFromString(poly2,index);
				if ((index+1) < poly2.length()&&poly2.charAt(index+1) == ','){
					if(sign == true){
						n1 = n;
					}
					else {
						n1 = 0-n; 
					}
				}
				else if ((index+1) <poly2.length()&&poly2.charAt(index+1) == ')' && (index+1) <= poly2.length()){
					n2 = n;
				}
			}
			
			else if (poly2.charAt(index) == ')'){
				if (m!=0&&op[m-1] == '('&&n2>=0&&n2<1000001){
				number[n2] = number[n2]+n1;
				m--;
				}	
				else {
					System.out.println("input error");
					errorrr = true;
					break;
					
				}
			}
			
			else if (poly2.charAt(index) == '}'){
				if (m!=0&&op[m-1] == '{'){
				m--;
				}	
				else {
					System.out.println("input error");
					errorrr = true;
					break;
				}
			}	
			
			else if((index+1) < poly2.length()&&poly2.charAt(index) == '-'&& poly2.charAt(index+1) == '{'){
				sign = false;
			}
			else if((index+1) <poly2.length()&&poly2.charAt(index) == '+'&& poly2.charAt(index+1) == '{'){
				sign = true;
			}
		}
	if (errorrr == false){
		System.out.print("{");
		for(int index = 0;index < number.length;index++){
			if(number[index] != 0){
				System.out.print("("+number[index]+","+index+")");
				for(int index2 = index+1;index2 < number.length && (index+1) < number.length;index2++){
					if(number[index2] != 0){
						System.out.print(",");
						break;
					}
				}
			}
		}
		System.out.print("}");
	}

	}
	public static int GetNumberFromString(String poly2,int index){
		int n;
		boolean sign=true;
		n = poly2.charAt(index)-48;
		if((index-1)>=0&&poly2.charAt(index-1) == '-'&&index != 0){
			sign=false;
		}
	//	if((index+1) < (poly2.length()-1)){
			while((index+1) < poly2.length()&&poly2.charAt(index+1) >= '0'&&poly2.charAt(index+1) <= '9' ){
				n = n*10 + poly2.charAt(index+1)-48;
				index++;
			}
//		}

		if (sign==false){
			return 0-n;
		}
		else return n;
	}
	public static int GetNumberIndexFromString(String poly2,int index){
		int n;
		n = poly2.charAt(index)-48;
//		if((index+1) < (poly2.length()-1)){
			while((index+1) <poly2.length()&&poly2.charAt(index+1) >= '0'&&poly2.charAt(index+1) <= '9'&& (index+1) < (poly2.length()-1)){
				n = n*10 + poly2.charAt(index+1)-48;
				index++;
			}
//		}
		return index;
	}
}
