package ru.crazzyoffice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.crazzyoffice.entity.JobType;
import ru.crazzyoffice.entity.Person;
import ru.crazzyoffice.entity.WorkDay;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.crazzyoffice.controller.TestData.*;

class WorkDayControllerTest extends  AbstractTestController {

    @Test
    void workdaysForMonth() throws  Exception   {
        MvcResult mvcResult = this.mockMvc.perform(get("/calendar"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        List<WorkDay> data = readValueFromJSON(response, List.class);
        assertEquals(2,  data.size());

    }

    @Test
    void getOneWorkday() throws  Exception {
        String firstDayOfMonth = LocalDate.now().withDayOfMonth(1).toString();
        this.mockMvc
                .perform(get("/calendar/{id}", firstDayOfMonth))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }

    @Test
    void getWrongWorkday() throws  Exception {
          this.mockMvc
                .perform(get("/calendar/{id}",  "Not Integer id data"))
                .andDo(print()).andExpect(status().isBadRequest());
    }


    @Test
    void deleteWorkDay() throws Exception{
        this.mockMvc
                .perform(delete("/calendar/{id}","2"))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void deleteWrongWorkDay() throws Exception{
        this.mockMvc
                .perform(delete("/calendar/{id}","22"))
                .andDo(print()).andExpect(status().isNotFound());
    }


    @Test
    void createWorkDay() throws  Exception {

        workDayTestData.addWorker(jobType2TestData,person2TestData);
        String inputJson = super.mapToJson(workDayTestData);
        MvcResult mvcResult = this.mockMvc.perform(post("/calendar")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
         assertEquals(201, mvcResult.getResponse().getStatus());
        WorkDay content = mapFromJson(mvcResult.getResponse().getContentAsString(),WorkDay.class);
        assertEquals(content.getId(), 3);
    }

    @Test
    void createWorkDayUnprocessEntity() throws  Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/calendar")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("This can't be a workday Object")).andReturn();
        assertEquals(400, mvcResult.getResponse().getStatus());

    }



    @Test
    void updateWorkDay() throws  Exception{
        workDayTestData.setId(1);
        workDayTestData.addWorker(jobType1TestData,person1TestData);
        workDayTestData.addWorker(jobType2TestData,person2TestData);
        workDayTestData.addWorker(jobType3TestData,person3TestData);
        String inputJson = super.mapToJson(workDayTestData);
        MvcResult mvcResult = this.mockMvc.perform(put("/calendar/1")
              .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andDo(print()).andReturn();
        assertEquals(202, mvcResult.getResponse().getStatus());
        WorkDay content = mapFromJson(mvcResult.getResponse().getContentAsString(),WorkDay.class);

    }
}