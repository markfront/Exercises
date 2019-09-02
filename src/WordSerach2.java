import java.util.*;

public class WordSerach2 {
    public static void main(String[] args) {

        char[][] board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[]{"oath","pea","ate","eat","rain"};

        List<String> result = findWords(board, words);

        for(String w : result) {
            System.out.println(w);
        }
    }

    static class TrieNode{
        Map<Character,TrieNode> children;
        //boolean isWord;
        public TrieNode(){
            children = new HashMap<>();
        }
        public boolean endOfWord() {
            return this.children==null || this.children.isEmpty();
        }
    }
    static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};

    public static List<String> findWords(char[][] board, String[] words) {
        //corner case è¿˜æ²¡å†™,ä¸è¿‡è¿™é‡Œæ— æ‰€è°“
        List<String> result = new ArrayList<>();
        TrieNode root = new TrieNode();
        for(String word:words){
            addTrie(root,word);
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dfs(board,i,j,root,new StringBuilder(),result,visited);
            }
        }
        return result;
    }

    private static void addTrie(TrieNode root, String word){
        TrieNode cur = root;
        //TrieNode prev = null;
        for(int i=0;i<word.length();i++){
            TrieNode next = cur.children.get(word.charAt(i));
            if(next==null){
                next = new TrieNode();
                cur.children.put(word.charAt(i),next);
            }
            //prev = cur;
            cur = next;
        }
        //if (prev!=null) prev.isWord = true; //åˆ«å¿˜äº†
    }

    private static void dfs(char[][] board, int x, int y, TrieNode root, StringBuilder sb, List<String> result, boolean[][] visited){
        if(x<0||x>=board.length||y<0||y>=board[0].length||visited[x][y]){
            return;
        }
        root = root.children.get(board[x][y]);
        if(root!=null){
            sb.append(board[x][y]);
            //if(root.isWord){
            if (root.endOfWord()) {
                result.add(sb.toString());
                //root.isWord = false; //å·§å¦™é¿å…resulté‡Œé¢çš„åŽ»é‡
            }
            visited[x][y]=true;
            for(int[] dir: DIRS){
                int neiX = dir[0]+x;
                int neiY = dir[0]+y;
                dfs(board,neiX,neiY,root,sb,result,visited);
            }
            visited[x][y]=false;
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
