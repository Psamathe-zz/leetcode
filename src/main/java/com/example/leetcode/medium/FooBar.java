package com.example.leetcode.medium;

/**
 * Suppose you are given the following code:
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: "foobar"
 * Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
 * Example 2:
 *
 * Input: n = 2
 * Output: "foobarfoobar"
 * Explanation: "foobar" is being output 2 times.
 */
public class FooBar {
    public static void main(String[] args) {

    }

    /**
     * https://blog.csdn.net/zhangpeterx/article/details/103943110
     * OR Semaphore
     * @param n
     */

    public FooBar(int n) {
        this.n = n;
    }

    private volatile  int n;
    public  volatile boolean flag = false;
    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (flag) {
                    this.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = true;
                this.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (!flag) {
                    this.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = false;
                this.notifyAll();
            }
        }
    }
}
