/*
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/
 * 
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

    Trie() Initializes the trie object.
    void insert(String word) Inserts the string word into the trie.
    boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
    boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True

Constraints:

    1 <= word.length, prefix.length <= 2000
    word and prefix consist only of lowercase English letters.
    At most 3 * 104 calls in total will be made to insert, search, and startsWith.

 */
package Trie;

import java.util.*;

public class PrefixTree {
    class TrieNode{
        Map<Character, TrieNode> children;
        boolean isLeaf = false;
        public TrieNode(){
            children = new HashMap<>();      
        }
    }
    
    class Trie {
        TrieNode head;
        /** Initialize your data structure here. */   
        public Trie() {
            head = new TrieNode();
        }
        
        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(word == null)
                return;
            TrieNode node = head;
            for(char ch : word.toCharArray()) {
                if(!node.children.containsKey(ch)){
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);
            }
            node.isLeaf = true;
        }
        
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if(word == null)
                return false;
            TrieNode node = head;
            for(char ch : word.toCharArray()) {
                if(!node.children.containsKey(ch)){
                    return false;
                } 
                node = node.children.get(ch);
            }
            return node.isLeaf;
        }
        
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if(prefix == null)
                return false;
            TrieNode node = head;
            for(char ch : prefix.toCharArray()) {
                if(!node.children.containsKey(ch)){
                    return false;
                } 
                node = node.children.get(ch);
                
            }
            return true; 
        }
    }
}
