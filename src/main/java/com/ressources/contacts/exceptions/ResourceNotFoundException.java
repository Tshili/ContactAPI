/**
 * 
 */
package com.ressources.contacts.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author Bernard MPOY
 * 
 * Exception thrown when the resource is not found
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message ) {
		super(message);
	}
	

}
