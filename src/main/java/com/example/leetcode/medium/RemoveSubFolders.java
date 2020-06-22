package com.example.leetcode.medium;

import java.util.*;

/**
 * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.
 *
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
 * For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 *
 *
 *
 * Example 1:
 *
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 * Example 2:
 *
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
 * Example 3:
 *
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 *
 */
public class RemoveSubFolders {

    public static void main(String[] args) {
        String[] folder = new String[]{"/a/b/c","/a/b/ca","/a/b/d"};
        RemoveSubFolders removeSubFolders = new RemoveSubFolders();
        List<String> result = removeSubFolders.removeSubfolders(folder);
        System.out.println(result);
    }
    public List<String> removeSubfolders(String[] folder) {
        List<String> result = new ArrayList<>();
        Arrays.sort(folder, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        boolean found;
        for(String value : folder){
            found = false;
            for(String exist: result){
                if(value.startsWith(exist+"/")){
                    found = true;
                    break;
                }
            }
            if(!found)
                result.add(value);
        }
        return result;
    }


    /**
     * faster solution
     * @param folder
     * @return
     */
    public List<String> removeSubfoldersV2(String[] folder) {
        Arrays.sort(folder, (a, b) -> (a.length() - b.length()));
        Set<String> set = new HashSet<>();
        for (String s : folder) {
            boolean check = true;
            for (int i = 2; i < s.length(); i++) {
                if (s.charAt(i) == '/' && set.contains(s.substring(0, i))) {
                    check = false;
                    break;
                }
            }
            if (check)
                set.add(s);
        }
        return new ArrayList(set);
    }

}
