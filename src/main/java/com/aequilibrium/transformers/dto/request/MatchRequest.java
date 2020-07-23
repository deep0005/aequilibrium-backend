package com.aequilibrium.transformers.dto.request;


import javax.validation.constraints.NotNull;
import java.util.List;

public class MatchRequest {

    @NotNull(message = "Autobots cannot be null")
    List<Long> autobots;

    @NotNull(message = "Decepticons cannot be null")
    List<Long> decepticons;

    public List<Long> getAutobots() {
        return autobots;
    }

    public void setAutobots(List<Long> autobots) {
        this.autobots = autobots;
    }

    public List<Long> getDecepticons() {
        return decepticons;
    }

    public void setDecepticons(List<Long> decepticons) {
        this.decepticons = decepticons;
    }
}
