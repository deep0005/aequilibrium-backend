package com.aequilibrium.transformers.controller;

import com.aequilibrium.transformers.domainvalue.Type;
import com.aequilibrium.transformers.dto.GeneralResponse;
import com.aequilibrium.transformers.dto.request.MatchRequest;
import com.aequilibrium.transformers.exception.DataValidationException;
import com.aequilibrium.transformers.service.MatchService;
import com.aequilibrium.transformers.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;

import javax.validation.Valid;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private AppUtil appUtil;

    @CrossOrigin(
            origins = { "*" },
            methods = { RequestMethod.POST },
            allowCredentials = "true",
            allowedHeaders = CorsConfiguration.ALL,
            exposedHeaders = {},
            maxAge = 1800)
    @RequestMapping(path = "/fight", method = RequestMethod.POST)
    public GeneralResponse performMatch(@Valid @RequestBody MatchRequest matchRequest, BindingResult bindingResult) throws DataValidationException {
        appUtil.validateBindingResult(bindingResult);

        return GeneralResponse.getResponse(false, matchService.getFightResult(matchRequest));
    }
}
