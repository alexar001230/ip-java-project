package com.yunlong.lee.dataStructure.stackAndQAndHash.set;

import com.yunlong.lee.utils.ds.DSUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

/**
 * @author lijie
 * @version 1.0
 * @description 380. O(1) 时间插入、删除和获取随机元素
 * @date 8/5/23 8:41 下午
 * @link https://leetcode.cn/problems/insert-delete-getrandom-o1/
 */
public class RandomizedSet {
    private ArrayList<Integer> list;
    private HashMap<Integer, Integer> val2IdxMap;
    private Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        val2IdxMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        Integer idx = val2IdxMap.get(val);
        if (Objects.isNull(idx)) {
            list.add(val);
            val2IdxMap.put(val, list.size() - 1);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        Integer idx = val2IdxMap.get(val);
        if (Objects.nonNull(idx)) {
            //将要删除的idx对应的值替换成列表最后一个值,然后删除最后列表中最后一个值
            swap(list,idx,list.size()-1);
            int newVal = list.get(idx);
            val2IdxMap.put(newVal, idx);
            list.remove(list.size() - 1);
            val2IdxMap.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int randomIdx = random.nextInt(list.size());//随机数右上限为开区间
        return list.get(randomIdx);
    }

    private void swap(ArrayList<Integer> arrayList,int idx1,int idx2){
        int tmp = arrayList.get(idx1);
        arrayList.set(idx1,arrayList.get(idx2));
        arrayList.set(idx2,tmp);
    }

    public static void main(String[] args) {
        String[] ops = new String[]{"RandomizedSet","remove","remove","insert","getRandom","remove","insert"};
        String params = "[[],[0],[0],[0],[],[0],[0]]";
        DSUtils.printResByOperatesAndParams(RandomizedSet.class,ops,params,",");
    }
}
