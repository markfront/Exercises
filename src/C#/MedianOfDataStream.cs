/*
The idea is to use two heaps to store the stream of data - a max heap to store the smaller half of the stream and a min heap to store the larger half of the stream. The median of the stream can be found by taking the top element of either the max heap or the min heap, depending on the size of the heaps. We also balance the heaps as we add new elements to ensure that the size difference between them is at most 1, which helps us to maintain the running median efficiently.
*/

using System.Collections.Generic;

public class MedianFinder {
    private PriorityQueue<int> maxHeap; // Stores the smaller half of the stream
    private PriorityQueue<int> minHeap; // Stores the larger half of the stream

    public MedianFinder() {
        maxHeap = new PriorityQueue<int>(Comparer<int>.Create((x, y) => y.CompareTo(x)));
        minHeap = new PriorityQueue<int>();
    }

    public void AddNum(int num) {
        if (maxHeap.Count == 0 || num <= maxHeap.Peek())
        {
            maxHeap.Push(num);
        }
        else
        {
            minHeap.Push(num);
        }

        // Balance the heaps to ensure that the size difference between them is at most 1
        if (maxHeap.Count - minHeap.Count > 1)
        {
            minHeap.Push(maxHeap.Pop());
        }
        else if (minHeap.Count - maxHeap.Count > 1)
        {
            maxHeap.Push(minHeap.Pop());
        }
    }

    public double FindMedian() {
        if (maxHeap.Count == minHeap.Count)
        {
            // If the size of the heaps is even, the median is the average of the two middle elements
            return (maxHeap.Peek() + minHeap.Peek()) / 2.0;
        }
        else if (maxHeap.Count > minHeap.Count)
        {
            // If the size of the max heap is greater, the median is the top element of the max heap
            return maxHeap.Peek();
        }
        else
        {
            // If the size of the min heap is greater, the median is the top element of the min heap
            return minHeap.Peek();
        }
    }
}
