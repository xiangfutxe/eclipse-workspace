package com.test;

import java.util.ArrayList;
import java.util.List;


public class GetReasult {
 public static void getlist(String[][] chars,int n,String[] tmp,List<String> slist){
	 for(int i=0;i<chars[n].length;i++){
		 if(chars[n][i]!=null){
			 if(!chars[n][i].equals("")){
				 tmp[n] = chars[n][i];
				 if(n<chars.length-1){
					 getlist(chars,n+1,tmp,slist);
				 }else{
					 String str="";
					 for(int j=0;j<tmp.length;j++){
						 if(tmp.length-j-1==0)
							 str = str +tmp[j];
						 else str=str+tmp[j]+";";
					 }
					 slist.add(str);
				 }
			 }
		 }
	 }
 }
 
	public static void main(String[] args) { 
		String[][] str = new String[3][4];
		int t=1;
		for(int i=0;i<str.length;i++){
			for(int j=0;j<str[i].length;j++){
				str[i][j] =String.valueOf(t);
				t++;
			}
		}
		String[] tmp = new String[3];
		List<String> slist = new ArrayList<String>();
		getlist(str,0,tmp,slist);
		for(int i=0;i<slist.size();i++){
			System.out.println(slist.get(i));
		}
	} 
}
