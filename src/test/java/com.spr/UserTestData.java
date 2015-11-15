package com.spr;

import com.spr.model.User;
import com.spr.matcher.ModelMatcher;

import static com.spr.model.User.START_SEQ;

public class UserTestData {
        public static final int USER_ID = START_SEQ;
        public static final User updatedUser=new User(START_SEQ,"maks666", 100, true);

        public static final ModelMatcher<User, String> MATCHER = new ModelMatcher<>(User::toString);

        public static User user1=new User(START_SEQ,"maks666", 22, true);
        public static User user2=new User(START_SEQ+1,"макс", 44, false);
        public static User user3=new User(START_SEQ+2,"дима", 11, true);
        public static User user4=new User(START_SEQ+3,"юра", 6, true);
        public static User user5=new User(START_SEQ+4,"дима", 3, false);
        public static User user6=new User(START_SEQ+5,"витя", 54, false);
        public static User user7=new User(START_SEQ+6,"вася", 32, false);
        public static User user8=new User(START_SEQ+7,"ваня", 21, false);
        public static User user9=new User(START_SEQ+8,"сергей", 46, false);
        public static User user10=new User(START_SEQ+9,"женя", 67, false);
        public static User user11=new User(START_SEQ+10,"влад", 33, true );
        public static User user12=new User(START_SEQ+11,"олег", 31, false);
        public static User user13=new User(START_SEQ+12,"andrew", 12, false);
        public static User user14=new User(START_SEQ+13,"tom", 16, true);
        public static User user15=new User(START_SEQ+14,"leo", 46, false);


}
