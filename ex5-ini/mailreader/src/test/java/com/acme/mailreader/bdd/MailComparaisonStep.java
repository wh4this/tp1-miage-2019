package com.acme.mailreader.bdd;

import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import com.acme.mailreader.domain.DateIncorrecteException;
import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.domain.MailComparator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Les steps (actions) du test
 * 
 * <p>
 * A noter qu'une nouvelle instance de cette classe est créée à chaque scenario
 * </p>
 *
 */

public class MailComparaisonStep {

	private Mail mail1;
	private Mail mail2;
	private Mail mail3;
	private TreeSet<Mail> listeMail;
	private String resultatComparaison;
	Comparator<Mail> comparator = new MailComparator();
	private static final Map<Integer, String> resuAsString = new HashMap<Integer, String>();
	static {
		resuAsString.put(MailComparator.PREMIER_PLUS_PETIT , "MAIL1_APRES");
		resuAsString.put(MailComparator.EGAUX, "EGAUX");
		resuAsString.put(MailComparator.PREMIER_PLUS_GRAND, "MAIL1_AVANT");
	}
	
	private static final Map<String, Integer> resuAsInt = new HashMap<String, Integer>();
	static {
		resuAsInt.put("MAIL1_APRES", MailComparator.PREMIER_PLUS_PETIT);
		resuAsInt.put("EGAUX", MailComparator.EGAUX);
		resuAsInt.put("MAIL1_AVANT", MailComparator.PREMIER_PLUS_GRAND);
	}
	

	@Given("^un premier mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_premier_mail(boolean importance, Statut statut,
			String sujet, String date) throws DateIncorrecteException {
		this.mail1 = new Mail.Builder(sujet).date(Instant.parse(date)).important(importance).statut(statut).build();
	}

	@Given("^un second mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_second_mail(boolean importance, Statut statut, String sujet,
			String date) throws DateIncorrecteException {
		this.mail2 = new Mail.Builder(sujet).date(Instant.parse(date)).important(importance).statut(statut).build();
	}
	
	@Given("^un troisième mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_troisieme_mail(boolean importance, Statut statut, String sujet,
			String date) throws DateIncorrecteException {
		this.mail3 = new Mail.Builder(sujet).date(Instant.parse(date)).important(importance).statut(statut).build();
	}

	@When("^je trie$")
	public void je_trie() throws Throwable {
		listeMail = new TreeSet<Mail>(comparator);
		listeMail.add(this.mail1);
		listeMail.add(this.mail2);
		listeMail.add(this.mail3);
	}

	@Then("^le tri doit retourner \"([^\"]*)\"$")
	public void le_tri_doit_retourner(String resu) throws Throwable {
		assertThat(comparator.compare(this.mail1, this.mail2), is(resuAsInt.get(resu)));
		//assertThat(...);
	}
	
	@Then("^le tri des trois mails doit retourner \"([^\"]*)\" \"([^\"]*)\"$")
	public void le_tri_des_trois_mails_doit_retourner(String resu1, String resu2) throws Throwable {
		assertThat(comparator.compare(this.mail1, this.mail2), is(resuAsInt.get(resu1)));
		assertThat(comparator.compare(this.mail2, this.mail3), is(resuAsInt.get(resu2)));
	}
}
