package zwy;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionUtil {

    /**
     * 用于得出生成的算术式集合
     *
     * @param numericalRange 生成的算术式的数字限制范围
     * @return 返回生成的算术字符串
     */
    public static String createQuestion(int numericalRange) {
        return supplyOperator(getNumericalRangeList(numericalRange));
    }

    /**
     * @param number 要组成的计算式内的数字个体集合
     * @return 返回一个已经组装好的计算式
     */
    private static String supplyOperator(List<Integer> number) {
        Random r = new Random();
        String equation = "";
        for (int i = 0; i < number.size(); i++) {
            if (i == 0) {
                equation = number.get(i).toString();
            } else {
                switch (r.nextInt(4)) {
                    case 0:
                        equation = String.join("+", equation, number.get(i).toString());
                        break;
                    case 1:
                        equation = String.join("-", equation, number.get(i).toString());
                        break;
                    case 2:
                        equation = String.join("*", equation, number.get(i).toString());
                        break;
                    case 3:
                        equation = String.join("/", equation, number.get(i).toString());
                        break;
                }
            }
        }

        return equation;
    }

    /**
     * 这个函数的作用是根据给定的数值范围，得出一个在这个数值范围内的包含2-4个数数组来构成算术式里面的数字元素
     *
     * @param numericalRange 数值范围
     * @return 生成的数组
     */
    private static ArrayList<Integer> getNumericalRangeList(int numericalRange) {
        ArrayList<Integer> resultList = new ArrayList<>();
        Random r = new Random();
        int i = r.nextInt(3) + 2;//生成数组的个数在2-4之间
        for (int j = 0; j < i; j++) {
            resultList.add(r.nextInt(numericalRange - 1) + 1);
        }
        return resultList;
    }

    /**
     * @param s 要运算的字符串
     * @return 运算结果
     * @throws ScriptException 不知道是什么鬼异常，应该是运算符格式不正确就会抛这个异常
     */
    public static String getOperationResult(String s) throws ScriptException {
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        return jse.eval(s).toString();
    }
}
