package com.xvitcoder.springmvcangularjs.dao.Mappers;


import com.xvitcoder.springmvcangularjs.model.Notebook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Elina on 08.12.2016.
 */
public class NotebookMapper implements RowMapper<Notebook> {
    @Override
    public Notebook mapRow(ResultSet rs, int rowNum) throws SQLException {
        Notebook notebook = new Notebook(
                rs.getInt("NOTE_ID"),
                rs.getInt("INV_STATUS_ID"),
                rs.getString("INV_STATUS_NAME"),
                rs.getString("NAME"),
                rs.getString("LOCATION"),
                rs.getString("MEM_TYPE"),
                rs.getString("MODEL"),
                rs.getLong("INVENTORY_NUM"),
                rs.getString("SERIAL_NUMBER")
        );
        return notebook;
    }
}
