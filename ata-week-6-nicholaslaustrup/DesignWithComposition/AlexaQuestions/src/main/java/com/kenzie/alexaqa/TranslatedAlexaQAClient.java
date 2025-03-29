package com.kenzie.alexaqa;

public class TranslatedAlexaQAClient {
    private BestAnswerAlexaQAClient bestAnswerAlexaQAClient;
    private AlexaTranslator translator;

    public TranslatedAlexaQAClient(BestAnswerAlexaQAClient bestAnswerAlexaQAClient, AlexaTranslator translator) {
        this.bestAnswerAlexaQAClient = bestAnswerAlexaQAClient;
        this.translator = translator;
    }

    public String findBestAnswerAndTranslate(AlexaQuestion question) {
        String bestAnswer = bestAnswerAlexaQAClient.findBestAnswer(question).getContent();
        return translator.getTranslation(bestAnswer);
    }
}
