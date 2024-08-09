## Секция 1 - Алгоритмическая секция

### Условия

Время 1 час</br>
Писать решение в простом online-редакторе без подсказок

### Задача

Дан список интов, повторяющихся элементов в списке нет.
Нужно преобразовать это множество в строку, сворачивая соседние по числовому ряду числа в диапазоны.

Примеры:
```
[1,4,5,2,3,9,8,11,0] => "0-5,8-9,11"
[1,4,3,2] => "1-4"
[1,4] => "1,4"
[1,3,2] => "1-3"
```

### Решение

```java
public String compress(int[] array) {
    Arrays.sort(array);
    List<String> seqs = new ArrayList<>();
    int windowStartIndex = 0;

    for (int windowEndIndex = 0; windowEndIndex < array.length; windowEndIndex++) {
        if (windowEndIndex + 1 == array.length || array[windowEndIndex] + 1 != array[windowEndIndex + 1]) {
            if (windowEndIndex - windowStartIndex >= 1) {
                seqs.add(array[windowStartIndex] + "-" + array[windowEndIndex]);
            } else {
                seqs.add(array[windowEndIndex] + "");
            }
            windowStartIndex = windowEndIndex + 1;
        }
    }
    return String.join(",", seqs);
}
```

### Результат

Решение верное

## Секция 2 - Production Code

### Условия 

Время 1 час 30 минут</br>
Можно пользоваться браузером

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

### Результат

Решение на уровне `Middle-`, отказ