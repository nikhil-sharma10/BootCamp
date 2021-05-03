package com.example.RestFulServicesExercise.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVersioningController {

    @GetMapping("/user/version1")
    public UserV1 getUserURIV1(){
        return new UserV1("Nikhil Sharma",21);
    }

    @GetMapping("user/version2")
    public UserV2 getUserURIV2(){
        return new UserV2(new Name("Nikhil","Sharma"),21);
    }

    @GetMapping(value="user/header",headers = "X-API-VERSION=1")
        public UserV1 getUserHeaderV1(){
            return new UserV1("Nikhil Sharma",21);
        }

    @GetMapping(value="user/header",headers = "X-API-VERSION=2")
    public UserV2 getUserHeaderV2(){
        return new UserV2(new Name("Nikhil","Sharma"),21);
    }

    @GetMapping(value="user/param",params = "version=1")
    public UserV1 getUserParamV1(){
        return new UserV1("Nikhil Sharma",21);
    }

    @GetMapping(value="user/param",params = "version=2")
    public UserV2 getUserParamV2(){
        return new UserV2(new Name("Nikhil","Sharma"),21);
    }

    @GetMapping(value="user/produces",produces="application/vnd.company.app-v1+json")
    public UserV1 getUserProducesV1(){
        return new UserV1("Nikhil Sharma",21);
    }

    @GetMapping(value="user/produces",produces="application/vnd.company.app-v2+json")
    public UserV2 getUserProducesV2(){
        return new UserV2(new Name("Nikhil", "Sharma"),21);
    }

}
