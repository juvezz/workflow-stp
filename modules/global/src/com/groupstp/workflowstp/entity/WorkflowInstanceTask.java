package com.groupstp.workflowstp.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Workflow step execution task
 *
 * @author adiatullin
 */
@Table(name = "WFSTP_WORKFLOW_INSTANCE_TASK")
@Entity(name = "wfstp$WorkflowInstanceTask")
public class WorkflowInstanceTask extends StandardEntity {
    private static final long serialVersionUID = -4736994489532462066L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTANCE_ID", nullable = false)
    private WorkflowInstance instance;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEP_ID", nullable = false)
    private Step step;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date endDate;

    @ManyToMany
    @JoinTable(name = "WFSTP_TASK_PERFORMERS_LINK",
            joinColumns = @JoinColumn(name = "TASK_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERFORMER_ID"))
    private Set<User> performers;


    public WorkflowInstance getInstance() {
        return instance;
    }

    public void setInstance(WorkflowInstance instance) {
        this.instance = instance;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<User> getPerformers() {
        return performers;
    }

    public void setPerformers(Set<User> performers) {
        this.performers = performers;
    }
}
