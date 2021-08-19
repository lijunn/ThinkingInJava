import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author : LiJun
 * @date : 2021-03-09 11:34
 **/
public class Test {

    public static void main(String[] args) {
//        String str = "abc";
//        String str2 = "abc";
//
//        Byte b = 2;
//        Boolean bo = true;
//        Person fff = new Person("fff");
//        Person eee = new Person("eee");
//
//        System.out.println(fff == eee);
//        System.out.println(fff.hashCode());
//        System.out.println(eee.hashCode());

        BigDecimal codeDiscount = new BigDecimal("7.7");


        BigDecimal unit = BigDecimal.valueOf(999);

        BigDecimal divide = BigDecimal.TEN.subtract(codeDiscount).divide(BigDecimal.TEN);

        BigDecimal bigDecimal = unit.multiply(divide).setScale(0, RoundingMode.UP);


        System.out.println(divide.toPlainString());
        System.out.println(bigDecimal.toPlainString());
    }

    public static String appendStr(String s){
        s+= "aa";
        return s;
    }

    public static class Person{
        String name;

        public Person(String name) {
            this.name = name;
        }
    }
}
