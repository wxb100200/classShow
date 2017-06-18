package com.hz.school.api.classbrand_getClassStuScoreList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiClassStudentScore extends ApiEntity{
    /**
     * 考试名称
     */
    private String examTitle;
    /**
     * 微校通考试id
     */
    private Long examid;
    /**
     * 单次考试，班级内该学生总分排名
     */
    private Integer ranking;
    /**
     * 老师的评语
     */
    private String teacherComment;
    /**
     * 学生id
     */
    private Long stuid;
    /**
     * 班级id
     */
    private Long classid;

    @OneToMany(mappedBy = "classStudentScore")
    private List<ApiStudentScore> scoreList;

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Long getExamid() {
        return examid;
    }

    public void setExamid(Long examid) {
        this.examid = examid;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public Long getStuid() {
        return stuid;
    }

    public void setStuid(Long stuid) {
        this.stuid = stuid;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public List<ApiStudentScore> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<ApiStudentScore> scoreList) {
        this.scoreList = scoreList;
    }
}
