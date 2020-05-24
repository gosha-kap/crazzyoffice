package ru.crazzyoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.crazzyoffice.entity.JobType;

@Repository
public interface   JobRepository  extends JpaRepository<JobType,Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM JobType r WHERE r.id=:id")
    int delete(@Param("id") int id);
}
