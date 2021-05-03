package com.example.RestFulServicesExercise.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeResource {

    @Autowired
    private EmployeeDaoService service;

    @GetMapping("/employees")
    public List<Employee> retrieveAllEmployee(){
        return service.findAll();
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> retrieveEmployee(@PathVariable int id){
        Employee user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id-"+ id);
        }
        //HateOAS implementation
        EntityModel<Employee> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllEmployee());
        resource.add(linkTo.withRel("all-employees"));

        return resource;
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee){
        Employee createdEmployee = service.save(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEmployee.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        Employee employee = service.deleteById(id);
        if(employee == null){
            throw new UserNotFoundException("id-"+ id);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee,@PathVariable int id){

        Employee user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id-"+ id);
        }

        user.setName(employee.getName());
        user.setAge(employee.getAge());
        final Employee updatedEmployee = service.save(user);
        return ResponseEntity.ok(updatedEmployee);
    }

}
