package astrolabe31.runningfx.dao;

import java.util.List;

import astrolabe31.runningfx.model.Person;

public interface EleveDaoInterface {
	 public List<Person> findEleves() ;
	 public List<Person> findElevesByPays(Integer pays);
}
