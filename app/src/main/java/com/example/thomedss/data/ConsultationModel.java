package com.example.thomedss.data;

public class ConsultationModel {

    String userID;
    String name;
    String caseType;
    String description;
    String dateCreated;
    String timeQueued;
    String queueStatus;
    String attendingDoctor;
    String caseID;
    String location;

    public ConsultationModel() {
    }

    public ConsultationModel(String userID, String name, String caseType, String description, String dateCreated, String timeQueued, String queueStatus, String attendingDoctor, String caseID, String location) {
        this.userID = userID;
        this.name = name;
        this.caseType = caseType;
        this.description = description;
        this.dateCreated = dateCreated;
        this.timeQueued = timeQueued;
        this.queueStatus = queueStatus;
        this.attendingDoctor = attendingDoctor;
        this.caseID = caseID;
        this.location = location;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTimeQueued() {
        return timeQueued;
    }

    public void setTimeQueued(String timeQueued) {
        this.timeQueued = timeQueued;
    }

    public String getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(String queueStatus) {
        this.queueStatus = queueStatus;
    }

    public String getAttendingDoctor() {
        return attendingDoctor;
    }

    public void setAttendingDoctor(String attendingDoctor) {
        this.attendingDoctor = attendingDoctor;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
