package br.edu.ifspsaocarlos.sdm.model;

/**
 * Created by agostta on 07/06/2016.
 */
public class User {

    private long id;
    private CharSequence completeName;
    private CharSequence name;
    private CharSequence password;

    public User(long id, CharSequence completeName, CharSequence name, CharSequence password) {
        this.id = id;
        this.completeName = completeName;
        this.name = name;
        this.password = password;
    }

    public CharSequence getCompleteName() {
        return completeName;
    }

    public CharSequence getName() {
        return name;
    }

    public CharSequence getPassword() {
        return password;
    }

    public void setCompleteName(CharSequence completeName) {
        this.completeName = completeName;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public void setPassword(CharSequence password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", completeName=" + completeName +
                ", name=" + name +
                ", password=" + password +
                '}';
    }
}
