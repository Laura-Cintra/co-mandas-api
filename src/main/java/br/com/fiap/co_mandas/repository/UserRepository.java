package br.com.fiap.co_mandas.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.co_mandas.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    // Repositório JPA para a entidade User

    Optional<User> findByEmail(String email);
}