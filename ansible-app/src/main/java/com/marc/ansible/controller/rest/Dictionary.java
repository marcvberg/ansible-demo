package com.marc.ansible.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;

@RestController
public class Dictionary {
    private HashMap<String, String> definitions;

    public Dictionary()
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try{
            definitions = mapper.readValue(new File("definitions.yml"), HashMap.class);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/rest/v1/dictionary/lookup/{value}", method=RequestMethod.GET)
    public String getDictionaryDefinition(@PathVariable("value")String value)
    {
        if(definitions.containsKey(value))
        {
            return definitions.get(value);
        }
        return "Sorry, a definition for that word was not found!";
    }
}
