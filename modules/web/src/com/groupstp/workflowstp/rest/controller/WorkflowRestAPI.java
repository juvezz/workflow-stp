package com.groupstp.workflowstp.rest.controller;

import com.groupstp.workflowstp.rest.dto.ResponseDTO;
import com.groupstp.workflowstp.rest.dto.ResultDTO;
import com.groupstp.workflowstp.rest.dto.WorkflowDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Workflow REST endpoint API
 *
 * @author adiatullin
 */
@RequestMapping(value = "/wf/workflow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface WorkflowRestAPI {

    /**
     * Get all active workflows from system
     *
     * @return all visible to user and ready to use workflows
     */
    @GetMapping(value = "/all")
    List<WorkflowDTO> getWorkflows();

    /**
     * Start workflow process
     *
     * @param entityId   processing entity id
     * @param entityName processing entity name (like bo$Entity)
     * @return id of created workflow instance
     */
    @PostMapping(value = "/start")
    ResponseDTO<String> start(@RequestParam(name = "entityId") String entityId,
                              @RequestParam(name = "entityName") String entityName);

    /**
     * Check is send entity in workflow process or not
     *
     * @param entityId   processing entity id
     * @param entityName processing entity name (like bo$Entity)
     * @return is provided entity in workflow process or not (also returning id of workflow instance if entity is processing)
     */
    @GetMapping(value = "/processing")
    ResponseDTO<String> isProcessing(@RequestParam(name = "entityId") String entityId,
                                     @RequestParam(name = "entityName") String entityName);

    /**
     * Check can current user perform workflow action of the specified entities
     *
     * @param entityId   performing entities ids
     * @param workflowId current processing workflow id
     * @param stepId     processing step id
     * @param actionId   performing actions ids
     * @return is provided user can perform actions to the specified entities or not (with flags actionId-result)
     */
    @GetMapping(value = "/performable")
    ResponseDTO<ResultDTO> isPerformable(@RequestParam(name = "entityId") String[] entityId,
                                         @RequestParam(name = "workflowId") String workflowId,
                                         @RequestParam(name = "stepId") String stepId,
                                         @RequestParam(name = "actionId") String[] actionId);

    /**
     * Perform provided action with current user for specified entities
     *
     * @param entityId   performing entities ids
     * @param workflowId current processing workflow id
     * @param stepId     processing step id
     * @param actionId   performing action id
     * @param payload    special payload message which can contains business logic data
     * @return result of the performing
     */
    @PostMapping(value = "/perform", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseDTO<String> perform(@RequestParam(name = "entityId") String[] entityId,
                                @RequestParam(name = "workflowId") String workflowId,
                                @RequestParam(name = "stepId") String stepId,
                                @RequestParam(name = "actionId") String actionId,
                                @RequestBody String payload);
}
