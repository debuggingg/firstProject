package springboot.website.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.website.entity.TestEntity;
import springboot.website.repository.TestRepository;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestEntity> getAllEntities() {
        return testRepository.findAll();
    }

    public TestEntity saveEntity(String name) {
        TestEntity entity = new TestEntity();
        entity.setName(name);
        return testRepository.save(entity);
    }
}