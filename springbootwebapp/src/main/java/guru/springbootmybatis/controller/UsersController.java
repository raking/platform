package guru.springbootmybatis.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springbootmybatis.mapper.UsersMapper;
import guru.springbootmybatis.model.UsersSalery;

@Controller
public class UsersController {
	


	@Autowired
    private UsersMapper usersMapper;

	public UsersMapper getUsersMapper() {
		return this.usersMapper;
	}


	public void setUsersMapper(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}

	@GetMapping("/users/all")
    public String getAll(Model model) {
   	
    	model.addAttribute("datas", getUsersMapper().findAll("vol1"));
    	return "user/index";
    }
    
	@GetMapping("/users/insertForm")
    public String insertForm() {
        return "user/insertForm";
    }    

   @ModelAttribute
    public FormData formData() {
        return new FormData();
    }	
	
    @PostMapping("/users/update")
    public String update(@ModelAttribute @Valid FormData formData, Errors errors) {
    	 //예외가 발생하면 에러 내용을 사용자의 하면에 뭐가 잘못되었는지 표시해준다.
        if (errors.hasErrors()) return "user/insertForm";
        UsersSalery users = new UsersSalery();
        users.setName(formData.getName());
        users.setSalary(Long.parseLong(formData.getSalary()));
         //iReturn이 0보다 큰값이 아니면 등록실패  
        int iReturn=getUsersMapper().insert(users);
         if(iReturn <=0) {
        	 System.out.println("등록실패"); 
         }
        return "user/index";
    }
}
