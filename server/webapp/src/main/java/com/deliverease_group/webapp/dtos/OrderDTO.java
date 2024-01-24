package com.deliverease_group.webapp.dtos;

import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.Issue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

public class OrderDTO {
    private Long id;

    private int issue;

    private ZonedDateTime timeIssuePosted;

    public OrderDTO(Long id, int issue, ZonedDateTime timeIssuePosted) {
        this.id = id;
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

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public ZonedDateTime getTimeIssuePosted() {
        return timeIssuePosted;
    }

    public void setTimeIssuePosted(ZonedDateTime timeIssuePosted) {
        this.timeIssuePosted = timeIssuePosted;
    }
}
