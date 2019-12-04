package edu.wctc.service;

import edu.wctc.GroceryItem;
import edu.wctc.dao.DAO;
import edu.wctc.dao.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public class GroceryItemServiceImpl implements GroceryItemService {
    // inject GroceryItem DAO
    @Autowired
    private DAOImpl groceryItemDAO;

    // inject image saving service
    //@Autowired
    //private ImageFileService imageFileService;

    @Override
    // with @Transactional annotation, no need to begin or commit transaction
    @Transactional
    public List<GroceryItem> getGroceryItem() {
        // Delegate call to DAO
        return groceryItemDAO.getGroceryItem();
    }

    @Override
    @Transactional
    public void saveGroceryItem(GroceryItem theGroceryItem, MultipartFile file, String applicationPath) {
        //String fileName = imageFileService.saveFile(file, applicationPath, theDonut.getShop().getImageDirectory());
        //if (fileName != null) {
        //    theDonut.setImageFilename(fileName);
        //}

        // Delegate call to DAO
        groceryItemDAO.saveGroceryItem(theGroceryItem);

    }

    @Override
    @Transactional
    public GroceryItem getGroceryItem(int theId)
    {
        return groceryItemDAO.getGroceryItem(theId);
    }

    @Override
    @Transactional
    public void deleteGroceryItem(int theId) {
       groceryItemDAO.deleteGroceryItem(theId);
    }

    @Override
    @Transactional
    public List<GroceryItem> getGroceryItemByName(String theSearchTerm) {
      return groceryItemDAO.getGroceryItemByName(theSearchTerm);
    }
}
