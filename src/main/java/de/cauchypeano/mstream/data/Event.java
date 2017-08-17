package de.cauchypeano.mstream.data;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @JsonProperty("id")
    private String id;

    @Column(name = "machine_id", nullable = false)
    @JsonProperty("maschine_id")
    private String maschineId;

    @Column(nullable = false)
    private Date timestamp;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    public Event() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaschineId() {
        return maschineId;
    }

    public void setMaschineId(String maschineId) {
        this.maschineId = maschineId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }
}