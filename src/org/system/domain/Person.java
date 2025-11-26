package org.system.domain;

public abstract class Person {
	protected String UUID;
	protected String name;
	
	public Person() {
		this.UUID = "";
		this.name = "";
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
