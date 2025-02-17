package loja.productservice.repository.category;

import loja.productservice.entity.category.CategoryModel;
import loja.productservice.entity.category.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    Optional<CategoryModel> findByCategoryName(CategoryName categoryName);
}
