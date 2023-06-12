package com.fleet.counties.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"fips",
	"state",
	"name"
})
public class County implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private long id;
	@JsonProperty("fips")
	private String fips;
	@JsonProperty("state")
	private String state;
	@JsonProperty("name")
	private String name;
	@JsonIgnore
	@Transient
	private Map<String, Object> optional = new HashMap<>();
	
	@JsonAnySetter
    public void addOptional(String name, Object value) {
        optional.put(name, value);
    }
    @JsonAnyGetter
    public Object getOptional(String name) {
        return optional.get(name);
    }
}
