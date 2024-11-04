# Terminology

### A. 子串 (Substring)
- **Definition:** A contiguous sequence of characters within a string.
- **Characteristics:** Continuous.

### B. 子序列 (Subsequence)
- **Definition:** A sequence derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
- **Characteristics:** Non-continuous.

### C. 子数组 (Subarray)
- **Definition:** A contiguous segment of an array.
- **Characteristics:** Continuous.

### D. 前缀和 (Prefix Sum)
- **Definition:** An array used to calculate the sum of any subarray in \( O(1) \) time complexity.
- **Usage:** Given an array `nums`, the prefix sum array `prefixSum` allows us to calculate the sum of elements between any two indices \( i \) and \( j \) with the formula:
  \[
  \text{sum}(i, j) = \text{prefixSum}[j + 1] - \text{prefixSum}[i]
  \]

#### Example
Given `nums = [2, 3, 1, 2, 4, 3]`, the prefix sum array is constructed as follows:

- **nums**: `2 3 1 2 4 3`
- **prefixSum**: `0 2 5 6 8 12 15`
- **index**: ` 0 1 2 3 4  5  6`

Here, `prefixSum[i]` represents the sum of elements from the start up to `index(i-1)` in `nums`.

---

# Summary of Algorithmic Complexities and Techniques

### 1. **O(n * log n) Complexity**
- **Sorting**: Sorting algorithms like Merge Sort and Quick Sort operate in O(n * log n) time.
- **Binary Search (n times)**: Performing binary search multiple times results in O(n * log n).
- **Fast Exponentiation (n times)**: Calculating powers efficiently in O(log n) per operation yields O(n * log n) for n operations.
- **Logarithmic Data Structures**: Heaps, segment trees, and binary indexed trees (Fenwick trees) offer O(log n) operations, resulting in O(n * log n) complexity when used n times.

### 2. **O(n * sqrt(n)) Complexity**
- **Factor-Related Problems**: Problems involving divisors or factors often operate in O(n * sqrt(n)) due to factor pair characteristics.

### 3. **O(n) Complexity**
- **Two Pointers**: The two-pointer technique varies based on the movement of the pointers:
    - **Same Direction (同向)**: Both pointers move forward, useful for problems like counting specific patterns in subarrays (e.g., counting all-zero subarrays). *Characteristics*: Pointers do not "backtrack."
    - **Opposite Direction (相向)**: Pointers start at opposite ends and move toward each other, useful for problems involving sums or combinations (e.g., Two Sum, Three Sum).
    - **Back-to-Back (背向)**: Pointers start together and move apart, useful for finding centered patterns, like the longest palindromic substring.
- **Monotonic Stack**: Useful for next greater or smaller element problems, typically operating in O(n).
- **Quick Select**: Finds the k-th smallest or largest element, achieving average O(n) complexity.
- **Tree Traversals & Divide and Conquer**: Depth-First Search (DFS), Breadth-First Search (BFS), and divide-and-conquer techniques work well for tree-based problems.
- **Union-Find Operations**: Efficient for connectivity problems, achieving near O(n) complexity with path compression.
- **Hash Table Operations**: Fast lookups, insertions, and deletions make hash tables efficient, generally O(1) per operation, yielding O(n) for n operations.

---

# Summary Table

| Complexity       | Techniques / Algorithms                                   | Suitable Problem Types                     |
|------------------|-----------------------------------------------------------|--------------------------------------------|
| **O(n * log n)** | Sorting, Binary Search (n times), Fast Exponentiation, Heap, Segment Tree | Sorting, repeated search, range queries   |
| **O(n * sqrt(n))** | Factor-related problems                                 | Problems involving divisors or factor pairs |
| **O(n)**        | Two Pointers (Same Direction, Opposite Direction, Back-to-Back), Monotonic Stack, Quick Select, Tree Traversals, Union-Find, Hash Tables | Linear scans, connectivity, frequency count |

Use this table to quickly assess the best approach based on the complexity required for efficient performance.

---

# Two Pointers: Variations and Applications

1. **Same Direction (同向)**
    - **Definition:** Both pointers move in the same direction, usually forward.
    - **Usage Example:** Counting the number of all-zero subarrays.
    - **Characteristics:** Pointers do not "backtrack."

2. **Opposite Direction (相向)**
    - **Definition:** Pointers start from opposite ends and move towards each other.
    - **Usage Examples:** Solving for sums or combinations, such as Two Sum or Three Sum problems.

3. **Back-to-Back (背向)**
    - **Definition:** Pointers start together and move away from each other.
    - **Usage Example:** Finding centered patterns like the longest palindromic substring.
