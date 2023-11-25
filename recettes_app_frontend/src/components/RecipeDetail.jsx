import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const RecipeDetail = () => {
    const { id } = useParams();
    const [recipe, setRecipe] = useState(null);

    useEffect(() => {
        // Fetch recipe details from the API
        axios.get(`http://localhost:8080/api/recettes/${id}`)
            .then(response => setRecipe(response.data))
            .catch(error => console.error('Error fetching recipe details:', error));
    }, [id]);

    if (!recipe) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>{recipe.nom}</h2>
            <p>Étapes: {recipe.etapes}</p>
            <p>Ingrédients: {recipe.ingredients.join(', ')}</p>
            <p>Durée de préparation: {recipe.dureePreparation} minutes</p>
            <Link to={`/recipe/${id}/edit`}>Edit Recipe</Link>
        </div>
    );
};

export default RecipeDetail;
