package ru.crazzyoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.crazzyoffice.entity.WorkDay;

import java.util.List;


@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay,Integer> {


    @Query( value = "SELECT * from workDay WHERE MONTH(date) = MONTH(?1)\n" +
            "                        AND YEAR(date) = YEAR(?1);",
            nativeQuery = true)
    public List<WorkDay> findMonthEntries(String date);


    public WorkDay findWorkDayByDate(String date);
}
