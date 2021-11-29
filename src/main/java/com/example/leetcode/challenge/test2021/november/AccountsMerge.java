package com.example.leetcode.challenge.test2021.november;

import java.util.*;

/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Example 2:
 *
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 */
public class AccountsMerge {
    public static void main(String[] args) {

    }

    Map<Integer, Integer> father;
    /**
     * @param accounts: List[List[str]]
     * @return: return a List[List[str]]
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        father = new HashMap();

        // union
        Map<String, List> emailToIds = getEmailToIds(accounts);
        for (String email : emailToIds.keySet()) {
            List<Integer> ids = emailToIds.get(email);
            for (int i = 1; i < ids.size(); i++) {
                union(ids.get(i), ids.get(0));
            }
        }

        // merge accounts
        Map<Integer, Set<String>> idToEmailSet = getIdToEmailSet(accounts);
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (Integer id : idToEmailSet.keySet()) {
            List<String> sortedEmails = new ArrayList(idToEmailSet.get(id));
            Collections.sort(sortedEmails);
            sortedEmails.add(0, accounts.get(id).get(0));
            mergedAccounts.add(sortedEmails);
        }

        return mergedAccounts;
    }

    private Map<String, List> getEmailToIds(List<List<String>> accounts) {
        Map<String, List> emailToIds = new HashMap<>();
        for (int user_id  = 0; user_id < accounts.size(); user_id++) {
            List<String> account = accounts.get(user_id);
            for (int i = 1; i < account.size(); i++) {
                List<Integer> ids = emailToIds.getOrDefault(account.get(i), new ArrayList<Integer>());
                ids.add(user_id);
                emailToIds.put(account.get(i), ids);
            }
        }
        return emailToIds;
    }

    private Map<Integer, Set<String>> getIdToEmailSet(List<List<String>> accounts) {
        Map<Integer, Set<String>> idToEmailSet = new HashMap<>();
        for (int user_id  = 0; user_id < accounts.size(); user_id++) {
            int root_id = find(user_id);
            Set<String> emailSet = idToEmailSet.getOrDefault(root_id, new HashSet<String>());
            List<String> account = accounts.get(user_id);
            for (int i = 1; i < account.size(); i++) {
                emailSet.add(account.get(i));
            }
            idToEmailSet.put(root_id, emailSet);
        }
        return idToEmailSet;
    }

    private int find(int id) {
        List<Integer> path = new ArrayList<>();
        while (father.containsKey(id)) {
            path.add(id);
            id = father.get(id);
        }

        for (Integer i : path) {
            father.put(i, id);
        }

        return id;
    }

    private void union(int id1, int id2) {
        int root1 = find(id1);
        int root2 = find(id2);
        if (root1 != root2) {
            father.put(root1, root2);
        }
    }


    /**
     * faster solution
     */
    private static final int NAME = 0;
    public List<List<String>> accountsMergeV2(List<List<String>> accounts) {
        // Step 1: Dedupe via union.
        var n = accounts.size();
        var solver = new UnionFindSolver(n);
        var emailToIndex = new HashMap<String, Integer>();
        for (var i = 0; i < n; i++) {
            var account = accounts.get(i);
            for (var j = NAME + 1; j < account.size(); j++) {
                var k = emailToIndex.put(account.get(j), i);
                if (k != null) {
                    solver.union(i, k);
                }
            }
        }

        // Step 2: Merge via find.
        var indexToEmail = new HashMap<Integer, Set<String>>();
        for (var i = 0; i < n; i++) {
            var j = solver.find(i);
            var account = accounts.get(i);
            indexToEmail.computeIfAbsent(j, k -> new HashSet<>()).addAll(account.subList(NAME + 1, account.size()));
        }
        // Step 3: Sort emails alphabetically.
        var result = new ArrayList<List<String>>(indexToEmail.size());
        for (var entry : indexToEmail.entrySet()) {
            var account = new ArrayList<>(entry.getValue());
            Collections.sort(account);
            account.add(NAME, accounts.get(entry.getKey()).get(NAME));
            result.add(account);
        }
        return result;
    }

    private static class UnionFindSolver {
        private final int[] parent;

        UnionFindSolver(int n) {
            parent = new int[n];
            for (var i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        void union(int a, int b) {
            var pa = find(a);
            var pb = find(b);
            if (pa != pb) {
                parent[pa] = pb;
            }
        }

        int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }
    }
}

