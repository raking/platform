package guru.springbootmybatis.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FormData {
    @NotBlank
    @Size(min=3, max=100)
    private String name;
    @NotBlank
    @Size(min=2, max=10)
    private String salary;

  	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
