package com.ssp.apps.pdp.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pdp_goal")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PdpGoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String goal;
    private Date targetDate;
    private boolean isCompleted;

    public PdpGoalEntity(String goal, Date targetDate, boolean isCompleted) {
        super();
        this.goal = goal;
        this.targetDate = targetDate;
        this.isCompleted = isCompleted;
    }

    public PdpGoalEntity(String goal) {
        super();
        this.goal = goal;
        this.targetDate = new Date();
    }


}
