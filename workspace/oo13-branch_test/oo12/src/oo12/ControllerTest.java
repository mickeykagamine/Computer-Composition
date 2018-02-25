package oo12;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	Controller aa=new Controller();
	@Test
	public void testMax() {
		double[] array = new double[10000];
		array[0]=1;
		array[1]=2;
		array[2]=3;
		for(int i=3;i<10000;i++){
			array[i]=0;
		}
		assertEquals(3,Controller.Max(array),0.00001);
	}

	@Test
	public void testFindj() {
		String[][] array = new String[3][4];
		array[0][0]=null;
		array[1][0]="FR";
		array[2][0]="ER";
		assertEquals(1,Controller.findj(0,3,array));
	}

	@Test
	public void testSum() {
		String[][] array = new String[4][4];
		array[0][0]=null;
		array[1][0]="FR";array[1][1]="1";array[1][2]="UP";array[1][3]="3";		
		array[2][0]="ER";array[2][1]="2";array[2][2]="4";
		array[3][0]=null;
		NewElevator.setFloor(1);
//		System.out.println(Controller.Sum(3,array));
		assertEquals(5.5,Controller.Sum(3,array),0.0001);
		
		String[][] array2 = new String[4][4];
		array2[0][0]=null;
		array2[1][0]="FR";array2[1][1]="1";array2[1][2]="UP";array2[1][3]="3";		
		array2[2][0]="ER";array2[2][1]="2";array2[2][2]="4";
		array2[3][0]=null;
		NewElevator.setFloor(10);
//		System.out.println(Controller.Sum(3,array2));
		assertEquals(10,Controller.Sum(3,array2),0.0001);
		
		String[][] array3 = new String[4][4];
		array3[0][0]=null;
		array3[1][0]="ER";array3[1][1]="2";array3[1][2]="4";
		array3[2][0]="FR";array3[2][1]="1";array3[2][2]="UP";array3[2][3]="3";				
		array3[3][0]=null;
		NewElevator.setFloor(10);
	//	System.out.println(Controller.Sum(3,array3));
		assertEquals(10.5,Controller.Sum(3,array3),0.0001);
		
		String[][] array4 = new String[4][4];
		array4[0][0]=null;
		array4[1][0]="ER";array4[1][1]="2";array4[1][2]="4";
		array4[2][0]="FR";array4[2][1]="1";array4[2][2]="UP";array4[2][3]="1000";				
		array4[3][0]="ER";array4[3][1]="2";array4[3][2]="2000";
		NewElevator.setFloor(10);
	//	System.out.println(Controller.Sum(3,array4));
		assertEquals(2001,Controller.Sum(3,array4),0.0001);
	}

	@Test
	public void testJudgefr() {
		String[][] array = new String[1000][4];
		Controller.first=true;
		assertEquals(false,Controller.judgefr("FR", "1", "UP", "2",array,0));
		assertEquals(true, Controller.judgefr("FR", "1", "UP", "0",array,0));
		Queue.insetfr(true, "FR", "1", "UP", "0",array);
		Queue.insetfr(true, "FR", "3", "UP", "4",array);
//		System.out.println(Controller.judgefr("FR", "1", "UP", "2",array,0));		
		assertEquals(false,Controller.judgefr("FR", "1", "UP", "2",array,4));
		assertEquals(false,Controller.judgefr("FR", "3", "UP", "4",array,4));
		
	}

	@Test
	public void testJudgeer() {
		String[][] array = new String[1000][4];
		Controller.first=true;
		assertEquals(false,Controller.judgeer("ER", "1", "2",array,0));
		assertEquals(true, Controller.judgefr("FR", "1", "UP", "0",array,0));
		Queue.insetfr(true, "FR", "1", "UP", "0",array);
		Queue.inseter(true, "ER", "3","4",array);
		assertEquals(false,Controller.judgeer("ER", "1", "2",array,4));
		assertEquals(false,Controller.judgeer("ER", "3", "4",array,4));
	}

	@Test
	public void testRepOK() {
		Controller.D=-1;
		assertEquals(false,Controller.repOK());
		Controller.D=0;
		Controller.sum=-1;
		assertEquals(false,Controller.repOK());
		Controller.D=0;
		Controller.sum=0;
		assertEquals(true,Controller.repOK());
	}
}
