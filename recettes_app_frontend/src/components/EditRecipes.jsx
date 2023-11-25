import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

const EditRecipe = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [recipe, setRecipe] = useState({
        nom: '',
        etapes: '',
        ingredients: [],
        dureePreparation: 0,
        photo: null,
        idUser: 1,
    });

    useEffect(() => {
        axios.get(`http://localhost:8080/api/recettes/${id}`)
            .then(response => setRecipe(response.data))
            .catch(error => console.error('Error fetching recipe details for editing:', error));
    }, [id]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setRecipe({ ...recipe, [name]: value });
    };

    const handleFileChange = (e) => {
        setRecipe({ ...recipe, photo: e.target.files[0] });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('nom', recipe.nom);
        formData.append('etapes', recipe.etapes);
        recipe.ingredients.forEach((ingredient, index) => formData.append(`ingredients[${index}]`, ingredient));
        formData.append('dureePreparation', recipe.dureePreparation);
        formData.append('photo', recipe.photo);
        formData.append('idUser', recipe.idUser);

        try {
            await axios.put(`http://localhost:8080/api/recettes/${id}`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            // Use navigate to redirect to the recipe details page
            navigate(`/recipe/${id}`);
        } catch (error) {
            console.error('Error editing recipe:', error);
        }
    };

    return (
        <div>
            <h2>Edit Recipe</h2>
            <form onSubmit={handleSubmit}>
                {/* Add form fields for each recipe property */}
                <label>
                    Nom:
                    <input type="text" name="nom" value={recipe.nom} onChange={handleInputChange} />
                </label>
                <br />
                <label>
                    Étapes:
                    <textarea name="etapes" value={recipe.etapes} onChange={handleInputChange} />
                </label>
                <br />
                <label>
                    Ingrédients (comma-separated):
                    <input type="text" name="ingredients" value={recipe.ingredients.join(',')} onChange={handleInputChange} />
                </label>
                <br />
                <label>
                    Durée de préparation (minutes):
                    <input type="number" name="dureePreparation" value={recipe.dureePreparation} onChange={handleInputChange} />
                </label>
                <br />
                <label>
                    Photo:
                    <input type="file" accept=".png, .jpg, .jpeg" name="photo" onChange={handleFileChange} />
                </label>
                <br />
                <button type="submit">Update Recipe</button>
            </form>
        </div>
    );
};

export default EditRecipe;
