package guru.springframework.services;

import guru.springframework.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {

    Set<Recipe> findAll();

    Recipe findById(Long id);

    Recipe save(Recipe recipe);

    void delete(Recipe recipe);

    void deleteById(Long id);
}
