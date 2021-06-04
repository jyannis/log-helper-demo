package top.jyannis.loghelperdemo;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jyannis
 * @version 1.0 update on 2021/5/21
 */
@RestController
public class MyRestController {

    @RequestMapping(value = "body",method = RequestMethod.POST)
    @ApiOperation(value = "a body POST request")
    public User post(@RequestBody User user){
        return user;
    }

    @RequestMapping(value = "/path/{name}",method = RequestMethod.GET)
    public String get(@PathVariable("name")String name){
        return name;
    }

    @RequestMapping(value = "formdata",method = RequestMethod.POST)
    public User postUser(User user){
        return user;
    }

    @RequestMapping(value = "query",method = RequestMethod.POST)
    public String postQuery(@RequestParam("name") String name){
        return name;
    }

    @RequestMapping(value = "file",method = RequestMethod.POST)
    public String get(@RequestParam("file")MultipartFile file){
        return file.getOriginalFilename();
    }

    @RequestMapping(value = "myError",method = RequestMethod.GET)
    public String error(User user){
        throw new RuntimeException("some error occurs...");
    }


}
