package com.example.leetcode.easy;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
 *
 * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.
 *
 * Example 1:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * Output: ["Shogun"]
 * Explanation: The only restaurant they both like is "Shogun".
 * Example 2:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 * Note:
 * The length of both lists will be in the range of [1, 1000].
 * The length of strings in both lists will be in the range of [1, 30].
 * The index is starting from 0 to the list length minus 1.
 * No duplicates in both lists.
 */
public class MinimumIndexSumTwoLists {

    public static void main(String[] args) {

        String[] list1 = new String[]{"pkwenkodtlbbdgvopqaeygphtlrmo","agfqdph","vagpvsdzqhwnlogzyje","lfsxwnhweveaaekybglvcluyeb","gpgbqii","fboaiwtlfccdolpqutf","swgsvdptrtepv","nqmgha","rfbmlfacpec","mjgmpewjnlwijzx","fxtsgerpchszrkfjpwwigy","yvizbmradwgxedumcbcktxublw","p","ijrbjrqopmbveayra","ckhb","fhjbrzhroorglgbltarrvtjnkz","bsmqkkfmzszgtffpkpjouoxdmofivm","vbqhjdqajtvy","eelflqtsplanaahmxssqe","gwurhajbnavidhipzhxvlf","lfgztikdpethoionvs","ywqhjouxx","zegpdvbmdgslagpvsjnmchspix","rooillldcagevixecdxffz","huhwnjqriyiweilhjzijuftlzp","lha","ashrazctgqgwrm","geg","nkmofftbapqitraxtfnilsfvwvpas","cblx","xhc","ykiatwmdigjxmxevdbd","alzzxhra","zcxbycuiqlvfpuslvv","ufolcgdeo","wnnhai","eypstxtyxnnqnkmqptekdgpdqcdqn","zosncavdfjcsdnrxdp","ptopwfkziuttfeazrrglqdbszvr","qlewoezxzmlhgypikhdl","ykoyilcm","fmgqdzvdtyethyynjjpbl","dxeyeyejkosdpnub","ymlznwinj","qyfopqgmglfiofw","wwqbevusanvcdzeshcglrzlarer","tmsfjgfjdjdbczcseznowpptr","zkrcckbowwjxmfmdikfsbpyxrmgg","aooilovrtwqoaremskb","acqnnodwqzwlqnynvb","xinbdvhzfykpmzpecgngelczoukzd","ugjnsgqlp","vdztloyrjpzzylesddiewfemmbgfwe","ucnfeysd","cywlrzkl","rezogqmazduvkcypiupyitmhrfmzki","hydpvnbhqpkvrcc","axdaqveyksrmpc","l","zuzgiuonhoaaqmafflrnsamdkefoki","wbtrhqdbwqyrvjizkgj","hyxexwoosybkpifyohasxtk","kexssggfp","eblxqnyke","etwelbjtxilczjudlyziyosp","coxufsaunxexcshrcqyjz","hlcyxndydrxgebcjtgrjlfhhiqeh","vlhyfvzcuuqrqymnzlbqwlwyk","xo","n","hytiwcsrbwyj","kbsglfxut","mducxmhhvuuoaxazhwpsuisush","fqoxrbegbqfseoiqvevfjua","cugcsdfdgggnxbsrloqynmjisfxqcf","gwmoxooqbgkuqhitqndhjknpoi","tvwhfeoxshl","ltvkvjigjqdjmfqihssvnnmxke","tvhkudu","utwqphdxvmtvtjw","npjohbkidogcjbkpairnmmgslk","xtzyn","shufzxmpntnwkljqlayypynmnsvjnq","iwmovzzkfpvixarqckonwqhb","bdjntlktemditgnn","tphnrrekc","jbtpcxbxdbdpjztbvp","jxejmmiwfrrknjyek","jzfkmowocsuhipwvyavcimgslsa","pgsbqvig","fnxfo","ys","jovaimqzyyputxnatlgvtflxmjwrgy","fndfnvpnmgcxjbfydgwiwpvst","eikqjjhbfnabekalchmoigbsvj","esvmtdtyojpoccpnhmweleclk","idrkmks","ucxqf","qaaoyjczafkqyncmmuqfj","xfhfqdqnklxq","krtfunvglmtcwasqlvhq","uilnponlhvulz","hpzca","fhukhpkax","bv","xxfrzciifwxwzkqlhzi","upmawfjrcvyqphcgznqvlunehgm","stbyc","klgybdvijbdraoveascq","zhcnue","njnpajotnwihwedlefrukx","mtndwtean","oblhinaeguymmkleqeogwrzmbltl","htjlnvbdfuzsjypkanhzg","zhjn","pjgfwoikxbybblja","ljdkfxvylzmspudjqqugddxrnhn","isl","azusu","ymyzwmsxcirfwhsafs","uvpjsrqhwxdoeuc","bl","gfubyw","sfxkoexlvpihhfhfq","jsmzeshdnugttvppha","vdvhdvfztaxcyhjdugctrvkgulmbj","knwvl","xengrpxq","qggmtusbpnypjbxcisnoy","rhehzhnlk","ztanalcvncvm","favfgqwpedkohfkoakisbmbxbxf","mofdl","fyfiwcckdrjjpqfyrldhdyvu","fuosbwciqqzosy","chnbkhdgyjoxeglvfc","xflneeeaomxjphvgyohoakxhj","stpuhrfbhoxf","czoepwnymcz","rvscpckcpjwgulnfnelspis","frtrodsnrmsmaaws","sykhquipjd","rrwyr","oglbicrgqvradxwtghghppnzznq","lhaijmcqlnrgimhbptwmkwt","lwdhpatigvhypgzrertalatqspohi","leyshdfdlagoalvfbegapxregtuh","occgkf","gscljupcrmbbeobwvdht","kiul","syc","uoejbnenp","takqafyujrchibalhi","gjcgpxz","jiuiut","faegljhiknwlygwhlu","yxsybpiprmuhzplfphfeqazzyndtv","ktbjtqyvuuxqajcesnaw","huuftubpzskytmniladxkkgczftxp","hjiq","d","udqooeglslqpajnip","lztmnoajehsvz","ybvtkizlmyfngyjwwowgsm","xgvvsydndmgs","smbvogzsmbaxktmqwleprmbdxqf","izdo","sdvhj","qyn","framwuszazkwdilvtylmkru","jmbefcplwqosvfx","kgsptbnifrvczouohrrprli","gcydrneexbcuqopkpcmd","tewapttno","lmhuzhpzmym","yqzblljae","pgxnwszovqgqtcqpzmssneqejgjs","xwetnsmpotlpn","utraamepcsomyxsqbkshgyraleekxw","dvjhqppvoowntco","yohsoxdaubemaogltuk","nasrnrizinuypjdnjehqqoitmvk","xerymxgwvzcb","ouzklsdedbvwwketdhcfeuluvckf","ztindosdnqsyquuhzjzpxkbeefun","kfjirgxu","ypmskmp","wsi","pcfbmtafjvrurtrvjsumfjfttu","aqlwxsrxggtfnz","vbvbde","ceyogdjazwskvjbdwrvufpqcjrxpws","vfhpldeneeqxykmum","vuqxhsrspvqath","tloeehsq","zo","hsxjfwgbuorfznekqdvahrxgui","ctsetmijpnp","jjdizlchlorva","rvrfcepmxpeofenooth","qojwwpjt","gtdq","rbdbetwubqjppupythw","ccdllhhubyjxfmvmxdcmpzdwy","vs","ndfm","selamkurrltpjkt","whxez","woryubernrrwthyce","ozzcx","abtsca","nqyxdju","phuolroyieomapmirpvaldtksgl","yaqhaymtozosbacorzavpgdgl","dbzlkbdgrtavnorpfvuvnxwnzeoc","user","dbtfwbavxuxqzjtycorez","hhemkmkwuikfasranjgsjtug","obsskhdangnyiok","ikkzxhjapdmkyiutryhzvsijxzeo","hvbgprgostxbh","ecnzsirsqqqwldmzsat","pewwoxivlouurvfsadrc","lbujpsqpfk","eqjukrlgnbpuesgea","sgsnxtsznntdptmxjg","bpkwyfwccmnjqwdyhxrjaaxjcw","omvzwyrzlwstnwhwhssajzhbsd","ux","exlyaiolmjrca","zpdbagmcdfltcoiobkdrticgzeyn","bqbebomqulgwltbortyu","ihgevtuipgkvqgpkhlui","tvrippfy","pdbmjihtodwwa","siucrhynh","izvwi","bxhsxqhtbdsgpfeiqpdjumcjm","zrnnxdcd","mfpobuitnnxmkddwwzlfauosuzd","utolwmietafptpdnb","mkointotmaled","likkuxoyubyandcnm","mszirwc","qp","dhvshmxnljtsozhsjugbokxjiiapmb","jxibbfteyfqh","strbmsndazagstxvojxc","tpyrljqdhatboqrnnnq","whjktettjhafcviiivw","pqcetglnhibyhmveyblnlrymw","fdrcqutgrpfdaec","cyfypt","iezxlfmnhwlvpgzensnq","iznzwltutyxd","dlwbfch","qyxaibavemizoahxwhtjexaujzbm","nrqifrrhcxkuny","yymgcbawh","wd","ztuolqf","slrdlcqyevsjvobfd","glotjh","dsbds","tpetl","fryosmcawjsvbutg","hudmoskbkakhsjqyuhrrt","aww","xjluxthorodhosxkrn","fkyhahxbisvfjxj","vkslygnbphhvdpauxjnfn","vvxoqmsatgqanusjbyrxwdshvven","hyb","qxxowalutfwwpv","sntazpdaodhdaid","ws","tsg","crpwxtfukvethkwhtisxqmvohsx","qfcpjzokpxxhgqajgq","svzaumwyydtuveq","inmwdmzejyl","aexhlovhykxeoyxfi","zswuf","hiuxr","fcxnirvfxzevmveemo","cffdctpjzpgvuinotvnywxqph","yzjbscqhtfyuubljpkjmxt"};
        String[] list2 = new String[]{"yzjbscqhtfyuubljpkjmxt","cffdctpjzpgvuinotvnywxqph","fcxnirvfxzevmveemo","hiuxr","zswuf","aexhlovhykxeoyxfi","inmwdmzejyl","svzaumwyydtuveq","qfcpjzokpxxhgqajgq","crpwxtfukvethkwhtisxqmvohsx","tsg","ws","sntazpdaodhdaid","qxxowalutfwwpv","hyb","vvxoqmsatgqanusjbyrxwdshvven","vkslygnbphhvdpauxjnfn","fkyhahxbisvfjxj","xjluxthorodhosxkrn","aww","hudmoskbkakhsjqyuhrrt","fryosmcawjsvbutg","tpetl","dsbds","glotjh","slrdlcqyevsjvobfd","ztuolqf","wd","yymgcbawh","nrqifrrhcxkuny","qyxaibavemizoahxwhtjexaujzbm","dlwbfch","iznzwltutyxd","iezxlfmnhwlvpgzensnq","cyfypt","fdrcqutgrpfdaec","pqcetglnhibyhmveyblnlrymw","whjktettjhafcviiivw","tpyrljqdhatboqrnnnq","strbmsndazagstxvojxc","jxibbfteyfqh","dhvshmxnljtsozhsjugbokxjiiapmb","qp","mszirwc","likkuxoyubyandcnm","mkointotmaled","utolwmietafptpdnb","mfpobuitnnxmkddwwzlfauosuzd","zrnnxdcd","bxhsxqhtbdsgpfeiqpdjumcjm","izvwi","siucrhynh","pdbmjihtodwwa","tvrippfy","ihgevtuipgkvqgpkhlui","bqbebomqulgwltbortyu","zpdbagmcdfltcoiobkdrticgzeyn","exlyaiolmjrca","ux","omvzwyrzlwstnwhwhssajzhbsd","bpkwyfwccmnjqwdyhxrjaaxjcw","sgsnxtsznntdptmxjg","eqjukrlgnbpuesgea","lbujpsqpfk","pewwoxivlouurvfsadrc","ecnzsirsqqqwldmzsat","hvbgprgostxbh","ikkzxhjapdmkyiutryhzvsijxzeo","obsskhdangnyiok","hhemkmkwuikfasranjgsjtug","dbtfwbavxuxqzjtycorez","user","dbzlkbdgrtavnorpfvuvnxwnzeoc","yaqhaymtozosbacorzavpgdgl","phuolroyieomapmirpvaldtksgl","nqyxdju","abtsca","ozzcx","woryubernrrwthyce","whxez","selamkurrltpjkt","ndfm","vs","ccdllhhubyjxfmvmxdcmpzdwy","rbdbetwubqjppupythw","gtdq","qojwwpjt","rvrfcepmxpeofenooth","jjdizlchlorva","ctsetmijpnp","hsxjfwgbuorfznekqdvahrxgui","zo","tloeehsq","vuqxhsrspvqath","vfhpldeneeqxykmum","ceyogdjazwskvjbdwrvufpqcjrxpws","vbvbde","aqlwxsrxggtfnz","pcfbmtafjvrurtrvjsumfjfttu","wsi","ypmskmp","kfjirgxu","ztindosdnqsyquuhzjzpxkbeefun","ouzklsdedbvwwketdhcfeuluvckf","xerymxgwvzcb","nasrnrizinuypjdnjehqqoitmvk","yohsoxdaubemaogltuk","dvjhqppvoowntco","utraamepcsomyxsqbkshgyraleekxw","xwetnsmpotlpn","pgxnwszovqgqtcqpzmssneqejgjs","yqzblljae","lmhuzhpzmym","tewapttno","gcydrneexbcuqopkpcmd","kgsptbnifrvczouohrrprli","jmbefcplwqosvfx","framwuszazkwdilvtylmkru","qyn","sdvhj","izdo","smbvogzsmbaxktmqwleprmbdxqf","xgvvsydndmgs","ybvtkizlmyfngyjwwowgsm","lztmnoajehsvz","udqooeglslqpajnip","d","hjiq","huuftubpzskytmniladxkkgczftxp","ktbjtqyvuuxqajcesnaw","yxsybpiprmuhzplfphfeqazzyndtv","faegljhiknwlygwhlu","jiuiut","gjcgpxz","takqafyujrchibalhi","uoejbnenp","syc","kiul","gscljupcrmbbeobwvdht","occgkf","leyshdfdlagoalvfbegapxregtuh","lwdhpatigvhypgzrertalatqspohi","lhaijmcqlnrgimhbptwmkwt","oglbicrgqvradxwtghghppnzznq","rrwyr","sykhquipjd","frtrodsnrmsmaaws","rvscpckcpjwgulnfnelspis","czoepwnymcz","stpuhrfbhoxf","xflneeeaomxjphvgyohoakxhj","chnbkhdgyjoxeglvfc","fuosbwciqqzosy","fyfiwcckdrjjpqfyrldhdyvu","mofdl","favfgqwpedkohfkoakisbmbxbxf","ztanalcvncvm","rhehzhnlk","qggmtusbpnypjbxcisnoy","xengrpxq","knwvl","vdvhdvfztaxcyhjdugctrvkgulmbj","jsmzeshdnugttvppha","sfxkoexlvpihhfhfq","gfubyw","bl","uvpjsrqhwxdoeuc","ymyzwmsxcirfwhsafs","azusu","isl","ljdkfxvylzmspudjqqugddxrnhn","pjgfwoikxbybblja","zhjn","htjlnvbdfuzsjypkanhzg","oblhinaeguymmkleqeogwrzmbltl","mtndwtean","njnpajotnwihwedlefrukx","zhcnue","klgybdvijbdraoveascq","stbyc","upmawfjrcvyqphcgznqvlunehgm","xxfrzciifwxwzkqlhzi","bv","fhukhpkax","hpzca","uilnponlhvulz","krtfunvglmtcwasqlvhq","xfhfqdqnklxq","qaaoyjczafkqyncmmuqfj","ucxqf","idrkmks","esvmtdtyojpoccpnhmweleclk","eikqjjhbfnabekalchmoigbsvj","fndfnvpnmgcxjbfydgwiwpvst","jovaimqzyyputxnatlgvtflxmjwrgy","ys","fnxfo","pgsbqvig","jzfkmowocsuhipwvyavcimgslsa","jxejmmiwfrrknjyek","jbtpcxbxdbdpjztbvp","tphnrrekc","bdjntlktemditgnn","iwmovzzkfpvixarqckonwqhb","shufzxmpntnwkljqlayypynmnsvjnq","xtzyn","npjohbkidogcjbkpairnmmgslk","utwqphdxvmtvtjw","tvhkudu","ltvkvjigjqdjmfqihssvnnmxke","tvwhfeoxshl","gwmoxooqbgkuqhitqndhjknpoi","cugcsdfdgggnxbsrloqynmjisfxqcf","fqoxrbegbqfseoiqvevfjua","mducxmhhvuuoaxazhwpsuisush","kbsglfxut","hytiwcsrbwyj","n","xo","vlhyfvzcuuqrqymnzlbqwlwyk","hlcyxndydrxgebcjtgrjlfhhiqeh","coxufsaunxexcshrcqyjz","etwelbjtxilczjudlyziyosp","eblxqnyke","kexssggfp","hyxexwoosybkpifyohasxtk","wbtrhqdbwqyrvjizkgj","zuzgiuonhoaaqmafflrnsamdkefoki","l","axdaqveyksrmpc","hydpvnbhqpkvrcc","rezogqmazduvkcypiupyitmhrfmzki","cywlrzkl","ucnfeysd","vdztloyrjpzzylesddiewfemmbgfwe","ugjnsgqlp","xinbdvhzfykpmzpecgngelczoukzd","acqnnodwqzwlqnynvb","aooilovrtwqoaremskb","zkrcckbowwjxmfmdikfsbpyxrmgg","tmsfjgfjdjdbczcseznowpptr","wwqbevusanvcdzeshcglrzlarer","qyfopqgmglfiofw","ymlznwinj","dxeyeyejkosdpnub","fmgqdzvdtyethyynjjpbl","ykoyilcm","qlewoezxzmlhgypikhdl","ptopwfkziuttfeazrrglqdbszvr","zosncavdfjcsdnrxdp","eypstxtyxnnqnkmqptekdgpdqcdqn","wnnhai","ufolcgdeo","zcxbycuiqlvfpuslvv","alzzxhra","ykiatwmdigjxmxevdbd","xhc","cblx","nkmofftbapqitraxtfnilsfvwvpas","geg","ashrazctgqgwrm","lha","huhwnjqriyiweilhjzijuftlzp","rooillldcagevixecdxffz","zegpdvbmdgslagpvsjnmchspix","ywqhjouxx","lfgztikdpethoionvs","gwurhajbnavidhipzhxvlf","eelflqtsplanaahmxssqe","vbqhjdqajtvy","bsmqkkfmzszgtffpkpjouoxdmofivm","fhjbrzhroorglgbltarrvtjnkz","ckhb","ijrbjrqopmbveayra","p","yvizbmradwgxedumcbcktxublw","fxtsgerpchszrkfjpwwigy","mjgmpewjnlwijzx","rfbmlfacpec","nqmgha","swgsvdptrtepv","fboaiwtlfccdolpqutf","gpgbqii","lfsxwnhweveaaekybglvcluyeb","vagpvsdzqhwnlogzyje","agfqdph","pkwenkodtlbbdgvopqaeygphtlrmo"};
        MinimumIndexSumTwoLists minimumIndexSumTwoLists = new MinimumIndexSumTwoLists();
        String[] result = minimumIndexSumTwoLists.findRestaurant(list1,list2);
        for(String value: result){
            System.out.println(value);
        }
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer> pointMap = new HashMap<>();
        List<String> arrayList1 = Arrays.stream(list1).collect(Collectors.toList());
        List<String> arrayList2 = Arrays.stream(list2).collect(Collectors.toList());
        int index = 0;
        for(String value : list1){
            if(arrayList2.contains(value))
                pointMap.put(value,pointMap.getOrDefault(value,0) + index);
            index++;
        }
        index = 0;
        for(String value : list2){
            if(arrayList1.contains(value))
                pointMap.put(value,pointMap.getOrDefault(value,0) + index);
            index++;
        }
        Integer bestValue = pointMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).findFirst().map(e -> e.getValue()).orElse(0);
        String[] result = pointMap.entrySet().stream().filter(e->e.getValue().equals(bestValue)).map(e-> e.getKey()).toArray(String[]::new);
        /*
        List<Map.Entry<String,Integer>> list = new ArrayList<>(pointMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        */

        return result;
    }


    /**
     *
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurantV2(String[] list1, String[] list2) {
        if(list1.length > list2.length){
            return findRestaurant(list2, list1);
        }
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new LinkedList<>();
        int minSum = Integer.MAX_VALUE;
        for (int i=0;i<list1.length;i++) map.put(list1[i], i);
        for (int i=0;i<list2.length;i++) {
            Integer j = map.get(list2[i]);
            if (j != null && i + j <= minSum) {
                if (i + j < minSum) { res.clear(); minSum = i+j; }
                res.add(list2[i]);
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
