package com.acme.mailreader.presentation;

public class MailInvalideException extends Exception {

	private static final long serialVersionUID = -3371711602820573858L;

	public enum ErreurMail {
		TROP_LONG("Le sujet est trop long");

		private String message;

		ErreurMail(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}

	public MailInvalideException(ErreurMail erreur) {
		super(erreur.getMessage());
	}

}
