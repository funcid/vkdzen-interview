package me.func.section;

public interface Scorer<Document, User> {

    double getScore(Document doc, User user);

}