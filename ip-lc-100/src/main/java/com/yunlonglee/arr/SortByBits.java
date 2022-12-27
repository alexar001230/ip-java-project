package com.yunlonglee.arr;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 1356.根据数字二进制1的数目排序
 * @date 5/12/22 10:56 下午
 * @link https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits/
 */
public class SortByBits {
    public static void main(String[] args) {
        int[] numsArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        // int[] numsArr = new int[]{1,3, 5, 6};
        System.out.println(JSON.toJSONString(sortedNumsArr(numsArr)));
    }


    //java函数式编程,核心点1.求1的个数  2.函数式编程的api熟悉度
    private static int[] sortByBitsFunc(int[] numsArr) {
        Comparator comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        return Arrays.stream(numsArr).boxed().sorted((o1, o2) -> {
            int o1OneCnt = valueOneCnt(o1);
            int o2OneCnt = valueOneCnt(o2);
            return (o1OneCnt == o2OneCnt) ? Integer.compare(o1, o2) :
                    Integer.compare(o1OneCnt, o2OneCnt);
        }).mapToInt(Integer::intValue).toArray();
    }

    private static int valueOneCnt(int value) {
        int cnt = 0;
        while (value > 0) {
            value = value & (value - 1);
            cnt++;
        }
        return cnt;
    }


    //巧解:利用Integer.bitCount计算每个数字的1,然后乘以个极大系数,排序,再取余数
    private static int[] sortByBits(int[] numsArr) {
        int maxmum = 10000000;
        int[] tempArr = new int[numsArr.length];
        for (int i = 0; i < numsArr.length; i++) {
            tempArr[i] = Integer.bitCount(numsArr[i]) * maxmum + numsArr[i];
        }
        Arrays.sort(tempArr);
        for (int i = 0; i < numsArr.length; i++) {
            tempArr[i] = tempArr[i] % maxmum;
        }
        return tempArr;
    }


    // 正常思路一步步求解
    private static int[] sortedNumsArr(int[] numsArr) {
        //求num对应1的个数，并将结果拼接成对象，放到list中
        List<NumOneCnt> numOneCnts = new ArrayList<>();
        for (int i = 0; i < numsArr.length; i++) {
            List<Integer> numBits = new ArrayList<>();
            convert2Bits(numsArr[i], numBits);
            int oneCnt = getOneCnt(numBits);
            NumOneCnt numOneCnt = new NumOneCnt();
            numOneCnt.setNum(numsArr[i]);
            numOneCnt.setOneCnt(oneCnt);
            numOneCnts.add(numOneCnt);
        }
        System.out.println("numOneCnts:" + JSON.toJSONString(numOneCnts));

        //将列表处理成<1的个数，数字列表> map
        Map<Integer, List<Integer>> cnt2NumsMap = new HashMap<>();

        for (int i = 0; i < numOneCnts.size(); i++) {
            int oneCnt = numOneCnts.get(i).getOneCnt();
            if (Objects.nonNull(cnt2NumsMap.get(oneCnt))) {
                List<Integer> oneCntList = cnt2NumsMap.get(oneCnt);
                List<Integer> newList = new ArrayList<>();
                for (int j = 0; j < oneCntList.size(); j++) {
                    newList.add(oneCntList.get(j));
                }
                newList.add(numOneCnts.get(i).getNum());
                cnt2NumsMap.put(oneCnt, newList);
            } else {
                cnt2NumsMap.put(oneCnt,
                        Arrays.asList(numOneCnts.get(i).getNum()));
            }
        }


        //将map处理成对象列表
        List<OneCnt2Nums> oneCnt2NumsList = new ArrayList<>();
        for (int oneCnt : cnt2NumsMap.keySet()) {
            OneCnt2Nums temp = new OneCnt2Nums();
            List<Integer> nums = cnt2NumsMap.get(oneCnt);
            Collections.sort(nums);
            temp.setNums(nums);
            temp.setOneCnt(oneCnt);
            oneCnt2NumsList.add(temp);
        }
        System.out.println("pre-sort:" + JSON.toJSONString(oneCnt2NumsList));
        //循环排序
        for (int i = 0; i < oneCnt2NumsList.size() - 1; i++) {
            for (int j = i + 1; j < oneCnt2NumsList.size(); j++) {
                if (oneCnt2NumsList.get(i).oneCnt > oneCnt2NumsList.get(j).oneCnt) {
                    int temp = oneCnt2NumsList.get(i).oneCnt;
                    oneCnt2NumsList.get(i).oneCnt =
                            oneCnt2NumsList.get(j).oneCnt;
                    oneCnt2NumsList.get(j).oneCnt = temp;
                    List<Integer> tempList = oneCnt2NumsList.get(i).getNums();
                    oneCnt2NumsList.get(i).setNums(oneCnt2NumsList.get(j).getNums());
                    oneCnt2NumsList.get(j).setNums(tempList);
                }
            }
        }
        System.out.println("post-sort:" + JSON.toJSONString(oneCnt2NumsList));
        //将排序结果放到最终的列表中

        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < oneCnt2NumsList.size(); i++) {
            for (int j = 0; j < oneCnt2NumsList.get(i).getNums().size(); j++) {
                resList.add(oneCnt2NumsList.get(i).getNums().get(j));
            }
        }
        System.out.println("resList:" + JSON.toJSONString(resList));

        int[] resArr = new int[numOneCnts.size()];
        for (int i = 0; i < resList.size(); i++) {
            resArr[i] = resList.get(i);
        }
        return resArr;
    }


    /**
     * 转换整数到二进制列表
     *
     * @param aNum
     * @return
     */
    static int JIN_ZHI = 2;

    private static void convert2Bits(int aNum, List<Integer> bits) {
        if (aNum >= JIN_ZHI) {
            int divideRes = aNum / JIN_ZHI;
            bits.add(aNum % JIN_ZHI);
            convert2Bits(divideRes, bits);
        } else {
            bits.add(aNum);
        }
    }

    /**
     * 统计列表中1的个数
     *
     * @param bits
     * @return
     */
    private static int getOneCnt(List<Integer> bits) {
        int result = 0;
        for (int i = 0; i < bits.size(); i++) {
            if (bits.get(i) == 1) {
                result++;
            }
        }
        return result;
    }


    static class OneCnt2Nums {
        int oneCnt;
        List<Integer> nums;

        public OneCnt2Nums() {
        }

        public int getOneCnt() {
            return oneCnt;
        }

        public void setOneCnt(int oneCnt) {
            this.oneCnt = oneCnt;
        }

        public List<Integer> getNums() {
            return nums;
        }

        public void setNums(List<Integer> nums) {
            this.nums = nums;
        }
    }

    static class NumOneCnt {
        int num;
        int oneCnt;

        public NumOneCnt() {
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getOneCnt() {
            return oneCnt;
        }

        public void setOneCnt(int oneCnt) {
            this.oneCnt = oneCnt;
        }
    }
}
