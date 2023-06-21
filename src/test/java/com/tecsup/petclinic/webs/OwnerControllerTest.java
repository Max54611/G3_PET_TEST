package com.tecsup.petclinic.webs;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.OwnerTO;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class OwnerControllerTest {
	
	private static final ObjectMapper om= new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testFindAllOwners() throws Exception{
		int ID=1;
		this.mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[0].id", is(ID+1)));
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testCreateOwner() throws Exception {

		String FIRSTN = "Magno";
		String LASTN = "Mori";
		String ADDRES = "Lima";
		String CITY = "LIMA";
		String TELEPHONE = "987654321";

		OwnerTO newOwnerTO = new OwnerTO();
		newOwnerTO.setFirstName(FIRSTN);
		newOwnerTO.setLastName(LASTN);
		newOwnerTO.setAddress(ADDRES);
		newOwnerTO.setCity(CITY);
		newOwnerTO.setTelephone(TELEPHONE);
		
		mockMvc.perform(post("/owners")
						.content(om.writeValueAsString(newOwnerTO))
						.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName",is(FIRSTN)))
				.andExpect(jsonPath("$.lastName",is(LASTN)))
				.andExpect(jsonPath("$.address",is(ADDRES)))
				.andExpect(jsonPath("$.city",is(CITY)))
				.andExpect(jsonPath("$.telephone",is(TELEPHONE)));
				
		}	
	
	@Test
	public void testDeleteOwner() throws Exception{
		String FIRSTN="Magno";
		String LASTN="Mori";
		String ADDRESS="Lima";
		String CITY="LIMA";
		String TELEPHONE="987654321";
		
		OwnerTO newOwnerTO = new OwnerTO();
		newOwnerTO.setFirstName(FIRSTN);
		newOwnerTO.setLastName(LASTN);
		newOwnerTO.setAddress(ADDRESS);
		newOwnerTO.setCity(CITY);
		newOwnerTO.setTelephone(TELEPHONE);
		
		ResultActions mvcActions = mockMvc.perform(post("/owners")
						.content(om.writeValueAsString(newOwnerTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());
		
		String response = mvcActions.andReturn().getResponse().getContentAsString();
		
		Integer id=JsonPath.parse(response).read("$.id");
		
		mockMvc.perform(delete("/owners/"+id))
		.andExpect(status().isOk());
	}
}