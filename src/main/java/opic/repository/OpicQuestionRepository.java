package opic.repository;

import opic.domain.OpicQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpicQuestionRepository extends JpaRepository<OpicQuestion, Long> {
    List<OpicQuestion> findByLevel(Integer level);

    List<OpicQuestion> findByLevelAndPart(Integer level, String part);

    List<OpicQuestion> findByLevelAndConditionText(Integer level, String conditionText);

    List<OpicQuestion> findByLevelAndPartAndConditionText(Integer level, String part, String conditionText);
}
