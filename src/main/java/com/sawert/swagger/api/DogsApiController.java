package com.sawert.swagger.api;

import org.springframework.stereotype.Controller;

@Controller
public class DogsApiController implements DogsApi {

    private final DogsApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public DogsApiController(DogsApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public DogsApiDelegate getDelegate() {
        return delegate;
    }
}
