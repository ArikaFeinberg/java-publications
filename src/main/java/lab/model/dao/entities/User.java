package lab.model.dao.entities;

import lab.model.dao.entities.enums.Role;

public class User {
    private int user_id;
    private String userName;
    private Role role;
    private String password;
    private String email;
    private Boolean blocked;

    User() {

    }

    User(UserBuilder userBuilder) {
        this.user_id = userBuilder.user_id;
        this.userName = userBuilder.userName;
        this.role = userBuilder.role;
        this.password = userBuilder.password;
        this.email = userBuilder.email;
        this.blocked = userBuilder.blocked;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }


    public static class UserBuilder {
        private int user_id;
        private String userName;
        private Role role;
        private String password;
        private String email;
        private Boolean blocked;


        public UserBuilder setUser_id(int user_id) {
            this.user_id = user_id;
            return this;
        }

        public UserBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }


        public UserBuilder setRole(Role role) {
            this.role = role;
            return this;
        }


        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }


        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }


        public UserBuilder setBlocked(Boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
