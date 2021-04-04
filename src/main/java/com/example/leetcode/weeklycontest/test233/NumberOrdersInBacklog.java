package com.example.leetcode.weeklycontest.test233;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a 2D integer array orders, where each orders[i] = [pricei, amounti, orderTypei] denotes that amounti orders have been placed of type orderTypei at the price pricei. The orderTypei is:
 *
 * 0 if it is a batch of buy orders, or
 * 1 if it is a batch of sell orders.
 * Note that orders[i] represents a batch of amounti independent orders with the same price and order type. All orders represented by orders[i] will be placed before all orders represented by orders[i+1] for all valid i.
 *
 * There is a backlog that consists of orders that have not been executed. The backlog is initially empty. When an order is placed, the following happens:
 *
 * If the order is a buy order, you look at the sell order with the smallest price in the backlog. If that sell order's price is smaller than or equal to the current buy order's price, they will match and be executed, and that sell order will be removed from the backlog. Else, the buy order is added to the backlog.
 * Vice versa, if the order is a sell order, you look at the buy order with the largest price in the backlog. If that buy order's price is larger than or equal to the current sell order's price, they will match and be executed, and that buy order will be removed from the backlog. Else, the sell order is added to the backlog.
 * Return the total amount of orders in the backlog after placing all the orders from the input. Since this number can be large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
 * Output: 6
 * Explanation: Here is what happens with the orders:
 * - 5 orders of type buy with price 10 are placed. There are no sell orders, so the 5 orders are added to the backlog.
 * - 2 orders of type sell with price 15 are placed. There are no buy orders with prices larger than or equal to 15, so the 2 orders are added to the backlog.
 * - 1 order of type sell with price 25 is placed. There are no buy orders with prices larger than or equal to 25 in the backlog, so this order is added to the backlog.
 * - 4 orders of type buy with price 30 are placed. The first 2 orders are matched with the 2 sell orders of the least price, which is 15 and these 2 sell orders are removed from the backlog. The 3rd order is matched with the sell order of the least price, which is 25 and this sell order is removed from the backlog. Then, there are no more sell orders in the backlog, so the 4th order is added to the backlog.
 * Finally, the backlog has 5 buy orders with price 10, and 1 buy order with price 30. So the total number of orders in the backlog is 6.
 * Example 2:
 *
 *
 * Input: orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
 * Output: 999999984
 * Explanation: Here is what happens with the orders:
 * - 109 orders of type sell with price 7 are placed. There are no buy orders, so the 109 orders are added to the backlog.
 * - 3 orders of type buy with price 15 are placed. They are matched with the 3 sell orders with the least price which is 7, and those 3 sell orders are removed from the backlog.
 * - 999999995 orders of type buy with price 5 are placed. The least price of a sell order is 7, so the 999999995 orders are added to the backlog.
 * - 1 order of type sell with price 5 is placed. It is matched with the buy order of the highest price, which is 5, and that buy order is removed from the backlog.
 * Finally, the backlog has (1000000000-3) sell orders with price 7, and (999999995-1) buy orders with price 5. So the total number of orders = 1999999991, which is equal to 999999984 % (109 + 7).
 *
 *
 * Constraints:
 *
 * 1 <= orders.length <= 105
 * orders[i].length == 3
 * 1 <= pricei, amounti <= 109
 * orderTypei is either 0 or 1.
 * [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
 * [[26,7,0],[16,1,1],[14,20,0],[23,15,1],[24,26,0],[19,4,1],[1,1,0]]
 * [[1,29,1],[22,7,1],[24,1,0],[25,15,1],[18,8,1],[8,22,0],[25,15,1],[30,1,1],[27,30,0]]
 * [[1,7,1],[22,6,1],[18,8,1],[30,1,1]]
 */
public class NumberOrdersInBacklog {
    public static void main(String[] args) {
        int[][] orders = new int[][]{
                {1,29,1},
                {22,7,1},
                {24,1,0},
                {25,15,1},
                {18,8,1},
                {8,22,0},
                {25,15,1},
                {30,1,1},
                {27,30,0}
        };
        NumberOrdersInBacklog numberOrdersInBacklog = new NumberOrdersInBacklog();
        numberOrdersInBacklog.getNumberOfBacklogOrders(orders);
    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        Queue<long[]> buy = new PriorityQueue<>((x, y) -> Long.compare(y[0], x[0])); // 大根堆
        Queue<long[]> sell = new PriorityQueue<>((x, y) -> Long.compare(x[0], y[0])); // 小根堆

        for (int[] order : orders) {
            long price = order[0], amount = order[1], type = order[2];

            if (type == 0) {
                while (amount > 0 && !sell.isEmpty()) {
                    if (sell.peek()[0] <= price) {
                        // sell 价格 <= buy 价格
                        if (sell.peek()[1] <= amount) {
                            // sell 数量 <= buy 数量
                            amount -= sell.peek()[1];
                            sell.poll();
                        } else {
                            // sell 数量 > buy 数量
                            long t1 = sell.peek()[0];
                            long t2 = sell.peek()[1] - amount;
                            amount = 0;
                            sell.poll();
                            sell.offer(new long[]{t1, t2});
                        }
                    } else {
                        // sell 价格 > buy 价格，当前 buy 入堆
                        buy.offer(new long[]{price, amount});
                        amount = 0;
                        break;
                    }
                }

                // 当前 buy 匹配后仍有剩余，将剩余入堆
                if (amount > 0) {
                    buy.offer(new long[]{price, amount});
                }
            } else {
                // 代码逻辑同上
                while (amount > 0 && !buy.isEmpty()) {
                    if (buy.peek()[0] >= price) {
                        if (buy.peek()[1] <= amount) {
                            amount -= buy.peek()[1];
                            buy.poll();
                        } else {
                            long t1 = buy.peek()[0];
                            long t2 = buy.peek()[1] - amount;
                            amount = 0;
                            buy.poll();
                            buy.offer(new long[]{t1, t2});
                        }
                    } else {
                        sell.offer(new long[]{price, amount});
                        amount = 0;
                        break;
                    }
                }

                if (amount > 0) {
                    sell.offer(new long[]{price, amount});
                }
            }
        }

        // 统计结果，注意对中间过程取余
        long res = 0, MOD = 1000000007;
        while (!buy.isEmpty()) {
            res = (res + buy.poll()[1]) % MOD;
        }

        while (!sell.isEmpty()) {
            res = (res + sell.poll()[1]) % MOD;
        }

        return (int) (res % MOD);
    }

    public int getNumberOfBacklogOrdersV0(int[][] orders) {
        int mod = 1000000007;
        PriorityQueue<Order> queueBuy = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);

        PriorityQueue<Order> queueSell = new PriorityQueue<>(Comparator.comparingInt(o -> o.price));
        for (int[] order : orders){
            int count = order[1];
            int price = order[0];
            Queue<Order> temp = new LinkedList<>();
            if(order[2] == 0){
                int size = queueSell.size();
                while (!queueSell.isEmpty() && queueSell.peek().price <= price && size > 0 && count > 0){
                    Order myOrder = queueSell.poll();
                    if(count < myOrder.count){
                        queueSell.offer(new Order(myOrder.price,myOrder.count - count));
                    }
                    count -= myOrder.count;
                    size--;
                }
                if(count > 0)
                    queueBuy.offer(new Order(price,count));
            } else {
                int size = queueBuy.size();
                while (!queueBuy.isEmpty() && size > 0  && count > 0){
                    Order myOrder = queueBuy.poll();
                    if(myOrder.price >= price){
                        if(count < myOrder.count)
                            temp.offer(new Order(myOrder.price,myOrder.count - count));
                        count -= myOrder.count;
                    } else {
                        temp.offer(myOrder);
                    }
                    size--;
                }
                queueBuy.addAll(temp);
                if(count > 0)
                    queueSell.offer(new Order(price,count));
            }
        }
        int res = 0;
        for (Order order : queueBuy){
            res += order.count;
            res %= mod;
        }
        for (Order order : queueSell){
            res += order.count;
            res %= mod;
        }
        return res;
    }

    public class Order{
        int price;
        int count;

        public Order(int price, int count) {
            this.price = price;
            this.count = count;
        }
    }
}
