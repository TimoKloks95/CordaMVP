package com.template.webserver.controllers;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseTestCase {

    @Before
    public void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

}