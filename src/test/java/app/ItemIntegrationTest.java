package app;

import app.entity.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemIntegrationTest
{
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper=new ObjectMapper();
    @Test
    @Sql(statements={"truncate table item","insert into item(id,name)values(1,'test')"})
    public void findById() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/item/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{'name':'test','id':1}"));
    }
    @Test
    @Sql(statements={"truncate table item","insert into item(id,name)values(1,'test1'),(2,'test2')"})
    public void findAll() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/item"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{'content':[{'name':'test1','id':1},{'name':'test2','id':2}]}"));
        mockMvc.perform(MockMvcRequestBuilders.get("/item?page=1&size=1&sort=name,desc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{'content':[{'name':'test1','id':1}]}"));
    }
    @Test
    @Sql(statements={"truncate table item"})
    public void save() throws Exception
    {
        Item item=new Item();
        item.setName("test-save");
        String itemJson=objectMapper.writeValueAsString(item);
        mockMvc.perform(MockMvcRequestBuilders.post("/item").contentType(MediaType.APPLICATION_JSON).content(itemJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("{'name':'"+item.getName()+"'}"));
    }
    @Test
    @Sql(statements={"truncate table item","insert into item(id,name)values(1,'test')"})
    public void update() throws Exception
    {
        Item item=new Item();
        item.setName("test-update");
        item.setId(1L);
        String itemJson=objectMapper.writeValueAsString(item);
        mockMvc.perform(MockMvcRequestBuilders.put("/item/"+item.getId()).contentType(MediaType.APPLICATION_JSON).content(itemJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{'name':'"+item.getName()+"'}"));
    }
    @Test
    @Sql(statements={"truncate table item","insert into item(id,name)values(1,'test')"})
    public void deleteById() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/item/"+1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}