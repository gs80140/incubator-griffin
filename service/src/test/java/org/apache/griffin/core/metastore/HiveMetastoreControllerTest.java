package org.apache.griffin.core.metastore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by xiangrchen on 5/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class HiveMetastoreControllerTest {
    private MockMvc mockMvc;

    @Mock
    HiveMetastoreService hiveMetastoreService;

    @InjectMocks
    private HiveMetastoreController hiveMetastoreController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(hiveMetastoreController).build();
    }

    @Test
    public void test_getAllDatabases() throws Exception {
        when(hiveMetastoreService.getAllDatabases()).thenReturn(null);
        mockMvc.perform(get("/metadata/hive/db"))
                .andExpect(status().isOk());
        verify(hiveMetastoreService).getAllDatabases();
    }

    @Test
    public void test_getDefAllTables() throws Exception{
        when(hiveMetastoreService.getAllTableNames("")).thenReturn(null);
        mockMvc.perform(get("/metadata/hive/table"))
                .andExpect(status().isOk());
        verify(hiveMetastoreService).getAllTableNames("");
    }

    @Test
    public void test_getAllTableNamess() throws Exception {
        String db="default";
        when(hiveMetastoreService.getAllTableNames(db)).thenReturn(null);
        mockMvc.perform(get("/metadata/hive/{db}/table",db))
                .andExpect(status().isOk());
        verify(hiveMetastoreService).getAllTableNames(db);
    }

    @Test
    public void test_getAllTables() throws Exception {
        String db="default";
        when(hiveMetastoreService.getAllTable(db)).thenReturn(null);
        mockMvc.perform(get("/metadata/hive/{db}/alltables",db))
                .andExpect(status().isOk());
        verify(hiveMetastoreService).getAllTable(db);
    }

    @Test
    public void test_getAllTables2() throws Exception {
        when(hiveMetastoreService.getAllTable()).thenReturn(null);
        mockMvc.perform(get("/metadata/hive/alltables"))
                .andExpect(status().isOk());
        verify(hiveMetastoreService).getAllTable();
    }

    @Test
    public void test_getDefTable() throws Exception {
        String dbName="";
        String tableName="cout";
        when(hiveMetastoreService.getTable(dbName,tableName)).thenReturn(null);
        mockMvc.perform(get("/metadata/hive/table/{table}",tableName))
                .andExpect(status().isOk());
        verify(hiveMetastoreService).getTable(dbName,tableName);
    }

    @Test
    public void test_getTable() throws Exception{
        String db="default";
        String table="cout";
        when(hiveMetastoreService.getTable(db,table)).thenReturn(null);
        mockMvc.perform(get("/metadata/hive/{db}/table/{table}",db,table))
                .andExpect(status().isOk());
        verify(hiveMetastoreService).getTable(db,table);
    }
}
