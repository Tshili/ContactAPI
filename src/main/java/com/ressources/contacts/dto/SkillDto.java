/**
 * 
 */
package com.ressources.contacts.dto;

import com.ressources.contacts.utils.Level;

/**
 * @author Bernard MPOY
 *
 */

public class SkillDto {

	private String name;

	private Level level;

	/**
	 * @param name
	 * @param level
	 */
	public SkillDto(String name, Level level) {
		this.name = name;
		this.level = level;
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

}
