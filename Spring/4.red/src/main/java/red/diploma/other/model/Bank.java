/*
 * Copyright (c) 2019 General Electric Company. All rights reserved.
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package red.diploma.other.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import red.diploma.accounts.model.Auditable;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bank extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    @Length(max = 100)
    private String name;

    @Column
    private String description;

    @Column(nullable = false, unique = true)
    private String legalName;

    @Column(nullable = false, unique = true)
    private Integer mfo;

    @Column(nullable = false, unique = true)
    private Integer edrpou;

    public Bank(String name, String description, String legalName, Integer mfo, Integer edrpou) {
        this.name = name;
        this.description = description;
        this.legalName = legalName;
        this.mfo = mfo;
        this.edrpou = edrpou;
    }
}
