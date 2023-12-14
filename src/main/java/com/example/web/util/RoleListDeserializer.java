package com.example.web.util;

import com.example.web.model.Role;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoleListDeserializer extends StdDeserializer<List<Role>> {

    public RoleListDeserializer() {
        this(null);
    }

    public RoleListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<Role> deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
        var result = new ArrayList<Role>();
        var token = jsonparser.nextToken();

        while (!token.equals(JsonToken.END_ARRAY)) {
            if (token.equals(JsonToken.VALUE_STRING)) {
                result.add(new Role(jsonparser.getText()));
            }
            token = jsonparser.nextToken();
        }

        return result;
    }
}