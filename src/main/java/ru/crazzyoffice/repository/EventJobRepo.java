package ru.crazzyoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.crazzyoffice.entity.JobEntity;
import ru.crazzyoffice.entity.WorkDay;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventJobRepo extends JpaRepository<JobEntity,Integer> {

    @Query(value = "SELECT * from jobentity WHERE extract(year from date)=?2 AND extract(month from date)=?1",
            nativeQuery = true)
    public List<JobEntity> findMonthEntries(int month, int year);

    public List<JobEntity> findAllByDateIsGreaterThanEqualAndDateIsLessThanEqual(LocalDate start, LocalDate end);


    @Query(value = "SELECT * from jobentity WHERE date >=?1 AND  date<=?2",
            nativeQuery = true)
    List<JobEntity> getWeekEvents(LocalDate monday, LocalDate sunday);
}
