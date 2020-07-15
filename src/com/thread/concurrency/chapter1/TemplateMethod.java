package com.thread.concurrency.chapter1;

/**
 * TODO
 *
 * @Author Administrator
 * @Date 2020/5/26 0:38
 */
public class TemplateMethod {

    public final void print (String msg)
    {
        System.out.println("############");
        wrapPrint(msg);
        System.out.println("#############");
    }

    protected void wrapPrint(String msg){

    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String msg) {
                System.out.println("现在输出的是*****" + msg);
            }
        };

        t1.print("看看");

        int i = 10;
        int a = 5;
        int b = 0;
        while(i > 0){
            int c = 0;
            try{
                c = a / b;
                //System.out.printf("第 %d 次循环\n", i --);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            System.out.println(c);
            System.out.printf("第 %d 次循环\n", i --);
        }
    }
}
