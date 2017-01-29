package com.xvitcoder.springmvcangularjs.dao;

import com.xvitcoder.springmvcangularjs.model.MailSettings;

/**
 * Created by Stas on 29.01.2017.
 */
public interface MailSettingsDao {
    MailSettings getMailSettings();
    void updateMailSettings(MailSettings mailSettings);
}
