package srinivasan.nexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import srinivasan.nexus.model.User;

public interface UserRepo  extends JpaRepository<User,Long>
{
    User findByEmail(String username);
}
