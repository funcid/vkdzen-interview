package me.func.section;

import me.func.section.storage.DocumentStorage;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CommonRecommenderService<Document, User> implements RecommenderService<Document, User> {

    private final Scorer<Document, User> scoreService;
    private final DocumentStorage<Document> documentStorage;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public CommonRecommenderService(Scorer<Document, User> scoreService, DocumentStorage<Document> documentStorage) {
        this.scoreService = scoreService;
        this.documentStorage = documentStorage;
    }

    @Override
    public List<Document> getTop(User user, int limit) {

        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }

        Set<Document> localDocuments;
        lock.readLock().lock();
        try {
            localDocuments = Set.copyOf(documentStorage.getDocuments());
        } finally {
            lock.readLock().unlock();
        }

        return localDocuments.stream()
                .map(document -> new Score<>(document, scoreService.getScore(document, user)))
                .sorted((score1, score2) -> Double.compare(score2.getScore(), score1.getScore()))
                .limit(limit)
                .map(Score::getDocument)
                .toList();
    }

    @Override
    public void addDocument(Document document) {
        lock.writeLock().lock();
        try {
            documentStorage.addDocument(document);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
