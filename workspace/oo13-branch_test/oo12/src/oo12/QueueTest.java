package oo12;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {

	@Before
	public void setUp() throws Exception {
		Queue.n=0;
	}

	
	Queue aa= new Queue();
	@Test
	public void testInseter() {
		String[][] array = new String[1000][4];		
		Queue.insetfr(true,"FR", "1", "UP","0",array);
		assertEquals(2,Queue.inseter(true,"ER", "1","2",array));
	}
	
	@Test
	public void testInsetfr() {
		String[][] array = new String[1000][4];	
	//	System.out.println(Queue.insetfr(true,"FR", "1", "UP","0",array));
		assertEquals(1,Queue.insetfr(true,"FR", "1", "UP","0",array));
	}

	@Test
	public void testGetQueue() {
		Queue.insetfr(true,"FR", "1", "UP","0",Queue.getQueue());
		assertEquals("FR",Queue.getQueue()[0][0]);
	}

	@Test
	public void testRepOK() {
		Queue.n=-1;
		assertEquals(false,Queue.repOK());
		Queue.n=3;
		assertEquals(true,Queue.repOK());
	}
}
