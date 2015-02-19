
package com.example.tanny.discipleaddressbook.model;

import com.google.gson.annotations.Expose;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean
public class AddressBookModel {

    @Expose
    private List<Contact> contacts = new ArrayList<Contact>();

    /**
     * 
     * @return
     *     The contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * 
     * @param contacts
     *     The contacts
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}
