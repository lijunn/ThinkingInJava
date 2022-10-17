package com.lijun.learn.algorithm.dp;

import com.sun.tools.javac.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 动态规划算法 测试
 * @author : LiJun
 * @date : 2022-08-16 10:40
 **/
public class MainDp {

    public static void main(String[] args) {
//        testDp(13,22);


//        int[][] arr = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
//        int[][] dp = new int[][]{};
//        System.out.println(getDp(arr,dp,2,2));


        HashMap<Integer, Integer> hashMap = new HashMap<>(100);
        hashMap.put(null,null);
        hashMap.put(1,2);
        System.out.println(hashMap.toString());

        ArrayList<Integer> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.toString());

        HashSet<Integer> set = new HashSet<>();
        set.add(null);
        System.out.println(set.toString());
    }

    public static void testDp(int m,int n){
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;
        for (int i = 1;i<=m;i++){
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        for (int j = 1;j<=n;j++){
            dp[0][j] = 0;
            dp[1][j] = 1;
        }

        for (int i = 2;i<=m;i++){
            for (int j = 2;j<=n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        System.out.println(dp[m][n]);
    }



    public static int getDp(int[][] arr,int[][] dp,int x,int y){
        if (x == 0 && y == 0){
            return arr[0][0];
        }
        if (x == 0){
            return arr[0][y] + getDp(arr,dp,0,y-1);
        }
        if (y == 0){
            return arr[x][0] + getDp(arr,dp,x-1,0);
        }

        int dpa = getDp(arr,dp, x-1, y - 1);
        int dp1 = getDp(arr,dp,x, y - 1);
        int dp2 = getDp(arr,dp,x - 1, y);

        return arr[x][y] + Math.min(dp1, dp2);
    }



    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[group.length][group.length];

        int[] gDp = new int[group.length];
        int[] pDp = new int[group.length];

        gDp[0] = group[0];
        pDp[0] = gDp[0] * profit[0];

        int count = 0;
        if (gDp[0] <= n && pDp[0] >= minProfit){
            count++;
        }

        for (int i = 1; i<group.length; i++){
            int curG = group[i];
            int curP = profit[i];
            for (int j=0; j< i; j++){
                int g = gDp[j] + curG;
                int p = pDp[j] + curG * curP;

                //符合条件
                if (g <= n && p >= minProfit){
                    count++;
                }
                //保存结果
                if (g <= n){
                    gDp[i] = g;
                }
                if (curG <= n){
                    gDp[i+1] = curG;
                }
                pDp[i] = p;
                pDp[i+1] = curP;
            }
        }

        return count;
    }

}
