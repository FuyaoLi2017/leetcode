/*
Suppose we have a class:

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().
*/

class Foo {

    private AtomicInteger atomicInteger;

    public Foo() {
        atomicInteger = new AtomicInteger();
    }

    public void first(Runnable printFirst) throws InterruptedException {
    while (true) {
        if (atomicInteger.compareAndSet(0, 0)) {
            printFirst.run();
            atomicInteger.compareAndSet(0, 1);
            break;
        }
    }
}

public void second(Runnable printSecond) throws InterruptedException {
    while (true) {
        if (atomicInteger.compareAndSet(1, 1)) {
            printSecond.run();
            atomicInteger.compareAndSet(1, 2);
            break;
        }
    }
    // printSecond.run() outputs "second". Do not change or remove this line.
}

public void third(Runnable printThird) throws InterruptedException {
    while (true) {
        if (atomicInteger.compareAndSet(2, 2)) {
            printThird.run();
            atomicInteger.compareAndSet(2, 3);
            break;
        }
    }
}
}
