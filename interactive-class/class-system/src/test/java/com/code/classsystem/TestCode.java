package com.code.classsystem;

public class TestCode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.replaceSpace(new StringBuffer("hello world"));
        System.out.print(s);
    }
      static class Solution {
        public String replaceSpace(StringBuffer str) {
            if(str==null){
                return null;
            }

            String str2 = str.toString();
            int len1 = str2.length();
            System.out.println(len1);
            int blankNum=0;
            for(int i=0; i < len1;i++){
                if(str2.charAt(i)== ' ')
                    blankNum++;
            }
            if(blankNum == 0){
                return str2;
            }

            char[] newStr = new char[len1 + 3*blankNum];
            for(int i= len1 -1 ; i >= 0;i--){
                char c = str2.charAt(i);
                if(c == ' '){
                    newStr[i + 3*blankNum] = '0';
                    newStr[i + 3*blankNum-1] = '2';
                    newStr[i + 3*blankNum-2] = '%';
                    blankNum--;
                }else{
                    newStr[i + 3*blankNum] = c ;
                }
            }
            System.out.println(new String(newStr).length());
            return new String(newStr);
        }
    }
}
