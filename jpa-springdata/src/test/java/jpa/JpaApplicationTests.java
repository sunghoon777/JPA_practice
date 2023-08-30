package jpa;

import jpa.dao.PlayerRepository;
import jpa.dao.UserRepository;
import jpa.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class JpaApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PlayerRepository playerRepository;

	//연관된 엔티티도 자동 조회됨
	@Test
	void findAll(){
		List<User> list = userRepository.findAll();
		list.stream().forEach(user -> System.out.println(user.getEmail()+" "+user.getName()));
	}

	@Test
	void findAllBySorting(){
		List<User> list = userRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
		list.stream().forEach(user -> System.out.println(user.getEmail()+" "+user.getName()));
	}

	@Test
	void findAllByIdList(){
		List<User> list = userRepository.findAllById(Arrays.asList("test@test.com","tester@test.com"));
		list.stream().forEach(user -> System.out.println(user.getEmail()+" "+user.getName()));
	}

	@Test
	void findById(){
		Optional<User> result = userRepository.findById("test@test.com");
		User user = result.get();
		System.out.println(user.getEmail()+" "+user.getName());
	}

	@Test
	void save(){
		userRepository.save(new User("mama@test.com","mama",new Date()));
	}

	@Test
	void saveAll(){
		userRepository.saveAll(Arrays.asList(
				new User("1@test.com","1",new Date()),
				new User("2@test.com","2",new Date())));
	}

	@Test
	void delete(){
		userRepository.delete(new User("s","s",new Date()));
	}

	@Test
	void deleteById(){
		userRepository.deleteById("tester@test.com");
	}

	@Test
	void deleteAll(){
		userRepository.deleteAll(Arrays.asList(new User("1","",new Date()),new User("2","",new Date())));
	}

	@Test
	void deleteAllById(){
		userRepository.deleteAllById(Arrays.asList("1@test.com","2@test.com"));
	}

	@Test
	void deleteInBatch(){
		userRepository.deleteAllByIdInBatch(Arrays.asList("1@test.com","2@test.com"));
	}

	@Test
	void count(){
		System.out.println(userRepository.count());
	}

	@Test
	void page1(){
		Page<User> page = userRepository.findAll(PageRequest.of(20,4,Sort.by(Sort.Direction.ASC,"createDate")));
		List<User> list = page.get().collect(Collectors.toList());
	}


}
