package com.sawert.swagger.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sawert.swagger.api.BreedsApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class BreedsApiDelegateImpl implements BreedsApiDelegate {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.of(this.objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.of(this.httpServletRequest);
    }
}
