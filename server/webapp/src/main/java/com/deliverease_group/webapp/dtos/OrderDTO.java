package com.deliverease_group.webapp.dtos;

import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.Issue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

public class OrderDTO {
    private Long id;
    private boolean isCompleted;
    private boolean isManagerReviewed;
    private Issue issue;
    private ZonedDateTime timeIssuePosted;

    public OrderDTO(Long id, boolean isCompleted, boolean isManagerReviewed, Issue issue, ZonedDateTime timeIssuePosted) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.isManagerReviewed = isManagerReviewed;
        this.issue = issue;
        this.timeIssuePosted = timeIssuePosted;
    }

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isManagerReviewed() {
        return isManagerReviewed;
    }

    public void setManagerReviewed(boolean managerReviewed) {
        isManagerReviewed = managerReviewed;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public ZonedDateTime getTimeIssuePosted() {
        return timeIssuePosted;
    }

    public void setTimeIssuePosted(ZonedDateTime timeIssuePosted) {
        this.timeIssuePosted = timeIssuePosted;
    }
}
