package com.example.leetcode.challenge.test2020.June.week4;

public class FindTheDuplicateNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{3,1,3,4,2};
        FindTheDuplicateNumber findTheDuplicateNumber = new FindTheDuplicateNumber();
        int result = findTheDuplicateNumber.findDuplicateV0_0(nums);
        System.out.println(result);
    }

    public int findDuplicate(int[] nums) {
        boolean found = false;
        int i;
        int j;
        for (i = 0; i < nums.length & !found; i++) {
            for (j = i + 1; j < nums.length & !found; j++) {
                if(nums[i] == nums[j]){
                    found = true;
                }
            }
        }
        return nums[i];
    }

    int findDuplicateV1(int[] nums) {
        int left = 1, right = nums.length;
        while (left < right){
            int mid = left + (right - left) / 2, cnt = 0;
            for (int num : nums) {
                if (num <= mid)
                    ++cnt;
            }
            if (cnt <= mid)
                left = mid + 1;
            else right = mid;
        }
        return right;
    }

    /**
     * faster solution
     * http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/
     * https://zhuanlan.zhihu.com/p/83798235
     *
     * 另一种方法是利用快慢指针，和有环链表找环入口很相似。
     *
     * 算法的思想是：把当前的元素值作为序号，指向下一个元素，假如当前位置在第i个元素，指向的下一个元素是a[a[i]]，形成类似链表的结构。
     * 因为数组中的元素值介于[1,n]之间，所以每次的指向，都会落在数组中的元素上。
     * 并且这个链表是有环的，因为如果没有环，这个链表将无限延伸下去，但是数组的序号是有限的，不可能每次都生成新的序号，所以必然会生成重复的序号，形成环。
     * 这样就转换成了有环链表找环的入口，也就是找重复的序号。
     *
     * 从nums[0]开始，设置快慢两个指针，慢的每次跳一步，快的每次跳两步，当快指针追上慢指针时，慢指针回到nums[0]，之后两个指针都每次走一步，相遇时则时环的入口。
     * @param nums
     * @return
     */
    public int findDuplicateV0_0(int[] nums) {
        int slow = 0, fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast)
                break;
        }
        slow = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[fast];
            if (slow == fast)
                break;
        }
        return slow;
    }
    public int findDuplicateV0(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        fast = nums[fast];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public int findDuplicateV2(int[] nums) {
        if(nums.length==0)
            return -1;
        for(int i=0;i<nums.length;i++){
            while(nums[i]!=i+1){

                if(nums[nums[i]-1]==nums[i])
                    return nums[i];
                int temp=nums[i];
                nums[i]=nums[temp-1];
                nums[temp-1]=temp;
            }
        }

        return -1;
    }
}
