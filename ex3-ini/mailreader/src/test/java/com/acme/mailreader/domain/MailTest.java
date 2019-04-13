package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Test;

import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.presentation.MailInvalideException;

public class MailTest {
	
	MailComparator comparator = new MailComparator();
	
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1970() throws DateIncorrecteException {
					
	}
	
	@Test
	public final void premierPlusPetitSiDateNulle() throws DateIncorrecteException  {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));
	}

	@Test(expected=MailInvalideException.class)
	public final void sujetTropLong() throws MailInvalideException {
		Mail mail1 = new Mail.Builder("Sujet trop long, Sujet trop long, Sujet trop long, Sujet trop long, Sujet trop long, Sujet trop long, Sujet trop long, Sujet trop long, Sujet trop long, Sujet trop long, Sujet trop long, Sujet trop long, ").build();
	}
	
	@Test
	public final void mailsEgaux() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(mail1.equals(mail2),is(true));	
	}
	
	@Test
	public final void mailsInegaux() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("JOUOJOU").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(mail1.equals(mail2),is(false));	
}

}
