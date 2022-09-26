import java.io.*;
import java.util.StringTokenizer;

//ba geeks for geeks
public class Main {

    public static void main(String[] args) throws IOException {
        MyScanner sc = new MyScanner();
        //PrintWriter outi = new PrintWriter(new BufferedOutputStream(System.out));
        OutputStream out = new BufferedOutputStream(System.out);
        // BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = sc.nextInt();
        MaxHeap maxheap = new MaxHeap(n);
        MinHeap minheap = new MinHeap(n);
        int mid = 0;

        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            if (m > mid) {
                if (minheap.size > maxheap.size) {
                    if (minheap.size == 1) {
                        maxheap.insert(minheap.Heap[1]);
                        minheap = new MinHeap(n / 2 + 1);
                    } else
                        maxheap.insert(minheap.extractMin());
                }
                minheap.insert(m);
                minheap.minHeap();
            } else {
                if (maxheap.size > minheap.size) {
                    if (maxheap.size == 1) {
                        minheap.insert(maxheap.Heap[1]);
                        maxheap = new MaxHeap(n / 2 + 1);
                    } else
                        minheap.insert(maxheap.extractMax());
                }
                maxheap.insert(m);
                maxheap.maxHeap();
            }

            if (maxheap.size == minheap.size)
                mid = maxheap.Heap[1];
            else if (maxheap.size > minheap.size)
                mid = maxheap.Heap[1];
            else if (minheap.size > maxheap.size)
                mid = minheap.Heap[1];

            //System.out.println(mid);
            //outi.println(mid);
            out.write((mid + "\n").getBytes());
            // log.write(mid + "\n");
        }
        out.flush();
        //  log.flush();

    }


}

class MinHeap {
    private static final int FRONT = 1;
    int[] Heap;
    int maxsize;
    int size;

    public MinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MIN_VALUE;
    }

    private int parent(int position) {
        return position / 2;
    }

    private int right(int position) {
        return (2 * position) + 1;
    }

    private int left(int position) {
        return (2 * position);
    }

    private boolean isLeaf(int position) {
        if (position >= (size / 2) && position <= size)
            return true;
        return false;
    }

    private void swap(int first, int second) {
        int help;
        help = Heap[first];
        Heap[first] = Heap[second];
        Heap[second] = help;
    }

    private void minHeapify(int position) {
        if (!isLeaf(position)) {
            if (Heap[position] > Heap[left(position)] || Heap[position] > Heap[right(position)]) {
                if (Heap[left(position)] >= Heap[right(position)]) {
                    swap(position, right(position));
                    minHeapify(right(position));
                } else {
                    swap(position, left(position));
                    minHeapify(left(position));
                }
            }
        }
    }

    public void insert(int element) {
        if (size >= maxsize)
            return;
        Heap[++size] = element;
        int current = size;
        while (Heap[current] < Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void minHeap() {
        for (int position = (size / 2); position >= 1; position--) {
            minHeapify(position);
        }
    }

    public int extractMin() {
        int first = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);
        return first;
    }
}

class MaxHeap {
    int[] Heap;
    int size;
    int maxsize;

    public MaxHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;
    }

    private int parent(int position) {
        return position / 2;
    }

    private int right(int position) {
        return (2 * position) + 1;
    }

    private int left(int position) {
        return (2 * position);
    }

    private boolean isLeaf(int position) {
        if (position >= (size / 2) && position <= size)
            return true;
        return false;
    }

    private void swap(int first, int second) {
        int help;
        help = Heap[first];
        Heap[first] = Heap[second];
        Heap[second] = help;
    }

    private void maxHeapify(int position) {
        if (!isLeaf(position)) {
            if (Heap[position] < Heap[left(position)] || Heap[position] < Heap[right(position)]) {
                if (Heap[left(position)] <= Heap[right(position)]) {
                    swap(position, right(position));
                    maxHeapify(right(position));
                } else {
                    swap(position, left(position));
                    maxHeapify(left(position));
                }
            }
        }
    }

    public void maxHeap() {
        for (int position = (size / 2); position >= 1; position--) {
            maxHeapify(position);
        }
    }

    public void insert(int element) {
        Heap[++size] = element;
        int current = size;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int extractMax() {
        int first = Heap[1];
        Heap[1] = Heap[size--];
        maxHeapify(1);
        return first;
    }
}

class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }


}





