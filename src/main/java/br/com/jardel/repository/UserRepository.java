package br.com.jardel.repository;

import br.com.jardel.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserName(String userName);
}
