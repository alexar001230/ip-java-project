package com.yunlonglee.str;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 16/1/22 12:30 上午
 */
public class StrReverse2 {
    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        System.out.println(reverseStr(s, k));
    }
    public static String reverseStr(String s, int k) {
        int length = s.length();
        int numsLeft = length%(2*k);
        //被2k整除部分

        int i = 0;
        int j = 2*k-1;
        String res = "";
        while(j<s.length()-numsLeft+1){
            String str2Replace = s.substring(i,i+k);
            String result = doReverse(str2Replace);
            res =  result+s.substring(i+k,2*k) +res;
            i = j+1;
            j = j+2*k;
        }
        //剩余部分
        String subStrLeft = s.substring(length - numsLeft);
        if(numsLeft >= k && numsLeft < 2*k){
            res =
                    res+doReverse(subStrLeft.substring(0,k))+subStrLeft.substring(k);
        }else{
            res = res + subStrLeft;
        }
        return res;
    }

    public static String doReverse(String origin){
        String[] originArr = origin.split("");
        int i = 0;
        int j = origin.length() - 1;
        while(j>i){
            String temp = originArr[i];
            originArr[i] = originArr[j];
            originArr[j] = temp;
            i++;
            j--;
        }
        StringBuilder sb = new StringBuilder();
        for(int m=0;m< originArr.length;m++){
            sb.append(originArr[m]);
        }
        return sb.toString();
    }
}
