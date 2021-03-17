package com.eShop.admin.notices.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminNoticeDao {
    List selectNotice();
}
