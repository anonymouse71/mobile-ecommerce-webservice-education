package com.google.mcommerce.server.sample.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.google.mcommerce.server.HelloWorld;
import com.google.mcommerce.server.sample.domain.Company;

public class CompanyApplication extends Application {

	HashSet<Object> singletons = new HashSet<Object>();

	public CompanyApplication() {
		singletons.add(new Company());
		singletons.add(new HelloWorld());
	}

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		// set.add(Library.class);
		return set;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}