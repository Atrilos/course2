package pro.sky.java.course2.examinerservice.service.constants;

import pro.sky.java.course2.examinerservice.domain.Question;

public class ExaminerServiceImplTestConstants {

    public static final Question MATH_QUESTION_1 = new Question(
            "Найдите число, если 3/4 от него равны 225?",
            "300"
    );
    public static final Question MATH_QUESTION_2 = new Question(
            "Бесконечные десятичные непериодические дроби называют ... числами.",
            "Иррациональными"
    );
    public static final Question MATH_QUESTION_3 = new Question(
            "Какие два числа называются взаимно обратными?",
            "Два числа, произведение которых равно 1"
    );
    public static final Question JAVA_QUESTION_1 = new Question(
            "Как между собой связаны Iterable, Iterator и «for-each»?",
            "Классы, реализующие интерфейс Iterable, могут применяться в конструкции for-each, которая использует Iterator."
    );
    public static final Question JAVA_QUESTION_2 = new Question(
            "Что такое «коллекция»?",
            "«Коллекция» - это структура данных, набор каких-либо объектов. Данными (объектами в наборе) " +
            "могут быть числа, строки, объекты пользовательских классов и т.п."
    );
    public static final Question JAVA_QUESTION_3 = new Question(
            "Всегда ли исполняется блок finally?",
            "Код в блоке finally будет выполнен всегда, независимо от того, выброшено исключение или нет."
    );
}
