/**
 * 
 */
package com.fs.movie.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fs.movie.service.model.User;

/**
 * @author fs_ms
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
