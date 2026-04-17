package com.demo.linkedinProject.postsService.restCall;

import com.demo.linkedinProject.postsService.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connection-service",path = "/core")
public interface ConnectionServiceClient {

    @GetMapping("/{userId}/first-degree")
    public List<PersonDto> getFirstDegreeConnections(@PathVariable String userId);
}
