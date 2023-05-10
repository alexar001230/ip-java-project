package com.yunlong.lee.dataStructure.linkedList;

import com.yunlong.lee.utils.list.ListNode;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 207. 课程表
 * @date 9/5/23 1:58 下午
 * @link https://leetcode.cn/problems/course-schedule/description/
 */
public class CanFinish {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return doCanFinishByBfs(numCourses, prerequisites);
    }


    //region bfs
    private boolean doCanFinishByBfs(int numCourses, int[][] prerequisites) {
        if (Objects.isNull(prerequisites) || prerequisites.length == 0) {
            return true;
        }
        //1.构造依赖关系  拓扑图依赖关系，边和入度
        ArrayList<ArrayList<Integer>> courseRelationEdges = new ArrayList<>();
        //课程前置依赖数(图的入度)
        ArrayList<Integer> courseIndegrees = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            courseIndegrees.add(0);
        }
        //1.1 初始化 边 列表
        for (int i = 0; i < numCourses; i++) {
            courseRelationEdges.add(new ArrayList<>());
        }
        //1.2 遍历依赖关系,初始化边集合及入度集合
        for (int[] aRelation : prerequisites) {
            int preCourseNo = aRelation[1];
            int postCourseNo = aRelation[0];
            courseRelationEdges.get(preCourseNo).add(postCourseNo);
            //初始化 post对应的入度
            Integer inDegree = courseIndegrees.get(postCourseNo);
            if (Objects.isNull(inDegree)) {
                courseIndegrees.set(postCourseNo, 1);
            } else {
                courseIndegrees.set(postCourseNo, inDegree + 1);
            }
        }

        //2.bfs
        LinkedList<Integer> queue = new LinkedList<>();
        //2.1 找入度为0的节点入队
        int notSelected = 0;
        for (int courseNo = 0; courseNo < courseIndegrees.size(); courseNo++) {
            if (courseIndegrees.get(courseNo) == 0/**课程入度为0**/) {
                int relationEdges = courseRelationEdges.get(courseNo).size();
                if (relationEdges > 0) {
                    //该课程在选课范围内
                    queue.offer(courseNo);
                } else {
                    notSelected++;
                }
            }
        }

        HashSet<Integer> visitedSet = new HashSet<>();


        while (!queue.isEmpty()) {
            int courseNo = queue.poll();
            visitedSet.add(courseNo);
            ArrayList<Integer> relationNos = courseRelationEdges.get(courseNo);
            //逐步抹去关联节点的入度
            for (Integer aCourseNo : relationNos) {
                int inDegrees = courseIndegrees.get(aCourseNo);
                courseIndegrees.set(aCourseNo, inDegrees - 1);
                if (courseIndegrees.get(aCourseNo) == 0) {
                    queue.offer(aCourseNo);
                }
            }
        }
        return visitedSet.size() + notSelected == numCourses;
    }
    //endregion


    //region 分析一坨shit
    private int unvisit = 0;
    private int visiting = 1;
    private int visited = 1;

    class Course {
        public int val;
        public HashSet<Integer> nextCourses;
        public int status;
        public boolean isPre = false;
        public boolean isPost = false;

        public Course(int val, HashSet<Integer> nextCourses) {
            this.val = val;
            this.nextCourses = nextCourses;
        }

        public Course(int val) {
            this.val = val;
        }

    }


    private boolean doCanFinish(int numCourses, int[][] prerequisites) {

        HashMap<Integer, Course> no2NextCoursesMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            Course aNextCourse = new Course(i, new HashSet<>());
            no2NextCoursesMap.put(i, aNextCourse);
        }
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode cursor = dummyHead;
        HashMap<ListNode, Boolean> hasNodeInMap = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisity = prerequisites[i];
            Course pre = no2NextCoursesMap.get(prerequisity[1]);
            pre.isPre = true;
            if (pre.isPost) {
                return false;
            }
            Course post = no2NextCoursesMap.get(prerequisity[0]);
            post.isPost = true;
            if (post.isPre) {
                return false;
            }
        }
        return false;
    }

    private boolean hasCircle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (Objects.nonNull(fast)) {
            slow = slow.next;
            if (Objects.nonNull(fast.next)) {
                fast = fast.next.next;
            } else {
                return false;
            }
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    //endregion

    public static void main(String[] args) {
        // int[][] prerequisites = new int[][]{{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        int[][] prerequisites = new int[][]{{2, 0}, {2, 1}};
        int numCourses = 3;
        System.out.println(new CanFinish().canFinish(numCourses, prerequisites));
    }
}
