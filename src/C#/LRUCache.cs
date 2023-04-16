/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

    - LRUCache(int capacity) -- Initialize the LRU cache with positive size capacity.
    - int get(int key) -- Return the value of the key if the key exists, otherwise return -1.
    - void put(int key, int value) -- Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.

The functions get and put must each run in O(1) average time complexity.
*/

using System.Collections.Generic;

public class LRUCache {
    // Define a Node class to represent each key-value pair in the cache
    private class Node {
        public int Key;
        public int Value;
        public Node Prev;
        public Node Next;
    }

    /*
    The idea is to use a Doubly Linked List to keep track of the least recently used and most recently used nodes. The least recently used node is always at the tail of the list, and the most recently used node is always at the head of the list. We also use a dictionary to look up nodes by their corresponding keys.
    */
    private readonly int capacity;
    private readonly Dictionary<int, Node> cache;
    private Node head; // most recently used node
    private Node tail; // least recently used node

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new Dictionary<int, Node>(capacity);
        head = null;
        tail = null;
    }

    public int Get(int key) {
        // If key exists in cache, move its corresponding node to the front of the list and return its value
        if (cache.TryGetValue(key, out Node node)) {
            MoveToFront(node);
            return node.Value;
        }
        // If key does not exist in cache, return -1
        return -1;
    }

    public void Put(int key, int value) {
        if (cache.TryGetValue(key, out Node node)) {
            // If key already exists in cache, update its value and move its corresponding node to the front of the list
            node.Value = value;
            MoveToFront(node);
        } else {
            // If key does not exist in cache, add its corresponding node to the front of the list
            node = new Node { Key = key, Value = value };
            cache.Add(key, node);
            if (cache.Count > capacity) {
                // If cache capacity is exceeded, remove the least recently used node from the cache and the list
                cache.Remove(tail.Key);
                RemoveFromTail();
            }
            AddToFront(node);
        }
    }

    private void MoveToFront(Node node) {
        if (node == head) {
            return;
        }
        if (node == tail) {
            tail = node.Prev;
        } else {
            node.Next.Prev = node.Prev;
        }
        node.Prev.Next = node.Next;
        AddToFront(node);
    }

    private void AddToFront(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.Next = head;
            head.Prev = node;
            head = node;
        }
    }

    private void RemoveFromTail() {
        if (tail == null) {
            return;
        }
        if (tail == head) {
            head = null;
            tail = null;
        } else {
            tail.Prev.Next = null;
            tail = tail.Prev;
        }
    }
}
