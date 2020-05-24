package ru.crazzyoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.crazzyoffice.entity.JobType;
import ru.crazzyoffice.error.IllegalRequestDataException;
import ru.crazzyoffice.error.NotFoundException;
import ru.crazzyoffice.repository.JobRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobRepository repository;


    @GetMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
    public List<JobType> getAll(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JobType getOne(@PathVariable Integer id){
        return Optional.of(repository.findById(id)).
                get().orElseThrow(()-> new NotFoundException("no job entry with id= "+id+" found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if (repository.delete(id)==0) throw  new NotFoundException("no job entry with id= "+id+" found");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public JobType create(@RequestBody JobType jobType) {
        if (jobType.getId()!=null)
            throw new IllegalRequestDataException(jobType + " must be new (id=null)");
        return repository.save(jobType);

    }

    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public JobType update(@PathVariable  Integer id,
                         @RequestBody JobType newJob){

        if (newJob.getId() != id)
            throw new IllegalRequestDataException(newJob + " must be with id=" + id);
        return repository.findById(id)
                .map(job -> {
                    job.setDescription(newJob.getDescription());
                    return repository.save(job);
                }).orElseThrow(() -> new NotFoundException("no entity find with id= " + id));

    }


}
