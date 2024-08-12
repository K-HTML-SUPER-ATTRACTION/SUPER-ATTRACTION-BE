package com.springboot.khtml.repository;

        import com.springboot.khtml.entity.User;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    User getByEmail(String email);
    User findByEmailAndConfirmationCode(String email, String confirmationCode);
    User findByEmailAndVerifiedTrue(String email);
    boolean existsByEmail(String userId);
}
