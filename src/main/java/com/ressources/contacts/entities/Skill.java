/**
 * 
 */
package com.ressources.contacts.entities;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ressources.contacts.utils.Level;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author Bernard MPOY
 *
 */

@Entity
@Table(name = "skills")
public class Skill {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "skill_id")
	private String idSkill;

	@Column(name = "user_id")
	// @JsonIgnore
	private String idUser;

	@Column(name = "name")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "level")
	private Level level;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "skills")
	@JsonIgnore
	private Set<Contact> contacts = new HashSet<>();

	public Skill() {

	}

	/**
	 * @param idSkill
	 * @param idUser
	 * @param name
	 * @param level
	 */
	public Skill(String idSkill, String idUser, String name, Level level) {
		this.idSkill = idSkill;
		this.idUser = idUser;
		this.name = name;
		this.level = level;
	}

	/**
	 * @param idUser
	 * @param name
	 * @param level
	 */
	public Skill(String idUser, String name, Level level) {

		this.idUser = idUser;
		this.name = name;
		this.level = level;
	}

	/**
	 * @return the idSkill
	 */
	public String getIdSkill() {
		return idSkill;
	}

	/**
	 * @param idSkill the idSkill to set
	 */
	public void setIdSkill(String idSkill) {
		this.idSkill = idSkill;
	}

	/**
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * @return the contacts
	 */
	public Set<Contact> getContacts() {
		return contacts;
	}

	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

}
