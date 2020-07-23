package com.aequilibrium.transformers.dto.response;

import com.aequilibrium.transformers.domainobject.Transformer;
import com.aequilibrium.transformers.domainvalue.Type;

public class MatchResultResponse {

    private Integer matchCount;
    private Type winningTeamType;
    private Transformer winningTransformer;
    private Type losingTeamType;
    private Transformer losingSurvivor;


    public Integer getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(Integer matchCount) {
        this.matchCount = matchCount;
    }

    public Type getWinningTeamType() {
        return winningTeamType;
    }

    public void setWinningTeamType(Type winningTeamType) {
        this.winningTeamType = winningTeamType;
    }

    public Transformer getWinningTransformer() {
        return winningTransformer;
    }

    public void setWinningTransformer(Transformer winningTransformer) {
        this.winningTransformer = winningTransformer;
    }

    public Type getLosingTeamType() {
        return losingTeamType;
    }

    public void setLosingTeamType(Type losingTeamType) {
        this.losingTeamType = losingTeamType;
    }

    public Transformer getLosingSurvivor() {
        return losingSurvivor;
    }

    public void setLosingSurvivor(Transformer losingSurvivor) {
        this.losingSurvivor = losingSurvivor;
    }
}
