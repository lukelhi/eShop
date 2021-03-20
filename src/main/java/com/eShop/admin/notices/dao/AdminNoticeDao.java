package com.eShop.admin.notices.dao;

import com.eShop.commons.bean.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminNoticeDao {
    List selectNotice();
    Notice selectNoticeById(String notice_id);
    void updateNotice(Notice notice);
    void insertNotice(Notice notice);
    void deleteNotice(Integer id);
}
