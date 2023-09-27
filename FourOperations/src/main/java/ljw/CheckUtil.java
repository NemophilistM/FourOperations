package zsc;

import java.util.ArrayList;
import java.util.List;

public class CheckUtil {

    /**
     * 此函数用于
     *
     * @param jobAnswerList      学生做出来的答案集合
     * @param standardAnswerList 标准答案集合
     * @return 返回答对的题号
     */
    public static ArrayList<Integer> checkAnswer(ArrayList<String> jobAnswerList, ArrayList<String> standardAnswerList) {
        ArrayList<Integer> rightAnswer = new ArrayList<>();
        for (int i = 0; i < standardAnswerList.size(); i++) {
            if (jobAnswerList.get(i).equals(standardAnswerList.get(i))) {
                rightAnswer.add(i + 1);
            }
        }
        return rightAnswer;
    }

    /**
     * 判断两道整数题目是否等价，即通过有限次交换+和×左右的算术表达式变换为同一道题目
     *
     * @param question1 第一道题目
     * @param question2 第二道题目
     * @return 如果两道题目等价则返回 true，否则返回 false
     */
    public static boolean isEquivalentQuestion(String question1, String question2) {
        String[] tokens1 = question1.split(" ");
        String[] tokens2 = question2.split(" ");
        // 如果两道题目的操作数数量不同，则它们不等价
        if (tokens1.length != tokens2.length) {
            return false;
        }
        // 统计两道题目中 + 和 × 的数量和位置
        int numPlus1 = 0, numPlus2 = 0, numTimes1 = 0, numTimes2 = 0;
        List<Integer> plusPositions1 = new ArrayList<>();
        List<Integer> plusPositions2 = new ArrayList<>();
        List<Integer> timesPositions1 = new ArrayList<>();
        List<Integer> timesPositions2 = new ArrayList<>();
        for (int i = 1; i < tokens1.length - 1; i += 2) {
            if (tokens1[i].equals("+")) {
                numPlus1++;
                plusPositions1.add(i);
            } else if (tokens1[i].equals("*")) {
                numTimes1++;
                timesPositions1.add(i);
            }
            if (tokens2[i].equals("+")) {
                numPlus2++;
                plusPositions2.add(i);
            } else if (tokens2[i].equals("*")) {
                numTimes2++;
                timesPositions2.add(i);
            }
        }
        // 如果两道题目的 + 和 × 的数量不同，则它们不等价
        if (numPlus1 != numPlus2 || numTimes1 != numTimes2) {
            return false;
        }
        // 如果两道题目中 + 和 × 的位置不同，则它们不等价
        if (!plusPositions1.equals(plusPositions2) || !timesPositions1.equals(timesPositions2)) {
            return false;
        }
        // 对于两道题目中每个操作数，如果它们在相同的位置上，但值不同，则两道题目不等价
        for (int i = 0; i < tokens1.length; i += 2) {
            if (!tokens1[i].equals(tokens2[i])) {
                return false;
            }
        }
        return true;
    }

}
