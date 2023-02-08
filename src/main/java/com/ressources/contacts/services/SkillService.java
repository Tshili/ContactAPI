/**
 * 
 */
package com.ressources.contacts.services;

import java.util.List;

import com.ressources.contacts.dto.SkillDto;

import com.ressources.contacts.entities.Skill;

/**
 * @author Bernard MPOY
 *
 */
public interface SkillService {

	List<Skill> getAllSkills(String userID);

	Skill getSkillById(String userId, String skillId);

	Skill createSkill(String userID, SkillDto skillDto);

	void deleteSkill(String userId, String skillId);

	void updateSkill(String userId, String skillId, SkillDto skillDto);

}
