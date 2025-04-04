package com.kenzie.cookies;

import com.kenzie.cookies.cookie.ChocolateChipCookie;
import com.kenzie.cookies.cookie.CookieBox;
import com.kenzie.cookies.exception.AllergenContaminantException;

import com.kenzie.cookies.exception.CookieSizeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Assembles the cookies into boxes for sale at Whole Foods.
 */
public class CookiePackager {
    private Logger logger = LogManager.getLogger(CookiePackager.class);
    private List<CookieBox> cookieBoxes;
    private List<ChocolateChipCookie> cookiesForCrumble;
    private List<CookieInspector> inspectors;

    /**
     * Instantiates a CookiePackager object.
     * @param inspectors - the inspectors that should be used to inspect any cookies being added to a package
     */
    public CookiePackager(List<CookieInspector> inspectors) {
        this.cookieBoxes = new ArrayList<>();
        this.cookiesForCrumble = new ArrayList<>();
        this.inspectors = new ArrayList<>(inspectors);
    }

    /**
     * Cookie boxes to sell.
     * @return cookie boxes
     */
    public List<CookieBox> getCookieBoxes() {
        return cookieBoxes;
    }

    /**
     * Cookies that can be used for cookie crumble in other recipes.
     * @return cookies eligible to be used as crumble.
     */
    public List<ChocolateChipCookie> getCookiesForCrumble() {
        return cookiesForCrumble;
    }

    /**
     * Attempts to add a cookie to a CookieBox. Before adding the cookie, inspect the cookie. Cookies that are too small
     * or are too large cannot be placed in the box and should be used for cookie crumble (waste not, want not). If
     * the most recently assembled box is full, assembles a new box containing just the provided cookie and adds it to
     * the list of CookieBoxes.
     *
     * @param cookie Cookie to try to package.
     * @throws AllergenContaminantException thrown if an allergen is detected in the cookie to be packaged
     */
    public void packageCookie(ChocolateChipCookie cookie) throws AllergenContaminantException {
        try{
            for (CookieInspector inspect: inspectors) {
                inspect.inspect(cookie);
            }
            addCookieToBox(cookie);
            } catch(CookieSizeException a) {
            cookiesForCrumble.add(cookie);
        }
    }

    private void addCookieToBox(ChocolateChipCookie cookie) {
        if (!cookieBoxes.isEmpty()) {
            CookieBox boxToFill = cookieBoxes.get(cookieBoxes.size() - 1);
            if (!boxToFill.isFull()) {
                boxToFill.addCookie(cookie);
                return;
            }
        }
        assembleCookieBox(cookie);
    }

    private void assembleCookieBox(ChocolateChipCookie cookie) {
        CookieBox emptyCookieBox = new CookieBox();
        emptyCookieBox.addCookie(cookie);
        cookieBoxes.add(emptyCookieBox);
    }
}
