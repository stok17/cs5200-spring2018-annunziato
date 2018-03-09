package edu.northeastern.cs5200;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.boot.SpringApplication;

import edu.northeastern.cs5200.Dao.BaseDao;
import edu.northeastern.cs5200.Dao.DeveloperDao;
import edu.northeastern.cs5200.Dao.PageDao;
import edu.northeastern.cs5200.Dao.RoleDao;
import edu.northeastern.cs5200.Dao.WebsiteDao;
import edu.northeastern.cs5200.Dao.WidgetDao;
import edu.northeastern.cs5200.Model.Developer;
import edu.northeastern.cs5200.Model.HeadingWidget;
import edu.northeastern.cs5200.Model.HtmlWidget;
import edu.northeastern.cs5200.Model.ImageWidget;
import edu.northeastern.cs5200.Model.Page;
import edu.northeastern.cs5200.Model.Phone;
import edu.northeastern.cs5200.Model.User;
import edu.northeastern.cs5200.Model.Website;
import edu.northeastern.cs5200.Model.Widget;
import edu.northeastern.cs5200.Model.YouTubeWidget;
import edu.northeastern.cs5200.Model.Role;


public class HW_JDBC_Liu_Siyu {
	
	public static void main(String[] args) {
		SpringApplication.run(Cs5200Spring2018AnnunziatoApplication.class, args);
		
		System.out.println("Part 1");
        Developer alice = new Developer(1, "alice", "alice", "Alice", "Wonder", "alice@wonder.com", null, "4321rewq");
        Developer bob = new Developer(2, "bob", "bob", "Bob", "Marley", "bob@marley.com", null, "5432trew");
        Developer charlie = new Developer(3, "charlie", "charlie", "Charles", "Garcia", "chuch@garcia.com", null, "6543ytre");
        User dan = new User(4, "dan", "dan", "Dan", "Martin", "dan@martin.com", null, true, "7654fda");
        User ed = new User(5, "ed", "ed", "Ed", "Karaz", "ed@kar.com",null, true, "5678dfgh");
		
        DeveloperDao.getInstance().createDeveloper(alice);
        DeveloperDao.getInstance().createDeveloper(bob);
        DeveloperDao.getInstance().createDeveloper(charlie);
        DeveloperDao.getInstance().createUser(dan);
        DeveloperDao.getInstance().createUser(ed);
        
        System.out.println("Part 2");
        Date grade = new Date(Calendar.getInstance().getTime().getTime());
        Website facebook = new Website(1, "Facebook", "an online social media and social networking service", grade, grade, 1234234);
        Website twitter = new Website(2, "Twitter", "an online news and social networking service", grade, grade, 4321543);
        Website wikipedia = new Website(3, "Wikipedia", "a free online encyclopedia", grade, grade, 3456654);
        Website cnn = new Website(4, "CNN", "an American basic cable and satellite television news channel", grade, grade, 6543345);
        Website cnet = new Website(5, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", grade, grade, 5433455);
        Website gizmodo = new Website(6, "Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", grade, grade, 4322345);

        WebsiteDao.getInstance().createWebsiteForDeveloper(alice.getId(), facebook);
        WebsiteDao.getInstance().createWebsiteForDeveloper(bob.getId(), twitter);
        WebsiteDao.getInstance().createWebsiteForDeveloper(charlie.getId(), wikipedia);
        WebsiteDao.getInstance().createWebsiteForDeveloper(alice.getId(), cnn);
        WebsiteDao.getInstance().createWebsiteForDeveloper(bob.getId(), cnet);
        WebsiteDao.getInstance().createWebsiteForDeveloper(charlie.getId(), gizmodo);

        RoleDao.getInstance().assignWebsiteRole(bob.getId(), twitter.getId(), Role.owner.id);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), wikipedia.getId(), Role.owner.id);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), gizmodo.getId(), Role.owner.id);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), facebook.getId(), Role.owner.id);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), cnn.getId(), Role.owner.id);
        RoleDao.getInstance().assignWebsiteRole(bob.getId(), cnet.getId(), Role.owner.id);
        RoleDao.getInstance().assignWebsiteRole(bob.getId(), facebook.getId(), Role.editor.id);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), facebook.getId(), Role.admin.id);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), twitter.getId(), Role.editor.id);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), twitter.getId(), Role.admin.id);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), wikipedia.getId(), Role.editor.id);
        RoleDao.getInstance().assignWebsiteRole(bob.getId(), wikipedia.getId(), Role.admin.id);
        RoleDao.getInstance().assignWebsiteRole(bob.getId(), cnn.getId(), Role.editor.id);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), cnn.getId(), Role.admin.id);
        RoleDao.getInstance().assignWebsiteRole(charlie.getId(), cnet.getId(), Role.editor.id);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), cnet.getId(), Role.admin.id);
        RoleDao.getInstance().assignWebsiteRole(alice.getId(), gizmodo.getId(), Role.editor.id);
        RoleDao.getInstance().assignWebsiteRole(bob.getId(), gizmodo.getId(), Role.admin.id);

        System.out.println("Part 3");
        Date start = Date.valueOf("2018-01-08");
        Date due = Date.valueOf("2018-03-07");
        Page home = new Page(1, "Home", "Landing page", start, due, 123434);
        Page about = new Page(2, "About", "Website description", start, due, 234545);
        Page contact = new Page(3, "Contact", "Addresses, phones, and contact info", start, due, 345656);
        Page preferences = new Page(4, "Preferences", "Where users can configure their preferences", start, due, 456776);
        Page profile = new Page(5, "Profile", "Users can configure their personal information", start, due, 567878);

        PageDao.getInstance().createPageForWebsite(cnet.getId(), home);
        PageDao.getInstance().createPageForWebsite(gizmodo.getId(), about);
        PageDao.getInstance().createPageForWebsite(wikipedia.getId(), contact);
        PageDao.getInstance().createPageForWebsite(cnn.getId(), preferences);
        PageDao.getInstance().createPageForWebsite(cnet.getId(), profile);

        RoleDao.getInstance().assignPageRole(alice.getId(), home.getId(), Role.editor.id);
        RoleDao.getInstance().assignPageRole(bob.getId(), home.getId(), Role.reviewer.id);
        RoleDao.getInstance().assignPageRole(charlie.getId(), home.getId(), Role.writer.id);
        RoleDao.getInstance().assignPageRole(bob.getId(), about.getId(), Role.editor.id);
        RoleDao.getInstance().assignPageRole(charlie.getId(), about.getId(), Role.reviewer.id);
        RoleDao.getInstance().assignPageRole(alice.getId(), about.getId(), Role.writer.id);
        RoleDao.getInstance().assignPageRole(charlie.getId(), contact.getId(), Role.editor.id);
        RoleDao.getInstance().assignPageRole(alice.getId(), contact.getId(), Role.reviewer.id);
        RoleDao.getInstance().assignPageRole(bob.getId(), contact.getId(), Role.writer.id);
        RoleDao.getInstance().assignPageRole(alice.getId(), preferences.getId(), Role.editor.id);
        RoleDao.getInstance().assignPageRole(bob.getId(), preferences.getId(), Role.reviewer.id);
        RoleDao.getInstance().assignPageRole(charlie.getId(), preferences.getId(), Role.writer.id);
        RoleDao.getInstance().assignPageRole(bob.getId(), profile.getId(), Role.editor.id);
        RoleDao.getInstance().assignPageRole(charlie.getId(), profile.getId(), Role.reviewer.id);
        RoleDao.getInstance().assignPageRole(alice.getId(), profile.getId(), Role.writer.id);

        System.out.println("Part 4");
        Widget head123 = new HeadingWidget(1, "head123", 0, 0, "Welcome", 0, 2);
        Widget post234 = new HtmlWidget(2, "post234", 0, 0, "", 0, "<p>Lorem</p>");
        Widget head345 = new HeadingWidget(3, "head345", 0, 0, "Hi", 1, 2);
        Widget intro456 = new HtmlWidget(4, "intro456", 0, 0, "", 2, "<h1>Hi</h1>");
        Widget image345 = new ImageWidget(5, "image345", 50, 100, null, 3, "/img/567.png");
        Widget video456 = new YouTubeWidget(6, "video456", 400, 300, 0, "https://youtu.be/h67VX51QXiQ", false, false);

        WidgetDao.getInstance().createWidgetForPage(home.getId(), head123);
        WidgetDao.getInstance().createWidgetForPage(about.getId(), post234);
        WidgetDao.getInstance().createWidgetForPage(contact.getId(), head345);
        WidgetDao.getInstance().createWidgetForPage(contact.getId(), intro456);
        WidgetDao.getInstance().createWidgetForPage(contact.getId(), image345);
        WidgetDao.getInstance().createWidgetForPage(preferences.getId(), video456);

        System.out.println("Part 5.a");
        Phone phone = new Phone("333-444-555", true);
        DeveloperDao.getInstance().updatePhoneForDeveloper(charlie.getId(), phone);

        System.out.println("Part 5.b");
        List<Widget> widgets = (List<Widget>) WidgetDao.getInstance().findWidgetsForPage(contact.getId());
        for(Widget widget : widgets) {
        	if(widget.getName().equals("head345")) {
        widget.setOrder(3);
        }else{
        widget.setOrder(widget.getOrder()-1);
        }
        WidgetDao.getInstance().updateWidget(widget.getId(), widget);
        }

        System.out.println("Part 5.c");
        List<Page> cnetPages = (List<Page>) PageDao.getInstance().findPagesForWebsite(cnet.getId());
        for (Page page: cnetPages ) {
        page.setTitle("CNET-" + page.getTitle());
        PageDao.getInstance().updatePage(page.getId(), page);
        }

        System.out.println("Part 5.d");
        Page homepage = null;
        for (Page page: cnetPages) {
        if(page.getTitle().equals("CNET-Home")) {
        homepage = page;
        break;
        		}
        }
		RoleDao.getInstance().swapPageRole(bob.getId(), charlie.getId(), homepage.getId());

		System.out.println("Part 6.a");
		DeveloperDao.getInstance().deleteAddressForDeveloper(alice.getId(), true);

		System.out.println("Part 6.b");
		List<Widget> contactWidgets = (List<Widget>) WidgetDao.getInstance().findWidgetsForPage(contact.getId());
		int max = -1;
		int id1 = 0;
		for(Widget widget : contactWidgets) {
			if(widget.getOrder() > max) {
				max = widget.getOrder();
				id1 = widget.getId();
			}
		}
		WidgetDao.getInstance().deleteWidget(id1);

		System.out.println("Part 6.c");
		List<Page> wikiPages = (List<Page>) PageDao.getInstance().findPagesForWebsite(wikipedia.getId());
		Date lastUpdated = Date.valueOf("2018-01-07");
		int id2 = 0;
		for(Page page : wikiPages) {
			if(page.getUpdated().compareTo(lastUpdated) > 0) {
				lastUpdated = page.getUpdated();
				id2 = page.getId();
			}
		}
		PageDao.getInstance().deletePage(id2);

		System.out.println("Part 6.d");
		WebsiteDao.getInstance().deleteWebsite(cnet.getId());
     
		System.out.println("GG!");
	}
}


