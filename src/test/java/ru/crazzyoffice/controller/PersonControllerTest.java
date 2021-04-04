package ru.crazzyoffice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.crazzyoffice.entity.DEPARTMENT;
import ru.crazzyoffice.entity.JobType;
import ru.crazzyoffice.entity.Person;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PersonControllerTest extends  AbstractTestController {

    @Test
    void getAll() throws  Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/person"))
                .andDo(print()).andExpect(status().isOk()).
                        andExpect(jsonPath("$.[0].alias").value("Иван")).
                        andExpect(jsonPath("$.[1].alias").value("Петров")).
                        andExpect(jsonPath("$.[2].alias").value("Сидоров")).
                        andReturn();
        assertEquals("application/json;charset=UTF-8",mvcResult.getResponse().getContentType());

    }

    @Test
    void getOne() throws Exception  {
        this.mockMvc
                .perform(get("/person/{id}", "2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.alias").value("Петров"));
    }

    @Test
    void deleteOne() throws Exception {
        this.mockMvc
                .perform(delete("/person/{id}","2"))
                .andDo(print()).andExpect(status().isNoContent());
    }
    @Test
    void create() throws Exception {
        String uri = "/person";
        Person job = new Person("TestAlias", DEPARTMENT.Кадры);
        String inputJson = super.mapToJson(job);

        MvcResult mvcResult = this.mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        Person content = mapFromJson(mvcResult.getResponse().getContentAsString(),Person.class);
        assertEquals(content.getAlias(), job.getAlias());
    }


    @Test
    void update() throws  Exception {
        String uri = "/person/2";
        Person person = new Person(2,"TestAlias",DEPARTMENT.Кадры);
        String inputJson = super.mapToJson(person);

        MvcResult mvcResult = this.mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        Person content = mapFromJson(mvcResult.getResponse().getContentAsString(),Person.class);
        assertEquals(content.getAlias(), person.getAlias());
    }

    @Test
    void getNotExist() throws Exception{
        this.mockMvc
                .perform(get("/person/{id}", "22"))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void getWrongREquest() throws Exception{
        this.mockMvc
                .perform(get("/person/{id}", "abs"))
                .andDo(print()).andExpect(status().isBadRequest());
    }


    @Test
    void deleteNotExist() throws  Exception{
        this.mockMvc
                .perform(delete("/person/{id}","23"))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void deleteBadRequest() throws  Exception{
        this.mockMvc
                .perform(delete("/person/{id}","aaa"))
                .andDo(print()).andExpect(status().isBadRequest());
    }


    @Test
    void createWrongEntity() throws Exception {
        String uri = "/person";
        Person person = new Person(3,"TestAlias",DEPARTMENT.Кадры);
        String inputJson = super.mapToJson(person);

        this.mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andDo(print()).andExpect(status().isBadRequest());
    }
    @Test
    void createSendCustomData() throws Exception {
        String uri = "/person";
        String customString = "absdr";
        this.mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(customString))
                .andDo(print()).andExpect(status().isBadRequest());

    }

    @Test
    void updateWrongID() throws  Exception {
        String uri = "/person/2";
        Person person = new Person(3,"UpdatedData",DEPARTMENT.Кадры);
        String inputJson = super.mapToJson(person);
        this.mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andDo(print()).andExpect(status().isBadRequest());

    }

}