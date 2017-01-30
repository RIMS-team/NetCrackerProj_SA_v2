package com.xvitcoder.springmvcangularjs.service.impl;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcMailSettings;
import com.xvitcoder.springmvcangularjs.model.MailSettings;
import com.xvitcoder.springmvcangularjs.service.MailSettingsService;

/**
 * Created by Stas on 29.01.2017.
 */
public class MailSettingsServiceImpl implements MailSettingsService {

    private JdbcMailSettings mailSettingsDao;

    public void setMailSettingsDao(JdbcMailSettings mailSettingsDao) {
        this.mailSettingsDao = mailSettingsDao;
    }

    @Override
    public MailSettings getMailSettings() {
        return mailSettingsDao.getMailSettings();
    }

    @Override
    public void updateMailSettings(MailSettings mailSettings) {
        mailSettingsDao.updateMailSettings(mailSettings);
    }
}
