

import zsc.CheckUtil;
import zsc.FileUtil;
import zwy.QuestionUtil;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScriptException {

        ArrayList<String> questionList = new ArrayList<>();
        ArrayList<String> answerList = new ArrayList<>();//这里存储正确答案
        ArrayList<String> yourAnswerList = new ArrayList<>();//这里存储学生答案
        System.out.println("输入要生成的题目数量：");
        Scanner scanner1 = new Scanner(System.in);
        int number = scanner1.nextInt();
        System.out.println("输入要生成的题目数值范围：");
        Scanner scanner2 = new Scanner(System.in);
        int number1 = scanner2.nextInt();
        for (int i = 0; i < number; i++) {
            boolean isEquivalent = true;//这个变量用于判断生成的算术式是否与已经有的算术式重复。true则为不重复，可以存入集合
            String question = QuestionUtil.createQuestion(number1);
            try {
                if (i != 0) {
                    for (String s : questionList) {
                        if (CheckUtil.isEquivalentQuestion(question, s)) {
                            isEquivalent = false;
                        }
                    }
                }

                if (isEquivalent) {
                    FileUtil.writeFile(question, "D:\\question.txt", i != 0);
                    String answer = QuestionUtil.getOperationResult(question);
                    FileUtil.writeFile(answer, "D:\\standrdanswer.txt", i != 0);
                    answerList.add(answer);
                    questionList.add(question);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下面为生成的题目，全部做完自动校对结果");
        for (int i = 0; i < questionList.size(); i++) {
            int x = i + 1;
            System.out.println("(" + x + "):" + questionList.get(i) + "=");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            yourAnswerList.add(answer);
            try {
                FileUtil.writeFile(QuestionUtil.getOperationResult(answer), "D:\\youranswer.txt", i != 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Correct:" + CheckUtil.checkAnswer(answerList, yourAnswerList).size() + "(" + CheckUtil.checkAnswer(answerList, yourAnswerList) + ")");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i <= answerList.size(); i++) {
            arrayList.add(i);
        }
        for (int i = 0; i < CheckUtil.checkAnswer(answerList, yourAnswerList).size(); i++) {
            arrayList.remove(CheckUtil.checkAnswer(answerList, yourAnswerList).get(i));
        }
        System.out.println("Wrong:" + arrayList.size() + "(" + arrayList + ")");
    }
}
