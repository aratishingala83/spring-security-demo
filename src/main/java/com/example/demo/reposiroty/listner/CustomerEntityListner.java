package com.example.demo.reposiroty.listner;

import javax.persistence.EntityListeners;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.demo.entity.Customer;

@EntityListeners(Customer.class)
public class CustomerEntityListner {
	
	 private static Log log = LogFactory.getLog(CustomerEntityListner.class); 
	 
	@PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Customer user) {
        if (user.getId() == 0) {
            log.info("[CUSTOMER AUDIT] About to add a user");
        } else {
            log.info("[CUSTOMER AUDIT] About to update/delete user: " + user.getId());
        }
    }
     
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Customer user) {
        log.info("[CUSTOMER AUDIT] add/update/delete complete for user: " + user.getId());
    }
     
    @PostLoad
    private void afterLoad(Customer user) {
        log.info("[CUSTOMER AUDIT] user loaded from database: " + user.getId());
    }

}
