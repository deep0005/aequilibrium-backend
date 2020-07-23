package com.aequilibrium.transformers.repository;

import com.aequilibrium.transformers.domainobject.Transformer;
import com.aequilibrium.transformers.domainvalue.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransformerRepository extends JpaRepository<Transformer, Long> {

    List<Transformer> findByType(Type type);
    List<Transformer> findByTypeAndIdIn(Type type, List<Long> id);
}
