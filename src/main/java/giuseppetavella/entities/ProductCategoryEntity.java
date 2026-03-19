package giuseppetavella.entities;

import giuseppetavella.enums.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductCategoryEntity {
    public static List<ProductCategory> getMissingProductCategoriesFrom(Set<ProductCategory> existingCategories) {
        List<ProductCategory> ret = new ArrayList<>();
        List<ProductCategory> allCategories = List.of(ProductCategory.values());
        for(ProductCategory category : allCategories) {
            // if a category is not in the existing categories,
            // then it's a missing category
            if(!existingCategories.contains(category)) {
                ret.add(category);
            }
        }
        return ret;
    }
}
