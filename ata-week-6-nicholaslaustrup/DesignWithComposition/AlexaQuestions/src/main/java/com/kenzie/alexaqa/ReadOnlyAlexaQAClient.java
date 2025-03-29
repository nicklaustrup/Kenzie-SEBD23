package com.kenzie.alexaqa;

import java.util.List;

public class ReadOnlyAlexaQAClient {

    private AlexaQA alexaQA;

    public ReadOnlyAlexaQAClient(AlexaQA alexaQA) {
        this.alexaQA = alexaQA;
    }

    public List<AlexaAnswer> findAnswers(AlexaQuestion question) {
        return alexaQA.findAnswers(question);
    }
}
