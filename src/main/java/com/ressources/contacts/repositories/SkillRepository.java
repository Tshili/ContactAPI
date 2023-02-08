/**
 * 
 */
package com.ressources.contacts.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ressources.contacts.entities.Skill;

/**
 * @author Bernard MPOY
 *
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {

	Optional<Skill> findByIdUserAndIdSkill(String userId, String skillId);

	Optional<List<Skill>> findByIdUser(String userId);

	void deleteByIdUserAndIdSkill(String userId, String skillId);

	void deleteByIdSkill(String idSkill);

}
