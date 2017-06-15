package com.hz.school.model;




import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 附件表
 */
@Entity
@Table(name = Constant.DB_PREFIX + "attachment")
public class Attachment extends BaseEntity implements Serializable {

    /*
    增加附件类型,需要修改:
    1: AttachmentUtil.java
     */
    public enum FileType{
        other                                    //其他附件

    }


	private String path;//本地存放路径。
	//文件类型名
    private String fieldName;
    //文件大小
    private Long fileSize; 
    
    //文件名
	private String fileName;
	
	//文件md5值。
	private String hash;

    private String uuid;
	
	private boolean downloaded = false;

    //新的存放方式
    private boolean migration = true;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    /**
     * 根据当前附件返回一个File对象
     * @return
     */
   /* public  File getFile(){
        File file = null;
        if(isMigration()){
            file = getMigrationFile();
        }else {
            String path = getPath();
            String attachmentPath = SSMConfig.getProperty("am.upload.root");
            file = new File(attachmentPath, path);
            //如果文件不存在就去qualification目录找
            if (!file.exists()) {
                file = new File(SSMConfig.getProperty("am.attachment.path"), path);
            }
            if (!file.exists()) {
                file = new File(SSMConfig.getProperty("am.qualification.path"), path);
            }
        }
        return file;
    }

    private File getMigrationFile(){
        String path = getPath();
        File file = new File(SSMConfig.getProperty("root"), path);
        if(!file.exists()){
            file =  new File( SSMConfig.getProperty("ftpRoot"), path);
        }
        return file;
    }*/

	public boolean isDownloaded() {
		return downloaded;
	}
	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isMigration() {
        return migration;
    }

    public void setMigration(boolean migration) {
        this.migration = migration;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
