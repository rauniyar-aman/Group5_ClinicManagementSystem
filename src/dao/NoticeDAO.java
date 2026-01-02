/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Notice;

/**
 *
 * @author Group 5
 */
public class NoticeDAO {

    private final MySqlConnection db = new MySqlConnection();

    private final String INSERT_NOTICE = "INSERT INTO notice (title, notice_date, content) VALUES (?, ?, ?)";
    private final String SELECT_ALL_NOTICES = "SELECT notice_id, title, notice_date, content FROM notice ORDER BY notice_date DESC";
    private final String SELECT_NOTICE_BY_ID = "SELECT notice_id, title, notice_date, content FROM notice WHERE notice_id = ?";

    /**
     * Insert a new notice into the database
     * 
     * @param notice The Notice object containing title, date, and content
     * @return true if insertion was successful, false otherwise
     */
    public boolean insertNotice(Notice notice) {
        try (Connection conn = db.openconnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_NOTICE)) {

            stmt.setString(1, notice.getTitle());
            stmt.setDate(2, new java.sql.Date(notice.getNoticeDate().getTime()));
            stmt.setString(3, notice.getContent());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting notice: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieve all notices from the database, sorted by date descending
     * 
     * @return List of all Notice objects
     */
    public List<Notice> getAllNotices() {
        List<Notice> notices = new ArrayList<>();

        try (Connection conn = db.openconnection();
                PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_NOTICES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Notice notice = new Notice();
                notice.setNoticeId(rs.getInt("notice_id"));
                notice.setTitle(rs.getString("title"));
                notice.setNoticeDate(new java.util.Date(rs.getDate("notice_date").getTime()));
                notice.setContent(rs.getString("content"));

                notices.add(notice);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving notices: " + e.getMessage());
            e.printStackTrace();
        }

        return notices;
    }

    /**
     * Retrieve a specific notice by ID
     * 
     * @param noticeId The ID of the notice to retrieve
     * @return Notice object if found, null otherwise
     */
    public Notice getNoticeById(int noticeId) {
        Notice notice = null;

        try (Connection conn = db.openconnection();
                PreparedStatement stmt = conn.prepareStatement(SELECT_NOTICE_BY_ID)) {

            stmt.setInt(1, noticeId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    notice = new Notice();
                    notice.setNoticeId(rs.getInt("notice_id"));
                    notice.setTitle(rs.getString("title"));
                    notice.setNoticeDate(new java.util.Date(rs.getDate("notice_date").getTime()));
                    notice.setContent(rs.getString("content"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving notice by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return notice;
    }
}
