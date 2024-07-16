package com.ttsw.officenotifier.mail;

import com.ttsw.officenotifier.events.MailEvent;

public interface MailService {

    void sendEmail(MailEvent mailEvent);
}
