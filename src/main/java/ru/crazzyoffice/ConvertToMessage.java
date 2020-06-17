package ru.crazzyoffice;

import ru.crazzyoffice.entity.WorkDay;

import java.util.Comparator;
import java.util.List;


public  class ConvertToMessage {
    private ConvertToMessage() {
    }
    public static String convertWorkDay(WorkDay workDay){
        StringBuilder str = new StringBuilder();
        String dayOfWeek = workDay.getDate().getDayOfWeek().toString();
        String date = workDay.getDate().toString();
        str.append(dayOfWeek+" ("+date+") "+"\n");
        if(!workDay.getWorkers().isEmpty())
        workDay.getWorkers().forEach((jobType, person) -> str.append(jobType.getDescription()+
                " : "+person.getAlias()+"\n"));
        else str.append("Нет данных");
        str.append("--------------- \n");
        return str.toString();
    }

    public static String convertWorkDays(List<WorkDay> workDayList){
        StringBuilder str = new StringBuilder();
        workDayList.stream().sorted(Comparator.comparing(WorkDay::getDate)).forEach(x->str.append(ConvertToMessage.convertWorkDay(x)));
        return str.toString();
    }

}
