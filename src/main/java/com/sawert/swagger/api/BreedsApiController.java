package com.sawert.swagger.api;

import org.springframework.stereotype.Controller;

@Controller
public class BreedsApiController implements BreedsApi {

    private final BreedsApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public BreedsApiController(BreedsApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public BreedsApiDelegate getDelegate() {
        return delegate;
    }
}
