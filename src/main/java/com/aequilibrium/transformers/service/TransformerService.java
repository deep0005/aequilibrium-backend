package com.aequilibrium.transformers.service;

import com.aequilibrium.transformers.domainobject.Transformer;
import com.aequilibrium.transformers.domainvalue.Type;
import com.aequilibrium.transformers.repository.TransformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransformerService {

    @Autowired
    TransformerRepository transformerRepository;

    public List<Transformer> getTransformers(Type type){
        return transformerRepository.findByType(type);
    }
}
