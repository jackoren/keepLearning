package com.sf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 * @author xin
 * @create 2020-11-21-23:09
 */
public class tanxin {

    /*
      1.局部最功利的标准，总是做出当前最好的选择
      2.难点在于证明局部最功利的标准可以得到全局最优解。

       贪心未必能得最优解

       分析业务逻辑
       根据业务找到不同的贪心策略
       对于能够举出反例的直接跳过，不能举出反例需要证明有效【困难】

     */



    public static void main(String[] args) {



    }


     //经典贪心算法
     //给定一个string类型的数组，必须把所有的字符串拼接起来，返回所有可能的拼接结果中，字典序最小的结果。
    //字典序：长度一致，当作k进制的正数，不一致的时候，短的补0，再比较。 javaComparaTo方法，比较的是字典序。

    //排序算法的排序性

    //一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
    //给你每一个项目开始的时间和结束的时间
    //你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
    //返回最多的宣讲场次。   例如  【6，8】 【8，12】【8，9】 【11，12】
    //贪心策略 谁结束早，安排谁，不能安排的删除

    class Progress {
        int begin;
        int end;

        public Progress(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }

    public int bestArray(Progress[] progresses){
        if (progresses == null || progresses.length == 0){
            return 0;
        }

       return  process(progresses,0,0);
    }

    /**
     *
     * @param progresses 还剩下的的会议
     * @param done 已经完成的会议数量
     * @param timeLine  现在的时间点来到了哪里
     * @return
     */
    private int process(Progress[] progresses, int done, int timeLine) {
        if(progresses.length == 0) return done;
        //保存上次的最大值
        int max = done;
        //依次遍历
        for (int i = 0; i < progresses.length;i++){
            if ( progresses[i].begin > timeLine){
                 Progress[] next = copyArray(progresses,i);
                 max = process(next,done +1,progresses[i].end);
            }
        }
         return  max;
    }

    /**
     *
     * @param progresses 原有的会议
     * @param i 删除位置i的会议
     * @return 返回新的会议集合
     */
    private Progress[] copyArray(Progress[] progresses, int i) {
        Progress[] ans = new Progress[progresses.length -1];
        int index = 0;
        for (int j = 0; j < progresses.length; j++) {
            if (j !=i){
                ans[index++] = progresses[j];
            }
        }
        return ans;
    }

    //方式二：
    private int bestArray2(Progress[] progresses){
        int done = 0;
        int timeLine = 0;
        //按找结束时间顺序进行排序
        Arrays.sort(progresses,new ProgressesComparator());
        for (int i = 0; i < progresses.length; i++) {
            if (timeLine <= progresses[i].begin){
                timeLine = progresses[i].end;
                done++;
            }
        }
       return done;
    }

    public static class ProgressesComparator implements Comparator<Progress>{
        @Override
        public int compare(Progress o1, Progress o2) {
            return o1.end - o2.end;
        }
    }


}
