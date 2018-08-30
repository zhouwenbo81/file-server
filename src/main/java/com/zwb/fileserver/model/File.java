package com.zwb.fileserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * <p>
 * Title: File
 * </p>
 * <p>
 * Description: 文件实体类---文件数据实体，该类对应MongDB数据库的文档对象
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/8/30 14:21
 */
/**
 * MongoDB数据库由数据库(database)、集合(collection)、文档对象(document)三部分组成，
 * 主要用于地理搜索，零散文件，对事务无要求的数据。
 * @Document(collection = "file") :指定对应的集合
 */
@Document
//@Document(collection = "file")
public class File {

    //文档Id
    @Id
    private String id;

    //文档名称
    private String name;

    //文档内容类型
    private String contentType;

    //文档大小
    private long size;

    //上传时间
    private Date uploadDate;

    //文档MD5值
    private String md5;

    //文件内容
    private byte[] content;

    //文档路径
    private String path;

    //jpa规范
    protected File() {

    }

    public File(String name, String contentType, long size, byte[] content) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }

    public File(String id, String name, String contentType, long size, Date uploadDate, byte[] content) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.uploadDate = uploadDate;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
