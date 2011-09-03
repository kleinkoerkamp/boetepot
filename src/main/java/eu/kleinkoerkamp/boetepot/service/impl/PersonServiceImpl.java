package eu.kleinkoerkamp.boetepot.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.util.Assert;

import eu.kleinkoerkamp.boetepot.domain.Person;
import eu.kleinkoerkamp.boetepot.service.PersonService;

public class PersonServiceImpl implements PersonService, InitializingBean {

	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(mongoTemplate, "test");
	}

	@Override
	public List<Person> getAll() {
		//Criteria.where("admin").is(false)
		Query query = new Query();
		return mongoTemplate.find(query, Person.class);
	}

	@Override
	public void delete(Person person) {
		mongoTemplate.remove(person);
	}

	@Override
	public Person get(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Person.class);
	}

	@Override
	public void save(Person person) {
		mongoTemplate.save(person);
	}
}
