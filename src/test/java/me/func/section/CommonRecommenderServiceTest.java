package me.func.section;

import me.func.section.storage.InMemoryDocumentStorage;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommonRecommenderServiceTest {

    private final FakeScorer scorer = new FakeScorer();

    private final RecommenderService<Document, User> recommenderServiceHappyWay
            = new CommonRecommenderService<>(scorer, new InMemoryDocumentStorage<>());

    private final RecommenderService<Document, User> recommenderServiceUnhappyWayZeroDocs
            = new CommonRecommenderService<>(scorer, new InMemoryDocumentStorage<>());

    private final RecommenderService<Document, User> recommenderServiceUnhappyWayNegativeLimit
            = new CommonRecommenderService<>(scorer, new InMemoryDocumentStorage<>());

    @Test
    public void happyTestTopCommand() {
        User user1 = new User("A");

        Document document1 = new Document("1", "", 1);
        Document document2 = new Document("2", "", 2);
        Document document3 = new Document("3", "", 3);
        Document document4 = new Document("4", "", 4);
        Document document5 = new Document("5", "", 5);

        recommenderServiceHappyWay.addDocument(document1);
        recommenderServiceHappyWay.addDocument(document2);
        recommenderServiceHappyWay.addDocument(document3);
        recommenderServiceHappyWay.addDocument(document4);
        recommenderServiceHappyWay.addDocument(document5);

        List<Document> result = recommenderServiceHappyWay.getTop(user1, 3);

        assertEquals(3, result.size());

        assertEquals(5, result.get(0).id());
        assertEquals(4, result.get(1).id());
        assertEquals(3, result.get(2).id());
    }

    @Test
    public void unhappyTestTopCommandZeroDocs() {
        User user1 = new User("A");

        List<Document> result1 = recommenderServiceUnhappyWayZeroDocs.getTop(user1, 3);
        List<Document> result2 = recommenderServiceUnhappyWayZeroDocs.getTop(user1, 1);

        assertEquals(0, result1.size());
        assertEquals(0, result2.size());
    }

    @Test
    public void unhappyTestTopCommandNegativeLimit() {
        User user1 = new User("A");

        assertThrows(
                IllegalArgumentException.class,
                () -> recommenderServiceUnhappyWayNegativeLimit.getTop(user1, -1)
        );
    }

}


