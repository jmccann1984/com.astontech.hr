package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.services.ElementTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Joshua.McCann on 7/24/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class ElementTypeServiceTest {

    @Autowired
    private ElementTypeService elementTypeService;

    @Test
    public void elementServiceSaveTest(){
        ElementType element = new ElementType("ServiceTest");

        //save ElementType, verify it has an ID after the save.
        assertNull(element.getId());
        elementTypeService.saveElementType(element);
        assertNotNull(element.getId());

        //fetch
        ElementType fetchedElement = elementTypeService.getElementTypeById(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update
        fetchedElement.setElementTypeName("Email");
        elementTypeService.saveElementType(fetchedElement);

        ElementType updatedElement = elementTypeService.getElementTypeById(fetchedElement.getId());
        assertEquals(updatedElement.getElementTypeName(), "Email");
    }
}
