package com.chen.base_utils;

import android.text.PrecomputedText;

import java.util.Arrays;
import java.util.EnumSet;

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
            if (strs[i].length() < sho.length()) {
                sho = strs[i];
            }
        }
        int pos = 0;
        boolean need = false;
        System.out.println("short:" + sho);
        while (pos < sho.length()) {
            need = false;
            System.out.println("---currentPos:" + pos);
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].charAt(pos) != sho.charAt(pos)) {
                    need = true;
                    break;
                }
            }
            if (need) {

                return sho.substring(0, pos);
            } else {

                pos++;
            }

        }
        return sho.substring(0, pos);
    }


    public static int strStr(String haystack, String needle) {
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean matched = true;
            for (int j = 0; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(j + i)) {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isPalindrome(String s) {
        int length = s.length();
        int startPos = 0;
        int endPos = length - 1;
        char start = ' ';
        char end = ' ';
        while (startPos <= endPos) {
            start = isChar(s.charAt(startPos++));
            System.out.println("currentStart: " + start + ", pos:" + (startPos - 1));
            while (start == ' ' && startPos < length) {
                start = isChar(s.charAt(startPos++));
                System.out.println("--currentStart: " + start + ", pos:" + (startPos - 1));
            }
            end = isChar(s.charAt(endPos--));
            System.out.println("currentend: " + end + ", end:" + (end + 1));
            while (end == ' ' && endPos >= 0) {
                end = isChar(s.charAt(endPos--));
                System.out.println("--currentend: " + end + ", end:" + (end + 1));
            }
            System.out.println("startPos = " + startPos + ", resu:" + start);
            System.out.println("endPos = " + endPos + ", resu:" + end);
            if (start != end) {
                return false;
            }
        }
        return true;
    }

    public static char isChar(char tmp) {
        if (tmp >= '0' && tmp <= '9') {
            return tmp;
        }
        if (tmp >= 'A' && tmp <= 'Z') {
            tmp += 32;
        }
        if (tmp >= 'a' && tmp <= 'z') {
            return tmp;
        }
        return ' ';
    }


    public static boolean isSubsequence(String s, String t) {

        int posS = 0;

        int posT = 0;
        int sLength = s.length();
        int tLength = t.length();
        boolean findChad = false;
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);

            System.out.println("\n startFIndChar: " + tmp + ",pos:" + i);
            findChad = false;
            for (int j = posT; j < tLength && (tLength - (sLength - i) >= sLength - i); j++) {
                System.out.println("currentStartPos= " + j + ",charAt: " + t.charAt(j));
                if (tmp == t.charAt(j)) {
                    posT = j + 1;
                    findChad = true;
                    break;

                }
                System.out.println("currentFind: " + findChad);
            }
            if (!findChad) {
                System.out.println("charNotFind: " + tmp + ";postion:" + i);
                System.out.println("charNotFind: " + tmp + ";postion:" + i);
                return false;
            }
        }
        return true;
    }

    public int[] twoSumSlow(int[] numbers, int target) {
        int startPos = 0;
        int endPos = numbers.length;
        int[] resultNums = new int[2];
        for (int i = 0; i < endPos - 1; i++) {
            for (int j = i + 1; j < endPos; j++) {
                if (numbers[i] + numbers[j] == target) {
                    resultNums[0] = numbers[i];
                    resultNums[1] = numbers[j];
                    return resultNums;
                }
            }
        }
        return resultNums;
    }


    /**
     * 在有序数组中找出两个数字满足两字之和等于指定数字
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int startPos = 0;
        int endPos = numbers.length - 1;
        int sum = 0;
        for (; startPos < endPos; ) {
            sum = numbers[startPos] + numbers[endPos];
            if (sum == target) {
                return new int[]{startPos + 1, endPos + 1};
            }
            if (sum > target) {
                endPos--;
            } else {
                startPos++;
            }

        }

        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 当前方法用于判断一个链表是否含有循环
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode != null) {
            fastNode = fastNode.next;
            if (fastNode != null) {
                fastNode = fastNode.next;
            }
            if (fastNode == slowNode) {
                return true;
            }
            slowNode = slowNode.next;

        }
        return false;
    }


    /**
     * 按照链表排列的两个数进行相加，结果还是链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
     /*   ListNode target = null;
        ListNode nodeA= l1;
        ListNode nodeB= l2;
        ListNode add =new ListNode(0);
        while (nodeB !=null && nodeA !=null){
            int tempVal = 0;
            if(add.next!=null){
                tempVal=add.next.val;
                add = add.next;
            }
            int value = (nodeA.val +nodeB.val+tempVal)%10;
            int tenVal = (nodeA.val +nodeB.val+tempVal)/10;

             add.val=value;
             if(tenVal!=0){
                 add.next = new ListNode(tenVal);
             }
            if(target ==null){
                target =add;
            }
            nodeA = nodeA.next;
            nodeB=nodeB.next;
            if(nodeA!=null || nodeB!=null){
                if(add.next ==null){
                    add.next= new ListNode(0);
                    add=add.next;
                }
            }

        }
        while(nodeB!=null) {
            if(){

            }
        }

        if(nodeA!=null){

        }*/
        return null;
    }


    public static ListNode addTwoNum(ListNode l1, ListNode l2) {
        ListNode tempNode = new ListNode(0);
        ListNode currentNode = new ListNode(0);
        ListNode start = currentNode;
        int lastVal = 0;
        int currentL1val = 0;
        int curretnL2val = 0;
        int count = 0;
        while (l1 != null || l2 != null || tempNode.val != 0) {

            lastVal = tempNode.val;
            if (l1 != null) {
                currentL1val = l1.val;
            } else {
                currentL1val = 0;
            }
            if (l2 != null) {
                curretnL2val = l2.val;
            } else {
                curretnL2val = 0;
            }

            int nextNumber = (currentL1val + curretnL2val + lastVal) / 10;
            int currentNum = (currentL1val + curretnL2val + lastVal) % 10;
            System.out.println("currentV1:" + currentL1val + "; curretnV2 :" + curretnL2val + "; lastva:" + lastVal);
            System.out.println("currentNum:" + currentNum + "; nextNumber :" + nextNumber);

            currentNode.val = currentNum;
            tempNode.val = nextNumber;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            if (l1 != null || l2 != null || tempNode.val != 0) {
                currentNode.next = new ListNode(0);
                currentNode = currentNode.next;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        System.out.println("dsjafkdasj;flkdasj------" + (9 / 10));

        ListNode nodeA = new ListNode(9);
        ListNode a2 = new ListNode(9);
        ListNode a3 = new ListNode(9);
        nodeA.next = a2;
        a2.next = a3;

        ListNode nodeB = new ListNode(9);
        ListNode b1 = new ListNode(9);
        //ListNode b2 = new ListNode(4);
        nodeB.next = b1;
        //b1.next=b2;
        addTwoNum(nodeA, nodeB);

    }


    /**
     * 对一个由证书组成的非空数组表示的非负整数，在该数字基础上加一
     *
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        int nextNumber = 1;
        int gewei = 0;
        int shiwei = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            gewei = (digits[i] + nextNumber) % 10;
            shiwei = (digits[i] + nextNumber) / 10;

            nextNumber = shiwei;
            digits[i] = gewei;
        }
        if (nextNumber != 0) {
            int[] result = new int[digits.length + 1];
            result[0] = nextNumber;

            for (int i = 0; i < digits.length; i++) {
                result[i + 1] = digits[i];
            }
            return result;
        }
        return digits;
    }

    public static int removeDuplicates2(int[] nums) {
        int currentPos = 0;
        int currentNum = nums[currentPos];
        int currentNumShowCount = 0;
        int reachedPos = 0;

        while (reachedPos < nums.length - 1) {
            int reachedNum = nums[++reachedPos];


            if (currentNumShowCount == 1) { //这里重复出现的元素已经到达上限，
                if (reachedNum == currentNum) {
                    while (reachedNum == currentNum && reachedPos < nums.length - 1) {
                        reachedNum = nums[++reachedPos];
                    }
                    if (reachedNum == currentNum) {
                        return currentPos + 1;
                    }
                    currentNumShowCount = 0;
                    currentNum = reachedNum;
                    nums[++currentPos] = reachedNum;

                } else {
                    currentNumShowCount = 0;
                    currentNum = reachedNum;
                    nums[++currentPos] = reachedNum;
                }


            } else {
                if (reachedNum == currentNum) {
                    currentNumShowCount++;
                } else {
                    currentNumShowCount = 0;
                    currentNum = reachedNum;
                }
                nums[++currentPos] = reachedNum;
            }
        }
        return currentPos + 1;
    }


    public void rotateN(int[] nums, int k) {
        if (nums.length <= 1 || k <= 0) {
            return;
        }
        k = k % nums.length;

        for (int i = 0; i < k; i++) {
            int temp = nums[0];
            int temp2 = nums[1];
            for (int j = 0; j < nums.length - 1; j++) {
                temp2 = nums[j + 1];
                nums[j + 1] = temp;
                temp = temp2;
            }
            nums[0] = temp;
        }


    }

    public int maxProfit1(int[] prices) {
        int maxProfit=0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i]<minPrice){
                minPrice=prices[i];
            }else if(prices[i]-minPrice>maxProfit){
                maxProfit = prices[i]-minPrice;
            }
        }
        return maxProfit;
    }
    public int maxProfitMulti(int[] prices) {
        int maxProfit=0;
        if(prices.length == 0){
            return 0;
        }
        for (int i = 0; i < prices.length-1; i++) {
             if(prices[i+1]-prices[i]>0){
                 maxProfit+=prices[i+1]-prices[i];
             }
        }
        return maxProfit;
    }


    /**
     * 这个算法的本质就是看数组中是否存在0元素，并且是否可以跳跃过零元素，
     * 如果可以正常跳过零元素那么一定可以到达数组终点。
     *
     * 解体思路： 先判断有没有0元素这个是最基础的，如果没有0元素，那么一定可以到达
     * 2. 然后判断有没有 值等于其到0元素距离的元素
     * 3.如果条件2存在，然后判定在这个角标之后有没有其值大于 其到0元素距离的元素，如果不存在，那么就越不过0元素。
     *  出现第一个数值等于角标的元素之后，
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums){
        if(nums.length>1){
            if(nums[0]==0){
                return false;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0){
                boolean jump=false;
                for (int j = 0; j <i; j++) {
                        int sum =0;

                            sum+=nums[j]-(i-j);

                        if(sum<=0){
                            return false;
                        }

                }
            }
        }
        return true;
    }

    /**
     * H指数  ， 至少有 n个数>=n
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations){
        int n=0;
        int cal=0;
        while (n< citations.length){
            cal =0;
            for (int i = 0; i < citations.length; i++) {
                if(citations[i]>n){
                    cal++;
                }
            }
            if(cal<=n){
                return n;
            }
            n++;
        }
        return n;
    }



}
