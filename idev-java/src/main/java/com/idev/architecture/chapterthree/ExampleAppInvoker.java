package com.idev.architecture.chapterthree;

public class ExampleAppInvoker {

    /**
     * Java 主方法
     * @param args
     */
    public static void main(String[] args){
        //输出Demo语句
        System.out.println("Hello Chapter Three");

        /**
         * byte 占用一个字节
         */
        byte byteMin = Byte.MIN_VALUE；

        byte byteMax = Byte.MAX_VALUE;


        /**
         * short占两个字节，计算最大值，最小值同int类型
         */
        short shortMin = Short.MIN_VALUE;

        short shortMax = Short.MAX_VALUE;


        /**
         * int 4个字节，每个字节占位8个字，所以由于二进制存在0的起始点，所以最小值为2的（32次方-1)
         */
        int intMin = Integer.MIN_VALUE;

        /**
         * 2的31次方 - 1
         */
        int intMax = Integer.MAX_VALUE;


        /**
         * long占用8个字节
         */
        long longMin = Long.MIN_VALUE;

        long longMax = Long.MAX_VALUE;

        /**
         * float占用4个字节
         */
        float floatMin = Float.MAX_VALUE;

        /**
         *
         */
        float floatMax = Float.MIN_VALUE;



        System.out.println( intNum );


    }

}
