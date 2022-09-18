package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.exception.RequestAmountIsExcessiveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static pro.sky.java.course2.examinerservice.service.constants.ExaminerServiceImplTestConstants.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private MathQuestionService mathQuestionMock;
    @Mock
    private JavaQuestionService javaQuestionMock;

    @Spy
    private ArrayList<QuestionService> questionServiceList;

    @InjectMocks
    private ExaminerServiceImpl out;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        questionServiceList.addAll(Arrays.asList(mathQuestionMock, javaQuestionMock));
        lenient().doCallRealMethod().when(questionServiceList).forEach(any(Consumer.class));
        lenient().when(mathQuestionMock.getRandomQuestion())
                .thenReturn(MATH_QUESTION_1, MATH_QUESTION_2, MATH_QUESTION_3);
        lenient().when(javaQuestionMock.getRandomQuestion())
                .thenReturn(JAVA_QUESTION_1, JAVA_QUESTION_2, JAVA_QUESTION_3);
        when(mathQuestionMock.getAll()).thenReturn(List.of(MATH_QUESTION_1, MATH_QUESTION_2, MATH_QUESTION_3));
        when(javaQuestionMock.getAll()).thenReturn(List.of(JAVA_QUESTION_1, JAVA_QUESTION_2, JAVA_QUESTION_3));
    }

    @Test
    public void shouldReturnCorrectQuestions() {
        assertIterableEquals(List.of(MATH_QUESTION_1, JAVA_QUESTION_1, MATH_QUESTION_2, JAVA_QUESTION_2, MATH_QUESTION_3, JAVA_QUESTION_3),
                out.getQuestions(6));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 7, 8, 15})
    public void shouldThrowIf_ValueNegativeOrMoreThanAmountOfQuestionsInRepo(int amount) {
        assertThrows(RequestAmountIsExcessiveException.class,
                () -> out.getQuestions(amount));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    public void doesntThrowIf_AmountInRangeOfRepoSize(int amount) {
        assertDoesNotThrow(() -> out.getQuestions(amount));
    }
}