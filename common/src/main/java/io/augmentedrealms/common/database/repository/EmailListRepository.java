package io.augmentedrealms.common.database.repository;

import io.augmentedrealms.common.database.model.EmailListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailListRepository extends JpaRepository<EmailListItem,Long> {

    boolean existsByEmail(String email);


}
