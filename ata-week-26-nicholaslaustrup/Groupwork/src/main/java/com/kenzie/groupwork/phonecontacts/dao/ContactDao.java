package com.kenzie.groupwork.phonecontacts.dao;

import com.kenzie.groupwork.phonecontacts.model.Contact;
import com.kenzie.groupwork.phonecontacts.model.Name;
import com.kenzie.groupwork.phonecontacts.model.SortBy;
import com.kenzie.groupwork.phonecontacts.model.SortOrder;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class for accessing contacts data.
 * Note that we are making this class a Singleton since we're storing our contacts in-memory inside of this class in
 * TreeMaps. If this wasn't a Singleton, then every request would get a new copy of ContactDao with empty TreeMaps.
 */
@Singleton
public class ContactDao {

    private final SortedMap<Name, Contact> contactsSortedByFirstName;
    private final SortedMap<Name, Contact> contactsSortedByLastName;


    @Inject
    public ContactDao() {
        this.contactsSortedByFirstName = new TreeMap<>(Comparator.comparing(Name::getFirstName));
        this.contactsSortedByLastName = new TreeMap<>(Comparator.comparing(Name::getLastName));
    }

    /**
     * Saves the given contact.
     * @param contact to add.
     * @return The added contact.
     */
    public Contact addContact(Contact contact) {
        this.contactsSortedByFirstName.put(contact.getName(), contact);
        this.contactsSortedByLastName.put(contact.getName(), contact);
        return contact;
    }

    /**
     * Retrieves all contacts in the provided sort by order. If no sort parameters are given, returns the default
     * of sorting by last name in ascending order.
     * Big O: O(n log n)
     * @param sortBy Attribute to sort contacts by, e.g. first name, last name.
     * @param sortOrder order to return contacts, either ascending or descending.
     * @return map of name to contacts in requested sorted order.
     */
    public SortedMap<Name, Contact> getContacts(SortBy sortBy, SortOrder sortOrder) {
        if (sortBy == null) {
            sortBy = SortBy.LAST_NAME;
        }
        if (sortOrder == null) {
            sortOrder = SortOrder.ASCENDING;
        }
        if (sortBy == SortBy.FIRST_NAME && sortOrder == SortOrder.ASCENDING) {
            return new TreeMap<>(this.contactsSortedByFirstName);
        }
        if (sortBy == SortBy.FIRST_NAME && sortOrder == SortOrder.DESCENDING) {
            return new TreeMap<>(this.contactsSortedByFirstName).descendingMap();
        }
        if (sortBy == SortBy.LAST_NAME && sortOrder == SortOrder.ASCENDING) {
            return new TreeMap<>(this.contactsSortedByLastName);
        }
        return new TreeMap<>(this.contactsSortedByLastName).descendingMap();

    }

    /**
     * Removes all contacts.
     */
    public void clearContacts() {
        this.contactsSortedByFirstName.clear();
        this.contactsSortedByLastName.clear();
    }

    /**
     * Retrieves a portion of the contacts whose Name is equal to or after the given startKey based on the sorting
     * parameters.
     * Big O: O(n log n)
     * @param startKey The Name that should be the first contact in the returned Map
     * @param sortBy Attribute to sort contacts by, e.g. first name, last name.
     * @param sortOrder order to return contacts, either ascending or descending.
     * @return portion of contacts that includes startKey and all contacts after startKey based on requested sorted
     * order.
     */
    public SortedMap<Name, Contact> getContactsStartingAt(Name startKey, SortBy sortBy, SortOrder sortOrder) {
        if (sortBy == null) {
            sortBy = SortBy.LAST_NAME;
        }
        if (sortOrder == null) {
            sortOrder = SortOrder.ASCENDING;
        }
        if (sortBy == SortBy.FIRST_NAME && sortOrder == SortOrder.ASCENDING) {
            return new TreeMap<>(this.contactsSortedByFirstName).tailMap(startKey, true);
        }
        if (sortBy == SortBy.FIRST_NAME && sortOrder == SortOrder.DESCENDING) {
            return new TreeMap<>(this.contactsSortedByFirstName).descendingMap().tailMap(startKey, true);
        }
        if (sortBy == SortBy.LAST_NAME && sortOrder == SortOrder.ASCENDING) {
            return new TreeMap<>(this.contactsSortedByLastName).tailMap(startKey, true);
        }
        return new TreeMap<>(this.contactsSortedByLastName).descendingMap().tailMap(startKey, true);
    }

    /**
     * Removes a contact.
     * Big O: O(n log n)
     * @param removeKey The Name of the contact to remove.
     */

    public void removeContact(Name removeKey) {
        this.contactsSortedByFirstName.remove(removeKey);
        this.contactsSortedByLastName.remove(removeKey);
    }

}
