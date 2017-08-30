package pers.caijx.java8.tutorial.features;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class NumberTest {

    public static void main(String[] args){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE+1);

        long maxUnsignedInt = (1L << 32) - 1;
        String string = String.valueOf(maxUnsignedInt);
        int unsignedInt = Integer.parseUnsignedInt(string,10);
        String string2 = Integer.toUnsignedString(unsignedInt,10);
        System.out.println(unsignedInt + " " + string2);

//        数值不可解析为有符号整数，因为它超出了最大范围 2 ** 31 - 1
        try {
            Integer.parseInt(string,10);
        } catch (NumberFormatException e){
            System.out.println("could not parse signed int of " + maxUnsignedInt);
        }
    }
}
