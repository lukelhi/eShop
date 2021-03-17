package com.eShop.admin.notices.service;

import com.eShop.commons.bean.Notice;

import java.util.List;

public interface AdminNoticeService {
    List listNotice();
    Notice selectNoticeById(String notice_id);
    void editNotice(Notice notice);
}
