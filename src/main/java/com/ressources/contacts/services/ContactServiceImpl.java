
package com.ressources.contacts.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ressources.contacts.dto.ContactDto;
import com.ressources.contacts.dto.SkillDto;
import com.ressources.contacts.entities.Contact;
import com.ressources.contacts.entities.Skill;
import com.ressources.contacts.exceptions.ResourceNotFoundException;
import com.ressources.contacts.repositories.ContactRepository;
import com.ressources.contacts.repositories.SkillRepository;

/**
 * @author Bernard MPOY
 *
 */

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	private ContactRepository contactRepository;
	private SkillRepository skillRepository;

	public ContactServiceImpl(ContactRepository contactRepository, SkillRepository skillRepository) {
		this.contactRepository = contactRepository;
		this.skillRepository = skillRepository;
	}

	@Override
	public Contact createContact(String userId, ContactDto contactDto) {

		Contact contact = new Contact();
		contact.setIdUser(userId);
		contact.setFullName(contactDto.getFullName());
		contact.setAddress(contactDto.getAddress());
		contact.setEmail(contactDto.getEmail());
		contact.setFirstName(contactDto.getFirstName());
		contact.setLastName(contactDto.getLastName());
		contact.setMobilePhoneNumber(contactDto.getMobilePhoneNumber());

		Set<Skill> skillSet = new HashSet<Skill>();
		if (!contactDto.getSkills().isEmpty()) {

			for (SkillDto skill : contactDto.getSkills()) {

				skillSet.add(new Skill(userId, skill.getName(), skill.getLevel()));
			}

		}

		contact.setSkills(skillSet);
		return contactRepository.save(contact);

	}

	@Override
	public List<Contact> getAllContacts(String userId) {

		List<Contact> contacts = contactRepository.findByIdUser(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Contacts with userId = " + userId));

		return contacts;
	}

	@Override
	public Contact getContactById(String userId, String contactId) {
		Contact contact = contactRepository.findByIdUserAndIdContact(userId, contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Contact with id = " + contactId));

		return contact;
	}

	@Override
	public void updateContact(String userId, String contactId, ContactDto contactDto) {

		Contact contactToUpdate = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Contact with id = " + contactId));
		Set<Skill> skillSet = new HashSet<Skill>();

		if (contactToUpdate != null) {
			contactToUpdate.setIdUser(userId);
			contactToUpdate.setAddress(contactDto.getAddress());
			contactToUpdate.setEmail(contactDto.getEmail());
			contactToUpdate.setFirstName(contactDto.getFirstName());
			contactToUpdate.setFullName(contactDto.getFullName());
			contactToUpdate.setLastName(contactDto.getLastName());
			contactToUpdate.setMobilePhoneNumber(contactDto.getMobilePhoneNumber());

			if (!contactDto.getSkills().equals(contactToUpdate.getSkills())) {

				for (Skill skill : contactToUpdate.getSkills()) {
					skillRepository.deleteByIdSkill(skill.getIdSkill());
				}

				if (!contactDto.getSkills().isEmpty()) {

					for (SkillDto skill : contactDto.getSkills()) {

						skillSet.add(new Skill(userId, skill.getName(), skill.getLevel()));
					}

				}

			}

			contactToUpdate.setSkills(skillSet);
			contactRepository.save(contactToUpdate);

		}

	}

	@Override
	public void deleteContactWithAllSkills(String userId, String contactId) {
		contactRepository.deleteByIdUserAndIdContact(userId, contactId);

	}

	public Contact assignSkillToContact(String userId, String contactId, String skillsID) {
		Set<Skill> skillSet = null;

		Contact contact = contactRepository.findByIdUserAndIdContact(userId, contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Contact with id = " + contactId));

		Skill skill = skillRepository.findByIdUserAndIdSkill(userId, skillsID)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Skill with id = " + skillsID));

		skillSet = contact.getSkills();
		skillSet.add(skill);
		contact.setSkills(skillSet);
		return contactRepository.save(contact);
	}

}
