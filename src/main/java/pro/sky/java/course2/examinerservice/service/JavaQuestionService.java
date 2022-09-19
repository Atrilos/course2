package pro.sky.java.course2.examinerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

import java.util.Collection;
import java.util.Random;

@Service
@Primary
@RequiredArgsConstructor
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository questionRepo;
    private final Random rng;

    @Override
    public Question add(String question, String answer) {
        return questionRepo.add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return questionRepo.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepo.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepo.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        final int size = questionRepo.getAll().size();
        return questionRepo
                .getAll()
                .stream()
                .skip(rng.nextInt(size))
                .findFirst().orElseThrow();
    }
}
