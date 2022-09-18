package pro.sky.java.course2.examinerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.RequestAmountIsExcessiveException;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionServiceList;

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount >
                questionServiceList.stream().mapToInt(q -> q.getAll().size()).sum()
                || amount < 0)
            throw new RequestAmountIsExcessiveException("The requested amount exceeds the number of available questions");
        Set<Question> result = new LinkedHashSet<>();
        while (result.size() < amount) {
            questionServiceList.forEach(questionService -> result.add(questionService.getRandomQuestion()));
        }
        return result.stream().toList();
    }
}
