package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.repositories.ElementRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Joshua.McCann on 7/21/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ElementRepTest {

    @Autowired
    private ElementRep elementRepository;

    @Test
    public void testSaveElement() {
        //setup element
        Element element = new Element();
        element.setElementName("Phone");

        //save Element, verify it has an ID after the save.
        assertNull(element.getId());
        elementRepository.save(element);
        assertNotNull(element.getId());

        //fetch
        Element fetchedElement = elementRepository.findOne(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update
        fetchedElement.setElementName("Email");
        elementRepository.save(fetchedElement);

        Element updatedElement = elementRepository.findOne(fetchedElement.getId());
        assertEquals(updatedElement.getElementName(), "Email");
    }

    @Test
    public void testSaveElementList(){
        List<Element> elementList = new ArrayList<>();

        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));

        elementRepository.save(elementList);

        Iterable<Element> fetchedElementsList = elementRepository.findAll();

        int count = 0;
        for(Element element : fetchedElementsList){
            System.out.println(element.getElementName());
            count++;
        }

//        assertEquals(4, count);
    }

    @Test
    public void testFindByName() {
        Element element = new Element ("FindOneTest");
        elementRepository.save(element);

        Element foundByNameElement = elementRepository.findByElementName("FindOneTest");
        assertEquals(element.getId(), foundByNameElement.getId());
    }

    @Test
    public void testFindAllByName() {
        Element element = new Element ("FindAllTest");
        elementRepository.save(element);
        Element element2 = new Element ("FindAllTest");
        elementRepository.save(element2);
        Element element3 = new Element ("FindAllTest");
        elementRepository.save(element3);

        List<Element> foundByNameElement = elementRepository.findAllByElementName("FindAllTest");
        assertEquals(3, foundByNameElement.size());
    }

    @Test
    public void findFirstAlphabetical(){
        Element elementA = new Element("Alpha");
        elementRepository.save(elementA);
        Element elementB = new Element("Bravo");
        elementRepository.save(elementB);
        Element elementC = new Element("Charlie");
        elementRepository.save(elementC);
        Element elementD = new Element("Delta");
        elementRepository.save(elementD);
        Element elementE = new Element("Echo");
        elementRepository.save(elementE);
        Element elementZ = new Element("Zulu");
        elementRepository.save(elementZ);

        Element firstElementByAlpha = elementRepository.findFirstByOrderByElementNameAsc();
        assertEquals("Alpha", firstElementByAlpha.getElementName());

    }

    public void findLastAlphabetical(){
        Element elementA = new Element("Alpha");
        elementRepository.save(elementA);
        Element elementB = new Element("Bravo");
        elementRepository.save(elementB);
        Element elementC = new Element("Charlie");
        elementRepository.save(elementC);
        Element elementD = new Element("Delta");
        elementRepository.save(elementD);
        Element elementE = new Element("Echo");
        elementRepository.save(elementE);
        Element elementZ = new Element("Zulu");
        elementRepository.save(elementZ);

        Element firstElementByAlpha = elementRepository.findFirstByOrderByElementNameDesc();
        assertEquals("Zulu", firstElementByAlpha.getElementName());

    }

    @Test
    public void countOfRecord(){
        Element element = new Element ("RecordCount");
        elementRepository.save(element);
        Element element2 = new Element ("RecordCount");
        elementRepository.save(element2);
        Element element3 = new Element ("RecordCount");
        elementRepository.save(element3);
        Element element4 = new Element ("RecordCount");
        elementRepository.save(element4);
        Element element5 = new Element ("RecordCount");
        elementRepository.save(element5);

        assertEquals(5, elementRepository.countByElementName("RecordCount"));
    }

    @Test
    public void findWhereIgnoreCase(){

        Element elementA = new Element("Alpha");
        elementRepository.save(elementA);
        Element elementB = new Element("Bravo");
        elementRepository.save(elementB);
        Element elementC = new Element("Charlie");
        elementRepository.save(elementC);
        Element elementD = new Element("Delta");
        elementRepository.save(elementD);
        Element elementE = new Element("Echo");
        elementRepository.save(elementE);
        Element elementZ = new Element("Zulu");
        elementRepository.save(elementZ);

        List<Element> elementLike = elementRepository.findByElementNameIgnoreCaseLike("delta");
        assertNotEquals(0, elementLike.size());

    }

    @Test
    public void findContaining(){

        Element elementA = new Element("Alpha");
        elementRepository.save(elementA);
        Element elementB = new Element("Bravo");
        elementRepository.save(elementB);
        Element elementC = new Element("Charlie");
        elementRepository.save(elementC);
        Element elementD = new Element("Delta");
        elementRepository.save(elementD);
        Element elementE = new Element("Echo");
        elementRepository.save(elementE);
        Element elementZ = new Element("Zulu");
        elementRepository.save(elementZ);

        List<Element> elementLike = elementRepository.findByElementNameIgnoreCaseContaining("el");
        assertNotEquals(0, elementLike.size());

    }
}
