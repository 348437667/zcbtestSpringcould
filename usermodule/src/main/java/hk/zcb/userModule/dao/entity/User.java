package hk.zcb.userModule.dao.entity;


import java.io.Serializable;
import java.util.Objects;
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String adminName;
    private String password;
    private String name;
    private String addTime;
    private int manager;

    @Override
    public String toString() {
        return "User{" +
                "adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", addTime='" + addTime + '\'' +
                ", manager=" + manager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return manager == user.manager &&
                Objects.equals(adminName, user.adminName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(addTime, user.addTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminName, password, name, addTime, manager);
    }


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }
}
