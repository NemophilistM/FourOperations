package zwy;

import org.junit.jupiter.api.Test;

import javax.script.ScriptException;

class QuestionUtilTest {

    @Test
    void createQuestion() {
        QuestionUtil.createQuestion(20);
    }

    @Test
    void getOperationResult() {
        try {
            QuestionUtil.getOperationResult("5+6");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}