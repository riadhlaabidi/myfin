package tn.riadh.myfin.domain.store;

import java.time.Instant;
import java.util.Set;

import tn.riadh.myfin.domain.common.AbstractEntity;
import tn.riadh.myfin.domain.user.User;

public class Store extends AbstractEntity {
    private String name;
    private String tin;
    private Instant createdAt;
    private User owner;
    private Set<User> employees;
}
