package app.entity;

import javax.persistence.*;

@Entity
public class Item
{
    @Id
    @SequenceGenerator(name="ITEM_SEQUENCE_GENERATOR",sequenceName="ITEM_SEQUENCE",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ITEM_SEQUENCE_GENERATOR")
    private Long id;
    private String name;
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id=id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
}