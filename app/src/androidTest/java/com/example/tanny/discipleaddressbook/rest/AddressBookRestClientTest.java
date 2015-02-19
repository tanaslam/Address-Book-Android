package com.example.tanny.discipleaddressbook.rest;

import android.os.AsyncTask;
import android.test.AndroidTestCase;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.tanny.discipleaddressbook.model.AddressBookModel;

public class AddressBookRestClientTest extends AndroidTestCase {

    private AddressBookRestClient_ restClient;
    private AddressBookModel model;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        restClient  =  AddressBookRestClient_.getInstance_(getContext());
    }

    @LargeTest
    @UiThreadTest
    public void testFetchContacts() throws Exception {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                restClient.fetchContacts(false);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                model = restClient.getContacts();

                assertNotNull(model);
                assertEquals(model.getContacts().size(), 4);

                //TODO assert other properties here
            }
        }.execute();
    }
}