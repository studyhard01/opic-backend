package opic.controller;

import opic.domain.OpicQuestion;
import opic.repository.OpicQuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class OpicQuestionController {

    private final OpicQuestionRepository repository;

    // ✅ 생성자 주입
    public OpicQuestionController(OpicQuestionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<OpicQuestion> getAllQuestions() {
        return repository.findAll();
    }


    @GetMapping("/random")
    public List<OpicQuestion> getRandomQuestions(
            @RequestParam Integer level,
            @RequestParam(required = false) String part,
            @RequestParam(required = false) String conditionText
    ) {
        List<OpicQuestion> matched;

        if (part != null && conditionText != null) {
            matched = repository.findByLevelAndPartAndConditionText(level, part, conditionText);
        } else if (part != null) {
            matched = repository.findByLevelAndPart(level, part);
        } else if (conditionText != null) {
            matched = repository.findByLevelAndConditionText(level, conditionText);
        } else {
            matched = repository.findByLevel(level); // 최소 조건: level
        }

        // 중복 제거 (질문 텍스트가 동일한 경우)
        List<OpicQuestion> deduplicated = matched.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                q -> q.getQuestionKr(), // 한글 질문 내용으로 중복 제거
                                q -> q,
                                (q1, q2) -> q1
                        ),
                        m -> new ArrayList<>(m.values())
                ));

        // 랜덤 셔플 후 최대 4개 추출
        Collections.shuffle(deduplicated);
        return deduplicated.stream()
                .limit(4)
                .collect(Collectors.toList());
    }


}
