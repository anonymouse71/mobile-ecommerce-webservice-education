/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.sessionbean;

import javax.ejb.Remote;

import server.entitybean.Customer;
@Remote
public interface RegistrationBeanRemote {
	void register(String username, String password, String name, String email);

	Customer validateLogin(String username, String password);
}
