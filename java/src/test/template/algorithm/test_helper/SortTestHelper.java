package src.test.template.algorithm.test_helper;

import java.lang.reflect.Method;

/**
 * Basic on generating the Random Integer Array
 */
public class SortTestHelper {

    private SortTestHelper() {}

    /**
     *  生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
     * @param n
     * @param left
     * @param right
     * @return
     */
    public static Integer[] generateRandomArray(int n, int left, int right) {
        assert left <= right;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)Math.floor(Math.random() * (right - left + 1)) + left;
        }
        return arr;
    }

    /**
     * 生成一个近乎有序的数组, 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据. swapTimes定义了数组的无序程度:
     *       swapTimes == 0 时, 数组完全有序
     *       swapTimes 越大, 数组越趋向于无序
     * @param n
     * @param swapTimes
     * @return
     */
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        for (int i = 0; i < swapTimes; i ++) {
            int a = (int)(Math.random() * n), b = (int)(Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }
        return arr;
    }

    public static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++){
            System.out.print( arr[i] );
            System.out.print( ' ' );
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] arr){
        for( int i = 0 ; i < arr.length - 1 ; i ++ )
            if( arr[i].compareTo(arr[i+1]) > 0 )
                return false;
        return true;
    }

    /**
     * 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
     *      将算法的运行时间打印在控制台上
     * @param sortClassName
     * @param arr
     */
    public static void testSort(String sortClassName, Comparable[] arr) {
        try {
            Class<?> sortClass = Class.forName(sortClassName);
            Object sortInstance = sortClass.getDeclaredConstructor().newInstance(); // Create an instance of the class
            Method sortMethod = sortClass.getMethod("sort", Comparable[].class);
            long startTime = System.currentTimeMillis();
            sortMethod.invoke(sortInstance, (Object) arr); // Invoke method on the instance
            long endTime = System.currentTimeMillis();
            assert isSorted(arr);
            System.out.println(sortClass.getSimpleName() + " : " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 有返回值的调用
     * @param sortClassName
     * @param arr
     * @return
     */
    public static long testSort_return(String sortClassName, Comparable[] arr) {
        try {
            Class<?> sortClass = Class.forName(sortClassName);
            Object sortInstance = sortClass.getDeclaredConstructor().newInstance(); // Create an instance of the class
            Method sortMethod = sortClass.getMethod("sort", Comparable[].class);
            long startTime = System.currentTimeMillis();
            sortMethod.invoke(sortInstance, (Object) arr); // Invoke method on the instance
            long endTime = System.currentTimeMillis();
            assert isSorted(arr);
            return endTime - startTime;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
