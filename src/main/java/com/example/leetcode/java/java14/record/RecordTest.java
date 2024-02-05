package com.example.leetcode.java.java14.record;

public class RecordTest {
    public static void main(String[] args) {
        //传统的方式
        User1 user1 = new User1("大阳", 1990);
        System.out.println(user1);
        //reocrd 记录类
        User2 user2 = new User2("大阳", 1990);
        System.out.println(user2);

        User2 user3 = User2.of("月亮", 1989);
        System.out.println(user3);
    }
}
