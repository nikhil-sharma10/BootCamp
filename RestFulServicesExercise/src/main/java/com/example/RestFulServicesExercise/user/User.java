package com.example.RestFulServicesExercise.user;

//import net.minidev.json.annotate.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFilter("UserFilter") // -> Used for Dynamic Filtering provide filter id here
public class User {

    private String name;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //  -> Used for Static filtering
    private String password;
    private Integer id;

    public User(String name, String password, Integer id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
