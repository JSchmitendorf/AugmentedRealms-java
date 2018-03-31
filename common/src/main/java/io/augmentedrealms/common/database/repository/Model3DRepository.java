package io.augmentedrealms.common.database.repository;

import io.augmentedrealms.common.database.model.Model3D;
import io.augmentedrealms.common.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface Model3DRepository extends JpaRepository<Model3D,Long> {

}
