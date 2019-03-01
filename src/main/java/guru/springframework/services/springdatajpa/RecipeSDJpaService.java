package guru.springframework.services.springdatajpa;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeSDJpaService implements RecipeService {
    private RecipeRepository recipeRepository;

    public RecipeSDJpaService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> findAll() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        return null;
    }

    @Override
    public Recipe save(Recipe recipe) {
        return null;
    }

    @Override
    public void delete(Recipe recipe) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
