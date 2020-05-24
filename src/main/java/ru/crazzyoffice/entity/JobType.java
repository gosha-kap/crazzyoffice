package ru.crazzyoffice.entity;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "jobtype")
public class JobType {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   private String description;

   public JobType() {
    }

    public JobType(Integer id , String description) {
        this.id = id;
        this.description = description;
    }



    public JobType(String json){
        String regex = ".+id=(\\d+),\\s?description=(.+).+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        if(matcher.find()) {
            this.id = Integer.parseInt(matcher.group(1));
            this.description = matcher.group(2);
        }
        else
            this.description = json;
      }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "JobType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
