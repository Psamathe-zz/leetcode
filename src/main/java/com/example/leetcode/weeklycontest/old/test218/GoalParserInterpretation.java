package com.example.leetcode.weeklycontest.old.test218;


/**
 * You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()" and/or "(al)" in some order. The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al". The interpreted strings are then concatenated in the original order.
 *
 * Given the string command, return the Goal Parser's interpretation of command.
 *
 *
 *
 * Example 1:
 *
 * Input: command = "G()(al)"
 * Output: "Goal"
 * Explanation: The Goal Parser interprets the command as follows:
 * G -> G
 * () -> o
 * (al) -> al
 * The final concatenated result is "Goal".
 * Example 2:
 *
 * Input: command = "G()()()()(al)"
 * Output: "Gooooal"
 * Example 3:
 *
 * Input: command = "(al)G(al)()()G"
 * Output: "alGalooG"
 *
 *
 * Constraints:
 *
 * 1 <= command.length <= 100
 * command consists of "G", "()", and/or "(al)" in some order.
 */
public class GoalParserInterpretation {
    public static void main(String[] args) {
        GoalParserInterpretation goalParserInterpretation = new GoalParserInterpretation();
        String res = goalParserInterpretation.interpret("(al)G(al)()()G");
        System.out.println(res);
    }

    public String interpret(String command) {
        char[] chars = command.toCharArray();
        int length = chars.length;
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < length){
            if(chars[index] == 'G'){
                stringBuilder.append('G');
                index++;

            } else{
                if(chars[index+1] == ')'){
                    stringBuilder.append("o");
                    index += 2;
                } else {
                    stringBuilder.append("al");
                    index += 4;
                }
            }

        }
        return stringBuilder.toString();
    }
}
