package opic.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "opic_questions")
public class OpicQuestion {
    @Id
    @GeneratedValue
    private Long id;

    private String part;
    private Integer questionId;
    private String conditionText;
    private Integer level;
    private String questionKr;
    private String questionEn;

    public void setPart(String part) {
        this.part = part;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setQuestionKr(String questionKr) {
        this.questionKr = questionKr;
    }

    public void setQuestionEn(String questionEn) {
        this.questionEn = questionEn;
    }

    public String getPart() {
        return part;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getConditionText() {
        return conditionText;
    }

    public Integer getLevel() {
        return level;
    }

    public String getQuestionKr() {
        return questionKr;
    }

    public String getQuestionEn() {
        return questionEn;
    }


}
