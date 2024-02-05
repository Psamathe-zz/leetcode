package com.example.leetcode.java.java14;

public class Test {
    //空指针报错
    public static void main(String[] args) {
        User user = null;
        System.out.println(user.getName());
    }

    static class User{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
