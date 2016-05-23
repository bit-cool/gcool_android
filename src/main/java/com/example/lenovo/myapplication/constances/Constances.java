package com.example.lenovo.myapplication.constances;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/22.
 */
public class Constances {
    private static ArrayList<String> RankTabs=new ArrayList<>();
    public static ArrayList<String> getRankTabs(){
        if(RankTabs.size()>0)
        return RankTabs;
        else {
            RankTabs.add("总榜");
            RankTabs.add("搞笑");
            RankTabs.add("音乐");
            RankTabs.add("舞蹈");
            RankTabs.add("时尚");
            RankTabs.add("旅行");
            RankTabs.add("萌宠");
            return RankTabs;
        }
    }
}
