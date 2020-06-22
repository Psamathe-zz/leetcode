package com.example.leetcode.easy;

/**
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 *
 *
 *
 * Example 1:
 *
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 * Example 2:
 *
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 * Example 3:
 *
 * Input: name = "leelee", typed = "lleeelee"
 * Output: true
 * Example 4:
 *
 * Input: name = "laiden", typed = "laiden"
 * Output: true
 * Explanation: It's not necessary to long press any character.
 * qqq
 * qqqqq
 *
 * "vtkgn"
 * "vttkgnn"
 */
public class LongPressedName {

    public static void main(String[] args) {
        String name = "vtkgn";
        String typed = "vttkgnn";
        LongPressedName longPressedName = new LongPressedName();
        boolean result = longPressedName.isLongPressedName(name,typed);
        System.out.println(result);
    }

    public boolean isLongPressedName(String name, String typed) {
        if(name.equals(typed))
            return true;
        if(name.length() > typed.length())
            return false;

        int indexName = 0;
        int indexTyped = 0;

        while (indexTyped<typed.length()-1){
            if(name.charAt(indexName) == typed.charAt(indexTyped + 1)){
                if(indexName+1 <name.length() &&  name.charAt(indexName + 1) == typed.charAt(indexTyped + 1))
                    indexName++;
                indexTyped++;

            } else {
                if(indexName+1 <name.length() && name.charAt(indexName + 1) == typed.charAt(indexTyped + 1)) {
                    indexName++;
                    indexTyped++;
                } else {
                    return false;
                }
            }
        }
        return indexName==name.length()-1 && indexTyped== typed.length() -1;
    }

    public boolean isLongPressedNameV2(String name, String typed) {

        char[] one = name.toCharArray();
        char[] two = typed.toCharArray();

        int i = 0, j=0;

        while(j<two.length) {
            if(i<one.length && one[i] == two[j]) {
                j++;
                i++;
            } else if(i>0 && one[i-1] == two[j]){
                j++;
            } else {
                return false;
            }
        }


        return i == one.length;
    }

    public boolean isLongPressedNameV3(String name, String typed) {
        int i=0,j=0;

        for(;j<typed.length()&& i<name.length();)
        {
            if(name.charAt(i)==typed.charAt(j))
            {
                j++;
                i++;
            } else {
                j++;
            }
        }
        if(j== typed.length() && i!= name.length())
        {
            return false;
        }
        return true;
    }

}
