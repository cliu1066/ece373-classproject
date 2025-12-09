package org.system.people;

/*
 * Class: Person
 */
public abstract class Person {
	protected String UUID;
	protected String name;
	
	public Person() {
		this.UUID = "";
		this.name = "";
	}
	
	public Person(String aUUID, String aName) {
		this.UUID = aUUID;
		this.name = aName;
	}
	
	public String getUUID() {
		return UUID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setUUID(String aUUID) {
		this.UUID = aUUID;
	}
	
	public void setName(String aName) {
		this.name = aName;
	}
}
