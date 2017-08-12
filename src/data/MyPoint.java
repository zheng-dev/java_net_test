package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/8/5.
 */
public class MyPoint<T extends Comparable> implements Comparable<T> {
    private T x;
    private T y;

    public MyPoint(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(T obj) {
        if (obj instanceof MyPoint) {
            MyPoint obj1 = (MyPoint) obj;
            int result = ((Comparable) this.x).compareTo(obj1.x);
            if (result == 0) result = ((Comparable) this.y).compareTo(obj1.y);
            return result;
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof MyPoint) {
            MyPoint obj1 = (MyPoint) obj;
            return this.x == obj1.x && this.y == obj1.y;
        }
        return false;
    }

    /**
     * 测试入口
     *
     * @param args
     */
    public static void main(String[] args) {
        MyPoint.compare1();

    }

    //比较器
    static private void compare1() {
        List<MyPoint> arr = new ArrayList<>(4);
        arr.add(new MyPoint(new MyT(3), new MyT(4)));
        arr.add(new MyPoint(new MyT(3), new MyT(2)));
        arr.add(new MyPoint(new MyT(7), new MyT(1)));
        arr.add(new MyPoint(new MyT(5), new MyT(4)));

        arr.sort((MyPoint o1, MyPoint o2) -> {
            int r = 0;
            r = ((Comparable) o1.x).compareTo(o2.x);
            if (r == 0) r = ((Comparable) o1.y).compareTo(o2.y);
            return r;
        });
//        arr.sort(new Comparator<MyPoint>() {
//            @Override
//            public int compare(MyPoint o1, MyPoint o2) {
//                int r = 0;
//                r = ((Comparable) o1.x).compareTo(o2.x);
//                if (r == 0) r = ((Comparable) o1.y).compareTo(o2.y);
//                return r;
//            }
//        });
        for (MyPoint<MyT> o : arr
                ) {
            System.out.println(String.format("%s  ==%s", o.x.x2, o.y.x2));
        }
    }

    //自比较
    static private void compare2() {
//        MyPoint<String>[] arr = new MyPoint[4];
//        arr[0] = new MyPoint<>("a7", "b3");
//        arr[1] = new MyPoint<>("a3", "b2");
//        arr[2] = new MyPoint<>("a", "b3");
//        arr[3] = new MyPoint<>("a", "b4");


        MyPoint<MyT>[] arr = new MyPoint[4];
        arr[0] = new MyPoint<>(new MyT(3), new MyT(4));
        arr[1] = new MyPoint<>(new MyT(3), new MyT(3));
        arr[2] = new MyPoint<>(new MyT(4), new MyT(3));
        arr[3] = new MyPoint<>(new MyT(3), new MyT(5));

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(String.format("%s  ==%s", arr[i].x.x2, arr[i].y.x2));
        }

        System.out.println("return:" + (arr[1].equals(arr[0])));
    }
}

class MyT implements Comparable<MyT> {
    public Integer x2;
    public MyT(Integer x) {
        this.x2 = x;
    }

    @Override
    public int compareTo(MyT x) {
        return this.x2.compareTo(x.x2);
    }
}
