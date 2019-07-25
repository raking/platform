package guru.springbootmybatis.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springbootmybatis.mapper.UsersMapper;
import guru.springbootmybatis.model.UsersSalery;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersResource {

    private UsersMapper usersMapper;

    public UsersResource(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }


    @GetMapping("/all")
    public List<UsersSalery> getAll() {
    	return usersMapper.findAll("rol1");
    }

    @GetMapping("/update")
    private List<UsersSalery> update() {

    	UsersSalery users = new UsersSalery();
        users.setName("Youtube");
        users.setSalary(2333L);

        usersMapper.insert(users);

        return usersMapper.findAll("rol1");
    }
}
