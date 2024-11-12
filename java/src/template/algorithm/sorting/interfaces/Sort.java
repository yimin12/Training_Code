package src.template.algorithm.sorting.interfaces;

public abstract class Sort<T extends Comparable<T>> {

    abstract void sort(T[] comparables);

    public static void swap(Object[] array, int i, int j) {
        swap_temp_memory(array, i, j);
    }

    /**
     * Swap 有三种实现方式
     *  1. 使用临时变量 -> 没有限制
     *  2. 加减 -> 适用于可加减的数值类型交换，注意避免溢出
     *  3. 异或 -> 同样只适用于数字类型交换
     * 需要特别注意：
     *  2，3 在遍历元素进行 swap 时， swap不能与自己做交换， 结果值会变成 0，第一种则无需特别注意
     * @param array
     * @param i
     * @param j
     */
    public static void swap_temp_memory(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap_add_minus(Integer[] numbers, int i, int j) {
        if (i == j) return;
        numbers[i] = numbers[i] + numbers[j];
        numbers[j] = numbers[i] - numbers[j];
        numbers[i] = numbers[i] - numbers[j];
    }

    public static void swap_xor(Integer[] numbers, int i, int j) {
        if (i == j) return;
        numbers[i] = numbers[i] ^ numbers[j];
        numbers[j] = numbers[i] ^ numbers[j];
        numbers[i] = numbers[i] ^ numbers[j];
    }

}
