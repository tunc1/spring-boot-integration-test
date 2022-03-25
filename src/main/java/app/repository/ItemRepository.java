package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Long>{}