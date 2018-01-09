package org.sid.dao;

import java.util.List;

import org.sid.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
