package bstu.yashny.nikitayashny_proj.repository;

import bstu.yashny.nikitayashny_proj.exception.RepositoryException;
import bstu.yashny.nikitayashny_proj.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Modifying
    void deleteById(int id)throws RepositoryException;

    @Modifying
    void deleteByName(String name)throws RepositoryException;

    Car getById(Long id);

    Car getByName(String name)throws RepositoryException;

    boolean existsByName(String name)throws RepositoryException;

    @Modifying
    @Query("update Car c set c.name=:name, c.description=:description, c.cost=:cost where c.id=:id")
    void updateCarById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("cost") int cost
    )throws RepositoryException;
}
