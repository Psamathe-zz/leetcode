package com.example.leetcode.weeklycontest.test251;


import java.util.*;

/**
 * Due to a bug, there are many duplicate folders in a file system. You are given a 2D array paths, where paths[i] is an array representing an absolute path to the ith folder in the file system.
 *
 * For example, ["one", "two", "three"] represents the path "/one/two/three".
 * Two folders (not necessarily on the same level) are identical if they contain the same non-empty set of identical subfolders and underlying subfolder structure. The folders do not need to be at the root level to be identical. If two or more folders are identical, then mark the folders as well as all their subfolders.
 *
 * For example, folders "/a" and "/b" in the file structure below are identical. They (as well as their subfolders) should all be marked:
 * /a
 * /a/x
 * /a/x/y
 * /a/z
 * /b
 * /b/x
 * /b/x/y
 * /b/z
 * However, if the file structure also included the path "/b/w", then the folders "/a" and "/b" would not be identical. Note that "/a/x" and "/b/x" would still be considered identical even with the added folder.
 * Once all the identical folders and their subfolders have been marked, the file system will delete all of them. The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.
 *
 * Return the 2D array ans containing the paths of the remaining folders after deleting all the marked folders. The paths may be returned in any order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: paths = [["a"],["c"],["d"],["a","b"],["c","b"],["d","a"]]
 * Output: [["d"],["d","a"]]
 * Explanation: The file structure is as shown.
 * Folders "/a" and "/c" (and their subfolders) are marked for deletion because they both contain an empty
 * folder named "b".
 * Example 2:
 *
 *
 * Input: paths = [["a"],["c"],["a","b"],["c","b"],["a","b","x"],["a","b","x","y"],["w"],["w","y"]]
 * Output: [["c"],["c","b"],["a"],["a","b"]]
 * Explanation: The file structure is as shown.
 * Folders "/a/b/x" and "/w" (and their subfolders) are marked for deletion because they both contain an empty folder named "y".
 * Note that folders "/a" and "/c" are identical after the deletion, but they are not deleted because they were not marked beforehand.
 * Example 3:
 *
 *
 * Input: paths = [["a","b"],["c","d"],["c"],["a"]]
 * Output: [["c"],["c","d"],["a"],["a","b"]]
 * Explanation: All folders are unique in the file system.
 * Note that the returned array can be in a different order as the order does not matter.
 * Example 4:
 *
 *
 * Input: paths = [["a"],["a","x"],["a","x","y"],["a","z"],["b"],["b","x"],["b","x","y"],["b","z"]]
 * Output: []
 * Explanation: The file structure is as shown.
 * Folders "/a/x" and "/b/x" (and their subfolders) are marked for deletion because they both contain an
 * empty folder named "y".
 * Folders "/a" and "/b" (and their subfolders) are marked for deletion because they both contain an empty
 * folder "z" and the folder "x" described above.
 * Example 5:
 *
 *
 * Input: paths = [["a"],["a","x"],["a","x","y"],["a","z"],["b"],["b","x"],["b","x","y"],["b","z"],["b","w"]]
 * Output: [["b"],["b","w"],["b","z"],["a"],["a","z"]]
 * Explanation: This has the same structure as the previous example, except with the added "/b/w".
 * Folders "/a/x" and "/b/x" are still marked, but "/a" and "/b" are no longer marked because "/b" has the
 * empty folder named "w" and "/a" does not.
 * Note that "/a/z" and "/b/z" are not marked because the set of identical subfolders must be non-empty, but these folders are empty.
 *
 *
 * Constraints:
 *
 * 1 <= paths.length <= 2 * 104
 * 1 <= paths[i].length <= 500
 * 1 <= paths[i][j].length <= 10
 * 1 <= sum(paths[i][j].length) <= 2 * 105
 * path[i][j] consists of lowercase English letters.
 * No two paths lead to the same folder.
 * For any folder not at the root level, its parent folder will also be in the input.
 */
public class DeleteDuplicateFolders {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/delete-duplicate-folders-in-system/solution/java-dfs-trie-by-jackie-tien-vx6s/
     * @param paths
     * @return
     */
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Node root = new Node("/", -1);
        Map<Integer, Set<Integer>> next = new HashMap<>();
        paths.sort(Comparator.comparingInt(List::size));

        for (int i = 0; i < paths.size(); i++) {
            buildTree(root, paths.get(i), i, next);
        }

        Map<String, Set<Integer>> map = new HashMap<>();
        dfs(root, map);
        Set<Integer> deleted = new HashSet<>();
        for (Set<Integer> set : map.values()) {
            if (set.size() > 1) {
                deleted.addAll(set);
                for (Integer p : set) {
                    deleted.addAll(next.get(p));
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < paths.size(); i++) {
            if (!deleted.contains(i)) {
                res.add(paths.get(i));
            }
        }
        return res;
    }

    private void buildTree(Node root, List<String> path, int index, Map<Integer, Set<Integer>> next) {
        Node parent = root;
        int n = path.size();
        for (int i = 0; i < n - 1; i++) {
            parent = parent.children.get(path.get(i));
        }
        next.computeIfAbsent(parent.index, k -> new HashSet<>()).add(index);
        parent.children.put(path.get(n - 1), new Node(path.get(n - 1), index));
    }

    private String dfs(Node root, Map<String, Set<Integer>> map) {
        if (root.children.isEmpty()) {
            return "{" + root.nodePath + "}";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("{");
            for (Node child : root.children.values()) {
                builder.append(dfs(child, map));
            }
            map.computeIfAbsent(builder.substring(1), k -> new HashSet<>()).add(root.index);
            return builder.append(root.nodePath).append("}").toString();
        }
    }

    private static class Node {
        String nodePath;
        int index;
        Map<String, Node> children;

        public Node(String nodePath, int index) {
            this.nodePath = nodePath;
            this.index = index;
            this.children = new HashMap<>();
        }
    }
}
