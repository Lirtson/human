package com.liang.human.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="evaluation")
public class Evaluation {
    @Id
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private long uid;
}
