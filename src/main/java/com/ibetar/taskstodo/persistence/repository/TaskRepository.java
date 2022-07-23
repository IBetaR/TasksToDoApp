package com.ibetar.taskstodo.persistence.repository;

import com.ibetar.taskstodo.persistence.entity.Task;
import com.ibetar.taskstodo.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE TASK SET FINISHED=true WHERE ID=:id",nativeQuery = true)
    public void markTaskAsFinished(@Param("id") Long id);
}
