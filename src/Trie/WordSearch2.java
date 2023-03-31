package Trie;
/*
 * https://leetcode.com/problems/word-search-ii/description/
 */

import java.util.*;

public class WordSearch2 {

    class TrieNode{
        Map<Character,TrieNode> children;
        public TrieNode(){
            children = new HashMap<>();
        }
        public boolean isLeaf() {
            return this.children==null || this.children.isEmpty();
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        TrieNode root = new TrieNode();
        for(String word : words){
            addTrie(root,word);
        }

        int m = board.length;
        int n = board[0].length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                dfs(board, i, j, root, new StringBuilder(), result);
            }
        }
        return result;
    }

    private void addTrie(TrieNode root, String word){
        TrieNode curr = root;
        for(char ch : word.toCharArray()){
            TrieNode child = curr.children.get(ch);
            if (child==null){
                child = new TrieNode();
                curr.children.put(ch, child);
            }
            curr = child;
        }
    }

    private void dfs(char[][] board, int x, int y, TrieNode node, StringBuilder sb, List<String> result) {
        int m = board.length;
        int n = board[0].length;

        if(x<0 || x>=m || y<0 || y>=n) {
            return;
        }

        TrieNode child = node.children.get(board[x][y]);

        if (child!=null) { // has a match
            sb.append(board[x][y]);

            if (child.isLeaf()) {
                result.add(sb.toString());
                sb = new StringBuilder();
            }
            
            char tmp = board[x][y]; // keep it for backtrack
            board[x][y]='#';

            dfs(board, x+1, y, child, sb, result);
            dfs(board, x-1, y, child, sb, result);
            dfs(board, x, y+1, child, sb, result);
            dfs(board, x, y-1, child, sb, result);
            
            board[x][y]=tmp; // backtrack
        }
    }

    public static void main(String[] args) {

        char[][] board = new char[][]{
            {'o','a','a','p'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = new String[]{"oath","pea","ate","eat", "rain", "kate"};

        WordSearch2 ws = new WordSearch2();
        List<String> result = ws.findWords(board, words);

        for(String w : result) {
            System.out.println(w);
        }
    }
}
