/**
 * 
 */
package com.fs.movie.service.constants;

/**
 * @author fs_ms
 *
 */
public enum Constants {
	
	RESTORE("restore"),
	YES("Yes"),
	NO("No");
	
	private String value ;
	
	Constants(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
