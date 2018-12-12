package com.ssm.utils;

import java.util.Random;

public class StringRandom {
	
    private char[] chars ={'A','B','C','0','1','2','3','4','5','6','7','8','9'};  
    private Random random = new Random();  
    
    public StringRandom() {  
       
    }  
    
    public StringRandom(char[] chars) {  
        this.chars = chars;  
    }  
      
    public String getNextString(int length){ 
        char[] data = new char[length];  
          
        for(int i = 0;i < length;i++){  
            int index = random.nextInt(chars.length);  
            data[i] = chars[index];  
        }  
        String s = new String(data);  
        return s;  
    }  
}
