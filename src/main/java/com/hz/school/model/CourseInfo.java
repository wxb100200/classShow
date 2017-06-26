package com.hz.school.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课程信息表
 */
@Entity
@Table(name = Constant.DB_PREFIX+"course_info")
public class CourseInfo extends BaseEntity{
    /**
     * 科目名称
     */
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
