package com.example.leetcode.codinggame;

import java.util.*;
import java.io.*;
import java.math.*;

public class Play {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        List<Recipe> recipes = new ArrayList<>();
        int id;
        // game loop
        while (true) {
            int actionCount = in.nextInt(); // the number of spells and recipes in play
            id = 0;
            for (int i = 0; i < actionCount; i++) {
                int actionId = in.nextInt(); // the unique ID of this spell or recipe
                String actionType = in.next(); // in the first league: BREW; later: CAST, OPPONENT_CAST, LEARN, BREW
                int delta0 = in.nextInt(); // tier-0 ingredient change
                int delta1 = in.nextInt(); // tier-1 ingredient change
                int delta2 = in.nextInt(); // tier-2 ingredient change
                int delta3 = in.nextInt(); // tier-3 ingredient change
                int price = in.nextInt(); // the price in rupees if this is a potion
                int tomeIndex = in.nextInt(); // in the first two leagues: always 0; later: the index in the tome if this is a tome spell, equal to the read-ahead tax; For brews, this is the value of the current urgency bonus
                int taxCount = in.nextInt(); // in the first two leagues: always 0; later: the amount of taxed tier-0 ingredients you gain from learning this spell; For brews, this is how many times you can still gain an urgency bonus
                boolean castable = in.nextInt() != 0; // in the first league: always 0; later: 1 if this is a castable player spell
                boolean repeatable = in.nextInt() != 0; // for the first two leagues: always 0; later: 1 if this is a repeatable player spell
                Recipe recipe = new Recipe(actionId,actionType,delta0,delta1,delta2,delta3,price,tomeIndex,taxCount,castable,repeatable);
                System.out.println(recipe.toString());
                recipes.add(recipe);
            }

            for (int i = 0; i < 2; i++) {
                int inv0 = in.nextInt(); // tier-0 ingredients in inventory
                int inv1 = in.nextInt();
                int inv2 = in.nextInt();
                int inv3 = in.nextInt();
                int score = in.nextInt(); // amount of rupees
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            int max = 0;
            int temp;
            int index = 0;
            for (Recipe recipe : recipes){
                temp = recipe.delta0 + recipe.delta1 + recipe.delta2 + recipe.delta3;
                System.out.println(temp);
                if(temp > max){
                    max = temp;
                    id = index;
                }
                index++;
            }
            System.out.println(id);
            recipes.clear();
            // in the first league: BREW <id> | WAIT; later: BREW <id> | CAST <id> [<times>] | LEARN <id> | REST | WAIT
            System.out.println("BREW " + id);
        }
    }




    public static class Recipe{
        int actionId;
        String actionType;
        int delta0;
        int delta1;
        int delta2;
        int delta3;
        int price;
        int tomeIndex;
        int taxCount;
        boolean castable;
        boolean repeatable;

        public Recipe(int actionId, String actionType, int delta0, int delta1, int delta2, int delta3, int price, int tomeIndex, int taxCount, boolean castable, boolean repeatable) {
            this.actionId = actionId;
            this.actionType = actionType;
            this.delta0 = delta0;
            this.delta1 = delta1;
            this.delta2 = delta2;
            this.delta3 = delta3;
            this.price = price;
            this.tomeIndex = tomeIndex;
            this.taxCount = taxCount;
            this.castable = castable;
            this.repeatable = repeatable;
        }

        @Override
        public String toString() {
            return "Recipe{" +
                    "actionId=" + actionId +
                    ", actionType='" + actionType + '\'' +
                    ", delta0=" + delta0 +
                    ", delta1=" + delta1 +
                    ", delta2=" + delta2 +
                    ", delta3=" + delta3 +
                    ", price=" + price +
                    ", tomeIndex=" + tomeIndex +
                    ", taxCount=" + taxCount +
                    ", castable=" + castable +
                    ", repeatable=" + repeatable +
                    '}';
        }
    }
}
