
package com.ressources.contacts.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;

import com.ressources.contacts.dto.ContactDto;
import com.ressources.contacts.entities.Contact;
import com.ressources.contacts.services.ContactService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Bernard MPOY
 * 
 * Controller for contact management
 *
 */

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

	private ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	// create a contact
	@Operation(summary = "Create a contact", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/create")
	public ResponseEntity<Contact> createContact(HttpServletRequest request, @RequestBody ContactDto contactDto) {
		String userId = (String) request.getAttribute("userId");
		Contact contact = contactService.createContact(userId, contactDto);
		return new ResponseEntity<>(contact, HttpStatus.CREATED);
	}

	// update a contact
	@Operation(summary = "update a contact", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("update/{contactId}")
	public ResponseEntity<Map<String, Boolean>> updateContact(HttpServletRequest request,
			@PathVariable("contactId") String contactId, @RequestBody ContactDto contact) {
		String userId = (String) request.getAttribute("userId");
		contactService.updateContact(userId, contactId, contact);
		Map<String, Boolean> map = new HashMap<>();
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	// Get list of all contact
	@Operation(summary = "get list of all contact", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("")
	public ResponseEntity<List<Contact>> getAllContacts(HttpServletRequest request) {
		String userId = (String) request.getAttribute("userId");
		List<Contact> contacts = contactService.getAllContacts(userId);

		if (contacts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}

	// Get a specific contact
	@Operation(summary = "get specific a contact", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/{contactId}")
	public ResponseEntity<Contact> getCategoryById(HttpServletRequest request,
			@PathVariable("contactId") String contactId) {
		String userId = (String) request.getAttribute("userId");
		Contact contact = contactService.getContactById(userId, contactId);
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}

	// Delete contact
	@Operation(summary = "delete a contact", security = @SecurityRequirement(name = "bearerAuth"))
	@DeleteMapping("/{contactId}")
	public ResponseEntity<HttpStatus> deleteContact(HttpServletRequest request,
			@PathVariable("contactId") String contactId) {
		String userId = (String) request.getAttribute("userId");
		contactService.deleteContactWithAllSkills(userId, contactId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Assign skill to a contact
	@Operation(summary = "Assign skill to a contact", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("/{contactId}/skills/{skillsId}")
	public ResponseEntity<Contact> assignSkillToContact(HttpServletRequest request, @PathVariable String contactId,
			@PathVariable String skillsId) {
		String userId = (String) request.getAttribute("userId");
		Contact contact = contactService.assignSkillToContact(userId, contactId, skillsId);
		return new ResponseEntity<>(contact, HttpStatus.OK);

	}

}
