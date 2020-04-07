package com.hrynyk.labs.repo;

import com.hrynyk.labs.domain.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AccountRepo extends JpaRepository<AccountsEntity, Long> {
    @Query("select a from AccountsEntity a where a.id = ?1")
    AccountsEntity findById(int id);

    @Transactional
    @Modifying
    @Query("delete from AccountsEntity a where a.id = ?1")
    void deleteById(int id);

}
