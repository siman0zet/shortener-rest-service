package com.simandev.shortener.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Url {
    @Id
    @Column
    private String shortURL;

    @Column
    private String sourceURL;

    @Column
    private Long countTransition;
}
