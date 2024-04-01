package suren.nexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suren.nexus.model.User;

public interface UserRepo  extends JpaRepository<User,Long>
{

}
