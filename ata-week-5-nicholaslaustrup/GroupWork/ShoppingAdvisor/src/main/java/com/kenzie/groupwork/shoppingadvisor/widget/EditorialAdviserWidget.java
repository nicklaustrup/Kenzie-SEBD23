package com.kenzie.groupwork.shoppingadvisor.widget;

import com.kenzie.groupwork.shoppingadvisor.client.EditorialServiceClient;
import com.kenzie.groupwork.shoppingadvisor.model.ShoppingAdviserProduct;
import com.kenzie.groupwork.shoppingadvisor.model.ShoppingContext;
import com.kenzie.groupwork.shoppingadvisor.resources.AmazonsChoiceProduct;
import com.kenzie.groupwork.shoppingadvisor.resources.EditorialRecommendedProduct;

import java.util.ArrayList;
import java.util.List;

public class EditorialAdviserWidget extends ShoppingAdviserWidget{

    private EditorialServiceClient editorialServiceClient;

    public EditorialAdviserWidget(EditorialServiceClient editorialServiceClient, ShoppingContext context) {
        super("Editorial Recommendations", context);
        this.editorialServiceClient = editorialServiceClient;
    }

    @Override
    public List<ShoppingAdviserProduct> getAdvisedProducts() {
        ShoppingContext shoppingContext = getShoppingContext();
       List<EditorialRecommendedProduct> list = this.editorialServiceClient.getEditorialRecommendedProducts(shoppingContext.getSearchTerm(), shoppingContext.getSearchCategory(), shoppingContext.getMarketplaceId());
       List<ShoppingAdviserProduct> shoppingAdviserProductList = new ArrayList<>();
       for (EditorialRecommendedProduct editorialRecommendedProduct : list) {
            shoppingAdviserProductList.add(new ShoppingAdviserProduct(editorialRecommendedProduct.getRecommendation(), editorialRecommendedProduct.getProduct()));
        }

        return shoppingAdviserProductList;
    }
}
