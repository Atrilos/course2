package pro.sky.java.course2.examinerservice.repository;

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
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> questionSet;

    public MathQuestionRepository() {
        this.questionSet = ConcurrentHashMap.newKeySet();
    }

    @Override
    @PostConstruct
    public void init() {
        questionSet.add(new Question(
                "Найдите число, если 3/4 от него равны 225?",
                "300"
        ));
        questionSet.add(new Question(
                "Бесконечные десятичные непериодические дроби называют ... числами.",
                "Иррациональными"
        ));
        questionSet.add(new Question(
                "Какие два числа называются взаимно обратными?",
                "Два числа, произведение которых равно 1"
        ));
        questionSet.add(new Question(
                "Упростите выражение 3(4a+4b)+2(2a+3b).",
                "16a+18b"
        ));
        questionSet.add(new Question(
                "Основные свойства арифметических действий называют ...",
                "законами"
        ));
        questionSet.add(new Question(
                "Сколько в прямоугольном параллелепипеде рёбер?",
                "12"
        ));
        questionSet.add(new Question(
                "Формула объёма прямоугольного параллелепипеда?",
                "V=abc"
        ));
        questionSet.add(new Question(
                "Автобус должен проехать от одного города до другого 40 км. Проехав 30 км., он сделал остановку. " +
                "Сколько процентов пути пройдено?",
                "75%"
        ));
        questionSet.add(new Question(
                "Чтобы найти процентное отношение двух чисел, надо ...",
                "их отношение умножить на 100 и к результату дописать знак процента"
        ));
        questionSet.add(new Question(
                "Какие числа являются общими делителями чисел 24 и 16?",
                "2, 4, 8"
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
