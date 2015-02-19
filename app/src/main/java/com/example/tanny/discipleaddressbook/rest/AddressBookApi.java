package com.example.tanny.discipleaddressbook.rest;

import com.example.tanny.discipleaddressbook.model.AddressBookModel;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Head;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tanny on 17/02/2015.
 */

@Accept(MediaType.APPLICATION_JSON)
@Rest(
        converters = {CustomGsonConverter.class,
                StringHttpMessageConverter.class,
                FormHttpMessageConverter.class},

        interceptors = {CustomRequestInterceptor.class})

public interface AddressBookApi extends RestClientErrorHandling {

    /**
     * Root URL of back-end REST API.
     *
     * @param rootUrl root URL of RESTful API from configuration.
     */
    void setRootUrl(String rootUrl);
    RestTemplate getRestTemplate();

    /**
     * Set additional headers for request.
     *
     * @param name Header name
     * @param value Header value
     */
    void setHeader(String name, String value);

    /**
     * Set custom REST template.
     * This allows to override REST template configuration that is set by
     * default.
     *
     * @param restTemplate an instance of custom {@link RestTemplate}
     */
    void setRestTemplate(RestTemplate restTemplate);

    @Get("/contacts.json")
    AddressBookModel getContacts();


}

