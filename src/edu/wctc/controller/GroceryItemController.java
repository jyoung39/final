package edu.wctc.controller;

import edu.wctc.GroceryItem;
import edu.wctc.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class GroceryItemController {

    @RequestMapping(path = "/mainDishes")
    public String showDishesPage(Model theModel) {
        // Get donuts from service
        List<GroceryItem> groceryItemList = groceryItemService.getGroceryItem();

        // Add the list of donuts to the model
        theModel.addAttribute("groceryItems", groceryItemList);
        return "main_dishesv2";
    }

    // Inject the GroceryItem Service
    @Autowired
    private GroceryItemService groceryItemService;

    @GetMapping("/delete")
    public String deleteGroceryItem(@RequestParam("groceryItemId") int theId) {
        // Delete the groceryItem
        groceryItemService.deleteGroceryItem(theId);

        return "redirect:/mainDishes";
    }

    @RequestMapping("/showAddItemPage")
    public String showAddDonutForm(Model theModel) {
        GroceryItem theGroceryItem = new GroceryItem();

        theModel.addAttribute("groceryItem", theGroceryItem);



        return "add_item_form";
    }

    @RequestMapping("/showUpdateGroceryItemForm")
    public String showUpdateGroceryItemForm(@RequestParam("groceryItemId") int theId,
                                      Model theModel) {
        // Get donut from the database
        GroceryItem theGroceryItem = groceryItemService.getGroceryItem(theId);

        // Set donut as a model attribute to pre-populate the form
        theModel.addAttribute("groceryItem", theGroceryItem);

        //theModel.addAttribute("donutShops", donutShopService.getDonutShops());

        // Return the view
        return "add_item_form";
    }


    @PostMapping("/save")
    public String saveDonut(@RequestParam("image") MultipartFile file,
                            @Valid @ModelAttribute("groceryItem") GroceryItem theItem,
                            BindingResult bindingResult,
                            HttpServletRequest request,
                            Model theModel) {
        // Any validation errors?
        if (bindingResult.hasErrors()) {
            // Display the errors in the console
            System.out.println(bindingResult);

            // Put list of donut shops back in the new model
            //theModel.addAttribute("donutShops", donutShopService.getDonutShops());

            // Send back to form with error messages
            return "add_item_form";
        }

        // Find the complete path of the application
        String applicationPath = request.getServletContext().getRealPath("/");

        // Use the service to save the donut
        groceryItemService.saveGroceryItem(theItem, file, applicationPath);

        // Redirect back to the donut list
        return "redirect:/mainDishes";
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchTerm") String theSearchTerm, Model theModel) {
        List<GroceryItem> groceryItemList = groceryItemService.getGroceryItemByName(theSearchTerm);

        theModel.addAttribute("groceryItems", groceryItemList);
        return "main_dishesv2";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Trim whitespace from all string form parameters read by this controller
        // If the entire string is whitespace, trim it to null
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        e.printStackTrace();
    }
}