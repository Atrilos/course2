package pro.sky.java.course2.examinerservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionAlreadyAddedException;
import pro.sky.java.course2.examinerservice.exception.QuestionAndAnswerAreSameException;
import pro.sky.java.course2.examinerservice.exception.QuestionDoesntExistException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionRepositoryTest {
    private JavaQuestionRepository out;

    @BeforeEach
    void setUp() {
        out = new JavaQuestionRepository();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForSameQuestionAndAnswerTest")
    public void shouldThrowException_IfAddingSameQuestionAndAnswer(Question q) {
        assertThrows(QuestionAndAnswerAreSameException.class,
                () -> out.add(q));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForExistingQuestionTest")
    public void shouldThrowException_IfAddingExistingQuestion(Question q1, Question q2) {
        assertDoesNotThrow(() -> out.add(q1));
        assertThrows(QuestionAlreadyAddedException.class,
                () -> out.add(q2));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForExistingQuestionTest")
    public void shouldChangeContentOfSet_IfAddingCorrectInstance(Question q1) {
        assertDoesNotThrow(() -> out.add(q1));
        assertIterableEquals(List.of(q1),
                out.getAll());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForExistingQuestionTest")
    public void shouldChangeContentOfSet_IfDeletingCorrectInstance(Question q1, Question q2) {
        assertDoesNotThrow(() -> out.add(q1));
        assertDoesNotThrow(() -> out.remove(q2));
        assertIterableEquals(Collections.EMPTY_LIST,
                out.getAll());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForNotExistingQuestionRemoveTest")
    public void shouldThrowException_IfDeletingNotExistingQuestion(Question q1, Question q2) {
        assertDoesNotThrow(() -> out.add(q1));
        assertThrows(QuestionDoesntExistException.class,
                () -> out.remove(q2));
    }

    public static Stream<Arguments> provideParamsForSameQuestionAndAnswerTest() {
        return Stream.of(
                Arguments.of(new Question("", "")),
                Arguments.of(new Question("1", "1")),
                Arguments.of(new Question("abc", "abc")),
                Arguments.of(new Question("Ad est.\nAd est.", "Ad est.\nAd est."))
        );
    }

    public static Stream<Arguments> provideParamsForExistingQuestionTest() {
        return Stream.of(
                Arguments.of(new Question("1", "2"), new Question("1", "2")),
                Arguments.of(new Question("", "Y"), new Question("", "Y")),
                Arguments.of(new Question("abc", "ab"), new Question("abc", "ab")),
                Arguments.of(new Question("Ad est.\nAd est.", "Ad est.\nAd est"), new Question("Ad est.\nAd est.", "Ad est.\nAd est"))
        );
    }

    public static Stream<Arguments> provideParamsForNotExistingQuestionRemoveTest() {
        return Stream.of(
                Arguments.of(new Question("1", "2"), new Question("2", "1")),
                Arguments.of(new Question("", "Y"), new Question("", "N")),
                Arguments.of(new Question("abc", "ab"), new Question("abc", "abh")),
                Arguments.of(new Question("Ad est.\nAd est.", "Ad est.\nAd est"), new Question("Ad est.\nAd est.", "Ad est."))
        );
    }
}