/**
 * 
 */
package com.ressources.contacts.services;

import com.ressources.contacts.entities.User;

/**
 * @author Bernard MPOY
 *
 */
public interface UserService {

	User validateUser(String email, String password);

	User registerUser(String firstName, String lastName, String email, String password);

}
