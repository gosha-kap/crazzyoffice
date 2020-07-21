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

   private String backgroundColor;

   private String textColor;


   private String icon;

   public JobType() {
    }

    public JobType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public JobType(String description, String backgroundColor, String textColor, String icon) {
        this.description = description;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.icon = icon;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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


}
