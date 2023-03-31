/*
https://leetcode.com/problems/lru-cache/

The problem can be solved with a hashtable that keeps track of the keys and its values in the double linked list. 
One interesting property about double linked list is that the node can remove itself without other reference. 
In addition, it takes constant time to add and remove nodes from the head or tail.

One particularity about the double linked list that I implemented is that I create a pseudo head and tail to mark the boundary, 
so that we don't need to check the NULL node during the update. This makes the code more concise and clean, and also it is good for the performance.
*/

import java.util.*;

public class LRU_Cache {

  class DblLinkNode {
    int key;
    int value;
    DblLinkNode prev;
    DblLinkNode post;
  }

  /**
   * Always add the new node right after head;
   */
  private void addToFront(DblLinkNode node) {
    node.prev = head;
    node.post = head.post;

    head.post.prev = node;
    head.post = node;
  }

  /**
   * Remove an existing node from the linked list.
   */
  private void removeNode(DblLinkNode node){
    DblLinkNode prev = node.prev;
    DblLinkNode post = node.post;

    prev.post = post;
    post.prev = prev;
  }

  /**
   * Move certain node in the middle to the head.
   */
  private void moveToFront(DblLinkNode node){
    this.removeNode(node);
    this.addToFront(node);
  }

  // remove the current tail. 
  private DblLinkNode removeFromEnd(){
    DblLinkNode res = tail.prev;
    this.removeNode(res);
    return res;
  }

  private Map<Integer, DblLinkNode> cache = new HashMap<>();
  private int count;
  private int capacity;
  private DblLinkNode head, tail;

  public LRU_Cache(int capacity) {
    this.count = 0;
    this.capacity = capacity;

    head = new DblLinkNode(); // dummy head
    head.prev = null;

    tail = new DblLinkNode(); // dummy tail
    tail.post = null;

    head.post = tail;
    tail.prev = head;
  }

  public int get(int key) {

    DblLinkNode node = cache.get(key);
    if(node == null){
      return -1; // should raise exception here.
    }

    // move the accessed node to the head;
    this.moveToFront(node);

    return node.value;
  }

  public void put(int key, int value) {
    DblLinkNode node = cache.get(key);

    if(node == null){

      DblLinkNode newNode = new DblLinkNode();
      newNode.key = key;
      newNode.value = value;

      this.cache.put(key, newNode);
      this.addToFront(newNode);

      ++count;

      if(count > capacity){
        // pop the tail
        DblLinkNode tail = this.removeFromEnd();
        this.cache.remove(tail.key);
        --count;
      }
    }else{
      // update the value.
      node.value = value;
      this.moveToFront(node);
    }
  }

}
