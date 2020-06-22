package com.example.leetcode.weeklycontest.test185;

import java.util.*;

/**
 * Given the array orders, which represents the orders that customers have done in a restaurant. More specifically orders[i]=[customerNamei,tableNumberi,foodItemi] where customerNamei is the name of the customer, tableNumberi is the table customer sit at, and foodItemi is the item customer orders.
 *
 * Return the restaurant's “display table”. The “display table” is a table whose row entries denote how many of each food item each table ordered. The first column is the table number and the remaining columns correspond to each food item in alphabetical order. The first row should be a header whose first column is “Table”, followed by the names of the food items. Note that the customer names are not part of the table. Additionally, the rows should be sorted in numerically increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * Output: [["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * Explanation:
 * The displaying table looks like:
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * For the table 3: David orders "Ceviche" and "Fried Chicken", and Rous orders "Ceviche".
 * For the table 5: Carla orders "Water" and "Ceviche".
 * For the table 10: Corina orders "Beef Burrito".
 * Example 2:
 *
 * Input: orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
 * Output: [["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
 * Explanation:
 * For the table 1: Adam and Brianna order "Canadian Waffles".
 * For the table 12: James, Ratesh and Amadeus order "Fried Chicken".
 * Example 3:
 *
 * Input: orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
 * Output: [["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
 */
public class DisplayTableFoodOrdersInRestaurant {
    public static void main(String[] args) {
        List<List<String>> orders = new ArrayList<>();
        orders.add(Arrays.asList("David","3","Ceviche"));
        orders.add(Arrays.asList("Corina","10","Beef Burrito"));
        orders.add(Arrays.asList("David","3","Fried Chicken"));
        orders.add(Arrays.asList("Carla","5","Water"));
        orders.add(Arrays.asList("Carla","5","Ceviche"));
        orders.add(Arrays.asList("Rous","3","Ceviche"));
        DisplayTableFoodOrdersInRestaurant displayTableFoodOrdersInRestaurant = new DisplayTableFoodOrdersInRestaurant();
        List<List<String>> result = displayTableFoodOrdersInRestaurant.displayTable(orders);
    }

    public List<List<String>> displayTable(List<List<String>> orders) {

        Map<TableFood,Integer> tableFoodMap = new HashMap<>();

        Set<String> tableSet = new HashSet<>();
        Set<String> foodSet = new HashSet<>();

        for(List<String> order : orders){
            String table = order.get(1);
            String food = order.get(2);

            tableSet.add(table);
            foodSet.add(food);

            TableFood tableFood = new TableFood(table,food);

            tableFoodMap.put(tableFood,tableFoodMap.getOrDefault(tableFood,0) + 1);
        }


        List<String> tableList = new ArrayList<>(tableSet);
        Collections.sort(tableList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.valueOf(o1) - Integer.valueOf(o2);
            }
        });

        List<String> foodList = new ArrayList<>(foodSet);
        Collections.sort(foodList);

        List<List<String>> result = new ArrayList<>();


        List<String> tableFoodList = new ArrayList<>();
        tableFoodList.add("Table");
        tableFoodList.addAll(foodList);
        result.add(tableFoodList);

        for(String table : tableList){
            List<String> tempList = new ArrayList<>();
            tempList.add(table);
            for(String food: foodList){
                tempList.add(tableFoodMap.getOrDefault(new TableFood(table,food),0).toString());
            }
            result.add(tempList);
        }
        return result;
    }

    private class TableFood  {
        String table;
        String food;


        public TableFood(String table, String food){
            this.table = table;
            this.food = food;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TableFood tableFood = (TableFood) o;
            return Objects.equals(table, tableFood.table) &&
                    Objects.equals(food, tableFood.food);
        }

        @Override
        public int hashCode() {
            return Objects.hash(table, food);
        }

    }
}
