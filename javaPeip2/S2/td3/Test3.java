public class Test3 {
    public static void main(String[] args) {
        MyStack<Double> p = new MyStack<Double>();

        p.push(2.25);
        p.push(-1.99);
        p.push(10.34);
        p.pop();
        System.out.println(p.peek());
        System.out.println(p);
    }

}