package models;

import interfaces.Prisoner;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PrisonerModel extends PrisonPerson implements Prisoner {
    @Override
    public void WorkAtFactory(Integer prisonerID, ArrayList<ArrayList<Object>> heretics) {
        AtomicInteger prisonerIndex = new AtomicInteger(-1);
        AtomicReference<Double> faith = new AtomicReference<>(null);
        heretics.forEach(h -> {
            if(prisonerID.equals(h.getFirst())) {
                prisonerIndex.set(heretics.indexOf(h));
                faith.set((double)h.getLast());
            }
        });
        if(prisonerID>=0 && faith.get() != null){
            heretics.get(prisonerIndex.get()).set(1, faith.get()-4);
        }
    }

    @Override
    public void WorkForSalvation(ArrayList<ArrayList<Object>> heretics) {
        ArrayList<Object> hereticsToEnlighten= new ArrayList<>();
        hereticsToEnlighten.addAll(0, heretics);

        hereticsToEnlighten.forEach(h -> {
            try {
                Integer index = heretics.indexOf(h);
                Double faith = (double)heretics.get(index).get(1);
                heretics.get(index).set(1,faith + 4);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        });
    }

    @Override
    public void WorkForSanity(ArrayList<ArrayList<Object>> heretics) {
        ArrayList<Object> hereticsToEnlighten= new ArrayList<>();
        hereticsToEnlighten.addAll(0, heretics);

        hereticsToEnlighten.forEach(h -> {
            try {
                Integer index = heretics.indexOf(h);
                Double faith = (double)heretics.get(index).get(1);
                heretics.get(index).set(1,faith - 5);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        });
    }
}
