# Stack and Queue

Stacks and queues are fundamental data structures that are widely used in programming. They both store data but have different rules for how data is added and removed.

---

## Stack

A stack is a **Last In, First Out (LIFO)** data structure, meaning that the last element added is the first one to be removed. You can think of it like a stack of plates where you can only take the top plate off first.

### Basic Operations
- **Push**: Add an element to the top of the stack.
- **Pop**: Remove the top element from the stack.
- **Peek**: View the top element of the stack without removing it.
- **Is Empty**: Check if the stack is empty.

### Use Cases
- **Undo Mechanism**: The last action performed is the first one to be undone, like in text editors.
- **Function Call Stack**: Keeps track of function calls and allows returning to the previous function when a function call completes.
- **Expression Parsing**: Used for evaluating mathematical expressions or validating parentheses.

### Example (Python)

```python
stack = []

# Push elements
stack.append(1)
stack.append(2)
stack.append(3)

# Peek the top element
top = stack[-1]  # Output: 3

# Pop an element
stack.pop()  # Removes 3

# Check if stack is empty
is_empty = len(stack) == 0

# For java
stack.push() is used to add elements to the stack.
stack.peek() retrieves the top element without removing it.
stack.pop() removes the top element.
stack.isEmpty() checks if the stack is empty.
```

## Queue

A queue is a **First In, First Out (FIFO)** data structure, meaning that the first element added is the first one to be removed. It’s like a line at a ticket counter where the first person in line is served first.

### Basic Operations
- **Enqueue**: Add an element to the end of the queue.
- **Dequeue**: Remove the element from the front of the queue.
- **Peek**: View the front element without removing it.
- **Is Empty**: Check if the queue is empty.

### Use Cases
- **Scheduling and Task Management**: Processes are executed in the order they arrive, like print jobs.
- **Breadth-First Search (BFS)**: Used to explore nodes in layers or levels in algorithms like BFS in graphs.
- **Resource Sharing**: Used to manage resources that are shared among multiple users or processes, such as a printer queue.

### Example (Python)

```python
from collections import deque

# Initialize a queue
queue = deque()

# Enqueue elements
queue.append(1)
queue.append(2)
queue.append(3)

# Peek the front element
front = queue[0]  # Output: 1

# Dequeue an element
queue.popleft()  # Removes 1

# Check if queue is empty
is_empty = len(queue) == 0

# For java
queue.add() is used to add elements to the queue.
queue.peek() retrieves the front element without removing it.
queue.poll() removes the front element from the queue.
queue.isEmpty() checks if the queue is empty.
```

## Key Differences

| Feature           | Stack                           | Queue                         |
|-------------------|---------------------------------|-------------------------------|
| **Access Order**  | LIFO (Last In, First Out)       | FIFO (First In, First Out)    |
| **Common Use**    | Undo mechanisms, function calls | Task scheduling, BFS traversal |
| **Main Operations** | Push, Pop, Peek               | Enqueue, Dequeue, Peek        |

---

## How to use LinkedList to maintain Stack or Queue
- **You need to know if you insert to the tail or insert to the head** 
  - The Implementation should be different.
