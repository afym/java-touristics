package com.personal;

import com.personal.repository.DestinyCodes;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by angel on 7/12/17.
 */
public class DestinyRepositoryTest extends TestCase {
    @Test
    public void testMockDestiny() {
        assertEquals(2, DestinyCodes.getDestiny("HTP", "50001").length);
        assertEquals(3, DestinyCodes.getDestiny("EZL", "50001").length);
        assertEquals(1, DestinyCodes.getDestiny("EZL", "10052").length);
        assertEquals("45001", DestinyCodes.getDestiny("EZL", "10052")[0]);
        assertEquals("45001", DestinyCodes.getDestiny("HTP", "52589")[0]);
    }
}
