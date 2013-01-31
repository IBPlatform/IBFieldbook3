package ibfb.studyexplorer.core;

import ibfb.domain.core.Trial;
import java.util.ArrayList;
import java.util.List;

public class TrialListDummy {

    private static List<Trial> trialList = new ArrayList<Trial>();

    public static void addTrial(Trial trial) {
        trialList.add(trial);
    }

    public static List<Trial> getTrialList() {
        return trialList;
    }
}
