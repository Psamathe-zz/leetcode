package com.example.leetcode.biweeklycontest.old.contest41;


/**
 * You have the task of delivering some boxes from storage to their ports using only one ship. However, this ship has a limit on the number of boxes and the total weight that it can carry.
 *
 * You are given an array boxes, where boxes[i] = [ports​​i​, weighti], and three integers portsCount, maxBoxes, and maxWeight.
 *
 * ports​​i is the port where you need to deliver the ith box and weightsi is the weight of the ith box.
 * portsCount is the number of ports.
 * maxBoxes and maxWeight are the respective box and weight limits of the ship.
 * The boxes need to be delivered in the order they are given. The ship will follow these steps:
 *
 * The ship will take some number of boxes from the boxes queue, not violating the maxBoxes and maxWeight constraints.
 * For each loaded box in order, the ship will make a trip to the port the box needs to be delivered to and deliver it. If the ship is already at the correct port, no trip is needed, and the box can immediately be delivered.
 * The ship then makes a return trip to storage to take more boxes from the queue.
 * The ship must end at storage after all the boxes have been delivered.
 *
 * Return the minimum number of trips the ship needs to make to deliver all boxes to their respective ports.
 *
 *
 *
 * Example 1:
 *
 * Input: boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3
 * Output: 4
 * Explanation: The optimal strategy is as follows:
 * - The ship takes all the boxes in the queue, goes to port 1, then port 2, then port 1 again, then returns to storage. 4 trips.
 * So the total number of trips is 4.
 * Note that the first and third boxes cannot be delivered together because the boxes need to be delivered in order (i.e. the second box needs to be delivered at port 2 before the third box).
 * Example 2:
 *
 * Input: boxes = [[1,2],[3,3],[3,1],[3,1],[2,4]], portsCount = 3, maxBoxes = 3, maxWeight = 6
 * Output: 6
 * Explanation: The optimal strategy is as follows:
 * - The ship takes the first box, goes to port 1, then returns to storage. 2 trips.
 * - The ship takes the second, third and fourth boxes, goes to port 3, then returns to storage. 2 trips.
 * - The ship takes the fifth box, goes to port 3, then returns to storage. 2 trips.
 * So the total number of trips is 2 + 2 + 2 = 6.
 * Example 3:
 *
 * Input: boxes = [[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]], portsCount = 3, maxBoxes = 6, maxWeight = 7
 * Output: 6
 * Explanation: The optimal strategy is as follows:
 * - The ship takes the first and second boxes, goes to port 1, then returns to storage. 2 trips.
 * - The ship takes the third and fourth boxes, goes to port 2, then returns to storage. 2 trips.
 * - The ship takes the fifth and sixth boxes, goes to port 3, then returns to storage. 2 trips.
 * So the total number of trips is 2 + 2 + 2 = 6.
 * Example 4:
 *
 * Input: boxes = [[2,4],[2,5],[3,1],[3,2],[3,7],[3,1],[4,4],[1,3],[5,2]], portsCount = 5, maxBoxes = 5, maxWeight = 7
 * Output: 14
 * Explanation: The optimal strategy is as follows:
 * - The ship takes the first box, goes to port 2, then storage. 2 trips.
 * - The ship takes the second box, goes to port 2, then storage. 2 trips.
 * - The ship takes the third and fourth boxes, goes to port 3, then storage. 2 trips.
 * - The ship takes the fifth box, goes to port 3, then storage. 2 trips.
 * - The ship takes the sixth and seventh boxes, goes to port 3, then port 4, then storage. 3 trips.
 * - The ship takes the eighth and ninth boxes, goes to port 1, then port 5, then storage. 3 trips.
 * So the total number of trips is 2 + 2 + 2 + 2 + 3 + 3 = 14.
 */
public class DeliveringBoxes {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/delivering-boxes-from-storage-to-ports/solution/hua-dong-chuang-kou-by-ayaphis-aghs/
     * 首先可以证明
     * 一次装运处理箱子比任意两次装运需要的跑路次数少
     *
     * 所以
     * 1.尽量多装少趟
     * 2.不影响跑路次数的情况下，最后一次考虑尽量少装
     *
     * 滑动窗口处理一趟最多能装运的最多箱子数。
     * 窗口右界每次 +1
     * 然后处理窗口左界
     *
     * 重量超过最大载重
     * 箱子数超过最大载箱
     * 前面处理到第 i 个箱子和 i+1 个箱子时跑路次数一样
     *
     * 作者：ayaphis
     * 链接：https://leetcode-cn.com/problems/delivering-boxes-from-storage-to-ports/solution/hua-dong-chuang-kou-by-ayaphis-aghs/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param boxes
     * @param portsCount
     * @param maxBoxes
     * @param maxWeight
     * @return
     */
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int [] dp = new int [boxes.length + 1];
        dp[1] = 2;
        int trip = 2;
        int load = boxes[0][1];
        int b = 1;
        for(int i = 0, j = 1; j < boxes.length; j++){
            load += boxes[j][1];
            b++;
            if (boxes[j][0] != boxes[j-1][0]) trip++;
            while(load > maxWeight || b > maxBoxes || dp[i+1] == dp[i]){
                b--;
                load -= boxes[i++][1];
                if (boxes[i][0] != boxes[i-1][0] )
                    trip--;
            }
            dp[j+1] = dp[i] + trip;

        }
        return dp[boxes.length];
    }
}
