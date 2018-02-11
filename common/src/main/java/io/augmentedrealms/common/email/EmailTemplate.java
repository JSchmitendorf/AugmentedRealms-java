package io.augmentedrealms.common.email;

public enum EmailTemplate {

    GREETING("Letter from Augmented Realms Team","Thanks for your interest!\n" +
            "\nWe'll let you know when we launch our site and start open beta!\n" +
            "\n-Augmented Realms Team");


    private final String subject;

    private final String text;

    EmailTemplate(String subject, String text){
        this.subject = subject;
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
