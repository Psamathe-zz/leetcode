package com.example.leetcode.biweeklycontest.contest68;

import java.util.*;

/**
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 *
 * Note that two recipes may contain each other in their ingredients.
 *
 *
 *
 * Example 1:
 *
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * Example 2:
 *
 * Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * Example 3:
 *
 * Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 *
 * ["bread","sandwich"]
 * [["yeast","flour"],["bread","meat"]]
 * ["yeast","flour","meat"]
 */
public class FindAllPossibleRecipes {
    public static void main(String[] args) {
        FindAllPossibleRecipes findAllPossibleRecipes = new FindAllPossibleRecipes();
        findAllPossibleRecipes.findAllRecipes(new String[]{"bread","sandwich"}, List.of(List.of("yeast","flour"),List.of("bread","meat")), new String[]{"yeast","flour","meat"});
    }

    Set<String> sup;
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        sup = new HashSet<>(Arrays.asList(supplies));

        Set<String> res = new HashSet<>();

        int loop = 100;

        while (loop >=0) {
            for (int i = 0; i < ingredients.size(); i++) {
                if (cancook(ingredients.get(i))) {
                    sup.add(recipes[i]);
                    res.add(recipes[i]);
                }
            }
            loop--;
        }



        return new LinkedList<>(res);

    }

    private boolean cancook(List<String> ingredient) {

        for (String s : ingredient) {
            if (!sup.contains(s)) {
                return false;
            }
        }
        return true;

    }
}
