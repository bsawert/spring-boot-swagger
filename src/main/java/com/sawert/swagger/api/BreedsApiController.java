package com.sawert.swagger.api;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class BreedsApiController implements BreedsApi {

    private final BreedsApiDelegate delegate;

    @Autowired
    public BreedsApiController(BreedsApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public BreedsApiDelegate getDelegate() {
        return delegate;
    }
}
