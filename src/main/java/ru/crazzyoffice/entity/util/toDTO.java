package ru.crazzyoffice.entity.util;

import ru.crazzyoffice.entity.JobEntity;
import ru.crazzyoffice.entity.JobType;
import ru.crazzyoffice.entity.Person;
import ru.crazzyoffice.entity.WorkDay;
import ru.crazzyoffice.entity.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public  class toDTO {
    private toDTO() {
    }

    public static final String CAR_ICON ="<i class='fas fa-car-side'></i>";
    public static final String PHONE_ICON="<i class=\"fas fa-phone-square-alt\"></i>";
    public static final String OFFICE_ICON="<i class=\"fas fa-house-user\"></i>";
    public static final String DUTY_ICON="<i class=\"far fa-eye\"></i>";

    public static Event toEvent(JobEntity jobEntity){
         return new Event(jobEntity.getId().toString(),
                           jobEntity.getDate().toString(),
                           jobEntity.getPerson().getAlias()+" - "+
                            jobEntity.getJobType().getDescription(),
                           jobEntity.getJobType().getIcon(),
                            jobEntity.getJobType().getBackgroundColor(),
                            jobEntity.getJobType().getTextColor()

                 );
    }


}
