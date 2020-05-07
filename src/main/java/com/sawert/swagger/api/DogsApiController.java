package com.sawert.swagger.api;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class DogsApiController implements DogsApi {

    private final DogsApiDelegate delegate;

    @Autowired
    public DogsApiController(DogsApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public DogsApiDelegate getDelegate() {
        return delegate;
    }
}
