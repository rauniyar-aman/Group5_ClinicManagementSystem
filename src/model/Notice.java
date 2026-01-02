/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Group 5
 */
public class Notice {

    private int noticeId;
    private String title;
    private Date noticeDate;
    private String content;

    // Empty constructor
    public Notice() {
    }

    // Constructor with all fields
    public Notice(int noticeId, String title, Date noticeDate, String content) {
        this.noticeId = noticeId;
        this.title = title;
        this.noticeDate = noticeDate;
        this.content = content;
    }

    // Constructor without noticeId (for new notices)
    public Notice(String title, Date noticeDate, String content) {
        this.title = title;
        this.noticeDate = noticeDate;
        this.content = content;
    }

    // Getters and Setters
    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
