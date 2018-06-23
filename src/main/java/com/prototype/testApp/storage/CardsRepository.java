package com.prototype.testApp.storage;

import com.prototype.testApp.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CardsRepository extends JpaRepository<Card,Long> {
}
