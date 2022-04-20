/**
 * 
 */
package com.fs.movie.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fs.movie.service.model.User;

/**
 * @author fs_ms
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailId(String username);

    Boolean existsByEmailId(String username);
}
