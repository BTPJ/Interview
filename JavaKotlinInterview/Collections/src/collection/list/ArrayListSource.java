package collection.list;

import java.util.ArrayList;

/**
 * 阅读ArrayList源码
 *
 * @author BTPJ  2022/1/14
 */
public class ArrayListSource {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            System.out.print(1);
//            arrayList.add("LTP" + i);
//        }
//
//        for (int i = 19; i >= 0; i--) {
//            System.out.print(2);
//            arrayList.remove(i);
//        }

        arrayList.add("12");
        arrayList.add("12");
        arrayList.add("12");

        arrayList.remove("12");
        System.out.println(arrayList);
    }
}
