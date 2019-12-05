package io.geekidea.springbootplus.foobar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Film implements Serializable {

    private Long idn;
    private String filmName;
    private String filmSer;
    private String filmPrice;
    private Date filmBirth;
}
