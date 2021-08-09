package com.example.leetcode.sometest.functionalInterface;

/**
 * https://www.huaweicloud.com/articles/e85c5efe04389e297c12bd2214ca280c.html
 * 是否可以不拼接show方法中接口的字符串，节省硬件，答案是肯定的，使用lambda书写，它具有延时性，只用调用了，才会执行。
 */
public class DemoLambda {
    public static void main(String[] args) {
        String a1 = "Hello,";
        String a2 = "World";
        show(1, () -> a1 + a2);
        show(2, () -> a1 + a2);
        show(1, () -> {
            System.out.println("只有被用到才执行1！");
            return a1 + a2;
        });
        // 参数为2，不满足level == 1，不用进入if方法体内，d.string_build()不会被调用，所以lambda不会被执行，节省硬件性能
        show(2,()->{
            System.out.println("只有被用到才执行2！");
            return a1+a2;
        });

    }
    private static void show(int level, DemoStringBuild d) {
        if (level == 1) {
            System.out.println(d.string_build());
        }
    }
}