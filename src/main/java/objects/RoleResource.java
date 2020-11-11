package objects;

import java.util.ArrayList;
import java.util.List;

public class RoleResource {

	private Integer id;
	private String name;
	private List<RolePrivilege> privileges = new ArrayList<RolePrivilege>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RolePrivilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<RolePrivilege> privileges) {
		this.privileges = privileges;
	}
	
}
