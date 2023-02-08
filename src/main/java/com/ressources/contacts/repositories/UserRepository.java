/**
 * 
 */
package com.ressources.contacts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ressources.contacts.entities.User;

/**
 * @author Bernard MPOY
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByEmailAndPassword(String email, String password);

	Optional<User> findByEmail(String email);

}
