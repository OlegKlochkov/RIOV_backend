package ru.example.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.project.dto.SecondObject;
import ru.example.project.repository.SecondObjectRepo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SecondObjectService {

    private final SecondObjectRepo secondObjectRepo;

    @Autowired
    public SecondObjectService(SecondObjectRepo secondObjectRepo) {
        this.secondObjectRepo = secondObjectRepo;
    }

    public SecondObject saveSO(SecondObject secondObject) {
        Optional<SecondObject> secondObjectOptional = Optional.empty();
        if(Objects.nonNull(secondObject.getId())) secondObjectOptional = secondObjectRepo.findById(secondObject.getId());
        if(secondObjectOptional.isEmpty()) {
            return secondObjectRepo.save(secondObject);
        } else {
            return updateSO(secondObject, secondObjectOptional.get());
        }
    }

    public SecondObject getSO(Long id) throws EntityNotFoundException {
        Optional<SecondObject> secondObjectOptional = secondObjectRepo.findById(id);
        if(secondObjectOptional.isEmpty()) throw new EntityNotFoundException();
        return secondObjectOptional.get();
    }

    public List<SecondObject> getAllSO() {
        return secondObjectRepo.findAll();
    }

    public void removeSO(Long id) {
        Optional<SecondObject> secondObjectOptional = secondObjectRepo.findById(id);
        if(secondObjectOptional.isEmpty()) throw new EntityNotFoundException();
        secondObjectRepo.delete(secondObjectOptional.get());
    }

    private SecondObject updateSO(SecondObject secondObject, SecondObject databaseSecondObject) {
        if(Objects.nonNull(secondObject.getName())) databaseSecondObject.setName(secondObject.getName());
        if(Objects.nonNull(secondObject.getProf_id())) databaseSecondObject.setProf_id(secondObject.getProf_id());
        secondObjectRepo.save(databaseSecondObject);
        return databaseSecondObject;
    }

}
