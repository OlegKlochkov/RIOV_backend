package ru.example.project.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.example.project.dto.SecondObject;
import ru.example.project.service.SecondObjectService;

import java.util.List;

@Controller
// Установка основного пути
@RequestMapping("second/object")
@CrossOrigin
public class SecondObjectController {

    private final SecondObjectService secondObjectService;

    @Autowired
    public SecondObjectController(SecondObjectService secondObjectService) {
        this.secondObjectService = secondObjectService;
    }

    // придаточный путь (пример итога: second/object/12)
    @GetMapping("/{id}")
    public ResponseEntity<SecondObject> getSO(@PathVariable Long id) {
        try {
            SecondObject request = secondObjectService.getSO(id);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<SecondObject>> getAllSO() {
        try {
            List<SecondObject> request = secondObjectService.getAllSO();
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<SecondObject> createSO(@RequestBody SecondObject newSecondObject) {
        try {
            SecondObject secondObject = secondObjectService.saveSO(newSecondObject);
            return new ResponseEntity<>(secondObject, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecondObject> updateSO(@PathVariable Long id, @RequestBody SecondObject newSecondObject) {
        try {
            newSecondObject.setId(id);
            SecondObject secondObject = secondObjectService.saveSO(newSecondObject);
            return new ResponseEntity<>(secondObject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSO(@PathVariable Long id) {
        try {
            secondObjectService.removeSO(id);
            return new ResponseEntity<>("Request was deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
