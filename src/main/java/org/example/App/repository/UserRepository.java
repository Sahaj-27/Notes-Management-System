package org.example.App.repository;

import java.util.Optional;
import java.util.UUID;

import org.example.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmailId(String emailId);

}
