package com.sawert.swagger.api;

import org.springframework.stereotype.Controller;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-01T16:24:37.503Z")

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
