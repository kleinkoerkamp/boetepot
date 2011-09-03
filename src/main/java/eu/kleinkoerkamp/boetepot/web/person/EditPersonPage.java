package eu.kleinkoerkamp.boetepot.web.person;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.kleinkoerkamp.boetepot.domain.Person;
import eu.kleinkoerkamp.boetepot.service.PersonService;
import eu.kleinkoerkamp.boetepot.web.HomePage;

public class EditPersonPage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private PersonService personService;
	
	private String id;
	
	public EditPersonPage() {
		this(null);
	}
	
	public EditPersonPage(Person person) {
		super();
		
		if(person != null) {
			id = person.getId();
		}
		
		IModel<Person> model = new LoadableDetachableModel<Person>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected Person load() {
				if (id == null) {
					return new Person();
				} 
				return personService.get(id);
			}
		};
		
		Form<Person> form = new Form<Person>("form", new CompoundPropertyModel<Person>(model)) {
			private static final long serialVersionUID = -5978266423103610814L;
			
			@Override
			protected void onSubmit() {
				super.onSubmit();
				personService.save(getModelObject());
				setResponsePage(HomePage.class);
			}
			
		};
		
		form.add(new TextField<String>("username").setEnabled(id == null));
		form.add(new TextField<String>("firstName"));
		form.add(new TextField<String>("lastName"));
		form.add(new Button("submit"));
		
		add(form);
	}

}
