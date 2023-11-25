package com.recette.app.service;

import com.recette.app.entity.Recette;
import com.recette.app.exception.errors.CustomException;
import com.recette.app.repository.RecetteRepository;
import com.recette.app.repository.UserRepository;
import jakarta.persistence.ElementCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class RecetteService {

    @Autowired
    RecetteRepository recetteRepo;
    @Autowired
    UserRepository userRepo;



    public Recette addRecette(
             String nom,
             String etapes,
             List<String> ingredients,
             int dureePreparation,
             MultipartFile photo,
             Long idUser

    ) throws IllegalStateException, IOException {

        Recette recette = new Recette(null, nom, ingredients, etapes, dureePreparation, "", userRepo.findById(idUser).get());
        recette = recetteRepo.save(recette);

        String path = "src/main/resources/static/photos/" + recette.getId() + ".png";
        photo.transferTo(Path.of(path));

        String url = "http://localhost:8080/api/recettes/photos/"+recette.getId();
        recette.setPhoto(url);

        return recetteRepo.save(recette);
    }

    public List<Recette> getAllRecettes() {
        return recetteRepo.findAll();
    }

    public Recette getRecetteById(Long id) {
        return recetteRepo.findById(id)
                .orElseThrow(() -> new CustomException("Recette non trouvée avec l' pour etre mise a jour : " + id));
    }

    public Recette updateRecette(Recette recette) {
        if (recetteRepo.existsById(recette.getId())) {
            return recetteRepo.save(recette);
        } else {
            throw new CustomException("Recette non trouvée avec l'ID : " + recette.getId());
        }
    }

    public void deleteRecette(Long id) {
        recetteRepo.deleteById(id);
    }
}