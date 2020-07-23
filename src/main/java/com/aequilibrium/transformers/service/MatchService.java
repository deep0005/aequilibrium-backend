package com.aequilibrium.transformers.service;

import com.aequilibrium.transformers.domainobject.Transformer;
import com.aequilibrium.transformers.domainvalue.Type;
import com.aequilibrium.transformers.dto.request.MatchRequest;
import com.aequilibrium.transformers.dto.response.MatchResultResponse;
import com.aequilibrium.transformers.repository.TransformerRepository;
import com.aequilibrium.transformers.util.MatchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private TransformerRepository transformerRepository;

    @Autowired
    private MatchUtil matchUtil;

    public MatchResultResponse getFightResult(MatchRequest request){
        List<Transformer> autobots = transformerRepository.findByTypeAndIdIn(Type.AUTOBOT, request.getAutobots());
        List<Transformer> decepticons = transformerRepository.findByTypeAndIdIn(Type.DECEPTICON, request.getDecepticons());


        return matchUtil.getFightResult(autobots, decepticons);
    }


}
