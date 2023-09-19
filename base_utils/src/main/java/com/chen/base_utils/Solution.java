package com.chen.base_utils;

import android.text.PrecomputedText;

import java.util.Arrays;
//https://leetcode.cn/studyplan/top-interview-150/
public class Solution {
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {

        int posA = m - 1;
        int posB = n - 1;
        int exchangedPos = nums1.length - 1;

        if (posA < 0) {
            nums1[0] = nums2[0];
            return nums1;
        }
        while (posB >= 0 && exchangedPos >= 0) {
            if (posA == -1 && posB >= 0) {
                nums1[exchangedPos--] = nums2[posB--];
                continue;
            }
            nums1[exchangedPos--] = nums1[posA] > nums2[posB] ? nums1[posA--] : nums2[posB--];
        }

        return nums1;
    }


    public int removeElement(int[] numbsers, int val) {
        int calculatedPos = 0;
        int copyPos = numbsers.length - 1;
/*        for (int i = 0; i < numbsers.length; i++) {
            if(numbsers[i]==val){
                numbsers[i]=
            }
        }*/
        while (calculatedPos < numbsers.length && copyPos >= calculatedPos) {
            if (numbsers[calculatedPos] == val) {
                numbsers[calculatedPos] = numbsers[copyPos];
                copyPos--;
            } else {
                calculatedPos++;
            }
        }


        return calculatedPos;
    }


    public static int removeDuplicates(int[] nums) {

        int posCurrent = 0;
        int reachedPos = 0;
        int numLength = nums.length;
        int max = nums[numLength - 1];


        while (nums[posCurrent] < max) {
            if (nums[posCurrent] == nums[reachedPos]) {
                reachedPos++;
            } else {
                nums[++posCurrent] = nums[reachedPos++];
            }
        }

        return posCurrent + 1;
    }


    public static int removeDuplicateBesideDouble(int[] nums) {
        int currentPos = 0;
        int reachedPos = 1;
        int count = 0;
        int length = nums.length;
        int maxNum = nums[length - 1];
        if (length <= 2) {
            return length;
        }

        while (reachedPos < length - 1) {

        }

        return currentPos;
    }

    public static int majorElement(int[] nums) {
        int currentPos = 0;
        int targetPos = 1;
        int length = nums.length;
        while (targetPos < length) {
            if (nums[currentPos] == nums[targetPos]) {
                nums[++currentPos] = nums[targetPos++];
            } else {
                if (currentPos == 0) {
                    nums[currentPos] = nums[targetPos];
                } else {
                    currentPos--;

                }

                targetPos++;
            }
        }

        return nums[0];
    }


    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        int j = 0;
        int temp = 0;
        int change = 0;
        System.out.println("开始转换： " + Arrays.toString(nums));
        int length = nums.length;
        temp = nums[j];
        System.out.println("\n");
        System.out.println("开始进行第" + j + "次：" + "," +
                "第一个数字是： " + temp + "\n;array:" + Arrays.toString(nums));
        while ((j + k) % length != 0) {
            change = nums[(j + k) % nums.length];
            nums[(j + k) % nums.length] = temp;
            temp = change;
            j += k;
            System.out.println("转换后的Array：" + Arrays.toString(nums) + ";. 数字是： " + temp + "j=" + j);
        }

        nums[(j + k) % nums.length] = temp;
        System.out.println("最后后的Array：" + Arrays.toString(nums) + ";. 数字是： " + temp);


    }


    public int maxProfit(int[] prices) {
        int maxPro = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > maxPro) {
                    maxPro = prices[j] - prices[i];
                }
            }
        }
        return maxPro;
    }


    public int lengthOfLastWord(String s) {
        int length = 0;
        s.trim();
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) != ' ') {
                length++;
            } else {
                return length;
            }
        }

        return length;
    }

    public static String longestCommonPrefix(String[] strs) {
        String sho = strs[0];
        for (int i = 0; i < strs.length; i++) {
            if(strs[i].length()<sho.length()){
                sho = strs[i];
            }
        }
        int pos =0;
        boolean need= false;
        System.out.println("short:"+sho);
         while (pos< sho.length()){
             need=false;
             System.out.println("---currentPos:"+pos);
             for (int i = 0; i < strs.length ; i++) {
                 if(strs[i].charAt(pos) != sho.charAt(pos)){
                     need = true;
                     break;
                 }
             }
             if(need ){

                 return sho.substring(0,pos);
             }else{

                 pos++;
             }

         }
          return sho.substring(0,pos);
    }


    public static void main(String[] args) {
        System.out.println("dsjafkdasj;flkdasj------" + (4 / 2));
    /*    String[] nums = {"flower","flow","flight"};
        String s =longestCommonPrefix(nums);
        System.out.println("array:: " +s);*/


        //是否是回文串

       String s = "0P";
        System.out.println("isPa: "+isPalindrome(s));


    }
    public static int strStr(String haystack,String needle){
        for (int i = 0; i <= haystack.length() -needle.length(); i++) {
            boolean matched = true;
            for (int j = 0; j < needle.length(); j++) {
                if(needle.charAt(j)!=haystack.charAt(j+i)){
                    matched=false;
                    break;
                }
            }
            if(matched){
                return i;
            }
        }
        return  -1;
    }

    public static boolean isPalindrome(String s){
        int length = s.length();
        int startPos =0;
        int endPos =length-1;
        char start= ' ';
        char end = ' ';
        while (startPos<=endPos){
            start = isChar(s.charAt(startPos++));
            System.out.println("currentStart: "+ start +", pos:"+(startPos-1));
            while (start == ' ' && startPos<length){
                start = isChar(s.charAt(startPos++));
                System.out.println("--currentStart: "+ start +", pos:"+(startPos-1));
            }
            end = isChar(s.charAt(endPos--));
            System.out.println("currentend: "+ end +", end:"+(end+1));
            while (end == ' ' && endPos>=0){
                end=isChar(s.charAt(endPos--));
                System.out.println("--currentend: "+ end +", end:"+(end+1));
            }
            System.out.println("startPos = "+startPos+", resu:"+start);
            System.out.println("endPos = "+endPos+", resu:"+end);
            if(start != end){
                return  false;
            }
        }
        return true;
    }

    public  static char isChar(char tmp){
        if(tmp >= '0' && tmp <= '9'){
            return tmp;
        }
        if(tmp >= 'A' && tmp <='Z'){
            tmp +=32;
        }
        if(tmp >='a'&& tmp <= 'z'){
            return tmp;
        }
        return  ' ';
    }
}
