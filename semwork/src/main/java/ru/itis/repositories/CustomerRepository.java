package ru.itis.repositories;

import java.util.Optional;


public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);
}
