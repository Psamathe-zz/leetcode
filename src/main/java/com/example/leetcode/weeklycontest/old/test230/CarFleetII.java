package com.example.leetcode.weeklycontest.old.test230;


import java.util.Arrays;
import java.util.Stack;

/**
 * There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array cars of length n, where cars[i] = [positioni, speedi] represents:
 *
 * positioni is the distance between the ith car and the beginning of the road in meters. It is guaranteed that positioni < positioni+1.
 * speedi is the initial speed of the ith car in meters per second.
 * For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.
 *
 * Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car, or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: cars = [[1,2],[2,1],[4,3],[7,2]]
 * Output: [1.00000,-1.00000,3.00000,-1.00000]
 * Explanation: After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.
 * Example 2:
 *
 * Input: cars = [[3,4],[5,4],[6,3],[9,1]]
 * Output: [2.00000,1.00000,1.50000,-1.00000]
 */
public class CarFleetII {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/car-fleet-ii/solution/zhi-jie-zhuang-si-wo-de-liao-java-by-xia-fieh/
     */
    class Car{
        double v;//speed
        double p;//position
        double t;//time
        Car(double p_,double v_,double t_){
            v = v_;
            p = p_;
            t = t_;
        }
        public double[] toArr(){
            return new double[]{p,v};
        }
    }
    public double calc(int[] car1,double[] car2){//计算相遇时间
        if(car1[1]<=car2[1])return -1;
        return (0.0+car2[0]-car1[0])/(car1[1]-car2[1]);
    }
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] res = new double[n];
        Arrays.fill(res,-1);
        Stack<Car> st = new Stack<>();
        for(int i=n-1;i>=0;i--){
            while(st.size()>0){//car_b的所有状态
                Car car_b = st.pop();
                double collide_t = calc(cars[i],car_b.toArr());
                if(collide_t==-1){//太快追不上，等它降速
                    continue;
                }
                if(st.size()==0||collide_t<=st.peek().t){
                    //在car_b降速前追上了，或者已经是最后一个阶段
                    //记录答案，car[i]结束
                    res[i] = collide_t;
                    double collide_v = car_b.v;
                    double collide_st_p = car_b.p;
                    //保存相遇时的状态
                    st.push(new Car(collide_st_p,collide_v,collide_t));
                    break;
                }
            }
            //保存初始状态
            st.push(new Car(cars[i][0],cars[i][1],0));
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/car-fleet-ii/solution/javadan-diao-zhan-jie-jue-che-dui-zhui-j-8744/
     * @param cars
     * @return
     */
    public double[] getCollisionTimesV0(int[][] cars) {
        int n = cars.length;
        double[] ans = new double[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=n-1;i>=0;i--){
            //栈不为空，需要比较当前车速与栈顶车速
            while(!stack.isEmpty()){
                //栈顶车速大于当前车速，则当前车追不上栈顶车，但是有可能追上栈顶元素的前一辆车
                if(cars[stack.peek()][1]>=cars[i][1]){
                    stack.pop();
                }else{//当前车速大于栈顶车速
                    //栈顶车撞不上它前面的车，因此，当前车一定可以撞上栈顶车
                    if(ans[stack.peek()]<0){
                        break;
                    }else{//栈顶车能撞上前面的车，需要分情况讨论
                        //如果当前车能在栈顶车撞上前面车之前就能够撞上栈顶车，则直接撞上去
                        if(((double)(cars[stack.peek()][0]-cars[i][0]))/((double)(cars[i][1]-cars[stack.peek()][1]))<=ans[stack.peek()]){
                            break;
                        }else{//否则的话，当前车就只能撞到栈顶车前面的车了
                            stack.pop();
                        }
                    }
                }
            }
            //初始时，栈为空，前面没车可撞
            if(stack.isEmpty()){
                ans[i] = -1;
            }else{//栈不为空，则撞上栈顶车
                ans[i] = ((double)(cars[stack.peek()][0]-cars[i][0]))/((double)(cars[i][1]-cars[stack.peek()][1]));
            }
            //当前车结果求出后，入栈
            stack.push(i);
        }
        return ans;
    }

}
