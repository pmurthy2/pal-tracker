package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
            this.timeEntryRepository= timeEntryRepository;

    }

    @PostMapping("/time-entries")
    public ResponseEntity create(TimeEntry timeEntryToCreate) {
            TimeEntry createTimeEntry= timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity<>(createTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);

        if (timeEntry!=null){

                return new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.OK);

        }else{

            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(long id, TimeEntry timeEntry) {
        TimeEntry timeEntry1= timeEntryRepository.update(id,timeEntry);
            if(timeEntry1!=null){

                return new ResponseEntity<TimeEntry>(timeEntry1,HttpStatus.OK);
            }else{
                return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);

            }
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> delete(long id) {

        timeEntryRepository.delete(id);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}
