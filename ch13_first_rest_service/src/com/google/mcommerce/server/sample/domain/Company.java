package com.google.mcommerce.server.sample.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

@Path("/company")
public class Company {
	public static List<Employee> employees = new ArrayList<Employee>();
	static {
		employees.add(new Employee(1, "lee", "ivan", 30));
		employees.add(new Employee(2, "lei", "qing", 28));
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/employees")
	public List<Employee> listEmployees() {
		return employees;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/employee/{firstname}-{lastname}")
	public Employee getEmployee(@PathParam("firstname") String firstName,
			@PathParam("lastname") String lastName) {
		for (Employee e : employees) {
			if (e.getFirstName().equals(firstName)
					&& e.getLastName().equals(lastName)) {
				return e;
			}
		}
		return null;
	}

	@PUT
	@Path("/employee/{id}")
	public void updateEmployee(@PathParam("id") PathSegment employee) {
		Iterator<Employee> it = employees.iterator();
		String firstName = String.valueOf(employee.getMatrixParameters().get(
				"firstname"));
		String lastName = String.valueOf(employee.getMatrixParameters().get(
				"lastname"));
		int age = Integer.parseInt(employee.getMatrixParameters().get("age")
				.get(0));
		while (it.hasNext()) {
			Employee employeetmp = it.next();
			if (firstName.equals(employeetmp.getFirstName())) {
				employeetmp.setLastName(lastName);
				employeetmp.setAge(age);
				break;
			}
		}
	}

	@POST
	@Path("/employee/{id}/{firstname}/{lastname}/{age}")
	public void addemployee(@PathParam("id") int id,
			@PathParam("firstname") String firstName,
			@PathParam("lastname") String lastName, @PathParam("age") int age) {
		employees.add(new Employee(id, firstName, lastName, age));
	}

	@DELETE
	@Path("/employee/{id}")
	public void delemployee(@PathParam("id") int id) {
		Iterator<Employee> it = employees.iterator();
		while (it.hasNext()) {
			Employee employee = it.next();
			if (id == employee.getID()) {
				it.remove();
			}
		}
	}
}