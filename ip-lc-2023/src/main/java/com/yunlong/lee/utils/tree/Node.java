package com.yunlong.lee.utils.tree;

import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description n叉树节点
 * @date 27/4/23 11:21 上午
 * @link
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
