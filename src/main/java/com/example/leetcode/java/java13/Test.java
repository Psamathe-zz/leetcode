package com.example.leetcode.java.java13;

public class Test {
    public static void main(String[] args) {

        //switch表达式改进
        var me = 4;
        var operation = "other";
        var result = switch (operation) {
            case "加倍" -> me * 2;
            case "平方" -> me * me;
            default -> me;
        };

        System.out.println(result);

        // JSON
        String TEXT_BLOCK_JSON = """
        {
            "name" : "Baeldung",
            "website" : "https://www.dayangshuo.cn/"
        }
        """;
        System.out.println(TEXT_BLOCK_JSON);
    }
}
