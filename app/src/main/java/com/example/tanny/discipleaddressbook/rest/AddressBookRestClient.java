package com.example.tanny.discipleaddressbook.rest;

import android.util.Log;

import com.example.tanny.discipleaddressbook.model.AddressBookModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;
import org.springframework.web.client.RestClientException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by tanny on 17/02/2015.
 */
@EBean(scope = EBean.Scope.Singleton)
public class AddressBookRestClient implements RestErrorHandler {

    private static final String LOG_TAG = AddressBookRestClient.class.getSimpleName();
    private static final String ROOT_URL = "https://raw.githubusercontent.com/tanaslam/Address-Book-Android/master";

    @RestService
    protected AddressBookApi restService;

    @Bean
    protected AddressBookModel addressBook;

    @AfterInject
    void setupRestClient() {
        restService.setRootUrl(ROOT_URL);
        restService.setRestErrorHandler(this);
    }

    public AddressBookModel getContacts() {
        return addressBook;
    }

    public void fetchContacts(boolean isRestful) {

        if(isRestful) {
            doGetAddressBookModel();
        } else {
            doGetContactsHttpsUrlConnection("/data/addressbook.json");
        }
    }

    protected void doGetAddressBookModel() {

        AddressBookModel response;

        try {

            response = restService.getContacts();

            if(response != null) {
                addressBook.setContacts(response.getContacts());
            }
        } catch(RestClientException ex) {
            Log.e(LOG_TAG, "Failed to get a response", ex);
        }
    }

    public void doGetContactsHttpsUrlConnection(String params) {

        URL url = null;
        HttpsURLConnection connection = null;

        try {

            url = new URL(ROOT_URL + params);
            connection = (HttpsURLConnection) url.openConnection();

            //if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = new BufferedInputStream(connection.getInputStream());
                Gson parser = new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .serializeNulls().create();
                AddressBookModel response =
                        parser.fromJson(new InputStreamReader(is), AddressBookModel.class);

                if(response != null) {
                    addressBook.setContacts(response.getContacts());
                }
            //}

        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to open connection", e);
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        Log.e(LOG_TAG, "Unable to get response from API:" + e.getLocalizedMessage());
    }
}
