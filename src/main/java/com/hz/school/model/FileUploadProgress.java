package com.hz.school.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 文件上传进度表
 */
@Entity
@Table(name = Constant.DB_PREFIX + "file_upload_progress")
public class FileUploadProgress extends BaseEntity implements Serializable {

	public enum FileUploadStatus {
        UPLOADING,  // 正在上传
        COMPLETED,  // 已经上传完成
        ERROR       // 出错
    }
	/**
	 * 上传令牌
	 */
	@Column(unique = true)
	private String token;

	/**
	 * 总文件大小
	 */
    private Long totalSize=0L;

	/**
	 * 已上传大小
	 */
    private Long uploadedSize=0L;

	/**
	 * 上传状态
	 */
    @Enumerated(EnumType.STRING)
	private FileUploadStatus uploadStatus = FileUploadStatus.UPLOADING ; // 默认是初始状态：上传中

    private String remark; // 备注， 如果上传失败，保存失败原因

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public Long getUploadedSize() {
		return uploadedSize;
	}

	public void setUploadedSize(Long uploadedSize) {
		this.uploadedSize = uploadedSize;
	}

    public FileUploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(FileUploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
