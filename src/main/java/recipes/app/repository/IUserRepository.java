package recipes.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import recipes.app.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

	public User findByName(String name);
}
