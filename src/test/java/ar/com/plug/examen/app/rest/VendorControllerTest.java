package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.VendorApi;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.service.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendorController.class)
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendorService vendorService;

    @Test
    public void testGetVendors() throws Exception {

        mockMvc.perform(get("/vendors")).andExpect(status().isOk());

        verify(vendorService).getAll();
    }

    @Test
    public void testGetVendorById() throws Exception {

        mockMvc.perform(get("/vendors/1")).andExpect(status().isOk());

        verify(vendorService).findById(1);
    }

    @Test
    public void testCreateVendor() throws Exception {

        mockMvc.perform(post("/vendors")
                        .content(new ObjectMapper().writeValueAsString(new VendorApi("name1", "11-22-333")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(vendorService).save(any(Vendor.class));
    }

    @Test
    public void testCreateVendorWithEmptyRequiredField() throws Exception {

        mockMvc.perform(post("/vendors")
                        .content(new ObjectMapper().writeValueAsString(new VendorApi(null, "ssn")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(vendorService, times(0)).save(any(Vendor.class));
    }

    @Test
    public void testUpdateVendor() throws Exception {

        when(vendorService.findById(1)).thenReturn(new Vendor("oldName", "oldSsn"));

        mockMvc.perform(put("/vendors/1")
                        .content(new ObjectMapper().writeValueAsString(new VendorApi("name1", "11-22-333")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).findById(1);
        verify(vendorService).save(any(Vendor.class));
    }

    @Test
    public void testDeleteVendor() throws Exception {

        mockMvc.perform(delete("/vendors/1"))
                .andExpect(status().isNoContent());

        verify(vendorService).deleteById(1);
    }
}
