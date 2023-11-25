package com.recette.app.controller;

import com.recette.app.entity.Recette;
import com.recette.app.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/recettes")
public class RecetteController {

    @Autowired
    private RecetteService recetteService;

    @PostMapping
    public ResponseEntity<Recette> addRecette(

            @RequestParam String nom,
            @RequestParam String etapes,
            @RequestParam List<String> ingredients,
            @RequestParam int dureePreparation,
            @RequestParam MultipartFile photo,
            @RequestParam Long idUser
    ) throws IOException {
        Recette newRecette = recetteService.addRecette(
                nom,
                etapes,
                ingredients,
                dureePreparation,
                photo,
                idUser);
        return new ResponseEntity<>(newRecette, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Recette>> getAllRecettes() {
        List<Recette> recettes = recetteService.getAllRecettes();
        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recette> getRecetteById(@PathVariable Long id) {
        Recette recette = recetteService.getRecetteById(id);
        return new ResponseEntity<>(recette, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recette> updateRecette(@PathVariable Long id, @RequestBody Recette recette) {
        recette.setId(id);
        Recette updatedRecette = recetteService.updateRecette(recette);
        return new ResponseEntity<>(updatedRecette, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecette(@PathVariable Long id) {
        recetteService.deleteRecette(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/photos/{id}")
    public ResponseEntity<Resource> getPhotos(@PathVariable Long id) {

        String filePath = "src/main/resources/static/photos/" + id + ".png";

        try {
            // Load the file as a resource
            Resource resource = new UrlResource(Paths.get(filePath).toUri());

            // Check if the file exists and is readable
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "image/png")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle exceptions, e.g., file not found, and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
