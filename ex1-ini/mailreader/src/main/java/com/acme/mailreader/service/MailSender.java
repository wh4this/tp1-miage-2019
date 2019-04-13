package com.acme.mailreader.service;

import com.acme.mailreader.domain.Mail;

public interface MailSender {
	
	void envoyerMail(Mail mail);

}
