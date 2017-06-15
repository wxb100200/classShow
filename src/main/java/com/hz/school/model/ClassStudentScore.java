package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 班级学生成绩表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"class_stuscore")
public class ClassStudentScore extends BaseEntity{
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

    @ManyToOne
    private Student student;
    /**
     * 班级id
     */
    private Long classid;

    @ManyToOne
    private ClassRoom classRoom;

    @OneToMany(mappedBy = "classStudentScore")
    private List<StudentScore> scoreList;

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

    public List<StudentScore> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<StudentScore> scoreList) {
        this.scoreList = scoreList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
