package com.lewic.todoscore.dao;

import com.lewic.todoscore.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<Tag, Integer> {
}
