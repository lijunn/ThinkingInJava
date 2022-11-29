import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : LiJun
 * @date : 2021-03-09 11:34
 **/
public class Test {

    public static void main(String[] args) {
        int f = 0x14141414;
        int i = 0333;

        int f2 = 0xDDDD;
//        int x2 = 0xDDDD;

        System.out.println(Integer.toBinaryString(i));
        System.out.println(i);
//        System.out.println(Integer.toBinaryString(x));
//        System.out.println(Integer.toHexString(336860180));
//
//        System.out.println(Integer.toBinaryString(f2));
//        System.out.println(f2);
    }


    public static class Person{


        int age;
        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static String appendStr(String s){
        s+= "aa";
        return s;
    }


}
