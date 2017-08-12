package my_lambda;

import jdk.nashorn.internal.objects.annotations.Function;

/**
 * Created by Administrator on 2017/7/23.
 */

interface IFun {
    public void print(String a);

    public void print(Integer a);
}

interface IFun2 {
    public void print(Object o);
}

public class MyLambda {
    static public void main(String[] args) {
        IFun t = new IFun() {
            @Override
            public void print(String a) {
                System.out.println(a);
            }

            @Override
            public void print(Integer a) {
                System.out.println(a);
            }
        };
        t.print("dddd");
        t.print(33);

        IFun2 f2 = (Object o) -> {
            System.out.print("fun2--");
            System.out.println(o);
        };
        f2.print(333);
        f2.print("33p");

        IFun2 refrence=System.out::println;
        refrence.print("hehe-yy");
        refrence.print("hehe-yy");
    }


}
