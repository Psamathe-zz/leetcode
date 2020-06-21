package com.example.leetcode.weeklycontest.test194;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water.
 * If it rains over a lake which is full of water, there will be a flood. Your goal is to avoid the flood in any lake.
 *
 * Given an integer array rains where:
 *
 * rains[i] > 0 means there will be rains over the rains[i] lake.
 * rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
 * Return an array ans where:
 *
 * ans.length == rains.length
 * ans[i] == -1 if rains[i] > 0.
 * ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
 * If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.
 *
 * Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes. (see example 4)
 *
 *
 *
 * Example 1:
 *
 * Input: rains = [1,2,3,4]
 * Output: [-1,-1,-1,-1]
 * Explanation: After the first day full lakes are [1]
 * After the second day full lakes are [1,2]
 * After the third day full lakes are [1,2,3]
 * After the fourth day full lakes are [1,2,3,4]
 * There's no day to dry any lake and there is no flood in any lake.
 * Example 2:
 *
 * Input: rains = [1,2,0,0,2,1]
 * Output: [-1,-1,2,1,-1,-1]
 * Explanation: After the first day full lakes are [1]
 * After the second day full lakes are [1,2]
 * After the third day, we dry lake 2. Full lakes are [1]
 * After the fourth day, we dry lake 1. There is no full lakes.
 * After the fifth day, full lakes are [2].
 * After the sixth day, full lakes are [1,2].
 * It is easy that this scenario is flood-free. [-1,-1,1,2,-1,-1] is another acceptable scenario.
 * Example 3:
 *
 * Input: rains = [1,2,0,1,2]
 * Output: []
 * Explanation: After the second day, full lakes are  [1,2]. We have to dry one lake in the third day.
 * After that, it will rain over lakes [1,2]. It's easy to prove that no matter which lake you choose to dry in the 3rd day, the other one will flood.
 * Example 4:
 *
 * Input: rains = [69,0,0,0,69]
 * Output: [-1,69,1,1,-1]
 * Explanation: Any solution on one of the forms [-1,69,x,y,-1], [-1,x,69,y,-1] or [-1,x,y,69,-1] is acceptable where 1 <= x,y <= 10^9
 * Example 5:
 *
 * Input: rains = [10,20,20]
 * Output: []
 * Explanation: It will rain over lake 20 two consecutive days. There is no chance to dry any lake.
 *
 *
 * Constraints:
 *
 * 1 <= rains.length <= 10^5
 * 0 <= rains[i] <= 10^9
 */
public class AvoidFlood {
    public static void main(String[] args) {
        int[] rains = new int[]{69,0,0,0,69};
        AvoidFlood avoidFlood = new AvoidFlood();
        int[] result = avoidFlood.avoidFlood(rains);
        result.toString();
    }

    public int[] avoidFlood(int[] rains) {
        int length = rains.length;
        int[] result = new int[length];
        Arrays.fill(result,1);
        int lastZeroIndex;
        int lastNormalIndex;
        int indexInZeroList;
        List<Integer> zeroList = new LinkedList<>();
        Map<Integer,Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if(rains[i] == 0) {
                zeroList.add(i);
            } else {
                if(lastIndex.containsKey(rains[i])){
                    lastZeroIndex = -1;
                    lastNormalIndex = lastIndex.get(rains[i]);
                    for ( indexInZeroList = 0; indexInZeroList <zeroList.size();indexInZeroList++){
                        if(zeroList.get(indexInZeroList) > lastNormalIndex){
                            lastZeroIndex = zeroList.get(indexInZeroList) ;
                            break;
                        }
                    }
                    if(lastZeroIndex == -1)
                        return new int[]{};
                    else {
                        result[lastZeroIndex] = rains[i];
                        zeroList.remove(indexInZeroList);
                    }
                }
                lastIndex.put(rains[i],i);
                result[i] = -1;
            }
        }

        return result;
    }


    /**
     * faster solution
     * @param rains
     * @return
     */
    public int[] avoidFloodV1(int[] rains) {
        int[] ans = new int[rains.length];
        int[] nextIdxSame = new int[rains.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = rains.length - 1; i >= 0; i--) {
            if (!map.containsKey(rains[i])) {
                nextIdxSame[i] = -1;
            } else {
                nextIdxSame[i] = map.get(rains[i]);
            }
            map.put(rains[i], i);
        }
        TreeSet<Integer> idxFull = new TreeSet<>();
        HashSet<Integer> valFull = new HashSet<>();
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] > 0) {
                ans[i] = -1;
                if (valFull.contains(rains[i]))
                    return new int[0];
                if (nextIdxSame[i] != -1) {
                    valFull.add(rains[i]);
                    idxFull.add(nextIdxSame[i]);
                }
            } else {
                if (idxFull.isEmpty()) {
                    ans[i] = 1048576;
                    continue;
                }
                int idx = idxFull.first();
                ans[i] = rains[idx];
                idxFull.remove(idx);
                valFull.remove(ans[i]);
            }
        }
        return ans;
    }


    public int[] avoidFloodV2(int[] rains) {
        int zeroCount = 0, ind = 0, l = rains.length;
        int[] ans = new int[l];
        Map<Integer, Integer> pos = new HashMap<>();
        TreeSet<Integer> zeroPos = new TreeSet<>();
        for (int i = 0; i < l; i++) {
            int r = rains[i];
            if (r == 0) {
                zeroPos.add(i);
            } else {
                if (pos.containsKey(r)) {
                    Integer zeroP = zeroPos.higher(pos.get(r));
                    if (zeroP == null) {
                        return new int[0];
                    } else {
                        ans[zeroP] = r;
                        zeroPos.remove(zeroP);
                    }
                }
                pos.put(r, i);
                ans[i] = -1;
            }
        }
        for (int i = 0; i < l; i++) {
            if (ans[i] == 0) ans[i] = 1;
        }
        return ans;
    }

}
