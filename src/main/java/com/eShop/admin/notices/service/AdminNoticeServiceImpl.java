package com.eShop.admin.notices.service;

import com.eShop.admin.notices.dao.AdminNoticeDao;
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
}
