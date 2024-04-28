package main;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;

public interface UserRepository extends JpaRepository<User, Long> {
}
