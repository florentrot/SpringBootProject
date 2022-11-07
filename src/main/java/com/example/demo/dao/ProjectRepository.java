package com.example.demo.dao;

import com.example.demo.dto.ChartData;
import com.example.demo.dto.TimeChartData;
import com.example.demo.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "apiprojects", path = "apiprojects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

    @Override
    public List<Project> findAll();

    @Query(nativeQuery = true, value = "SELECT stage AS label, COUNT(*) AS counted " +
            "FROM project GROUP BY stage")
    public List<ChartData> getProjectStatus();

    @Query(nativeQuery = true, value="SELECT name as projectName, start_date as startDate, end_date as endDate" +
            " from project")
    public List<TimeChartData> getTimeData();
}
