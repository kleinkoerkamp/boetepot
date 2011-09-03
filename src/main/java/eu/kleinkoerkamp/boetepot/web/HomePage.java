package eu.kleinkoerkamp.boetepot.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.kleinkoerkamp.boetepot.domain.Person;
import eu.kleinkoerkamp.boetepot.service.PersonService;
import eu.kleinkoerkamp.boetepot.web.person.EditPersonPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	//private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
	
	@SpringBean
	private PersonService personService;
	
	public HomePage() {
		super();
		
		ListView<Person> listView = new ListView<Person>("listview", personService.getAll()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<Person> item) {
				IModel<Person> model = item.getModel();
				
				Link<String> link = new Link<String>("link") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setResponsePage(new EditPersonPage(item.getModelObject()));
					}
				};
				link.add(new Label("firstname", new PropertyModel<String>(model, "firstName")));
				item.add(link);
				item.add(new Label("lastname", new PropertyModel<String>(model, "lastName")));
				
				Link<String> deleteLink = new Link<String>("deleteLink") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						personService.delete(item.getModelObject());
						setResponsePage(HomePage.class);
					}
				};
				item.add(deleteLink);
				
			}
		};
		
		add(listView);
		
		Link<String> addLink = new Link<String>("addLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(EditPersonPage.class);
			}
		};
		add(addLink);
	}
}
