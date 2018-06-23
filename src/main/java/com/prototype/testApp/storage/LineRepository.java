package com.prototype.testApp.storage;

import com.prototype.testApp.domain.Line;
import org.springframework.data.repository.CrudRepository;

public interface LineRepository extends CrudRepository<Line,Long>{
}
