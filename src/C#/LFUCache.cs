/*
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

    - LFUCache(int capacity): Initializes the object with the capacity of the data structure.
    - int get(int key): Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
    - void put(int key, int value): Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.

To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.
*/

using System.Collections.Generic;

public class LFUCache {
    // Define a Node class to represent each key-value pair in the cache
    private class Node {
        public int Key;
        public int Value;
        public int Frequency;
        public Node Prev;
        public Node Next;
    }

    /*
    The idea is to use a doubly linked list to keep track of nodes with the same frequency in the frequency map.
    The key with the smallest frequency is the least frequently used key. We also use a dictionary to look up nodes by their corresponding keys.
    */
    private readonly int capacity;
    private readonly Dictionary<int, Node> cache;
    private readonly Dictionary<int, LinkedList<Node>> frequencyMap;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cache = new Dictionary<int, Node>(capacity);
        frequencyMap = new Dictionary<int, LinkedList<Node>>(); // multiple nodes can have same frequency.
        minFrequency = 0;
    }

    public int Get(int key) {
        if (cache.TryGetValue(key, out Node node)) {
            // If key exists in cache, increment its frequency and move its corresponding node to the front of the list for that frequency
            UpdateFrequency(node);
            return node.Value;
        }
        // If key does not exist in cache, return -1
        return -1;
    }

    public void Put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (cache.TryGetValue(key, out Node node)) {
            // If key already exists in cache, update its value, increment its frequency, and move its corresponding node to the front of the list for that frequency
            node.Value = value;
            UpdateFrequency(node);
        } else {
            if (cache.Count >= capacity) {
                // If cache capacity is exceeded, remove the least frequently used node from the cache and the frequency map
                RemoveLFUNode();
            }
            // Add the new node to the cache and the frequency map with a frequency of 1
            node = new Node { Key = key, Value = value, Frequency = 1 };
            cache.Add(key, node);
            if (!frequencyMap.TryGetValue(1, out LinkedList<Node> nodeList)) {
                nodeList = new LinkedList<Node>();
                frequencyMap.Add(1, nodeList);
            }
            nodeList.AddFirst(node);
            minFrequency = 1;
        }
    }

    private void UpdateFrequency(Node node) {
        // Remove the node from its current frequency list
        int frequency = node.Frequency;
        LinkedList<Node> nodeList = frequencyMap[frequency];
        nodeList.Remove(node);
        if (nodeList.Count == 0 && frequency == minFrequency) {
            minFrequency++;
        }
        // Increment the node's frequency and add it to the front of the list for its new frequency
        node.Frequency++;
        frequency = node.Frequency;
        if (!frequencyMap.TryGetValue(frequency, out LinkedList<Node> newList)) {
            newList = new LinkedList<Node>();
            frequencyMap.Add(frequency, newList);
        }
        newList.AddFirst(node);
    }

    private void RemoveLFUNode() {
        // Remove the least frequently used node from the cache and the frequency map
        LinkedList<Node> nodeList = frequencyMap[minFrequency];
        Node node = nodeList.Last.Value;
        cache.Remove(node.Key);
        nodeList.RemoveLast();
        if (nodeList.Count == 0) {
            frequencyMap.Remove(minFrequency);
        }
    }
}
