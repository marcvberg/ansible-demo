package com.marc.ansible.controller.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Math {

    @RequestMapping("/rest/v1/math/add/{first}/{second}")
    public Long add(@PathVariable("first")Long first, @PathVariable("second")Long second)
    {
        return first + second;
    }
}
