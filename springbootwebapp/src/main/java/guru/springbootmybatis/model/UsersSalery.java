package guru.springbootmybatis.model;

import java.io.Serializable;

public class UsersSalery implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5683140831897046677L;
	private String name;
    private Long salary;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
