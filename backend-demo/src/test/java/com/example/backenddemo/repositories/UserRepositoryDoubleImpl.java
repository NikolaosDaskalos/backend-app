package com.example.backenddemo.repositories;

import com.example.backenddemo.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDoubleImpl implements UserRepositoryDouble {
    List<User> users=new ArrayList<>();

    @Override
    public List<User> findAll() {
         List<User> users = new ArrayList<>();
         User emptyUser = new User();
         User user1 = new User(1L,"John","Smith","jsmith@gmail.com","1993-04-12");
         User user2 = new User(2L, "Peter", "Lin", "linlin@gmail.com","1983-09-12");
         users.add(emptyUser);
         users.add(user1);
         users.add(user2);
         this.users = users;
         return users;
    }

    @Override
    public User findById(Long id) {
        int idFound = -1;
        for(int i=0;i<users.size();i++){
            if (users.get(i).getUser_id() == id)
                idFound= i;
        }
        if(idFound !=-1)
            return users.get(idFound);
        else
            return null;
    }


}
