package com.example.leetcode.biweeklycontest.old.contest60;

import java.util.*;

/**
 * You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of the ith node. The root of the tree is node 0, so parent[0] = -1 since it has no parent. You want to design a data structure that allows users to lock, unlock, and upgrade nodes in the tree.
 *
 * The data structure should support the following functions:
 *
 * Lock: Locks the given node for the given user and prevents other users from locking the same node. You may only lock a node if the node is unlocked.
 * Unlock: Unlocks the given node for the given user. You may only unlock a node if it is currently locked by the same user.
 * Upgrade: Locks the given node for the given user and unlocks all of its descendants. You may only upgrade a node if all 3 conditions are true:
 * The node is unlocked,
 * It has at least one locked descendant (by any user), and
 * It does not have any locked ancestors.
 * Implement the LockingTree class:
 *
 * LockingTree(int[] parent) initializes the data structure with the parent array.
 * lock(int num, int user) returns true if it is possible for the user with id user to lock the node num, or false otherwise. If it is possible, the node num will become locked by the user with id user.
 * unlock(int num, int user) returns true if it is possible for the user with id user to unlock the node num, or false otherwise. If it is possible, the node num will become unlocked.
 * upgrade(int num, int user) returns true if it is possible for the user with id user to upgrade the node num, or false otherwise. If it is possible, the node num will be upgraded.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock"]
 * [[[-1, 0, 0, 1, 1, 2, 2]], [2, 2], [2, 3], [2, 2], [4, 5], [0, 1], [0, 1]]
 * Output
 * [null, true, false, true, true, true, false]
 *
 * Explanation
 * LockingTree lockingTree = new LockingTree([-1, 0, 0, 1, 1, 2, 2]);
 * lockingTree.lock(2, 2);    // return true because node 2 is unlocked.
 *                            // Node 2 will now be locked by user 2.
 * lockingTree.unlock(2, 3);  // return false because user 3 cannot unlock a node locked by user 2.
 * lockingTree.unlock(2, 2);  // return true because node 2 was previously locked by user 2.
 *                            // Node 2 will now be unlocked.
 * lockingTree.lock(4, 5);    // return true because node 4 is unlocked.
 *                            // Node 4 will now be locked by user 5.
 * lockingTree.upgrade(0, 1); // return true because node 0 is unlocked and has at least one locked descendant (node 4).
 *                            // Node 0 will now be locked by user 1 and node 4 will now be unlocked.
 * lockingTree.lock(0, 1);    // return false because node 0 is already locked.
 *
 */
public class LockingTree {
    public static void main(String[] args) {
        LockingTree lockingTree = new LockingTree(new int[]{-1,0,3,1,0});
        lockingTree.upgrade(4,5);
        lockingTree.upgrade(3,8);
        lockingTree.unlock(0,7);
        lockingTree.lock(2, 7);
        lockingTree.upgrade(4,6);
    }

    int[] lockUser;
    int[] parent;
    Map<Integer, List<Integer>> descendant;
    public LockingTree(int[] parent) {
        this.parent = parent;
        descendant = new HashMap<>();
        lockUser = new int[parent.length];
        for (int i = 0; i < parent.length; i++) {
            int finalI = i;
            descendant.compute(parent[i], (k, v) -> {
                if(v == null) {
                    List<Integer> list = new ArrayList<>();
                    list.add(finalI);
                    return list;
                }
                v.add(finalI);
                return v;
            });
        }
    }

    public boolean lock(int num, int user) {
        if(lockUser[num] == 0) {
            lockUser[num] = user;
            return true;
        }
        return false;
    }

    public boolean unlock(int num, int user) {
        if(lockUser[num] == user) {
            lockUser[num] = 0;
            return true;
        }
        return false;
    }

    public boolean upgrade(int num, int user) {
        if(lockUser[num] == 0){
            int parentNode = num;
            do {
                parentNode = parent[parentNode];

            } while (parentNode != -1 && lockUser[parentNode] == 0);
            if(parentNode != -1)
                return false;

            Queue<Integer> nextNodes = new LinkedList<>();
            List<Integer> lockedList = new ArrayList<>();
            int nextNode;
            if(descendant.get(num) != null)
                nextNodes.addAll(descendant.get(num));
            int size;
            while (!nextNodes.isEmpty()){
                size = nextNodes.size();
                while (size > 0){
                    nextNode = nextNodes.poll();
                    if(lockUser[nextNode] != 0){
                        lockedList.add(nextNode);
                    }
                    if(descendant.get(nextNode) != null)
                        nextNodes.addAll(descendant.get(nextNode));
                    size--;
                }
            }
            if(lockedList.isEmpty())
                return false;
            else {
                for (Integer node : lockedList){
                    lockUser[node] = 0;
                }
            }
            lock(num, user);
            return true;
        }
        return false;
    }
}
