package com.example.leetcode.medium;

import java.util.*;

/**
 * In a special ranking system, each voter gives a rank from highest to lowest to all teams participated in the competition.
 *
 * The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.
 *
 * Given an array of strings votes which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.
 *
 * Return a string of all teams sorted by the ranking system.
 *
 * http://www.dzjqx.cn/news/show-30531.html
 *
 * Example 1:
 *
 * Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
 * Output: "ACB"
 * Explanation: Team A was ranked first place by 5 voters. No other team was voted as first place so team A is the first team.
 * Team B was ranked second by 2 voters and was ranked third by 3 voters.
 * Team C was ranked second by 3 voters and was ranked third by 2 voters.
 * As most of the voters ranked C second, team C is the second team and team B is the third.
 * Example 2:
 *
 * Input: votes = ["WXYZ","XYZW"]
 * Output: "XWYZ"
 * Explanation: X is the winner due to tie-breaking rule. X has same votes as W for the first position but X has one vote as second position while W doesn't have any votes as second position.
 *
 * Example 3:
 *
 * Input: votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]
 * Output: "ZMNAGUEDSJYLBOPHRQICWFXTVK"
 * Explanation: Only one voter so his votes are used for the ranking.
 * Example 4:
 *
 * Input: votes = ["BCA","CAB","CBA","ABC","ACB","BAC"]
 * Output: "ABC"
 * Explanation:
 * Team A was ranked first by 2 voters, second by 2 voters and third by 2 voters.
 * Team B was ranked first by 2 voters, second by 2 voters and third by 2 voters.
 * Team C was ranked first by 2 voters, second by 2 voters and third by 2 voters.
 * There is a tie and we rank teams ascending by their IDs.
 * Example 5:
 *
 * Input: votes = ["M","M","M","M"]
 * Output: "M"
 * Explanation: Only team M in the competition so it has the first rank.
 *
 *
 * Constraints:
 *
 * 1 <= votes.length <= 1000
 * 1 <= votes[i].length <= 26
 * votes[i].length == votes[j].length for 0 <= i, j < votes.length.
 * votes[i][j] is an English upper-case letter.
 * All characters of votes[i] are unique.
 * All the characters that occur in votes[0] also occur in votes[j] where 1 <= j < votes.length.
 * "FVSHJIEMNGYPTQOURLWCZKAX","AITFQORCEHPVJMXGKSLNZWUY","OTERVXFZUMHNIYSCQAWGPKJL","VMSERIJYLZNWCPQTOKFUHAXG","VNHOZWKQCEFYPSGLAMXJIUTR","ANPHQIJMXCWOSKTYGULFVERZ","RFYUXJEWCKQOMGATHZVILNSP","SCPYUMQJTVEXKRNLIOWGHAFZ","VIKTSJCEYQGLOMPZWAHFXURN","SVJICLXKHQZTFWNPYRGMEUAO","JRCTHYKIGSXPOZLUQAVNEWFM","NGMSWJITREHFZVQCUKXYAPOL","WUXJOQKGNSYLHEZAFIPMRCVT","PKYQIOLXFCRGHZNAMJVUTWES","FERSGNMJVZXWAYLIKCPUQHTO","HPLRIUQMTSGYJVAXWNOCZEKF","JUVWPTEGCOFYSKXNRMHQALIZ","MWPIAZCNSLEYRTHFKQXUOVGJ","EZXLUNFVCMORSIWKTYHJAQPG","HRQNLTKJFIEGMCSXAZPYOVUW","LOHXVYGWRIJMCPSQENUAKTZF","XKUTWPRGHOAQFLVYMJSNEIZC","WTCRQMVKPHOSLGAXZUEFYNJI"
 */
public class RankTeamsByVotes {
    public static void main(String[] args) {

        String[] votes = new String[]{"FVSHJIEMNGYPTQOURLWCZKAX","AITFQORCEHPVJMXGKSLNZWUY","OTERVXFZUMHNIYSCQAWGPKJL","VMSERIJYLZNWCPQTOKFUHAXG","VNHOZWKQCEFYPSGLAMXJIUTR","ANPHQIJMXCWOSKTYGULFVERZ","RFYUXJEWCKQOMGATHZVILNSP","SCPYUMQJTVEXKRNLIOWGHAFZ","VIKTSJCEYQGLOMPZWAHFXURN","SVJICLXKHQZTFWNPYRGMEUAO","JRCTHYKIGSXPOZLUQAVNEWFM","NGMSWJITREHFZVQCUKXYAPOL","WUXJOQKGNSYLHEZAFIPMRCVT","PKYQIOLXFCRGHZNAMJVUTWES","FERSGNMJVZXWAYLIKCPUQHTO","HPLRIUQMTSGYJVAXWNOCZEKF","JUVWPTEGCOFYSKXNRMHQALIZ","MWPIAZCNSLEYRTHFKQXUOVGJ","EZXLUNFVCMORSIWKTYHJAQPG","HRQNLTKJFIEGMCSXAZPYOVUW","LOHXVYGWRIJMCPSQENUAKTZF","XKUTWPRGHOAQFLVYMJSNEIZC","WTCRQMVKPHOSLGAXZUEFYNJI"};
        RankTeamsByVotes rankTeamsByVotes = new RankTeamsByVotes();
        String result = rankTeamsByVotes.rankTeams(votes);
        System.out.println(result);
    }

    class Team{
        char name;
        int[] votes;

        public Team(char name,int[] votes){
            this.name = name;
            this.votes = votes;
        }
    }

    public String rankTeams(String[] votes) {

        Map<Character,Team> result = new HashMap<>();
        List<Team> teams = new ArrayList<>(votes.length);
        for(String vote:votes){
            for(int i = 0; i< vote.length();i++){
                char teamName = vote.charAt(i);
                Team team = result.get(teamName);
                if(team == null){
                    team = new Team(teamName,new int[vote.length()]);
                    result.put(teamName,team);
                    teams.add(team);
                }
                team.votes[i]++;
            }
        }
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team team, Team team1) {
                int len = team.votes.length;
                for (int i = 0; i < len; i++) {
                    if (team.votes[i] != team1.votes[i]) {
                        return team1.votes[i] - team.votes[i];
                    }
                }
                return team.name - team1.name;
            }
        });


        StringBuilder ans = new StringBuilder();
        for (Team team : teams) {
            ans.append(team.name);
        }
        return ans.toString();
    }
}
