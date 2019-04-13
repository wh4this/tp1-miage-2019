package com.acme.mailreader.utils;

import java.util.Comparator;

import com.acme.mailreader.domain.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element
 * le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {

	public static final int EGAUX = 0;
	public static final int PREMIER_INFERIEUR = 1;
	public static final int PREMIER_SUPERIEUR = -1;

	public int compare(Mail mail1, Mail mail2) {
		if (isNull(mail1) || isNull(mail2)) {
			return EGAUX;
		}

		if (differentImportance(mail1, mail2)) {
			return compareImportance(mail1, mail2);
		}

		if (differentStatut(mail1, mail2)) {
			return compareStatut(mail1, mail2);
		}

		if (differentSujet(mail1, mail2)) {
			return compareSujet(mail1, mail2);
		}

		return compareDate(mail1, mail2);
	}

	private static boolean isNull(Mail mail) {
		return mail == null;
	}

	private static boolean differentImportance(Mail mail1, Mail mail2) {
		return mail1.isImportant() != mail2.isImportant();
	}

	private static boolean differentSujet(Mail mail1, Mail mail2) {
		return mail1.getSujet() != mail2.getSujet();
	}

	private static boolean differentStatut(Mail mail1, Mail mail2) {
		return mail1.getStatut() != mail2.getStatut();
	}

	private static int compareImportance(Mail mail1, Mail mail2) {
		if (mail1.isMoreImportantThan(mail2)) {
			return PREMIER_SUPERIEUR;
		} else {
			return PREMIER_INFERIEUR;
		}
	}

	private static int compareStatut(Mail mail1, Mail mail2) {
		int comp = mail1.getStatut().ordinal() - mail2.getStatut().ordinal();
		return comp > 0 ? -1 : 1;
	}

	private static int compareSujet(Mail mail1, Mail mail2) {
		return mail2.getSujet().compareTo(mail1.getSujet());
	}

	private static int compareDate(Mail mail1, Mail mail2) {
		return mail2.getDate().compareTo(mail1.getDate());
	}
}
