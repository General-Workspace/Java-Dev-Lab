package stacksandqueues.leetcodeinterview.stacks_enqueue_6;

import java.util.Stack;

public class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // WRITE THE ENQUEUE METHOD HERE //
    //                               //
    //                               //
    //                               //
    //                               //
    ///////////////////////////////////

    public int peek() {
        return stack1.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }
}

