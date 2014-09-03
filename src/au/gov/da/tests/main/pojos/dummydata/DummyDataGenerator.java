package au.gov.da.tests.main.pojos.dummydata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import au.gov.da.tests.main.pojos.MyPojo;

/**
 * 
 * @author Villaca Klaus
 * 
 *         Class to generate random data, for tests
 * 
 */
public class DummyDataGenerator {

	private static final String NAME = "NAME";
	private static final String SURNAME = "SURNAME";
	private MyPojo pojo;

	/*
	 * Using Collections
	 */

	/**
	 * Generate a List<MyPojo> with dummy data
	 * 
	 * @param recordsNumber
	 * @return
	 */
	public List<MyPojo> createListDummyData(final int recordsNumber,
			final int startwith) {
		List<MyPojo> listPojo = null;
		listPojo = new ArrayList<MyPojo>();
		for (int i = startwith; i < recordsNumber; i++) {
			listPojo.add(getDummyPojoData(i));
		}
		return listPojo;
	}

	/**
	 * Generate a Map<Integer, MyPojo> with dummy data
	 * 
	 * @param recordsNumber
	 * @return
	 */
	public Map<Integer, MyPojo> createMapDummyData(final int recordsNumber,
			final int startwith) {
		Map<Integer, MyPojo> mapPojo = new HashMap<Integer, MyPojo>();
		for (int i = startwith; i < recordsNumber; i++) {
			mapPojo.put(i, getDummyPojoData(i));
		}
		return mapPojo;
	}

	/*
	 * Using primitive types
	 */

	/**
	 * Generate an MyPojo array with dammu data
	 * 
	 * @param recordsNumber
	 * @return
	 */
	public MyPojo[] createArrayDummyData(final int recordsNumber,
			final int startwith) {
		MyPojo[] pojoArray = new MyPojo[recordsNumber];
		int secondaryCounter = 0;
		for (int i = startwith; i < recordsNumber; i++) {
			pojoArray[secondaryCounter] = getDummyPojoData(i);
			secondaryCounter++;
		}
		return pojoArray;
	}

	/**
	 * Generate a MyPojo object with random id and random name and surname
	 * 
	 * @param recordNumber
	 * @return
	 */
	public MyPojo getDummyPojoData(int recordNumber) {
		if (pojo == null)
			pojo = new MyPojo();

		// Generate a random number to be ordered later
		pojo.setId((int) Math.random() * recordNumber);
		pojo.setGivenName(NAME + UUID.randomUUID());
		pojo.setSurName(SURNAME + UUID.randomUUID());
		return pojo;
	}

}
