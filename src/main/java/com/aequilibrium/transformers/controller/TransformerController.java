package com.aequilibrium.transformers.controller;

import com.aequilibrium.transformers.domainvalue.Type;
import com.aequilibrium.transformers.dto.GeneralResponse;
import com.aequilibrium.transformers.service.TransformerService;
import com.aequilibrium.transformers.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/transformer")
public class TransformerController {

    @Autowired
    private TransformerService transformerService;

    @CrossOrigin(
            origins = { "*" },
            methods = { RequestMethod.GET },
            allowCredentials = "true",
            allowedHeaders = CorsConfiguration.ALL,
            exposedHeaders = {},
            maxAge = 1800)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public GeneralResponse getTransformersList(@PathParam("type") Type type) {
        return GeneralResponse.getResponse(true, transformerService.getTransformers(type));
    }
}
