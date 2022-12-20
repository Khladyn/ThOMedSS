package com.example.thomedss.data;

public class ComplaintModel {

    private String
            id,
            caseType,
            location,
            patientName,
            dateCreated,
            timeQueued,
            queueStatus = "Waiting",
            attendingDoctor,
            caseDetails;

    public ComplaintModel() {
    }

    public ComplaintModel
            (String id,
             String caseType,
             String location,
             String patientName,
             String dateCreated,
             String timeQueued,
             String attendingDoctor,
             String caseDetails) {

        this.id = id;
        this.caseType = caseType;
        this.location = location;
        this.patientName = patientName;
        this.dateCreated = dateCreated;
        this.timeQueued = timeQueued;
        this.attendingDoctor = attendingDoctor;
        this.caseDetails = caseDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String caseType) {
        this.id = id;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public String getCaseDetails() {
        return caseDetails;
    }

    public void setCaseDetails(String caseDetails) {
        this.caseDetails = caseDetails;
    }
}
