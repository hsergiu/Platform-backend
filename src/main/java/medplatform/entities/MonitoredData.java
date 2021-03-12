package medplatform.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class MonitoredData implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "activity")
    private String activity;

    @Column(name = "start")
    private String start;

    @Column(name = "endt")
    private String end;

    @Column(name = "patientid")
    private UUID patient;

    public MonitoredData() {
    }

    public MonitoredData(String activity, String start, String end, UUID patient) {
        this.activity = activity;
        this.start = start;
        this.end = end;
        this.patient = patient;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStart() { return start; }

    public void setStart(String start) { this.start = start; }

    public String getEnd() { return end; }

    public void setEnd(String end) { this.end = end; }

    public UUID getPatient() { return patient; }

    public void setPatient(UUID patient) { this.patient = patient; }

    @Override
    public String toString(){
        return start + " " + end + " " + activity + " " + patient;
    }
}
