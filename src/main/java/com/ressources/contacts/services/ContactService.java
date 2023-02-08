/**
 * 
 */
package com.ressources.contacts.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ressources.contacts.dto.ContactDto;
import com.ressources.contacts.entities.Contact;

/**
 * @author Bernard MPOY
 *
 */

@Service
public interface ContactService {

	List<Contact> getAllContacts(String userID);

	Contact getContactById(String userId, String contactId);

	Contact createContact(String userID, ContactDto contactDto);

	void deleteContactWithAllSkills(String userId, String contactId);

	void updateContact(String userId, String contactId, ContactDto contactDto);
	
	Contact assignSkillToContact(String userId, String contactId,String skillsID);

}
