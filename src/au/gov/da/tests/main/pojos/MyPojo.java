package au.gov.da.tests.main.pojos;

public class MyPojo {

	private int id;
	private String givenName;
	private String surName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	@Override
	public String toString() {
		return "{\"MyPojo\":{{\"id\":\"" + id + "\"}, {\"givenName\":\""
				+ givenName + "\"}, {\"surName\":\"" + surName + "\"}}}";
	}

}
