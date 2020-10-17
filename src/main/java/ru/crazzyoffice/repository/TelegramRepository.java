package ru.crazzyoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.crazzyoffice.entity.TelegramUser;

import java.util.List;
import java.util.Optional;


@Repository
public interface TelegramRepository extends JpaRepository<TelegramUser,Integer> {

    public Optional<TelegramUser> getByUserId(Integer userId);


    @Query("SELECT p FROM TelegramUser p")
    public List<TelegramUser> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM TelegramUser r WHERE r.id=:id")
    int delete(@Param("id") int id);

    

}
