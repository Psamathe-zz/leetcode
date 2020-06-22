package com.example.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a list accounts, each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts.
 * Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order.
 * The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */
public class AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        AccountsMerge accountsMerge = new AccountsMerge();
        List<List<String>>  result = accountsMerge.accountsMergeV2(accounts);
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        List<UserAccount> userAccounts = new ArrayList<>();
        for(List<String> account : accounts){
            String userName = account.get(0);
            List<String> listAccount = account.stream().filter(e->!e.equals(userName)).collect(Collectors.toList());
            boolean canMerge = false;
            UserAccount accountBase = null;
            List<UserAccount> temp = new ArrayList<>();
            for(UserAccount userAccount : userAccounts){
                if(userAccount.canMerge(userName,listAccount)){
                    if(!canMerge) {
                        userAccount.addAccount(listAccount);
                        accountBase = userAccount;
                        canMerge = true;
                    } else {
                        accountBase.mergeAccount(userAccount);
                    }

                } else {
                    temp.add(userAccount);
                }
            }
            if(!canMerge){
                userAccounts.add(new UserAccount(userName,listAccount));
            } else {
                temp.add(accountBase);
                userAccounts = temp;
            }
        }
        for(UserAccount userAccount: userAccounts){
            List<String> list = new ArrayList<>();
            list.add(userAccount.name);
            list.addAll(userAccount.accounts.stream().sorted().collect(Collectors.toList()));
            result.add(list);
        }
        return result;
    }

    public class UserAccount{
        String name;
        Set<String> accounts;

        public UserAccount(String name, List<String> accounts) {
            this.name = name;
            this.accounts = new HashSet<>(accounts);
        }

        public void addAccount(List<String> accounts){
            this.accounts.addAll(accounts);
        }

        public void mergeAccount(UserAccount account){
            this.accounts.addAll(account.accounts);
        }

        public boolean canMerge(String name,List<String> accounts){
            if(name.equals(name)){
                for(String account : accounts){
                    if(this.accounts.contains(account)){
                        return true;
                    }
                }
            }
            return false;
        }
    }


    /**
     * faster solution
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMergeV2(List<List<String>> accounts) {
        Map<String, String> nameMap = new HashMap<>();        //<email, username>
        Map<String, Set<String>> adjGraph = new HashMap<>();  //<email node, neighbor nodes>
        // Build the graph;
        for (List<String> account : accounts) {
            String userName = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                nameMap.put(account.get(i), userName);
                adjGraph.putIfAbsent(account.get(i), new HashSet<>());
                if (i == 1)
                    continue;
                adjGraph.get(account.get(i)).add(account.get(i - 1));
                adjGraph.get(account.get(i - 1)).add(account.get(i));
            }
        }

        List<List<String>> retVal = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        // DFS search the graph;
        for (String email : nameMap.keySet()) {
            List<String> tempList = new LinkedList<>();
            if (visited.add(email)) {
                dfs(adjGraph, email, visited, tempList);
                Collections.sort(tempList);
                tempList.add(0, nameMap.get(email));
                retVal.add(tempList);
            }
        }

        return retVal;
    }

    public void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        list.add(email);
        for (String next : graph.get(email)) {
            if (visited.add(next)) {
                dfs(graph, next, visited, list);
            }
        }
    }
}
