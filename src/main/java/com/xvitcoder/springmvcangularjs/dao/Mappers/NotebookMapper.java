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
                rs.getString("STATUS"),
                rs.getString("NAME"),
                rs.getString("LOCATION"),
                rs.getString("MEM_TYPE"),
                rs.getString("MODEL"),
                rs.getInt("A_F_N"),
                rs.getString("SERIAL_NUMBER")
        );
        return notebook;
    }
}
