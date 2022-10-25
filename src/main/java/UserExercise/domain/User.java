package UserExercise.domain;

public class User {
    private String id;
    private String name;
    private String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        if(this.id.equals(user.id) && this.name.equals(user.name) && this.password.equals(user.password)) {
            return true;
        }
        return false;
    }
}
