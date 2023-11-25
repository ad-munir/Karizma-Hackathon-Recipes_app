import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import PrivateRoute from './PrivateRoute';
import RecipeList from './components/RecipeList';
import AddRecipe from './components/AddRecipe';
import RecipeDetail from './components/RecipeDetail';
import EditRecipe from './components/EditRecipes';
import Login from './components/auth/Login';

const App = () => {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login} />
        <PrivateRoute exact path="/" component={RecipeList} />
        <PrivateRoute path="/recipe/add" component={AddRecipe} />
        <PrivateRoute path="/recipe/:id/edit" component={EditRecipe} />
        <PrivateRoute path="/recipe/:id" component={RecipeDetail} />
      </Switch>
    </Router>
  );
};

export default App;
