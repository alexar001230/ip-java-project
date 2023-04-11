package com.yunlong.lee.dataStructure.binTree.traversal;

import com.alibaba.fastjson.JSON;
import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 297. 二叉树的序列化与反序列化
 * @notice hard直接用递归, 思路更清晰
 * @date 10/4/23 5:39 下午
 * @link https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeAndDeserializeBinTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return doSerialize(root);
    }

    private String doSerialize(TreeNode root) {
        return serializeRecursion(root, "");
    }

    private String serializeRecursion(TreeNode root, String serializeStr) {
        if (Objects.isNull(root)) {
            serializeStr = serializeStr + ",";
            serializeStr = serializeStr + "null";
            return serializeStr;
        } else {
            serializeStr = serializeStr + ",";
            String left = serializeRecursion(root.left, serializeStr);
            String right = serializeRecursion(root.right, serializeStr);
            return left + right;
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return doDeserialize(data);
    }

    private TreeNode doDeserialize(String data) {
        String[] dataArr = data.split(",");
        LinkedList<String> dataList = new LinkedList<>(Arrays.asList(dataArr));
        TreeNode root = deserializeRecursion(dataList);
        return root;
    }

    private TreeNode deserializeRecursion(LinkedList<String> dataList) {
        if (dataList.get(0).equals("null")) {
            dataList.remove(0);
            return null;
        }
        System.out.println(dataList.get(0));
        TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
        dataList.remove(0);
        if (dataList.size() > 0) {
            root.left = deserializeRecursion(dataList);
        }
        if (dataList.size() > 0) {
            root.right = deserializeRecursion(dataList);
        }
        return root;
    }

    //region 层序序列化
    private String myDoSerialize(TreeNode root) {
        levelTravel(root);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < levelTraversalNodes.size(); i++) {
            LinkedList<Integer> aLevel = levelTraversalNodes.get(i);
            for (int j = 0; j < aLevel.size(); j++) {
                Integer aValue = aLevel.get(j);
                if (Objects.nonNull(aValue)) {
                    sb.append(aValue);
                } else {
                    sb.append("null");
                }
                boolean isLast =
                        i == levelTraversalNodes.size() - 1 && j == aLevel.size() - 1;
                if (!isLast) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    LinkedList<LinkedList<Integer>> levelTraversalNodes = new LinkedList<>();
    LinkedList<TreeNode> aLevelNodesQ = new LinkedList<>();

    private void levelTravel(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        aLevelNodesQ.offer(root);
        TreeNode intervalNode = new TreeNode(Integer.MIN_VALUE);
        aLevelNodesQ.offer(intervalNode);
        while (!aLevelNodesQ.isEmpty()) {
            TreeNode curNode = aLevelNodesQ.peek();
            if (Objects.nonNull(curNode) && curNode.val == Integer.MIN_VALUE) {
                break;
            }
            LinkedList<Integer> aLevelNodes = new LinkedList<>();
            for (int i = 0; i < aLevelNodesQ.size(); i++) {
                curNode = aLevelNodesQ.poll();
                //1.处理间隔节点
                if (Objects.nonNull(curNode) && curNode.val == Integer.MIN_VALUE) {
                    break;
                } else {
                    //2.非间隔节点,先保存记录
                    if (Objects.nonNull(curNode)) {
                        aLevelNodes.add(curNode.val);
                        aLevelNodesQ.offer(curNode.left);
                        aLevelNodesQ.offer(curNode.right);
                    } else {
                        aLevelNodes.add(null);
                    }
                }
            }
            aLevelNodesQ.offer(intervalNode);
            levelTraversalNodes.add(aLevelNodes);
            if (!isALevelNodesAvailable(aLevelNodesQ)) {
                break;
            }
        }
    }

    private boolean isALevelNodesAvailable(LinkedList<TreeNode> aLevelNodesQ) {
        for (int i = 0; i < aLevelNodesQ.size(); i++) {
            TreeNode aNode = aLevelNodesQ.get(i);
            if (Objects.nonNull(aNode) && aNode.val != Integer.MIN_VALUE) {
                return true;
            }
        }
        return false;
    }
    //endregion


    private TreeNode myDoDeserialize(String data) {
        if (Objects.isNull(data) || data.length() == 0) {
            return null;
        }
        if (data.length() == 1) {
            return new TreeNode(Integer.parseInt(data));
        }
        String newData = data.replace("null", "_");
        String[] nodesArr = newData.split(",");
        LinkedList<TreeNode> aLevelNodesQ = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodesArr[0]));
        aLevelNodesQ.offer(root);
        for (int i = 1; i < nodesArr.length; ) {
            int levelMaxSize = aLevelNodesQ.size() * 2;
            int levelMaxRightIdx = i + levelMaxSize - 1;
            TreeNode curNode = aLevelNodesQ.peek();
            while (Objects.nonNull(curNode)) {
                curNode = aLevelNodesQ.peek();
                if (i > levelMaxRightIdx) {
                    break;
                }
                String leftChar = nodesArr[i];
                if (leftChar.equals("_")) {
                    curNode.left = null;
                } else {
                    TreeNode left =
                            new TreeNode(Integer.parseInt(leftChar));
                    curNode.left = left;
                    aLevelNodesQ.offer(left);
                }
                i++;
                if (i > nodesArr.length - 1) {
                    break;
                }
                String rightChar = nodesArr[i];
                if (rightChar.equals("_")) {
                    curNode.right = null;
                } else {
                    TreeNode right =
                            new TreeNode(Integer.parseInt(rightChar));
                    curNode.right = right;
                    aLevelNodesQ.offer(right);
                }
                i++;
                if (i > nodesArr.length - 1) {
                    break;
                }
                curNode = aLevelNodesQ.poll();
            }

        }
        return root;
    }

    //region 层序遍历
    LinkedList<LinkedList<Integer>> allLevels = new LinkedList<>();
    LinkedList<TreeNode> levelNodesQ = new LinkedList<>();


    public LinkedList<LinkedList<Integer>> toLevelTravel(TreeNode root) {
        levelTraversal(root);
        return allLevels;
    }


    public void levelTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        levelNodesQ.offer(root);
        levelNodesQ.offer(null);
        while (!levelNodesQ.isEmpty()) {
            TreeNode curNode = levelNodesQ.peek();
            if (Objects.isNull(curNode)) {
                break;
            }
            LinkedList<Integer> aLevelNodes = new LinkedList<>();
            //循环入队节点子节点
            while (Objects.nonNull(curNode)) {
                curNode = levelNodesQ.poll();
                if (Objects.nonNull(curNode.left)) {
                    levelNodesQ.offer(curNode.left);
                }
                if (Objects.nonNull(curNode.right)) {
                    levelNodesQ.offer(curNode.right);
                }
                aLevelNodes.add(curNode.val);
                curNode = levelNodesQ.peek();
            }

            levelNodesQ.poll();//null对头出队
            levelNodesQ.offer(null);//队尾null间隔入队

            allLevels.add(aLevelNodes);
        }

    }
    //endregion

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.genBinTree();
        // System.out.println(new SerializeAndDeserializeBinTree().toLevelTravel(root));
        // String rootLevelSerial = new SerializeAndDeserializeBinTree().serialize(root);
        String rootLevelSerial = "1,2,3,null,null,4,5";

        System.out.println(rootLevelSerial);
        TreeNode rootDeserialize = new SerializeAndDeserializeBinTree().deserialize(rootLevelSerial);
        System.out.println(JSON.toJSONString(TreeNodeUtils.levelOrderTraversal(rootDeserialize)));
        // System.out.println(new SerializeAndDeserializeBinTree().serialize(rootDeserialize));

    }
}
