package guru.springframework.domain;
/*
 * http://appsdeveloperblog.com/user-authentication-spring-boot-spring-security-jwt/
 */
import org.springframework.data.repository.CrudRepository;
public interface UsersRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);
}