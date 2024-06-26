package top.panson.irpc.framework.core.router;

/**
 * @Author linhao
 * @Date created in 3:46 下午 2022/1/16
 */
public class MyRandom {

    private long seed;

    private int mod;

    private long last;

    public MyRandom(int end) {
        this.seed = System.nanoTime();
        this.mod = end;

    }

    public long randomCount() {
        if (last == 0) {
            last = (int) (System.nanoTime() % mod);
        }
        long n1 = (last * seed + 11) % mod;
        last = n1;
        return n1;
    }

    public static void main(String[] args) {
        MyRandom myRandom = new MyRandom(15);
        for (int i = 0; i < 100; i++) {
            long result = myRandom.randomCount();
            System.out.print(result + " ");
        }
    }
}
