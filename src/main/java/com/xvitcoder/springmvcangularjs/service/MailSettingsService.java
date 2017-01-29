package com.xvitcoder.springmvcangularjs.service;

import com.xvitcoder.springmvcangularjs.model.MailSettings;

/**
 * Created by Stas on 29.01.2017.
 */
public interface MailSettingsService {
    MailSettings getMailSettings();
    void updateMailSettings(MailSettings mailSettings);
}
