package com.eShop.admin.notices.service;

import com.eShop.admin.notices.dao.AdminNoticeDao;
import com.eShop.commons.bean.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminNoticeServiceImpl implements AdminNoticeService {
    @Resource
    AdminNoticeDao adminNoticeDao;

    @Override
    public List listNotice() {
        return adminNoticeDao.selectNotice();
    }

    @Override
    public Notice selectNoticeById(String notice_id) {
        return adminNoticeDao.selectNoticeById(notice_id);
    }

    @Override
    public void editNotice(Notice notice) {
        adminNoticeDao.updateNotice(notice);
    }

    @Override
    public void addNotice(Notice notice) {
        adminNoticeDao.insertNotice(notice);
    }
}
