package ibfb.domain.core;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * List info
 * @author TMSANCHEZ
 */
public class GermplasmList {
    private Integer id;
    private String name;
    private Date date;
    private String type;
    private String title;

    private List<ListOfEntries> listEntries;

    public GermplasmList() {
        listEntries = new ArrayList<ListOfEntries>();
    }

    public GermplasmList(Integer id, String name, Date date, String type, String title) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.title = title;
    }

    public GermplasmList(Integer id, String name, Date date, String type, String title, List<ListOfEntries> listEntries) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.title = title;
        this.listEntries = listEntries;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ListOfEntries> getListEntries() {
        return listEntries;
    }

    public void setListEntries(List<ListOfEntries> listEntries) {
        this.listEntries = listEntries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GermplasmList{" + "id=" + id + "name=" + name + "date=" + date + "type=" + type + "title=" + title + "listEntries size=" + listEntries.size() + '}';
    }

    
}
