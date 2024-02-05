package com.example.leetcode.weeklycontest.old.test257;

import java.util.Arrays;

/**
 * You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
 *
 * A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
 *
 * Return the number of weak characters.
 *
 *
 *
 * Example 1:
 *
 * Input: properties = [[5,5],[6,3],[3,6]]
 * Output: 0
 * Explanation: No character has strictly greater attack and defense than the other.
 * Example 2:
 *
 * Input: properties = [[2,2],[3,3]]
 * Output: 1
 * Explanation: The first character is weak because the second character has a strictly greater attack and defense.
 * Example 3:
 *
 * Input: properties = [[1,5],[10,4],[4,3]]
 * Output: 1
 * Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 *[[7,7],[1,2],[9,7],[7,3],[3,10],[9,8],[8,10],[4,3],[1,5],[1,5]]
 */
public class NumberWeakCharacters {
    public static void main(String[] args) {
        NumberWeakCharacters  numberWeakCharacters = new NumberWeakCharacters();
        numberWeakCharacters.numberOfWeakCharacters(new int[][]{
                {7,7},
                {1,2},
                {9,7},
                {7,3},
                {3,10},
                {9,8},
                {8,10},
                {4,3},
                {1,5},
                {1,5},
        });
    }

    /**
     * 预处理：对properties数组进行排序，先按攻击力从大到小排序，若攻击力相等，则按防御力从小到大排序
     * 一次遍历过程：
     * 1.前提条件，因为按照攻击力从大到小排过序，所以当我们从前往后遍历数组时，每一个property的攻击力必定是<=上一个property的攻击力的，所以主要考虑防御力大小即可
     * 2.设置一个maxDef代表数组中的最大防御力
     * 3.从前往后遍历时，当碰到一个property的防御力<maxDef，代表前面的properties中，至少有一个防御值是maxDef的property的防御力是大于当前property的
     * 4.剩下来再判断攻击力。若当前property的攻击力 < maxDef的property的攻击力，则显然攻防都<maxDef的property，则是弱角色。
     * 5.若当前property的攻击力 == maxDef的property的攻击力，因为我们排序的时候要求，当攻击力相等时，防御力是从小到大排序的，所以在当前property前方的maxDef对应的proerty，它的防御力必然是≤当前property的防御力，这与最开始的已知条件“碰到一个property的防御力<maxDef”发生冲突，所以代表这种情况是不存在的。
     * 6.因此只要有防御力小于maxDef的proerty出现，必然是弱角色
     *
     * 作者：easontong-xue
     * 链接：https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game/solution/java-pai-xu-yi-ci-bian-li-by-easontong-x-i4ha/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param properties
     * @return
     */
    public int numberOfWeakCharacters(int[][] properties) {
        int ans = 0;
        Arrays.sort(properties,(a,b)->(a[0]==b[0]?a[1]-b[1]:b[0]-a[0]));
        int maxDef = 0;
        for(int[] property:properties){
            if(property[1]<maxDef){
                ans++;
            }else{
                maxDef = property[1];
            }
        }
        return ans;

    }
}
