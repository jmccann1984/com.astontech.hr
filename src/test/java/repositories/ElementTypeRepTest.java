package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.ElementType;

import com.astontech.hr.repositories.ElementTypeRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Joshua.McCann on 7/24/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ElementTypeRepTest {

    @Autowired
    private ElementTypeRep elementTypeRep;

    @Test
    public void elementServiceSaveTest(){
        ElementType element = new ElementType("ServiceTest");

        //save ElementType, verify it has an ID after the save.
        assertNull(element.getId());
        elementTypeRep.save(element);
        assertNotNull(element.getId());

        //fetch
        ElementType fetchedElement = elementTypeRep.findOne(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update
        fetchedElement.setElementTypeName("Email");
        elementTypeRep.save(fetchedElement);

        ElementType updatedElement = elementTypeRep.findOne(fetchedElement.getId());
        assertEquals(updatedElement.getElementTypeName(), "Email");
    }
}
