package edu.wctc.service;

import edu.wctc.GroceryItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GroceryItemService {
    List<GroceryItem> getGroceryItem();

    void saveGroceryItem(GroceryItem theGroceryItem, MultipartFile file, String applicationPath);

    GroceryItem getGroceryItem(int theId);

    void deleteGroceryItem(int theId);

    List<GroceryItem> getGroceryItemByName(String theSearchTerm);
}
