package tn.riadh.myfin.domain.store;

import tn.riadh.myfin.domain.common.AbstractEntity;
import tn.riadh.myfin.domain.user.User;

public class Store extends AbstractEntity {
    private String name;
    private String tin;
    private User owner;
}
