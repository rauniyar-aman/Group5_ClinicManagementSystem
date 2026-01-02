/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.NoticeDAO;
import model.Notice;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Group 5
 */
public class NoticeController {

    private final NoticeDAO noticeDAO;

    public NoticeController() {
        noticeDAO = new NoticeDAO();
    }

    /**
     * Post a new notice to the database
     * 
     * @param title   The title of the notice
     * @param date    The date of the notice
     * @param content The content/body of the notice
     * @return true if the notice was posted successfully, false otherwise
     */
    public boolean postNotice(String title, Date date, String content) {

        // Validate inputs
        if (title == null || title.trim().isEmpty()) {
            System.err.println("Notice title cannot be empty");
            return false;
        }

        if (date == null) {
            System.err.println("Notice date cannot be null");
            return false;
        }

        if (content == null || content.trim().isEmpty()) {
            System.err.println("Notice content cannot be empty");
            return false;
        }

        // Create Notice object and insert via DAO
        Notice notice = new Notice(title.trim(), date, content.trim());
        return noticeDAO.insertNotice(notice);
    }

    /**
     * Fetch all notices from the database
     * 
     * @return List of all Notice objects
     */
    public List<Notice> fetchAllNotices() {
        return noticeDAO.getAllNotices();
    }

    /**
     * Fetch a specific notice by ID
     * 
     * @param noticeId The ID of the notice to fetch
     * @return Notice object if found, null otherwise
     */
    public Notice fetchNoticeById(int noticeId) {
        if (noticeId <= 0) {
            System.err.println("Invalid notice ID");
            return null;
        }

        return noticeDAO.getNoticeById(noticeId);
    }
}
