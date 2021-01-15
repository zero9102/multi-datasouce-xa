package com.x2021.mycloud.multidatasoucexa.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Builder
@ToString
@Data
@Table(name = "log")
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;


    @Column(name = "content")
    private String content;

    @Column(name = "des")
    private String describe;
}
