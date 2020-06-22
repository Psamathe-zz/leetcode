package com.example.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you how many other rabbits have the same color as them. Those answers are placed in an array.
 *
 * Return the minimum number of rabbits that could be in the forest.
 *
 * Examples:
 * Input: answers = [1, 1, 2]
 * Output: 5
 * Explanation:
 * The two rabbits that answered "1" could both be the same color, say red.
 * The rabbit than answered "2" can't be red or the answers would be inconsistent.
 * Say the rabbit that answered "2" was blue.
 * Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
 * The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
 *
 * Input: answers = [10, 10, 10]
 * Output: 11
 *
 * Input: answers = []
 * Output: 0
 * [1,0,1,0,0]
 * 5
 * [0,0,1,1,1]
 * 6
 */
public class RabbitsInForest {

    public static void main(String[] args) {
        int[] num = new int[]{10, 10, 10};
        RabbitsInForest rabbitsInForest = new RabbitsInForest();
        int result = rabbitsInForest.numRabbits(num);
        System.out.println(result);
    }
    public int numRabbits(int[] answers) {
        int count = 0;
        Map<Integer,IndexValue> myMap = new HashMap<>();
        for(int value : answers){
            if(myMap.containsKey(value)){
                IndexValue indexValue = myMap.get(value);
                if(indexValue.getIndex() <= value){
                    indexValue.setIndex(indexValue.getIndex() + 1);
                } else {
                    indexValue.setIndex(1);
                    indexValue.setValue(indexValue.getValue() + 1);
                }
                myMap.put(value,indexValue);
            } else{
                IndexValue indexValue = new IndexValue(1,1);
                myMap.put(value,indexValue);
            }
        }
        for(Map.Entry<Integer,IndexValue> entry: myMap.entrySet()){
            count += (entry.getKey() + 1) * entry.getValue().getValue();
        }
        return count;
    }

    public class IndexValue{
        int index;
        int value;

        public IndexValue(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * faster solutioin
     * @param answers
     * @return
     */
    public int numRabbitsV2(int[] answers) {
        int total = 0;
        int[] extra = new int[1000];
        for (int rabbit : answers) {
            if (extra[rabbit] > 0)
                extra[rabbit]--;
            else {
                extra[rabbit] = rabbit;
                total += rabbit + 1;
            }
        }
        return total;
    }


    /**
     * less memory
     * @param answers
     * @return
     */
    public int numRabbitsV3(int[] answers) {
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        int count=0;
        for(int i: answers){
            if(i==0){
                count++;
            }
            else if(map.containsKey(i)){
                map.put(i,map.get(i)-1);
                if(map.get(i)==0){
                    map.remove(i);
                }
            }
            else{
                map.put(i,i);
                count+=(i+1);
            }
        }
        return count;

    }
}
