package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
 *
 * Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".
 *
 * We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
 *
 * Example 1:
 * Input:
 * ["9001 discuss.leetcode.com"]
 * Output:
 * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
 * Explanation:
 * We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
 *
 * Example 2:
 * Input:
 * ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * Output:
 * ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 * Explanation:
 * We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
 */
public class SubdomainVisitCount {

    public static void main(String[] args) {
        String[] cpdomaines = new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        SubdomainVisitCount subdomainVisitCount = new SubdomainVisitCount();
        subdomainVisitCount.subdomainVisits(cpdomaines);
    }
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String,Integer> map = new HashMap<>();
        for(String cpdomain:cpdomains){
            String[] cp = cpdomain.split(" ");
            int times = Integer.valueOf(cp[0]);
            String[] subDomains = cp[1].split("\\.");
            StringBuffer stringBuffer = new StringBuffer();
            for(int i = subDomains.length - 1; i >= 0; i--){
                stringBuffer.insert(0,subDomains[i]);
                map.put(stringBuffer.toString(),map.getOrDefault(stringBuffer.toString(),0) + times);
                stringBuffer.insert(0,".");
            }
        }
        List<String> result = new ArrayList<>();
        for(Map.Entry entry : map.entrySet()){
            result.add(entry.getValue() + " " + entry.getKey());
        }
        return result;
    }

    /**
     * less memory
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisitsV2(String[] cpdomains) {
        Map<String,Integer> cnts = new HashMap<>();
        for (String cpdomain: cpdomains){
            String[] a = cpdomain.split(" ");
            int cnt = Integer.parseInt(a[0]);
            String s2=a[1];
            cnts.put(s2,cnts.getOrDefault(s2,0)+cnt);
            // now each subdomain
            int idx;
            while ((idx=s2.indexOf(".")) >=0){
                s2 = s2.substring(idx+1);
                cnts.put(s2,cnts.getOrDefault(s2,0)+cnt);
            }
        }
        return cnts.entrySet().stream()
                .map(e -> String.format("%d %s", e.getValue(), e.getKey()))
                .collect(Collectors.toList());
    }
}
