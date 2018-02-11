package io.augmentedrealms.common.database.repository;

import io.augmentedrealms.common.database.model.Realm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealmRepository extends JpaRepository<Realm,Long> {
}
