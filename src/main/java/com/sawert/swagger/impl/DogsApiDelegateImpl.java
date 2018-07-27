package com.sawert.swagger.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sawert.swagger.api.BreedsApiDelegate;
import com.sawert.swagger.api.DogsApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class DogsApiDelegateImpl implements DogsApiDelegate {

    private ObjectMapper objectMapper;
    private HttpServletRequest httpServletRequest;

    @Autowired
    public DogsApiDelegateImpl(ObjectMapper objectMapper, HttpServletRequest httpServletRequest) {
        this.objectMapper = objectMapper;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.of(this.objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.of(this.httpServletRequest);
    }

    @Override
    public Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }
}
