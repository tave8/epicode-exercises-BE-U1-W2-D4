package giuseppetavella.entities;

import giuseppetavella.enums.ProductCategory;

import java.util.*;

public class ProductCategoryEntity {
    public static Set<ProductCategory> getMissingProductCategoriesFrom(Set<ProductCategory> existingCategories) {
        Set<ProductCategory> ret = new HashSet<>();
        Set<ProductCategory> allCategories = Set.of(ProductCategory.values());
        for(ProductCategory category : allCategories) {
            // if a category is not in the existing categories,
            // then it's a missing category
            if(!existingCategories.contains(category)) {
                ret.add(category);
            }
        }
        return ret;
    }

    // TODO 2026-03-19: when I'm not interested in a type, I can mark it as ?
    public static Set<ProductCategory> getMissingProductCategoriesFromMap(Map<ProductCategory, ?> sourceMap) {
        // Category<ProductCategory, ?> cat = new Category<>();
        // return Collections.singleton(cat.getMissingFromMap((Map<ProductCategory, ?>) sourceMap.keySet()));
        
        return getMissingProductCategoriesFrom(sourceMap.keySet());
    }
    
}
