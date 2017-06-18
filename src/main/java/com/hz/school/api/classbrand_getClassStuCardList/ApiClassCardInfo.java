package com.hz.school.api.classbrand_getClassStuCardList;

import com.hz.school.api.ApiEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
@Entity
public class ApiClassCardInfo extends ApiEntity{
    /**
     * 学生卡信息
     */
    @OneToMany(mappedBy = "classRoom")
    private List<ApiCardInfo> stuInfo;

    /**
     * 教师信息
     */
    @OneToMany(mappedBy = "classRoom")
    private List<ApiTeacher> teacherInfo;

    public List<ApiCardInfo> getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(List<ApiCardInfo> stuInfo) {
        this.stuInfo = stuInfo;
    }

    public List<ApiTeacher> getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(List<ApiTeacher> teacherInfo) {
        this.teacherInfo = teacherInfo;
    }
}
