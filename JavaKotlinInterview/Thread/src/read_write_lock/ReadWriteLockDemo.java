package read_write_lock;

import read_write_lock.bean.IPerson;
import read_write_lock.bean.PersonReadWriteLock;
import read_write_lock.bean.PersonSync;

public class ReadWriteLockDemo {

    public static void main(String[] args) {
//        new ReadWriteLockDemo().startSync();
        new ReadWriteLockDemo().startReadWrite();
    }

    private void startSync() {
        PersonSync personSync = new PersonSync();
        for (int i = 0; i < 3; i++) {
            SetThread setThread = new SetThread(personSync);
            setThread.start();
        }

        for (int i = 0; i < 30; i++) {
            GetThread getThread = new GetThread(personSync);
            getThread.start();
        }
    }

    private void startReadWrite() {
        PersonReadWriteLock personReadWriteLock = new PersonReadWriteLock();
        for (int i = 0; i < 3; i++) {
            SetThread setThread = new SetThread(personReadWriteLock);
            setThread.start();
        }

        for (int i = 0; i < 30; i++) {
            GetThread getThread = new GetThread(personReadWriteLock);
            getThread.start();
        }
    }


    /**
     * 写线程
     */
    private class SetThread extends Thread {
        private final IPerson person;

        public SetThread(IPerson person) {
            this.person = person;
        }

        @Override
        public void run() {
            super.run();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                person.setName("BTPJ" + i);
                person.setAge(i);
            }
            System.out.println(Thread.currentThread().getName() + "写入数据耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**
     * 读线程
     */
    private class GetThread extends Thread {
        private final IPerson person;

        public GetThread(IPerson person) {
            this.person = person;
        }

        @Override
        public void run() {
            super.run();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                person.getName();
                person.getAge();
            }
            System.out.println(Thread.currentThread().getName() + "读取数据耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
