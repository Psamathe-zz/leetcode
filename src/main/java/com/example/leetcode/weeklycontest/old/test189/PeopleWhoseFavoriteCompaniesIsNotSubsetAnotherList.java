package com.example.leetcode.weeklycontest.old.test189;

import java.util.*;

/**
 * Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies for the ith person (indexed from 0).
 *
 * Return the indices of people whose list of favorite companies is not a subset of any other list of favorites companies.
 * You must return the indices in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
 * Output: [0,1,4]
 * Explanation:
 * Person with index=2 has favoriteCompanies[2]=["google","facebook"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] corresponding to the person with index 0.
 * Person with index=3 has favoriteCompanies[3]=["google"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] and favoriteCompanies[1]=["google","microsoft"].
 * Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
 * Example 2:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
 * Output: [0,1]
 * Explanation: In this case favoriteCompanies[2]=["facebook","google"] is a subset of favoriteCompanies[0]=["leetcode","google","facebook"], therefore, the answer is [0,1].
 * Example 3:
 *
 * Input: favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
 * Output: [0,1,2,3]
 */
public class PeopleWhoseFavoriteCompaniesIsNotSubsetAnotherList {
    public static void main(String[] args) {
        List<List<String>> favoriteCompanies = new ArrayList<>();
        favoriteCompanies.add(Arrays.asList("leetcode","google","facebook"));
        favoriteCompanies.add(Arrays.asList("leetcode","amazon"));
        favoriteCompanies.add(Arrays.asList("facebook","google"));
        PeopleWhoseFavoriteCompaniesIsNotSubsetAnotherList peopleWhoseFavoriteCompaniesIsNotSubsetAnotherList = new PeopleWhoseFavoriteCompaniesIsNotSubsetAnotherList();
        List<Integer> result = peopleWhoseFavoriteCompaniesIsNotSubsetAnotherList.peopleIndexes(favoriteCompanies);
        System.out.println(result);
    }

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> result = new ArrayList<>();
        Map<String,Set<Integer>> map = new HashMap<>();
        int index = 0;
        for(List<String> list : favoriteCompanies){
            for(String str : list){
                if(!map.containsKey(str)){
                    map.put(str, new HashSet<>());
                    map.get(str).add(index);
                } else {
                    map.get(str).add(index);
                }
            }
            index++;
        }
        for(int i = 0 ; i < index; i++){
            Set<Integer> toTest = new HashSet<>();
            for(Map.Entry<String,Set<Integer>> entry : map.entrySet()){
                Set<Integer> set = entry.getValue();
                if(set.contains(i)){
                    if(toTest.size() == 0){
                        toTest.addAll(set);
                    } else {
                        Iterator iterator = toTest.iterator();
                        while (iterator.hasNext()){
                            Integer value = (Integer) iterator.next();
                            if(!set.contains(value)){
                                iterator.remove();
                            }
                        }
                    }
                }
                if(toTest.size() == 1)
                    break;
            }

            if(toTest.size() == 1){
                result.add(i);
            }
        }

        return result;
    }

    /**
     * faster solution
     * @param favoriteCompanies
     * @return
     */
    public List<Integer> peopleIndexesV2(List<List<String>> favoriteCompanies) {
        List<Set<String>> fcs = new ArrayList<>();
        for(List<String> companies : favoriteCompanies) {
            fcs.add(new HashSet<>(companies));
        }
        int n = favoriteCompanies.size();
        List<Integer> output = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            boolean isSubset = false;
            Set set1 = fcs.get(i);
            for(int j = 0; j < n; j++) {
                Set set2 = fcs.get(j);
                if(i!=j && set2.size()>set1.size() && set2.containsAll(set1)) {
                    isSubset=true;
                    break;
                }
            }

            if(!isSubset) {
                output.add(i);
            }
        }
        return output;
    }
}
