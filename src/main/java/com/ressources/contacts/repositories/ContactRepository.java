package com.ressources.contacts.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ressources.contacts.entities.Contact;

/**
 * @author Bernard MPOY
 *
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

	Optional<Contact> findByIdUserAndIdContact(String userId, String contactId);

	Optional<List<Contact>> findByIdUser(String userId);

	void deleteByIdUserAndIdContact(String userId, String contactId);

}
