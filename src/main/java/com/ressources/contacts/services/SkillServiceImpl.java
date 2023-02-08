/**
 * 
 */
package com.ressources.contacts.services;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ressources.contacts.dto.SkillDto;
import com.ressources.contacts.entities.Skill;
import com.ressources.contacts.exceptions.ResourceNotFoundException;
import com.ressources.contacts.repositories.SkillRepository;

/**
 * @author Bernard MPOY
 *
 */

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	private SkillRepository skillRepository;
	
	public SkillServiceImpl(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}

	@Override
	public Skill createSkill(String userID, SkillDto skillDto) {
		Skill skill = new Skill();
		skill.setIdUser(userID);
		skill.setLevel(skillDto.getLevel());
		skill.setName(skillDto.getName());
		return skillRepository.save(skill);
	}

	@Override
	public List<Skill> getAllSkills(String userId) {
		List<Skill> skills = skillRepository.findByIdUser(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Contacts with userId = " + userId));
		return skills;
	}

	@Override
	public Skill getSkillById(String userId, String skillId) {
		Skill skill = skillRepository.findByIdUserAndIdSkill(userId, skillId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found skill with id = " + skillId));

		return skill;
	}

	@Override
	public void updateSkill(String userId, String skillId, SkillDto skillDto) {

		Skill skillToUpdate = skillRepository.findById(skillId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found skill with id = " + skillId));

		if (skillToUpdate != null) {
			skillToUpdate.setIdUser(userId);
			skillToUpdate.setName(skillDto.getName());
			skillToUpdate.setLevel(skillDto.getLevel());
			skillRepository.save(skillToUpdate);
		}
	}

	@Override
	public void deleteSkill(String userId, String skillId) {
		skillRepository.deleteByIdUserAndIdSkill(userId, skillId);
	}

	

}
