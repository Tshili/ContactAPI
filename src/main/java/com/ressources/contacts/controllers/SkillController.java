/**
 * 
 */
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

import com.ressources.contacts.dto.SkillDto;
import com.ressources.contacts.entities.Skill;
import com.ressources.contacts.services.SkillService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Bernard MPOY
 * 
 * Controller for skills management
 *
 */

@RestController
@RequestMapping("/api/skills")
public class SkillController {

	private SkillService skillService;

	public SkillController(SkillService skillService) {
		super();
		this.skillService = skillService;
	}

	// Create a skill 
	@Operation(summary = "Create a skill", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/create")
	public ResponseEntity<Skill> createSkill(HttpServletRequest request, @RequestBody SkillDto skillDto) {
		String userId = (String) request.getAttribute("userId");
		Skill contact = skillService.createSkill(userId, skillDto);
		return new ResponseEntity<>(contact, HttpStatus.CREATED);
	}

	// Update a skill 
	@Operation(summary = "update a skill", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("update/{skillId}")
	public ResponseEntity<Map<String, Boolean>> updateSkills(HttpServletRequest request,
			@PathVariable("skillId") String skillId, @RequestBody SkillDto skill) {
		String userId = (String) request.getAttribute("userId");
		skillService.updateSkill(userId, skillId, skill);
		Map<String, Boolean> map = new HashMap<>();
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	// Get list of all skills
	@Operation(summary = "get list of all skills ", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("")
	public ResponseEntity<List<Skill>> getAllSkills(HttpServletRequest request) {
		String userId = (String) request.getAttribute("userId");
		List<Skill> skills = skillService.getAllSkills(userId);

		if (skills.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(skills, HttpStatus.OK);
	}

	// Get a specific skill
	@Operation(summary = "Get a specific skill", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/{skillId}")
	public ResponseEntity<Skill> getSkillById(HttpServletRequest request, @PathVariable("skillId") String skillId) {
		String userId = (String) request.getAttribute("userId");
		Skill skill = skillService.getSkillById(userId, skillId);
		return new ResponseEntity<>(skill, HttpStatus.OK);
	}

	// Delete a skill
	@Operation(summary = "Delete a skill", security = @SecurityRequirement(name = "bearerAuth"))
	@DeleteMapping("/{skillId}")
	public ResponseEntity<HttpStatus> deleteSkill(HttpServletRequest request, @PathVariable("skillId") String skillId) {
		String userId = (String) request.getAttribute("userId");
		skillService.deleteSkill(userId, skillId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	

}
