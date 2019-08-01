package com.sawert.swagger.api;

import org.springframework.stereotype.Controller;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-01T16:20:58.572Z")

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
