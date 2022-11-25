package com.yunlonglee.dataStructure;

/**
 * @author lijie
 * @version 1.0
 * @description 208. 实现 Trie (前缀树)
 * @date 19/2/22 12:19 下午
 */
public class Trie {
    /**
     * 指向26个字母的分支
     */
    private Trie[] children;
    /**
     * 节点是否为单词结束
     */
    private boolean isEnd;


    public Trie() {
        this.children = new Trie[26];
        this.isEnd = false;
    }

    public void insert(String word) {
        Trie aNode = new Trie();
        for (int i = 0; i < word.length(); i++) {
            int charIndex = word.charAt(i) - 'a';
            if (null == aNode.children[charIndex]) {
                aNode.children[charIndex] = new Trie();
            }
            aNode = aNode.children[charIndex];
        }
        aNode.isEnd = true;
    }

    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return null != trie && trie.isEnd == true;

    }

    public boolean startsWith(String prefix) {
        Trie trie = searchPrefix(prefix);
        return null != trie && trie.isEnd == false;
    }

    private Trie searchPrefix(String prefix) {
        Trie[] children = this.children;
        Boolean isEnd = this.isEnd;
        Trie itor = null;
        for (int i = 0; i < prefix.length(); i++) {
            int charIndex = prefix.charAt(i) - 'a';
            itor = children[charIndex];
            if (null != itor) {
                children = itor.children;
                isEnd = itor.isEnd;
                continue;
            } else {
                return null;
            }
        }
        return itor;

    }
}
