package me.func.section.storage;

import java.util.Set;

public interface DocumentStorage<Document> {

    Set<Document> getDocuments();

    void addDocument(Document document);

}
