package bstu.yashny.nikitayashny_proj.repository;


import bstu.yashny.nikitayashny_proj.models.Role;
import bstu.yashny.nikitayashny_proj.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(Role name);
}
