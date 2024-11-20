package com.taxi.service;

import java.util.List;

import com.taxi.model.ContactForm;

public interface ContactFormService {
	public ContactForm ContactFormServiceData(ContactForm contactForm);
	public List<ContactForm>readAllContactsService();
	public void deleteContactService(int id);

}
