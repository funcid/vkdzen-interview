# 8 августа

### Условия 

Время 1 час 30 минут

1. Дан интерфейс для получения оценки релевантности
документа для конкретного пользователя (выполняется быстро)
```java
public interface Scorer<Document, User> {

    double getScore(Document doc, User user);

}
```

2. Дан интерфейс для получения топа релевантных документов для пользователя
с лимитом, и с методом для добавления нового документа
```java
public interface RecommenderService<Document, User> {

    List<Document> getTop(User user, int limit);

    void addDocument(Document document);

}
```

### Задача

Необходимо реализовать RecommenderService, написать тесты,
реализация должна быть потокобезопастной 