/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bergerproject1;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author khari
 */
public class BergerProject1Test {
    
    public BergerProject1Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddObject() {
        System.out.println("addObject");
        List<Media> list = new ArrayList();
        String uTitle = "Game1";
        String uFormat = "Ps4";
        boolean expResult = true;
        boolean result = BergerProject1.addObject((ArrayList<Media>) list, uTitle, uFormat);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of deleteObject method, of class BergerProject1.
     */
    @Test
    public void testDeleteObject() {
        System.out.println("deleteObject");
        List<Media> list = new ArrayList();
        Media Game1 = new Media("Game1", null,null);
        list.add(Game1);
        String uTitle = "Game1";
        boolean expResult = true;
        boolean result = BergerProject1.deleteObject((ArrayList<Media>) list, uTitle);
        assertEquals(expResult, result);
       
    }
    
    
}
