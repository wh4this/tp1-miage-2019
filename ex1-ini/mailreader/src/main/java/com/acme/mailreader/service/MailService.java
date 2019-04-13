package com.acme.mailreader.service;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.presentation.MailInvalideException;
import com.acme.mailreader.presentation.MailInvalideException.ErreurMail;

public class MailService {
	
	private static final int TAILLE_MAX_S = 20;
	private MailSender sender;
	
	public MailService() {
	}
	
	public void envoyerMail(Mail mail) throws MailInvalideException {
		if (mail.getSujet().length() > TAILLE_MAX_S){
			throw new MailInvalideException(ErreurMail.TROP_LONG);
		}
		sender.envoyerMail(mail);

	}
}
