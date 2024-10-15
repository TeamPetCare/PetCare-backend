package com.application.petcare.repository;

import com.application.petcare.security.model.UserTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserTokenModel, Integer> {
    @Query(value = """
    select t from UserTokenModel t inner join User u\s
    on t.user.id = u.id \s
    where u.id = :id and (t.expired = false or t.revoked = false)
    """)
    List<UserTokenModel> findAllValidTokenByUser(Integer id);

    Optional<UserTokenModel> findByToken(String token);
}
