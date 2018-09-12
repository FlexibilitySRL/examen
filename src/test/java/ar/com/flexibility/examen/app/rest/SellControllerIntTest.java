package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.app.api.SellApi;
import ar.com.flexibility.examen.app.rest.errors.ExceptionTranslator;
import ar.com.flexibility.examen.domain.model.Sell;
import ar.com.flexibility.examen.domain.service.mapper.SellMapper;
import ar.com.flexibility.examen.repository.SellRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SellResource REST controller.
 *
 * @see SellController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SellControllerIntTest {

    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private SellMapper sellMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSellMockMvc;

    private Sell sell;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SellController sellController = new SellController(sellRepository, sellMapper);
        this.restSellMockMvc = MockMvcBuilders.standaloneSetup(sellController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(TestUtil.createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sell createEntity(EntityManager em) {
        Sell sell = new Sell();
        return sell;
    }

    @Before
    public void initTest() {
        sell = createEntity(em);
    }

    @Test
    @Transactional
    public void createSell() throws Exception {
        int databaseSizeBeforeCreate = sellRepository.findAll().size();

        // Create the Sell
        SellApi sellApi = sellMapper.toApi(sell);
        restSellMockMvc.perform(post("/api/sells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellApi)))
            .andExpect(status().isCreated());

        // Validate the Sell in the database
        List<Sell> sellList = sellRepository.findAll();
        assertThat(sellList).hasSize(databaseSizeBeforeCreate + 1);
        Sell testSell = sellList.get(sellList.size() - 1);
    }

    @Test
    @Transactional
    public void createSellWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sellRepository.findAll().size();

        // Create the Sell with an existing ID
        sell.setId(1L);
        SellApi sellApi = sellMapper.toApi(sell);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSellMockMvc.perform(post("/api/sells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellApi)))
            .andExpect(status().isBadRequest());

        // Validate the Sell in the database
        List<Sell> sellList = sellRepository.findAll();
        assertThat(sellList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSells() throws Exception {
        // Initialize the database
        sellRepository.saveAndFlush(sell);

        // Get all the sellList
        restSellMockMvc.perform(get("/api/sells?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sell.getId().intValue())));
    }

    @Test
    @Transactional
    public void getSell() throws Exception {
        // Initialize the database
        sellRepository.saveAndFlush(sell);

        // Get the sell
        restSellMockMvc.perform(get("/api/sells/{id}", sell.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sell.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSell() throws Exception {
        // Get the sell
        restSellMockMvc.perform(get("/api/sells/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSell() throws Exception {
        // Initialize the database
        sellRepository.saveAndFlush(sell);
        int databaseSizeBeforeUpdate = sellRepository.findAll().size();

        // Update the sell
        Sell updatedSell = sellRepository.findOne(sell.getId());
        // Disconnect from session so that the updates on updatedSell are not directly saved in db
        em.detach(updatedSell);
        SellApi sellApi = sellMapper.toApi(updatedSell);

        restSellMockMvc.perform(put("/api/sells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellApi)))
            .andExpect(status().isOk());

        // Validate the Sell in the database
        List<Sell> sellList = sellRepository.findAll();
        assertThat(sellList).hasSize(databaseSizeBeforeUpdate);
        Sell testSell = sellList.get(sellList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingSell() throws Exception {
        int databaseSizeBeforeUpdate = sellRepository.findAll().size();

        // Create the Sell
        SellApi sellApi = sellMapper.toApi(sell);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSellMockMvc.perform(put("/api/sells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellApi)))
            .andExpect(status().isCreated());

        // Validate the Sell in the database
        List<Sell> sellList = sellRepository.findAll();
        assertThat(sellList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSell() throws Exception {
        // Initialize the database
        sellRepository.saveAndFlush(sell);
        int databaseSizeBeforeDelete = sellRepository.findAll().size();

        // Get the sell
        restSellMockMvc.perform(delete("/api/sells/{id}", sell.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sell> sellList = sellRepository.findAll();
        assertThat(sellList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sell.class);
        Sell sell1 = new Sell();
        sell1.setId(1L);
        Sell sell2 = new Sell();
        sell2.setId(sell1.getId());
        assertThat(sell1).isEqualTo(sell2);
        sell2.setId(2L);
        assertThat(sell1).isNotEqualTo(sell2);
        sell1.setId(null);
        assertThat(sell1).isNotEqualTo(sell2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SellApi.class);
        SellApi sellApi1 = new SellApi();
        sellApi1.setId(1L);
        SellApi sellApi2 = new SellApi();
        assertThat(sellApi1).isNotEqualTo(sellApi2);
        sellApi2.setId(sellApi1.getId());
        assertThat(sellApi1).isEqualTo(sellApi2);
        sellApi2.setId(2L);
        assertThat(sellApi1).isNotEqualTo(sellApi2);
        sellApi1.setId(null);
        assertThat(sellApi1).isNotEqualTo(sellApi2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sellMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sellMapper.fromId(null)).isNull();
    }
}
