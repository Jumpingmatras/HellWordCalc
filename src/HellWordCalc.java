import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//--------------------------------------------------------------------

enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100);
    private int value;
    RomanNumeral(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}



public class HellWordCalc {

    public static void main(String args[]) throws NumberFormatException{
        String arabic[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String roman[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String matOper[] = {"+", "-", "*", "/"};
        String temp[] = new String[0];
        String[] words = new String[0];
        int a = 0;
        int b = 0;
        int chs;

        // Приучаем юзера к правильному вводу
        int i = 0;
        while (i != 3) {
            System.out.print("Введите выражение: ");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine().toUpperCase();
            // чистим от дублированных пробелов
            while (line.contains("  ")) {
                String replace = line.replace("  ", " ");
                line = replace;
            }
            words = line.split(" ");
            i = words.length;
//            System.out.println(Arrays.toString(words));
//            System.out.println("Стартовое i =" + i);
//            if (i < 3) System.out.println("В выражении мало слов");
//            if (i > 3) System.out.println("В выражении много слов");
//            if (i != 3)
//                System.out.println("Формат ввода: 'аргумент' 'пробел' 'знак математического действия' 'аргумент'");

//            System.out.println(words[0] + words[1] + words[2]);

// Опеределим по коду символа принадлежность первого эл-та к arabic
            chs = choice(line, 0);
//            System.out.println("Переменная chs = " + chs);
            try {
                if (49 <= chs && chs <= 57) {
                    int result;
                    temp = (String[]) check1(words, arabic, matOper);
//                    System.out.println("Проскачили по арабски");
                    try {
                        a = Integer.parseInt(temp[0].trim());
//                        System.out.println(a);
                        b = Integer.parseInt(temp[2].trim());
//                        System.out.println(b);
                    }catch (NullPointerException e){
                        System.out.println("Все пропало! Выходим!");
                        System.exit(1);
                    }
                    chs = choice(temp[1], 0);
                    if (chs != 47) {
//                        System.out.println("if -------------");
//                        System.out.println("код мат символа " + chs);
                        result = (int) mathAction(a, b, temp[1]);
                        System.out.println(Math.round(result));
//                        System.out.println("if -------------");
                    } else {
//                        System.out.println("else -------------");
                        result = (int) mathAction(a, b, temp[1]);
                        System.out.println(result);
//                        System.out.println("else -------------");
                    }
                } else {
                    temp = (String[]) check1(words, roman, matOper);
                    int result;
//                    System.out.println("Римские каникулы");
//                    System.out.println(Arrays.toString(temp));
                    temp[0] = String.valueOf(romanToArabic(temp[0]));
                    temp[2] = String.valueOf(romanToArabic(temp[2]));
//                    System.out.println(Arrays.toString(temp));
                    try {
                        a = Integer.parseInt(temp[0].trim());
//                        System.out.println(a);
                        b = Integer.parseInt(temp[2].trim());
//                        System.out.println(b);
                    }catch (NullPointerException e){
                        System.out.println("Все пропало! Выходим!");
                        System.exit(1);
                    }
                    chs = choice(temp[1], 0);
                    if (chs != 47) {
//                        System.out.println("if -------------");
//                        System.out.println("код мат символа " + chs);
                        result = (int) mathAction(a, b, temp[1]);
                        if(result > 0) {
                            System.out.println(arabicToRoman(result));
//                        System.out.println(Math.round(result));
//                        System.out.println("if -------------");
                        }
                        if(result < 0){
                            {
                                System.out.println("Все пропало! Выходим!");
                                System.exit(1);
                            }
//                            result = result * (-1);
//                            System.out.println("-"+arabicToRoman(result));
                        }
                        if(result == 0) {
                            System.out.println("Все пропало! Выходим!");
                            System.exit(1);
                        }
                    } else {
//                        System.out.println("else -------------");
                        result = (int) mathAction(a, b, temp[1]);
                        System.out.println(arabicToRoman(result));
//                        System.out.println(result);
//                        System.out.println(arabicToRoman(result));
//                        System.out.println("else -------------");
                    }
                }
            }catch (NumberFormatException e) {
                System.out.println("Все пропало! Выходим!");
                System.exit(1);
            }
        }
//        System.out.println(new StringBuilder().append("Вернули").append(Arrays.toString(check1(words, roman, matOper))).toString());
//        System.out.println(Arrays.toString(words));
/*        a = Integer.parseInt(temp[0].trim());
        System.out.println(a);
        b = Integer.parseInt(temp[2].trim());
        System.out.println(b);

        mathAction(a, b, temp[1]);
        System.out.println(Arrays.toString(temp));
        System.out.println(arabicToRoman(5));
        System.out.println("Program terminated");

 */
    }

    public static int mathAction(int a, int b, String s) {
        int result = 0;
        try {
            if (s.equals("+")) {
                result = a + b;
                //            System.out.println("Ждали результата и получили: " + result);
            }
            if (s.equals("-")) {
                result = a - b;
                //            System.out.println("Ждали результата и получили: " + result);
            }
            if (s.equals("*")) {
                result = a * b;
                //            System.out.println("Ждали результата и получили: " + result);
            }
            if (s.equals("/")) {
                try {
                    result =  a / b;
                    //                System.out.println("Ждали результата и получили: " + result);
                    return result;
                } catch (ArithmeticException e) {
                    System.out.println("На 0 не делим");
                }

            }
            return result;
        } catch (NullPointerException e) {
            System.out.println("Все пропало! Выходим!");
            System.exit(1);
        }
        return 0;
    }


    public static int romanToArabic(String roman) throws NullPointerException {
        try {
            if (roman.equals("I"))
                return 1;
            else if (roman.equals("II"))
                return 2;
            else if (roman.equals("III"))
                return 3;
            else if (roman.equals("IV"))
                return 4;
            else if (roman.equals("V"))
                return 5;
            else if (roman.equals("VI"))
                return 6;
            else if (roman.equals("VII"))
                return 7;
            else if (roman.equals("VIII"))
                return 8;
            else if (roman.equals("IX"))
                return 9;
            else if (roman.equals("X"))
                return 10;
            else {
                System.out.println("Все пропало! Выходим!");
                System.exit(1);
            }
        } catch (NullPointerException e) {
            System.out.println("Все пропало! Выходим!");
            System.exit(1);
        }
        return 0;
    }

    //-----------------------------------------------------------------
    public static String arabicToRoman(int number) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        try {
            if ((number <= 0) || (number > 100)) {
                throw new IllegalArgumentException(number + " is not in range (0,100]");
            }
            List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

            int i = 0;


            while ((number > 0) && (i < romanNumerals.size())) {
                RomanNumeral currentSymbol = romanNumerals.get(i);
                if (currentSymbol.getValue() <= number) {
                    sb.append(currentSymbol.name());
                    number -= currentSymbol.getValue();
                } else {
                    i++;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Все пропало! Выходим!");
            System.exit(1);
        }
        return sb.toString();
    }
    //-----------------------------------------------------------------


    static int choice(String word, int i) throws NullPointerException {
        try {
            var s = word.charAt(i);
            int f = s;
            return f;
        } catch (NullPointerException e) {
            System.out.println("Все пропало! Выходим!");
            System.exit(1);
        }
        return 0;
    }


    //-------------------------------------------------------------------------------------
    static Object check1(String[] word, String[] lang, String[] Oper) throws ArrayIndexOutOfBoundsException {
        String[] RetArr = new String[0];
        try {
            RetArr = new String[3];
            String firstArg = word[0];
            String mathOper = word[1];
            String scndArg = word[2];
/*            System.out.println("----------------------------------------");
            System.out.println("Первый аргумент :" + firstArg);
            System.out.println("Знак операции :" + mathOper);
            System.out.println("Второй аргумент:" + scndArg);
            System.out.println("----------------------------------------");
            byte flag1 = 0;

 */
// проверяем принадлежит ли первое слово к localArr
            for (int x = 0; x < lang.length; x++)
                if (lang[x].equals(firstArg)) {
                    RetArr[0] = word[0];
//                    flag1 = 1;
                }
/*            if (flag1 == 1) {
                System.out.println(RetArr[0] + " - 1й аргумент принадлежит массиву ");
            } else {
                System.out.println("1й аргумент НЕ найден в массиве ");

            }

 */
// проверяем принадлежит ли второе слово массиву Oper
//            flag1 = 0;
            for (int x = 0; x < Oper.length; x++)
                if (Oper[x].equals(mathOper)) {
                    RetArr[1] = word[1];
//                    flag1 = 1;
                }
/*            if (flag1 == 1) {
                System.out.println(RetArr[1] + " - оператора принадлежит массиву ");
            } else {
                System.out.println("Оператор НЕ найден в массиве ");

            }

 */
// проверяем принадлежит ли третье слово массиву lang
//            flag1 = 0;
            for (int x = 0; x < lang.length; x++)
                if (lang[x].equals(scndArg)) {
                    RetArr[2] = word[2];
//                    flag1 = 1;
                }
/*            if (flag1 == 1) {
                System.out.println(RetArr[2] + " - 2й аргумент принадлежит массиву ");
            } else {
                System.out.println("2й аргумент НЕ найден в массиве ");

            }

 */
//            System.out.println("----------------------------------------");
//        String [] newwordS = {firstArg, mathOper, scndArg};
//        System.out.println(Arrays.toString(newwordS));
//        return newwordS;
            return RetArr;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Все пропало! Выходим!");
            System.exit(1);
        }
        return RetArr;
    }
}
