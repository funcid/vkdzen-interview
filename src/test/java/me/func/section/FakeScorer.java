package me.func.section;

public class FakeScorer implements Scorer<Document, User> {

    @Override
    public double getScore(Document doc, User user) {
        return doc.id();
    }

}
