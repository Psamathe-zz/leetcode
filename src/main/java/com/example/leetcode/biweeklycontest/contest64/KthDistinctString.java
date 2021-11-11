package com.example.leetcode.biweeklycontest.contest64;

import java.util.*;

/**
 * A distinct string is a string that is present only once in an array.
 *
 * Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are fewer than k distinct strings, return an empty string "".
 *
 * Note that the strings are considered in the order in which they appear in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = ["d","b","c","b","c","a"], k = 2
 * Output: "a"
 * Explanation:
 * The only distinct strings in arr are "d" and "a".
 * "d" appears 1st, so it is the 1st distinct string.
 * "a" appears 2nd, so it is the 2nd distinct string.
 * Since k == 2, "a" is returned.
 * Example 2:
 *
 * Input: arr = ["aaa","aa","a"], k = 1
 * Output: "aaa"
 * Explanation:
 * All strings in arr are distinct, so the 1st string "aaa" is returned.
 * Example 3:
 *
 * Input: arr = ["a","b","a"], k = 3
 * Output: ""
 * Explanation:
 * The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "".
 *
 * ["meio","l","xhb","p","psuzr","hrp","bhqxm","ccqrl","d","nix","ce","bkm","jvqh","c","mpah","uh","z","hin","ekaxo","cpcy","cmvj","glnrk","uqem","vq","tw","p","tqlrv","uxf","kzxf","tjd","arm","iqfc","fmze","txq","ij","rlqv","j","up","om","hdvku","tkp","hm","vdbdd","lbmc","bpx","mqy","ddecp","zmdg","ik","msy","pzohq","fuj","s","bbb","qhy","nbz","xbhvi","dh","w","nznd","lvl","nru","y","q","jciw","lmnmm","e","lvnrk","eoi","fp","neq","wuw","qsjga","fy","qvg","dqjd","rb","ck","uhall","qcly","q","igv","uq","ijjqt","er","yxx","wyx","jlasw","aln","ohy","vf","gpzy","mywlj","xf","cgwl","wyjli","pyow","i","upic","mpze","ol","z","mxwb","iouzk","zfx","f","y","gsvv","hi","x","qgj","zvnz","vb","yyl","m","uwyhh","qgd","qcbky","h","wqiyo","ey","uqjn","ma","h","phnc","ozptm","rwk","w","yfw","lkfbb","hvaq","hh","arhm","rz","gtvi","tgpyt","np","e","z","cmodm","jhhga","yal","unhsp","acg","yn","d","vndjs","ntrj","rmixt","fh","xjs","oib","mk","p","rrhep","zdk","dyy","eox","hrtr","n","ty","nj","o","s","ewt","dyvn","hrejt","vkzj","y","swzzb","dnelj","ow","pv","c","muc","unvy","vnbk","nkwte","ef","bminn","zjgcy","u","g","fwks","cjtv","ximu","oiwp","h","h","zpbm","w","h","h","vqbq","mg","xopv","m","zceki","rn","abiwc","bid","gjvsu","pv","i","cemf","x","jrxa","ye","vovg","uhlp","enpj","oyr","mgrvo","jk","kuqe","q","k","v","gzo","zcx","ylj","kmt","x","byudz","eh","lonmh","iqnx","apzod","vif","f","bq","ik","utjnj","bx","c","oyf","kqp","zc","oxpi","t","pagk","yrup","xly","o","ipndf","qy","rqfnp","c","abnh","gm","yvzh","jro","gjxq","ir","oicxq","yplnw","rxji","cdwr","nmnv","qeiht","bu","gg","jlg","ajvqg","bumzg","lpuf","lzypp","fpxj","uwyqf","c","e","flubx","cb","se","mfw","wmerw","xun","xq","hkuiw","z","ffop","qvc","xl","yp","v","lv","ij","au","m","yjlxq","oqfne","ave","oqc","qlly","zf","pa","h","pfr","vhee","gh","lswh","si","p","nwzeb","cy","fbddc","xkvqd","smhl","t","gdlvc","umj","xujwi","sqjvz","m","bkvv","tdkg","nbk","m","wvvc","d","mlpn","zi","wemrh","qv","xww","gzq","qa","nqcp","hat","jqdg","bjz","pozj","ehv","bqct","pihs","yodi","yaxhs","if","xlw","ums","v","pa","accg","wcfdf","t","j","tlijm","twibw","q","gq","w","cyrop","von","crdtn","tjt","sldvo","ykyg","wi","uej","zmqda","b","rbim","r","r","cknvt","drmac","mnxm","bsgw","c","vwyil","hblbj","ddzr","ixe","s","yd","dx","bj","fxtmw","mbxvz","kwut","cpnt","ctr","r","a","bmxg","ecr","guofg","c","eczhh","sunz","ic","d","nu","xtle","w","ckb","fnelp","z","kpdw","pe","lz","me","vbc","sk","n","gp","fud","qphr","bbius","jyqa","anhge","tuqse","d","fi","wmrn","heds","djyrj","vv","e","cf","gylm","mdswr","jxyc","stn","uo","hyjt","nl","wcay","oee","ng","dwaii","d","kkxpi","jxir","wsv","lkz","tyf","fenfb","xfzi","o","yf","xq","etvcv","c","ajv","qm","hbfy","krzac","nd","oymuu","fsok","sblyv","fgubg","bxy","clex","cbny","y","kfgi","e","lpjd","wuq","um","trv","mkgb","dtdcj","xuetk","cj","hhzl","jcni","wk","jtrcp","jbkju","hwrr","tlbw","xypv","qmsbe","gkzm","lmwz","gwyw","yuq","uz","cq","jatsr","jpd","xw","ebfoa","kbr","zvtl","bhqhj","en","jvj","ua","pth","joral","pw","wlw","vvddv","pnx","v","u","fy","drdf","m","xepi","e","rtk","byqvc","ewu","k","d","nef","lihc","puvu","hdi","ymcnd","vwo","dripr","jloqq","jyy","b","fbzn","fth","ptzw","u","rcjjy","udm","rb","nky","txz","w","wkx","kp","ay","ots","adkr","u","tmh","ayqc","cs","ulzbt","tnz","rha","he","ly","fhanm","julf","vwpap","m","fzlx","tc","sohc","x","u","hwdgu","tdlcd","dhlq","tvs","ftam","fyjg","kq","qlww","gbn","bw","tqx","kcfmg","lahfo","ipst","pyddr","ua","ilhj","fdor","ch","lp","gcduz","trjo","kuz","rizf","xmzg","pyykm","idr","adog","i","lrot","vu","r","phbq","sbvpo","x","tb","hh","xzco","xbx","z","ccgr","xdo","qb","mf","lcib","rsir","zgyxt","zpvai","yi","clyuu","nix","h","lndqw","odz","rkjnh","bl","hhuwe","eqnmj","yt","zb","dhm","mdxow","sdhd","ugybz","caf","jfjxw","ztuoz","mxoz","e","tdo","zp","yc","tg","rtki","z","icppp","ficph","oq","jmxj","nor","dlhp","iaca","qin","qghtw","n","mrjtx","bx","fmyfr","pp"]
 * 374
 */
public class KthDistinctString {
    public static void main(String[] args) {
        KthDistinctString kthDistinctString = new KthDistinctString();
        kthDistinctString.kthDistinct(new String[]{"meio","l","xhb","p","psuzr","hrp","bhqxm","ccqrl","d","nix","ce","bkm","jvqh","c","mpah","uh","z","hin","ekaxo","cpcy","cmvj","glnrk","uqem","vq","tw","p","tqlrv","uxf","kzxf","tjd","arm","iqfc","fmze","txq","ij","rlqv","j","up","om","hdvku","tkp","hm","vdbdd","lbmc","bpx","mqy","ddecp","zmdg","ik","msy","pzohq","fuj","s","bbb","qhy","nbz","xbhvi","dh","w","nznd","lvl","nru","y","q","jciw","lmnmm","e","lvnrk","eoi","fp","neq","wuw","qsjga","fy","qvg","dqjd","rb","ck","uhall","qcly","q","igv","uq","ijjqt","er","yxx","wyx","jlasw","aln","ohy","vf","gpzy","mywlj","xf","cgwl","wyjli","pyow","i","upic","mpze","ol","z","mxwb","iouzk","zfx","f","y","gsvv","hi","x","qgj","zvnz","vb","yyl","m","uwyhh","qgd","qcbky","h","wqiyo","ey","uqjn","ma","h","phnc","ozptm","rwk","w","yfw","lkfbb","hvaq","hh","arhm","rz","gtvi","tgpyt","np","e","z","cmodm","jhhga","yal","unhsp","acg","yn","d","vndjs","ntrj","rmixt","fh","xjs","oib","mk","p","rrhep","zdk","dyy","eox","hrtr","n","ty","nj","o","s","ewt","dyvn","hrejt","vkzj","y","swzzb","dnelj","ow","pv","c","muc","unvy","vnbk","nkwte","ef","bminn","zjgcy","u","g","fwks","cjtv","ximu","oiwp","h","h","zpbm","w","h","h","vqbq","mg","xopv","m","zceki","rn","abiwc","bid","gjvsu","pv","i","cemf","x","jrxa","ye","vovg","uhlp","enpj","oyr","mgrvo","jk","kuqe","q","k","v","gzo","zcx","ylj","kmt","x","byudz","eh","lonmh","iqnx","apzod","vif","f","bq","ik","utjnj","bx","c","oyf","kqp","zc","oxpi","t","pagk","yrup","xly","o","ipndf","qy","rqfnp","c","abnh","gm","yvzh","jro","gjxq","ir","oicxq","yplnw","rxji","cdwr","nmnv","qeiht","bu","gg","jlg","ajvqg","bumzg","lpuf","lzypp","fpxj","uwyqf","c","e","flubx","cb","se","mfw","wmerw","xun","xq","hkuiw","z","ffop","qvc","xl","yp","v","lv","ij","au","m","yjlxq","oqfne","ave","oqc","qlly","zf","pa","h","pfr","vhee","gh","lswh","si","p","nwzeb","cy","fbddc","xkvqd","smhl","t","gdlvc","umj","xujwi","sqjvz","m","bkvv","tdkg","nbk","m","wvvc","d","mlpn","zi","wemrh","qv","xww","gzq","qa","nqcp","hat","jqdg","bjz","pozj","ehv","bqct","pihs","yodi","yaxhs","if","xlw","ums","v","pa","accg","wcfdf","t","j","tlijm","twibw","q","gq","w","cyrop","von","crdtn","tjt","sldvo","ykyg","wi","uej","zmqda","b","rbim","r","r","cknvt","drmac","mnxm","bsgw","c","vwyil","hblbj","ddzr","ixe","s","yd","dx","bj","fxtmw","mbxvz","kwut","cpnt","ctr","r","a","bmxg","ecr","guofg","c","eczhh","sunz","ic","d","nu","xtle","w","ckb","fnelp","z","kpdw","pe","lz","me","vbc","sk","n","gp","fud","qphr","bbius","jyqa","anhge","tuqse","d","fi","wmrn","heds","djyrj","vv","e","cf","gylm","mdswr","jxyc","stn","uo","hyjt","nl","wcay","oee","ng","dwaii","d","kkxpi","jxir","wsv","lkz","tyf","fenfb","xfzi","o","yf","xq","etvcv","c","ajv","qm","hbfy","krzac","nd","oymuu","fsok","sblyv","fgubg","bxy","clex","cbny","y","kfgi","e","lpjd","wuq","um","trv","mkgb","dtdcj","xuetk","cj","hhzl","jcni","wk","jtrcp","jbkju","hwrr","tlbw","xypv","qmsbe","gkzm","lmwz","gwyw","yuq","uz","cq","jatsr","jpd","xw","ebfoa","kbr","zvtl","bhqhj","en","jvj","ua","pth","joral","pw","wlw","vvddv","pnx","v","u","fy","drdf","m","xepi","e","rtk","byqvc","ewu","k","d","nef","lihc","puvu","hdi","ymcnd","vwo","dripr","jloqq","jyy","b","fbzn","fth","ptzw","u","rcjjy","udm","rb","nky","txz","w","wkx","kp","ay","ots","adkr","u","tmh","ayqc","cs","ulzbt","tnz","rha","he","ly","fhanm","julf","vwpap","m","fzlx","tc","sohc","x","u","hwdgu","tdlcd","dhlq","tvs","ftam","fyjg","kq","qlww","gbn","bw","tqx","kcfmg","lahfo","ipst","pyddr","ua","ilhj","fdor","ch","lp","gcduz","trjo","kuz","rizf","xmzg","pyykm","idr","adog","i","lrot","vu","r","phbq","sbvpo","x","tb","hh","xzco","xbx","z","ccgr","xdo","qb","mf","lcib","rsir","zgyxt","zpvai","yi","clyuu","nix","h","lndqw","odz","rkjnh","bl","hhuwe","eqnmj","yt","zb","dhm","mdxow","sdhd","ugybz","caf","jfjxw","ztuoz","mxoz","e","tdo","zp","yc","tg","rtki","z","icppp","ficph","oq","jmxj","nor","dlhp","iaca","qin","qghtw","n","mrjtx","bx","fmyfr","pp"},
                374);
    }

    public String kthDistinct(String[] arr, int k) {
        List<String> list = new ArrayList<>();
        List<String> exist = new ArrayList<>();
        for (String str : arr) {
            if(!exist.contains(str)) {
                if (list.contains(str)) {
                    list.remove(str);
                    exist.add(str);
                } else {
                    list.add(str);
                }
            }
        }

        return list.size() >= k? list.get(k-1): "";
    }
}
