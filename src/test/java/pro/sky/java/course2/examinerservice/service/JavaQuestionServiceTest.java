package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.JavaQuestionRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static pro.sky.java.course2.examinerservice.service.constants.ExaminerServiceImplTestConstants.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private JavaQuestionRepository repoMock;

    private Random rngMock;

    private JavaQuestionService out;

    @BeforeEach
    void setUp() {
        rngMock = mock(Random.class, withSettings().withoutAnnotations());
        out = new JavaQuestionService(repoMock, rngMock);
        when(repoMock.getAll()).thenReturn(List.of(JAVA_QUESTION_1, JAVA_QUESTION_2, JAVA_QUESTION_3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForSkipTest")
    public void shouldSkipCorrectEntries(int skip, Question question) {
        when(rngMock.nextInt(anyInt())).thenReturn(skip);
        assertEquals(question, out.getRandomQuestion());
    }

    public static Stream<Arguments> provideParamsForSkipTest() {
        return Stream.of(
                Arguments.of(2, JAVA_QUESTION_3),
                Arguments.of(1, JAVA_QUESTION_2),
                Arguments.of(0, JAVA_QUESTION_1)
        );
    }
}