package com.workspace;

public class WorkUtility {
    // leet code challenge 1 merge string alternatively
    public static String mergeAlternatively(String word1, String word2){
        int i=0; int j =0;
        StringBuilder sb = new StringBuilder();
        while(i < word1.length() && j < word2.length()){
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(j));
            i++;j++;
        }
        while (i < word1.length()) {
            sb.append(word1.charAt(i));
            i++;
        }

        while (j < word2.length()) {
            sb.append(word2.charAt(j));
            j++;
        }
        return sb.toString();

/*
*
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();
        int index = 0;
        String p1,p2 = "";
        if(char1.length > char2.length ){
           index =  char1.length-char2.length;
          p1 =  word1.substring(0,index);
           p2 = word1.substring(index);
           char1 = p1.toCharArray();
        }else if(char1.length < char2.length){
            index =  char2.length-char1.length;
             p1 = word2.substring(0,index);
             p2 = word2.substring(index);
             char2 = p1.toCharArray();
        }

        char[] char3 = new char[char1.length+ char2.length];
        int count = 0;
        for(char c : char1) {
            char3[count] = c;
            count+=2;
        }
        count=1;
        for(char c:char2){
            char3[count] = c;
            count+=2;
        }
        String s = new String(char3);
        return s+p2;
*/
    }
}
