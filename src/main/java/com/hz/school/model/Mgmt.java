package com.hz.school.model;



import javax.persistence.*;

/**
 * Mgmt记录专用
 */
@Entity
@Table(name = Constant.DB_PREFIX+"mgmt")
public class Mgmt extends BaseEntity {

    public static enum Type {
        SQL,
        JS,
        OTHER
    }
    /**
     * 类型，sql,js,...
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    /**
     * 内容
     */
    @Column(nullable = false)
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 星等，用于收藏重要sql/js，并可用于界面排序
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private int star=0;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
