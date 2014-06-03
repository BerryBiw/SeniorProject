
package org.springframework.samples.ltas.entity;

import java.text.DateFormat;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "activities")
public class Activity extends NamedEntity {
    @Column(name = "description")
    @NotEmpty
    private String description;

    @Column(name = "number_student")
    @NotEmpty
    private String numberStudent;



    @Column(name = "start_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime startDate;

    @Column(name = "end_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime endDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ActType type;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private Set<Student> students;


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberStudent() {
        return this.numberStudent;
    }

    public void setNumberStudent(String numberStudent) {
        this.numberStudent = numberStudent;
    }


    public void setStartDate(DateTime startDate) {
       this.startDate = startDate;
    }

    public DateTime getStartDate() {
        return this.startDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public DateTime getEndDate() {
        return this.endDate;
    }

    public void setType(ActType type) {
        this.type= type;
    }

    public ActType getType() {
        return this.type;
    }

    protected void setStudentsInternal(Set<Student> students) {
        this.students = students;
    }

    protected Set<Student> getStudentsInternal() {
        if (this.students == null) {
            this.students = new HashSet<Student>();
        }
        return this.students;
    }

    public List<Student> getStudents() {
        List<Student> sortedStudents = new ArrayList<Student>(getStudentsInternal());
        PropertyComparator.sort(sortedStudents, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedStudents);
    }

    public void addStudent(Student student) {
        getStudentsInternal().add(student);
        student.setActivity(this);
    }


    public Student getStudent(String name) {
        return getStudent(name, false);
    }


    public Student getStudent(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Student student : getStudentsInternal()) {
            if (!ignoreNew || !student.isNew()) {
                String compName = student.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return student;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("name", this.getName())
                .append("description", this.description)
                .append("number_student", this.numberStudent)
                .append("start_date", this.getStartDate().toDate())
                .append("end_date", this.getEndDate().toDate())
                .append("type_id", this.getType().getId())



                .toString();
    }

    public String getStartDatet(){
        return DateFormat.getDateTimeInstance(
                DateFormat.SHORT, DateFormat.SHORT).format(getStartDate().toDate());
    }

    public String getEndDatet(){
        return DateFormat.getDateTimeInstance(
                DateFormat.SHORT, DateFormat.SHORT).format(getEndDate().toDate());
    }
}
