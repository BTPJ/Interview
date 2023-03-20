/**
 * finally
 * finally 部分存在，则一定会执行finally里面的代码，
 * 如果try/catch中包含return语句，finally中的return语句也会最后执行进行覆盖，
 * 除非在try或finally块中用了System.exit(0)退出程序（如果 System.exit(int)在异常语句之后，finally 还是会被执行）
 * 或程序所在线程死亡
 *
 * @author BTPJ  2023/3/15
 */
public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println(finallyReturn());
    }

    private static int finallyReturn() {
        try {
            System.out.println("try1执行");
            // System.exit(0);
            int i = 1 / 0;
            System.out.println("try2执行");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch执行");
            return 2;
        } finally {
            System.out.println("finally执行");
            return 3;
        }
    }
}
