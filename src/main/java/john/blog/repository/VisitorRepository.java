package john.blog.repository;

import john.blog.domain.Visitor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VisitorRepository extends CrudRepository<Visitor, Long> {

    @Query( value = "SELECT * " +
                    "FROM visitor " +
                    "WHERE username = ?1",
            nativeQuery = true)
    Visitor findVisitorByUsername(String username);
}
