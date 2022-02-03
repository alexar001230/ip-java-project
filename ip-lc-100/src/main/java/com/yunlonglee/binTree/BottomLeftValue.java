package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.genBinTree;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 21/1/22 7:32 上午
 */
public class BottomLeftValue {
    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return 0;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> layerItems = new ArrayList<>();
            int layerSize = queue.size();
            while (layerSize > 0) {
                TreeNode toVisit = queue.poll();
                if (toVisit.left != null) {
                    queue.offer(toVisit.left);
                }
                if (toVisit.right != null) {
                    queue.offer(toVisit.right);
                }
                layerItems.add(toVisit.val);
                layerSize--;
            }
            result.add(layerItems);
        }
        return result.get(result.size()-1).get(0);
    }

    public static void main(String[] args) {
        TreeNode root = genBinTree();
        System.out.println(findBottomLeftValue(root));
    }
}
