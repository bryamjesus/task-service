package com.code.repository;

import com.code.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Hacemos conexion con db en la cual podemos utilizar
public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Query Method: keyword => findByAtributterOperatorAtributte(Param)
    List<Project> findByNameContaining(String name);

    // @Query -> jpql | nativo sql
    @Query("SELECT p FROM Project p Where p.name LIKE %:name%")
    List<Project> searchByName(String name);

    @Query(value = "SELECT * FROM projects Where name LIKE %:name%", nativeQuery = true)
    List<Project> searchByNameNative(String name);

}
