package pro.sky.java.course2.examinerservice.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionAlreadyAddedException;
import pro.sky.java.course2.examinerservice.exception.QuestionAndAnswerAreSameException;
import pro.sky.java.course2.examinerservice.exception.QuestionDoesntExistException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Primary
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questionSet;

    public JavaQuestionRepository() {
        this.questionSet = ConcurrentHashMap.newKeySet();
    }

    @Override
    @PostConstruct
    public void init() {
        questionSet.add(new Question(
                "Как между собой связаны Iterable, Iterator и «for-each»?",
                "Классы, реализующие интерфейс Iterable, могут применяться в конструкции for-each, которая использует Iterator."
        ));
        questionSet.add(new Question(
                "Что такое «коллекция»?",
                "«Коллекция» - это структура данных, набор каких-либо объектов. Данными (объектами в наборе) " +
                "могут быть числа, строки, объекты пользовательских классов и т.п."
        ));
        questionSet.add(new Question(
                "Всегда ли исполняется блок finally?",
                "Код в блоке finally будет выполнен всегда, независимо от того, выброшено исключение или нет."
        ));
        questionSet.add(new Question(
                "Может ли метод main() выбросить исключение во вне " +
                "и если да, то где будет происходить обработка данного " +
                "исключения?",
                "Может и оно будет передано в виртуальную машину Java (JVM)."
        ));
        questionSet.add(new Question(
                "Есть класс Point{int x, y;}. " +
                "Почему хэш код в виде 31 * x + y предпочтительнее, " +
                "чем x + y?",
                "Множитель создает зависимость значения хэш кода от очередности обработки полей, что в итоге " +
                "порождает лучшую хэш функцию."
        ));
        questionSet.add(new Question(
                "Дайте определение понятию «конструктор».",
                "Конструктор — это специальный метод, у которого отсутствует возвращаемый тип и который имеет " +
                "то же имя, что и класс, в котором он используется. Конструктор вызывается при создании нового " +
                "объекта класса и определяет действия необходимые для его инициализации."
        ));
        questionSet.add(new Question(
                "Почему в некоторых интерфейсах вообще не определяют методов?",
                "Это так называемые маркерные интерфейсы. Они просто указывают что класс относится к " +
                "определенному типу. Примером может послужить интерфейс Cloneable, который указывает на то, что " +
                "класс поддерживает механизм клонирования."
        ));
        questionSet.add(new Question(
                "Какая коллекция реализует дисциплину обслуживания FIFO?",
                "FIFO, First-In-First-Out («первым пришел-первым ушел») - по этому принципу построена коллекция " +
                "Queue"
        ));
        questionSet.add(new Question(
                "Какое худшее время работы метода contains() для элемента, который есть в ArrayList?",
                "O(N). Время поиска элемента линейно пропорционально количеству элементов с списке."
        ));
        questionSet.add(new Question(
                "Что такое «интеграционное тестирование»?",
                "Интеграционное тестирование (integration testing) — это тестирование, проверяющие " +
                "работоспособность двух или более модулей системы в совокупности — то есть нескольких объектов как " +
                "единого блока."
        ));
    }

    @Override
    public Question add(Question question) {
        if (question.getQuestion().equalsIgnoreCase(question.getAnswer()))
            throw new QuestionAndAnswerAreSameException("The same entry inside question and answer fields is prohibited");
        if (!questionSet.add(question))
            throw new QuestionAlreadyAddedException("The same question already exists in the repository");
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionSet.remove(question))
            throw new QuestionDoesntExistException("The repository doesn't contain the specified question");
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionSet.stream().toList();
    }
}
