package io.pivotal.pal.tracker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {




    private Map<Long, TimeEntry> map = new HashMap<>();

    private long counter=0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        counter = counter+1;
        TimeEntry timeEntry1  = new TimeEntry(counter, timeEntry.getProjectId(),
                timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours()
        );

        map.put(counter,timeEntry1);
        return timeEntry1;
    }

    @Override
    public TimeEntry find(long id) {
        TimeEntry timeEntry = map.get(id);
        return timeEntry;
    }

    public List<TimeEntry> list(){
        List list = new ArrayList<TimeEntry>( map.values());
        return  list;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        timeEntry.setId(id);
        map.put(id, timeEntry);

        TimeEntry timeEntry1 = map.get(id);

        return timeEntry1;
    }

    @Override
    public void delete (long id){

        map.remove(id);

    }


}
