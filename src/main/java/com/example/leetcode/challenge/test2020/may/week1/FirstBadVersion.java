package com.example.leetcode.challenge.test2020.may.week1;

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * Example:
 *
 * Given n = 5, and version = 4 is the first bad version.
 *
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 *
 * Then 4 is the first bad version.
 */
public class FirstBadVersion {

    public static int BAD = 1702766719;
    public static void main(String[] args) {
        int n = 2126753390;
        FirstBadVersion firstBadVersion = new FirstBadVersion();
        int result = firstBadVersion.firstBadVersion(n);
        System.out.println(result);
    }

    public int firstBadVersion(int n) {
        long left = 1;
        long right = n;
        long mid;
        while(left < right){
            mid = (left + right) >> 1;
            if(isBadVersion((int)mid)){
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return (int)left;
    }
    public boolean isBadVersion(int version){
        if(version >= BAD)
            return true;
        else
            return false;
    }

    /**
     * less memory
     * @param n
     * @return
     */
    public int firstBadVersionV2(int n) {
        int low=0,mid=0,high=n;
        while(low<=high){
            mid=low+(high-low)/2;
            if(isBadVersion(mid)){
                high=mid-1;
            }
            else{
                low=mid+1;
            }
        }
        return low;
    }
}
