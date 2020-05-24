package ru.crazzyoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.crazzyoffice.entity.WorkDay;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay,Integer> {


    @Query(value = "SELECT * from workDay WHERE extract(year from date)=?2 AND extract(month from date)=?1",
            nativeQuery = true)
    public List<WorkDay> findMonthEntries(int month, int year);


    public Optional<WorkDay> findWorkDayByDate(LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM WorkDay r WHERE r.id=:id")
    int delete(@Param("id") int id);
}
