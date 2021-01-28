package com.zw.admin.framework.domain.universal;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 通用实体类
 * @author: ZhouWei
 * @create: 2020-12
 **/
@Data
@ToString
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String type;
    private List<String> tags;
    private List<Object> images;
    private List<Object> positions;
    private HashMap<String,String> labels;
    private HashMap<String,String> descs;
    private HashMap<String,List<String>> aliases;
    private HashMap<String,List<Claim>> claims;
    private Date mod;
    private Integer version;
    private List<Gene> genes;
    private boolean pushed;
}
