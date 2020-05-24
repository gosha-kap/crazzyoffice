package ru.crazzyoffice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.crazzyoffice.entity.JobType;



import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class JobControllerTest extends  AbstractTestController {

    @Test
    void getAll() throws Exception {
         MvcResult mvcResult = this.mockMvc.perform(get("/job"))
                 .andDo(print()).andExpect(status().isOk()).
                         andExpect(jsonPath("$.[0].description").value("Водитель")).
                         andExpect(jsonPath("$.[1].description").value("Дежурный")).
                         andExpect(jsonPath("$.[2].description").value("На телефоне")).
                         andReturn();

         assertEquals("application/json;charset=UTF-8",mvcResult.getResponse().getContentType());
    }

    @Test
    void getOne() throws Exception  {
        this.mockMvc
                .perform(get("/job/{id}", "2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.description").value("Дежурный"));
    }

    @Test
    void deleteOne() throws Exception {
         this.mockMvc
                .perform(delete("/job/{id}","2"))
                .andDo(print()).andExpect(status().isNoContent());
    }
    @Test
    void create() throws Exception {
        String uri = "/job";
        JobType job = new JobType("TestJobDescription");
        String inputJson = super.mapToJson(job);

        MvcResult mvcResult = this.mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        JobType content = mapFromJson(mvcResult.getResponse().getContentAsString(),JobType.class);
        assertEquals(content.getDescription(), job.getDescription());
    }

    @Test
    void update() throws  Exception {
        String uri = "/job/2";
        JobType job = new JobType(2,"UpdatedJobDescription");
        String inputJson = super.mapToJson(job);

        MvcResult mvcResult = this.mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        JobType content = mapFromJson(mvcResult.getResponse().getContentAsString(),JobType.class);
        assertEquals(content.getDescription(), job.getDescription());
    }

    @Test
    void getNotExist() throws Exception{
        this.mockMvc
                .perform(get("/job/{id}", "22"))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void getWrongREquest() throws Exception{
        this.mockMvc
                .perform(get("/job/{id}", "abs"))
                .andDo(print()).andExpect(status().isBadRequest());
    }


    @Test
    void deleteNotExist() throws  Exception{
        this.mockMvc
                .perform(delete("/job/{id}","23"))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void deleteBadRequest() throws  Exception{
        this.mockMvc
                .perform(delete("/job/{id}","aaa"))
                .andDo(print()).andExpect(status().isBadRequest());
    }


    @Test
    void createWrongEntity() throws Exception {
        String uri = "/job";
        JobType job = new JobType(3,"TestJobDescription");
        String inputJson = super.mapToJson(job);

      this.mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andDo(print()).andExpect(status().isBadRequest());

    }
    @Test
    void createSendCustomData() throws Exception {
        String uri = "/job";
        String customString = "absdr";
        this.mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(customString))
                .andDo(print()).andExpect(status().isBadRequest());

    }

    @Test
    void updateWrongID() throws  Exception {
        String uri = "/job/2";
        JobType job = new JobType(3,"UpdatedJobDescription");
        String inputJson = super.mapToJson(job);
        this.mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andDo(print()).andExpect(status().isBadRequest());

    }
}