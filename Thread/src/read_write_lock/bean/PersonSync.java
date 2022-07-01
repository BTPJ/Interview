package read_write_lock.bean;

/**
 * 使用synchronized方式
 *
 * @author BTPJ  2022/2/22
 */
public class PersonSync implements IPerson {
    private String name = "";
    private int age = 0;

    @Override
    public synchronized String getName() {
        try {
            // 模拟读取耗时
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public synchronized void setName(String name) {
        // 模拟写入耗时
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    @Override
    public int getAge() {
        // 模拟读取耗时
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return age;
    }

    @Override
    public void setAge(int age) {
        // 模拟写入耗时
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.age = age;
    }
}
