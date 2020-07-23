package com.aequilibrium.transformers.util;

import com.aequilibrium.transformers.domainobject.Transformer;
import com.aequilibrium.transformers.domainvalue.Type;
import com.aequilibrium.transformers.dto.response.MatchResultResponse;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Component
public class MatchUtil {

    Comparator<Transformer> transformersComparator = new Comparator<Transformer>() {
        @Override
        public int compare(Transformer left, Transformer right) {
            return left.getRank() - right.getRank();
        }
    };

    /**
     * Perform fight
     * @param autobots
     * @param decepticons
     * @return
     */
    public MatchResultResponse getFightResult(List<Transformer> autobots, List<Transformer> decepticons){

        MatchResultResponse result = new MatchResultResponse();

        // Sort the teams
        autobots.sort(transformersComparator);
        decepticons.sort(transformersComparator);

        // Convert to linked list for O(1) removals
        LinkedList<Transformer> autobotsLinkedList = new LinkedList<>(autobots);
        LinkedList<Transformer> decepticonsLinkedList = new LinkedList<>(decepticons);

        int autobotWinCount = 0;
        int decepticonWinCount = 0;
        int matchCount = 0;

        // Iterate till any of the teams gets empty
        while(!autobotsLinkedList.isEmpty() && !decepticonsLinkedList.isEmpty()){

            // Increment match count
            matchCount++;

            // Fetch Candidates
            Transformer autobotCandidate = autobotsLinkedList.getFirst();
            Transformer decepticonCandidate = decepticonsLinkedList.getFirst();

            // Calculate overall Ratings
            int autobotOverallRating = getOverallRating(autobotCandidate);
            int decepticonOverallRating = getOverallRating(decepticonCandidate);

            // Check for optimus prime or predaking
            if(autobotCandidate.getSuper() && decepticonCandidate.getSuper()){
                setResults(result, autobotWinCount, decepticonWinCount, matchCount, autobotsLinkedList, decepticonsLinkedList);
                return result;
            }else if(autobotCandidate.getSuper()){
                setResults(result, Integer.MAX_VALUE, decepticonWinCount, matchCount, autobotsLinkedList, decepticonsLinkedList);
                return result;
            }else if(decepticonCandidate.getSuper()){
                setResults(result, autobotWinCount, Integer.MAX_VALUE, matchCount, autobotsLinkedList, decepticonsLinkedList);
                return result;
            }

            // If any fighter is down 4 or more points of courage
            if(Math.abs(autobotCandidate.getCourage() - decepticonCandidate.getCourage()) >= 4){
                if(autobotCandidate.getCourage() > decepticonCandidate.getCourage()){
                    decepticonsLinkedList.removeFirst();
                    autobotWinCount++;
                }else{
                    autobotsLinkedList.removeFirst();
                    decepticonWinCount++;
                }

                // If any fighter is down 3 or more points of strength
            }else if(Math.abs(autobotCandidate.getStrength() - decepticonCandidate.getStrength()) >= 3){
                if(autobotCandidate.getStrength() > decepticonCandidate.getStrength()){
                    decepticonsLinkedList.removeFirst();
                    autobotWinCount++;
                }else{
                    autobotsLinkedList.removeFirst();
                    decepticonWinCount++;
                }

                // if one of the fighters is 3 or more points of skill above their opponent,
            }else if(Math.abs(autobotCandidate.getSkill() - decepticonCandidate.getSkill()) >= 3){
                if(autobotCandidate.getSkill() > decepticonCandidate.getSkill()){
                    decepticonsLinkedList.removeFirst();
                    autobotWinCount++;
                }else{
                    autobotsLinkedList.removeFirst();
                    decepticonWinCount++;
                }

                // In the event of a tie, both Transformers are considered destroyed
            }else if(autobotOverallRating == decepticonOverallRating){
                decepticonsLinkedList.removeFirst();
                autobotsLinkedList.removeFirst();
            }else{

                // The winner is the Transformer with the highest overall rating
                if(autobotOverallRating > decepticonOverallRating){
                    decepticonsLinkedList.removeFirst();
                    autobotWinCount++;
                }else{
                    autobotsLinkedList.removeFirst();
                    decepticonWinCount++;
                }
            }

        }


        setResults(result, autobotWinCount, decepticonWinCount, matchCount, autobotsLinkedList, decepticonsLinkedList);

        return result;

    }

    private void setResults(MatchResultResponse response, int autobotWinCount, int decepticonWinCount, int matchCount, LinkedList<Transformer> autobotsLinkedList, LinkedList<Transformer> decepticonsLinkedList){
        response.setMatchCount(matchCount);

        if(autobotWinCount > decepticonWinCount){
            response.setWinningTeamType(Type.AUTOBOT);
            response.setWinningTransformer(autobotsLinkedList.getFirst());

            response.setLosingTeamType(Type.DECEPTICON);
            if(!decepticonsLinkedList.isEmpty()){
                response.setLosingSurvivor(decepticonsLinkedList.getFirst());
            }

        }else if(decepticonWinCount > autobotWinCount){
            response.setWinningTeamType(Type.DECEPTICON);
            response.setWinningTransformer(decepticonsLinkedList.getFirst());

            response.setLosingTeamType(Type.AUTOBOT);
            if(!autobotsLinkedList.isEmpty()){
                response.setLosingSurvivor(autobotsLinkedList.getFirst());
            }
        }
    }

    private int getOverallRating(Transformer transformer){
        return transformer.getSkill() + transformer.getStrength() + transformer.getCourage() + transformer.getRank() + transformer.getIntelligence() + transformer.getFirepower() + transformer.getEndurance() + transformer.getSpeed();
    }
}
