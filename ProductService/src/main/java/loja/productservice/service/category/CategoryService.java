package loja.productservice.service.category;

import loja.productservice.entity.category.CategoryModel;
import loja.productservice.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public String addCategory(CategoryModel category){
        category.setCategoryName(category.getCategoryName().toUpperCase());
        return repository.save(category).getCategoryName();
    }
    public Optional<CategoryModel> findByName(String name){
        return repository.findByCategoryName(name);
    }

    public List<CategoryModel> findAll (){
        return repository.findAll();
    }
    public void remove(Long id){
        repository.deleteById(id);
    }
}
