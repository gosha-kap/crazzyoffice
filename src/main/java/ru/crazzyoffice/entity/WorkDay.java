package ru.crazzyoffice.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "workday")
public class WorkDay  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Convert(converter = DateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @JsonProperty("workers")
    @JsonDeserialize(keyUsing = WorkersDeserializer.class)
    @OneToMany(fetch = FetchType.EAGER )
    @JoinTable(name = "person_workday_relashion",
    joinColumns = @JoinColumn(name = "workday_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id",referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "person_job")
    Map<JobType,Person> workers = new HashMap<>();

    public WorkDay() {

    }

    public WorkDay(LocalDate date) {
        this.date = date;
    }

    @JsonCreator
    public WorkDay(@JsonProperty("date") LocalDate date, @JsonProperty("workers") Map<JobType, Person> workers) {
        this.date = date;
        this.workers = workers;
    }

    public Map<JobType, Person> getWorkers() {
        return workers;
    }

    public void setWorkers(Map<JobType, Person> workers) {
        this.workers = workers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addWorker(JobType jobType , Person person){
        this.workers.put(jobType,person);
    }


    @Override
    public String toString() {
        return "WorkDay{" +
                "id=" + id +
                ", date=" + date +
                ", workers=" + workers +
                '}';
    }
}
