package ru.crazzyoffice;

import ru.crazzyoffice.entity.JobEntity;
import ru.crazzyoffice.entity.WorkDay;

import java.time.LocalDate;
import java.util.*;


public  class ConvertToMessage {
    private ConvertToMessage() {
    }
    public static String convertWorkDay(WorkDay workDay){
        StringBuilder str = new StringBuilder();
        String dayOfWeek = workDay.getDate().getDayOfWeek().toString();
        String date = workDay.getDate().toString();
        str.append(dayOfWeek+" ("+date+") "+"\n");
        workDay.getWorkers().forEach((jobType, person) -> str.append(jobType.getDescription()+
                " : "+person.getAlias()+"\n"));
        str.append("--------------- \n");
        return str.toString();
    }

    public static String convertWorkDays(List<WorkDay> workDayList){
        StringBuilder str = new StringBuilder();
        workDayList.stream().sorted(Comparator.comparing(WorkDay::getDate)).forEach(x->str.append(ConvertToMessage.convertWorkDay(x)));
        if(str.length()==0) {
            str.append("Нет данных");
            str.append("--------------- \n");
        }
        return str.toString();
    }

    public static String convertEvents(List<JobEntity> weekJobs) {
         StringBuilder str = new StringBuilder();
         Map<LocalDate,String> data = new LinkedHashMap<>();
         Comparator<JobEntity> comparator =
                 (h1, h2) -> h2.getJobType().getId().compareTo(h2.getJobType().getId());
         weekJobs.stream().sorted(Comparator.comparing(JobEntity::getDate).thenComparing(comparator))
                 .forEach(x->{
                     if(data.containsKey(x.getDate())){
                         String oldValue = data.get(x.getDate());
                         String updateValue = x.getJobType().getDescription()+" : "+
                                 x.getPerson().getAlias()+"\n";
                         data.replace(x.getDate(),oldValue+updateValue);
                     }
                     else{
                         data.put(x.getDate(),x.getJobType().getDescription()+" : "+
                                 x.getPerson().getAlias()+"\n");
                     }
                 });
         for(Map.Entry<LocalDate,String> entry:data.entrySet() ){
             String dayOfWeek = entry.getKey().getDayOfWeek().toString();
             String date = entry.getKey().toString();
             str.append(dayOfWeek+" ("+date+") "+"\n");
             str.append(entry.getValue());
             str.append("--------------- \n");
         }



         return str.toString();
    }
}
