package com.kenzie.alexaqa;

import java.util.List;
import java.util.Map;

public class BestAnswerAlexaQAClient {
    private AlexaQA alexaQA;

    public BestAnswerAlexaQAClient(AlexaQA alexaQA) {
        this.alexaQA = alexaQA;
    }

    public List<AlexaAnswer> findAnswers(AlexaQuestion question) {
        return alexaQA.findAnswers(question);
    }

    public void submitQuestionAnswerSuggestion(AlexaQuestion question, AlexaAnswer answer) {
        alexaQA.submitQuestionAnswerSuggestion(question, answer);
    }

    //TODO: Implement this method
    public AlexaAnswer findBestAnswer(AlexaQuestion question) {
        String bestAnswer = "";
        double bestAnswerScore = 0.0;
        List<AlexaAnswer> answers = findAnswers(question);
        for (AlexaAnswer questions: answers) {
            if (questions.getAnswerQuality() > bestAnswerScore){
                bestAnswerScore = questions.getAnswerQuality();
                bestAnswer = questions.getContent();
            }
        }
        return new AlexaAnswer(bestAnswer, bestAnswerScore);
    }

    //TODO: Implement this method
    public void submitQuestionAnswerSuggestions(AlexaQuestion question, List<AlexaAnswer> answers) {
        for (AlexaAnswer answer : answers) {
            submitQuestionAnswerSuggestion(question, answer);
        }
    }
}
