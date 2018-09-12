package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.app.rest.errors.ExceptionTranslator;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.mapper.SellerMapper;
import ar.com.flexibility.examen.repository.SellerRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SellerResource REST controller.
 *
 * @see SellerController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SellerControllerIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSellerMockMvc;

    private Seller seller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SellerController sellerController = new SellerController(sellerRepository, sellerMapper);
        this.restSellerMockMvc = MockMvcBuilders.standaloneSetup(sellerController)
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
    public static Seller createEntity(EntityManager em) {
        Seller seller = new Seller()
            .name(DEFAULT_NAME)
            .lastname(DEFAULT_LASTNAME)
            .username(DEFAULT_USERNAME)
            .createdAt(DEFAULT_CREATED_AT);
        return seller;
    }

    @Before
    public void initTest() {
        seller = createEntity(em);
    }

    @Test
    @Transactional
    public void createSeller() throws Exception {
        int databaseSizeBeforeCreate = sellerRepository.findAll().size();

        // Create the Seller
        SellerApi sellerApi = sellerMapper.toApi(seller);
        restSellerMockMvc.perform(post("/api/sellers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerApi)))
            .andExpect(status().isCreated());

        // Validate the Seller in the database
        List<Seller> sellerList = sellerRepository.findAll();
        assertThat(sellerList).hasSize(databaseSizeBeforeCreate + 1);
        Seller testSeller = sellerList.get(sellerList.size() - 1);
        assertThat(testSeller.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSeller.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testSeller.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testSeller.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
    }

    @Test
    @Transactional
    public void createSellerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sellerRepository.findAll().size();

        // Create the Seller with an existing ID
        seller.setId(1L);
        SellerApi sellerApi = sellerMapper.toApi(seller);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSellerMockMvc.perform(post("/api/sellers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerApi)))
            .andExpect(status().isBadRequest());

        // Validate the Seller in the database
        List<Seller> sellerList = sellerRepository.findAll();
        assertThat(sellerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sellerRepository.findAll().size();
        // set the field null
        seller.setName(null);

        // Create the Seller, which fails.
        SellerApi sellerApi = sellerMapper.toApi(seller);

        restSellerMockMvc.perform(post("/api/sellers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerApi)))
            .andExpect(status().isBadRequest());

        List<Seller> sellerList = sellerRepository.findAll();
        assertThat(sellerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sellerRepository.findAll().size();
        // set the field null
        seller.setLastname(null);

        // Create the Seller, which fails.
        SellerApi sellerApi = sellerMapper.toApi(seller);

        restSellerMockMvc.perform(post("/api/sellers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerApi)))
            .andExpect(status().isBadRequest());

        List<Seller> sellerList = sellerRepository.findAll();
        assertThat(sellerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsernameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sellerRepository.findAll().size();
        // set the field null
        seller.setUsername(null);

        // Create the Seller, which fails.
        SellerApi sellerApi = sellerMapper.toApi(seller);

        restSellerMockMvc.perform(post("/api/sellers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerApi)))
            .andExpect(status().isBadRequest());

        List<Seller> sellerList = sellerRepository.findAll();
        assertThat(sellerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = sellerRepository.findAll().size();
        // set the field null
        seller.setCreatedAt(null);

        // Create the Seller, which fails.
        SellerApi sellerApi = sellerMapper.toApi(seller);

        restSellerMockMvc.perform(post("/api/sellers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerApi)))
            .andExpect(status().isBadRequest());

        List<Seller> sellerList = sellerRepository.findAll();
        assertThat(sellerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSellers() throws Exception {
        // Initialize the database
        sellerRepository.saveAndFlush(seller);

        // Get all the sellerList
        restSellerMockMvc.perform(get("/api/sellers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(seller.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())));
    }

    @Test
    @Transactional
    public void getSeller() throws Exception {
        // Initialize the database
        sellerRepository.saveAndFlush(seller);

        // Get the seller
        restSellerMockMvc.perform(get("/api/sellers/{id}", seller.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(seller.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME.toString()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSeller() throws Exception {
        // Get the seller
        restSellerMockMvc.perform(get("/api/sellers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSeller() throws Exception {
        // Initialize the database
        sellerRepository.saveAndFlush(seller);
        int databaseSizeBeforeUpdate = sellerRepository.findAll().size();

        // Update the seller
        Seller updatedSeller = sellerRepository.findOne(seller.getId());
        // Disconnect from session so that the updates on updatedSeller are not directly saved in db
        em.detach(updatedSeller);
        updatedSeller
            .name(UPDATED_NAME)
            .lastname(UPDATED_LASTNAME)
            .username(UPDATED_USERNAME)
            .createdAt(UPDATED_CREATED_AT);
        SellerApi sellerApi = sellerMapper.toApi(updatedSeller);

        restSellerMockMvc.perform(put("/api/sellers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerApi)))
            .andExpect(status().isOk());

        // Validate the Seller in the database
        List<Seller> sellerList = sellerRepository.findAll();
        assertThat(sellerList).hasSize(databaseSizeBeforeUpdate);
        Seller testSeller = sellerList.get(sellerList.size() - 1);
        assertThat(testSeller.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSeller.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testSeller.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testSeller.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingSeller() throws Exception {
        int databaseSizeBeforeUpdate = sellerRepository.findAll().size();

        // Create the Seller
        SellerApi sellerApi = sellerMapper.toApi(seller);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSellerMockMvc.perform(put("/api/sellers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerApi)))
            .andExpect(status().isCreated());

        // Validate the Seller in the database
        List<Seller> sellerList = sellerRepository.findAll();
        assertThat(sellerList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSeller() throws Exception {
        // Initialize the database
        sellerRepository.saveAndFlush(seller);
        int databaseSizeBeforeDelete = sellerRepository.findAll().size();

        // Get the seller
        restSellerMockMvc.perform(delete("/api/sellers/{id}", seller.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Seller> sellerList = sellerRepository.findAll();
        assertThat(sellerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Seller.class);
        Seller seller1 = new Seller();
        seller1.setId(1L);
        Seller seller2 = new Seller();
        seller2.setId(seller1.getId());
        assertThat(seller1).isEqualTo(seller2);
        seller2.setId(2L);
        assertThat(seller1).isNotEqualTo(seller2);
        seller1.setId(null);
        assertThat(seller1).isNotEqualTo(seller2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SellerApi.class);
        SellerApi sellerApi1 = new SellerApi();
        sellerApi1.setId(1L);
        SellerApi sellerApi2 = new SellerApi();
        assertThat(sellerApi1).isNotEqualTo(sellerApi2);
        sellerApi2.setId(sellerApi1.getId());
        assertThat(sellerApi1).isEqualTo(sellerApi2);
        sellerApi2.setId(2L);
        assertThat(sellerApi1).isNotEqualTo(sellerApi2);
        sellerApi1.setId(null);
        assertThat(sellerApi1).isNotEqualTo(sellerApi2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sellerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sellerMapper.fromId(null)).isNull();
    }
}
