package com.first.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "comments_file")
public class CommentsFile {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论id
     */
    @Column(name = "comment_id")
    private Integer commentId;

    /**
     * 评论附件地址
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 附件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 附件原名
     */
    @Column(name = "file_origname")
    private String fileOrigname;

    /**
     * 上传时间
     */
    private Date uploadtime;

    /**
     * 获取唯一标识
     *
     * @return id - 唯一标识
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取评论id
     *
     * @return comment_id - 评论id
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 设置评论id
     *
     * @param commentId 评论id
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取评论附件地址
     *
     * @return file_path - 评论附件地址
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置评论附件地址
     *
     * @param filePath 评论附件地址
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取附件名称
     *
     * @return file_name - 附件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置附件名称
     *
     * @param fileName 附件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取附件原名
     *
     * @return file_origname - 附件原名
     */
    public String getFileOrigname() {
        return fileOrigname;
    }

    /**
     * 设置附件原名
     *
     * @param fileOrigname 附件原名
     */
    public void setFileOrigname(String fileOrigname) {
        this.fileOrigname = fileOrigname;
    }

    /**
     * 获取上传时间
     *
     * @return uploadtime - 上传时间
     */
    public Date getUploadtime() {
        return uploadtime;
    }

    /**
     * 设置上传时间
     *
     * @param uploadtime 上传时间
     */
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }
}