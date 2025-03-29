package com.kenzie.groupwork.shoppingadvisor;

import com.kenzie.groupwork.shoppingadvisor.client.AmazonSearchServiceClient;
import com.kenzie.groupwork.shoppingadvisor.client.AmazonsChoiceServiceClient;
import com.kenzie.groupwork.shoppingadvisor.client.EditorialServiceClient;
import com.kenzie.groupwork.shoppingadvisor.model.ShoppingContext;
import com.kenzie.groupwork.shoppingadvisor.resources.SearchCategory;
import com.kenzie.groupwork.shoppingadvisor.widget.AmazonsChoiceAdviserWidget;
import com.kenzie.groupwork.shoppingadvisor.widget.EditorialAdviserWidget;
import com.kenzie.groupwork.shoppingadvisor.widget.ShoppingAdviserWidget;

public class ShoppingAdviserWidgetTestRenderer {

    /**
     * Returns the renderable content for a widget in String format.
     * @param widget the widget to render
     * @return A String containing the renderable content of a widget
     */
    public String getRenderableContent(ShoppingAdviserWidget widget) {
        return (widget.getSimpleRendering());
    }

    public static void main(String[] args) {

        ShoppingAdviserWidgetTestRenderer renderer = new ShoppingAdviserWidgetTestRenderer();

        AmazonsChoiceAdviserWidget amazonsChoiceAdviserWidget = new AmazonsChoiceAdviserWidget(
            new ShoppingContext("grill", SearchCategory.HOME_AND_GARDEN, "ATVPDKIKX0DER"),
            new AmazonsChoiceServiceClient(), new AmazonSearchServiceClient());

//        EditorialAdviserWidget editorialAdviserWidget = new EditorialAdviserWidget(new EditorialServiceClient(),
//                new ShoppingContext("grill", SearchCategory.HOME_AND_GARDEN, "ATVPDKIKX0DER"));

        System.out.println(renderer.getRenderableContent(amazonsChoiceAdviserWidget));
    }
}
