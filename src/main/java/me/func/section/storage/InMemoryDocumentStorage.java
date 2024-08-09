package me.func.section.storage;

import java.util.HashSet;
import java.util.Set;

public class InMemoryDocumentStorage<Document> implements DocumentStorage<Document> {

    private final Set<Document> documents = new HashSet<>();

    public Set<Document> getDocuments() {
        return documents;
    }

    @Override
    public void addDocument(Document document) {
        documents.add(document);
    }
}
