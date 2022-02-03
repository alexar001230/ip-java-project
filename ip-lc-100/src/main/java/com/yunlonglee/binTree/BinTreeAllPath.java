package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.List;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.genBinTree;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 19/1/22 3:01 上午
 */
public class BinTreeAllPath {
    //前序深度优先遍历
    //singlePathNodes记录当前路径,遇到左右孩子为空为止,构造一条路径，并加入所有路径中
    //allPaths记录所有路径
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> allPaths = new ArrayList<>();
        List<Integer> singlePathNodes = new ArrayList<>();
        recursiveGetPath(root, singlePathNodes, allPaths);
        return allPaths;
    }

    /**
     *
     * @param root
     * @param singlePathNodes
     * @param allPaths
     * @return
     */
    public static void recursiveGetPath(TreeNode root,
                                                List<Integer> singlePathNodes,
                                                List<String> allPaths) {
        singlePathNodes.add(root.val);
        if (root.left == null && root.right == null) {
            //到叶子节点,遍历paths形成路径
            StringBuilder pathSb = new StringBuilder();
            for (int i = 0; i < singlePathNodes.size()-1; i++) {
                pathSb.append(singlePathNodes.get(i)).append("-->");
            }
            pathSb.append(singlePathNodes.get(singlePathNodes.size()-1));
            allPaths.add(pathSb.toString());
            return;
        }
        if (root.left != null) {
            recursiveGetPath(root.left, singlePathNodes, allPaths);
            //单条路径回溯
            singlePathNodes.remove(singlePathNodes.size() - 1);
        }
        if (root.right != null) {
            recursiveGetPath(root.right, singlePathNodes, allPaths);
            //单条路径回溯
            singlePathNodes.remove(singlePathNodes.size() - 1);
        }
    }

    private void traversal(TreeNode root, List<Integer> paths, List<String> res) {
        paths.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            res.add(sb.toString());
            return;
        }
        if (root.left != null) {
            traversal(root.left, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
        if (root.right != null) {
            traversal(root.right, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
    }


    public static void main(String[] args) {
        TreeNode root = genBinTree();
        System.out.println(binaryTreePaths(root));
    }
}
