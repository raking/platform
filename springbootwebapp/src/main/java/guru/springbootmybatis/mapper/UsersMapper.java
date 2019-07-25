package guru.springbootmybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;


import guru.springbootmybatis.model.UsersSalery;

import java.util.List;
@Mapper
public interface UsersMapper {

	@Cacheable(value= {"cache1"},key="#a0")
    @Select("select id,name,salary from users_salary where name=#{name}")
    public List<UsersSalery> findAll(@Param("name") String name);

	@CacheEvict(value= {"cache1"},key="#root.args[0].name")
    @Insert("insert into users_salary(name,salary) values(#{name},#{salary})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    public int insert(UsersSalery users);
}
