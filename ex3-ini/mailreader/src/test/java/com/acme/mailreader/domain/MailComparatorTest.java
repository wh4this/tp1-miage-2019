package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.MailComparator;
import com.acme.mailreader.domain.Mail.Statut;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail1 = null;
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail mail1 = new Mail();
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test 
	public final void egauxSiMailsEgaux() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(0));	
	}	
	
	@Test
	public final void inferieurSiDateNulle() throws DateIncorrecteException  {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));
	}
	
	@Test 
	public final void inferieurSiSujetNul() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder(null).important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));	
	}
	
	@Test 
	public final void inferieurSiImportanceFalse() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(true).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));	
	}

	@Test
	public final void mailDateRecenteEnPremier() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("sujet").date(Instant.now()).build();
		Mail mail2 = new Mail.Builder("sujet").date(Instant.now().plusSeconds(360000)).build();
		assertThat(comparator.compare(mail1, mail2), is(MailComparator.PREMIER_PLUS_GRAND));		
} 
	
}
