package io.augmentedrealms.common.database.repository;

import io.augmentedrealms.common.database.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Long> {
}
