package src.template.algorithm.data_structure.heap.impl;

import src.template.algorithm.data_structure.heap.interfaces.Heap;

import java.util.Arrays;

/**
 * Optimize Heap / PriorityQueue with indexed, can support better delete and update
 * 1 start index for array and avoid using hash
 * Introduce indexes array and reverse indexes array
 *      reverse[i] 表示数据索引 i 在 indexes 中的位置
 *      两个比较常用的方法是：
 *          如果： indexes[i] = j 则 reverse[j] = i
 *          恒等式： indexes[reverse[i]] = reverse[indexes[i]] = i
 * @param <T>
 */

public class IndexHeap<T extends Comparable<T>> implements Heap<T> {

    public static final int DEFAULT_CAPACITY = (1 << 8);
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    protected T[] data;
    // 最大索引堆中的索引, indexes[x] = i 表示索引i在x的位置, 最大索引堆中的反向索引, reverse[i] = x 表示索引i在x的位置
    protected int[] indexes,reverse;
    private int size, capacity;
    protected String mode;
    private final float loadFactor; // determine if need to rehash

    public IndexHeap(String mode) {
        this(mode, DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public IndexHeap(String mode, int capacity, float loadFactor) {
        this.mode = mode;
        this.capacity = capacity;
        this.size = 0;
        data = (T[]) new Comparable[capacity + 1]; // start from index 1
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i < capacity + 1; i++) {
            reverse[i] = 0;
        }
        this.loadFactor = loadFactor;
    }

    public IndexHeap(String mode, int capacity) {
        this.mode = mode;
        this.capacity = capacity;
        this.size = 0;
        data = (T[]) new Comparable[capacity + 1]; // start from index 1
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i < capacity + 1; i++) {
            reverse[i] = 0;
        }
        this.loadFactor = this.DEFAULT_LOAD_FACTOR;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void offer(T val) {
        offer(size + 1, val);
    }

    @Override
    public T peek() {
       return getMax(); // if it is mode == min then the get Max function return minimum value
    }

    public void offer(int index,T val) {
        assert size + 1 <= capacity;
        assert index + 1 >= 1 && index + 1 <= capacity;
        assert !contains(index);

        index += 1;
        data[index] = val;
        indexes[size + 1] = index;
        reverse[index] = size + 1;
        this.size ++;
        if (needExpanding()) {
            expanding();
        }
        percolateUp(size);
    }

    public T poll() {
        assert size > 0;

        T val = data[indexes[1]];
        swayIndexes(1, size);
        reverse[indexes[size]] = 0; // point to 0 means nothing, or you can put -1 if you want
        this.size --;
        percolateDown(1);
        return val;
    }

    public int getMaxIndex() {
        assert size > 0;
        return indexes[1] - 1;
    }

    public T getMax() {
        assert size > 0;
        return data[indexes[1]];
    }

    public T getValue(int index) {
        assert contains(index);
        return data[index + 1];
    }

    public int pollMaxIndex() {
        assert size > 0;
        int maxIndex = indexes[1] - 1;
        swayIndexes(1, size);
        reverse[indexes[size]] = 0;
        this.size --;
        percolateDown(1);
        return maxIndex;
    }

    /**
     * 看索引i所在的位置是否存在元素
     * @param index
     * @return
     */
    public boolean contains(int index) {
        assert index + 1 >= 1 && index + 1 <= capacity;
        return reverse[index + 1] != 0;
    }

    public T getData(int index) {
        assert contains(index);
        return data[index + 1];
    }

    public void update(int index, T new_data) {
        assert contains(index);
        index += 1; // 1 start index
        data[index] = new_data;

        // 我们可以非常简单的通过reverse直接定位索引i在indexes中的位置
        percolateUp(reverse[index]);
        percolateDown(reverse[index]);
    }

    private void swayIndexes(int i, int j) {
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;

        reverse[indexes[i]] = i;
        reverse[indexes[j]] = j;
    }

    private boolean compare(T a, T b) {
        if (a.compareTo(b) < 0) {
            return this.mode.equals("min");
        } else {
            return this.mode.equals("max");
        }
    }

    /**
     *     //********************
     *     //* 最大索引堆核心辅助函数
     *     //********************
     *     索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引
     * @param index
     */
    private void percolateUp(int index) {
        while (index > 1 && compare(data[indexes[index]], data[indexes[index >> 1]])) {
            swayIndexes(index, index >> 1);
            index >>= 1;
        }
    }

    private void percolateDown(int index) {
        while ((index << 1) <= size) {
            int left_son = index << 1, right_son = left_son + 1, son = left_son;
            if (right_son <= size && compare(data[indexes[right_son]], data[indexes[left_son]])) {
                son = right_son;
            }
            if (compare(data[indexes[index]], data[indexes[son]])) {
                break;
            }
            swayIndexes(index, son);
            index = son;
        }
    }

    public void prettyPrint() {
        System.out.println("Heap elements in data[]:");
        for (int i = 1; i <= capacity; i++) {
            if (data[i] != null) {
                System.out.print(data[i] + " ");
            }
        }
        System.out.println();

        System.out.println("Indexes array:");
        for (int i = 1; i <= size; i++) {
            System.out.print(indexes[i] + " "); // you do have print the capacity of size, because element after size should be removed
        }
        System.out.println();

        System.out.println("Reverse array:");
        for (int i = 1; i <= capacity; i++) {
            System.out.print(reverse[i] + " ");
        }
        System.out.println();
    }

    private boolean needExpanding() {
        float ratio = (size + 0.0f) / data.length;
        return ratio >= loadFactor;
    }

    private void expanding() {
        int newCapacity = capacity * 2;

        // Create new arrays with the updated capacity
        T[] newData = (T[]) new Comparable[newCapacity + 1];
        int[] newIndexes = new int[newCapacity + 1];
        int[] newReverse = new int[newCapacity + 1];

        // Copy elements from the old arrays to the new arrays
        System.arraycopy(data, 0, newData, 0, capacity + 1);
        System.arraycopy(indexes, 0, newIndexes, 0, capacity + 1);
        System.arraycopy(reverse, 0, newReverse, 0, capacity + 1);

        // Update references to the new arrays
        data = newData;
        indexes = newIndexes;
        reverse = newReverse;

        // Update the capacity
        capacity = newCapacity;
    }

    public void clear() {
        Arrays.fill(data, null);
        size = 0;
        Arrays.fill(indexes, 0);
        Arrays.fill(reverse, 0);
    }

    public boolean testIndexes() {
        int[] copyIndexes = new int[size + 1];
        int[] copyReverseIndexes = new int[size + 1];

        for (int i = 0; i <= size; i ++) {
            copyIndexes[i] = indexes[i];
            copyReverseIndexes[i] = reverse[i];
        }

        copyIndexes[0] = 0;
        copyReverseIndexes[0] = 0;
        Arrays.sort(copyIndexes);
        Arrays.sort(copyReverseIndexes);

        // 在对索引堆中的索引和反向索引进行排序后,
        // 两个数组都应该正好是1...count这count个索引
        boolean res = true;
        for(int i = 1 ; i <= size ; i ++)
            if( copyIndexes[i-1] + 1 != copyIndexes[i] ||
                    copyReverseIndexes[i-1] + 1 != copyReverseIndexes[i] ){
                res = false;
                break;
            }

        if(!res){
            System.out.println("Error!");
            return false;
        }
        return true;
    }

    public void print() {

    }
    // 测试 IndexMaxHeap
    public static void main(String[] args) {

        int N = 1000000;
        IndexHeap<Integer> indexMaxHeap = new IndexHeap<Integer>("max", N);
        for( int i = 0 ; i < N ; i ++ )
            indexMaxHeap.offer( i , (int)(Math.random()*N) );
        System.out.println(indexMaxHeap.testIndexes());
    }

}
