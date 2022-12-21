package ru.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.project.dto.SecondObject;

import java.util.List;

@Repository
public interface SecondObjectRepo extends CrudRepository<SecondObject, Long> {

    List<SecondObject> findAll();

}
