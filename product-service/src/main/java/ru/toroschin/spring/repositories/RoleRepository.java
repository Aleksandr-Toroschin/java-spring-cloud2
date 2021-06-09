package ru.toroschin.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.toroschin.spring.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
