package SchoolManagementApp.Entity;


import jakarta.persistence.*;

@Entity
@Table(name="configuration_file")
public class StudenMangementConfi {

    @Id
    private Integer id;

    @Column(name = "`key`")
    private String key;

    private String api;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }


}
