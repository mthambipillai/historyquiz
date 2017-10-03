/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.TagCategory;
import java.util.HashMap;
import session.TagCategoryFacade;

/**
 *
 * @author Melkior
 */
public class TagCategoriesWrapper {
    private HashMap<Integer,Boolean> selectedCategories = new HashMap<Integer,Boolean>();
    
    public TagCategoriesWrapper(TagCategoryFacade categories){
        for(TagCategory tc : categories.findAll()){
            selectedCategories.put(tc.getTagCategoryId(), false);
        }
    }
    
    public void categorySelectedChange(int tagCategoryId){
        boolean isSelected = selectedCategories.get(tagCategoryId);
        selectedCategories.put(tagCategoryId, !isSelected);
    }
    
    public boolean getIsSelected(int tagCategoryId){
        return selectedCategories.get(tagCategoryId);
    }
}
