package com.recette.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "recettes")
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nom;
    @ElementCollection
    List<String> ingredients;
    String etapes;
    int dureePreparation;
    String photo;

    @ManyToOne
    User user;

}
