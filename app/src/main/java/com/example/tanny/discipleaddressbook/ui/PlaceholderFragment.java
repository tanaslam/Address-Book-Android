package com.example.tanny.discipleaddressbook.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.test.UiThreadTest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanny.discipleaddressbook.R;
import com.example.tanny.discipleaddressbook.model.AddressBookModel;
import com.example.tanny.discipleaddressbook.model.Contact;
import com.example.tanny.discipleaddressbook.rest.AddressBookRestClient;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.fragment_main)
public class PlaceholderFragment extends Fragment {

    @ViewById(R.id.contact_name)
    protected TextView tv;

    @Bean
    protected AddressBookRestClient restClient;

    private AddressBookModel model;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        fetchContacts();
    }

    @UiThread
    protected void updateViews() {

        model = restClient.getContacts();

        if(model != null) {

            StringBuilder builder = new StringBuilder();

            for (Contact contact : model.getContacts()) {
                builder.append(contact.getFirstName()).append(" ")
                        .append(contact.getLastName()).append("\n");
            }

            tv.setText(builder.toString().isEmpty() ? "" : builder.toString());
        }
    }

    @Background
    protected void fetchContacts() {
        restClient.fetchContacts(false);
        updateViews();
    }
}