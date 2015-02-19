
package com.example.tanny.discipleaddressbook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    @Expose
    private Long id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private Object lastName;
    @Expose
    private Boolean favourite;
    @SerializedName("phone_numbers")
    @Expose
    private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

    /**
     * 
     * @return
     *     The id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *     The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     *     The lastName
     */
    public Object getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *     The last_name
     */
    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     *     The favourite
     */
    public Boolean getFavourite() {
        return favourite;
    }

    /**
     * 
     * @param favourite
     *     The favourite
     */
    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    /**
     * 
     * @return
     *     The phoneNumbers
     */
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * 
     * @param phoneNumbers
     *     The phone_numbers
     */
    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

}
