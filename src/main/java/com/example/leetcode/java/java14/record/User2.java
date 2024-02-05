package com.example.leetcode.java.java14.record;

public record User2(String name, Integer age) {

    public static User2 of(String name, Integer age) {
        return new User2(name, age);
    }
    @Override
    public String toString() {
        return "User2[" +
                "name='" + name + '\'' +
                ", age=" + age +
                ']';
    }
    @Override
    public boolean equals(Object obj) {
        return false;
    }
    @Override
    public int hashCode() {
        return 0;
    }
}
