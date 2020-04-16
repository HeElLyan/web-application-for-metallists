package ru.he.repositoriesJdbc.impl;//package ru.he.repositoriesJdbc.impl;
//
//import ru.he.models.entities.Token;
//import ru.he.repositories.TokensRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Component;
//
//import java.sql.PreparedStatement;
//import java.sql.Statement;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class TokensRepositoryImpl implements TokensRepository {
//
//    //language=SQL
//    public static final String SQL_SELECT_ALL = "SELECT * FROM token";
//
//    //language=SQL
//    public static final String SQL_SELECT_BY_VALUE = "SELECT * FROM token WHERE value = ?";
//
//    //language=SQL
//    public static final String SQL_SELECT_BY_ID = "SELECT * FROM token WHERE id = ?";
//
//    //language=SQL
//    private static final String SQL_UPDATE = "UPDATE token SET value = ? WHERE id = ?";
//
//    //language=SQL
//    private static final String SQL_DELETE_BY_ID = "DELETE FROM token WHERE id = ?";
//
//    //language=SQL
//    private static final String SQL_INSERT = "INSERT INTO token(value) VALUES (?)";
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    private RowMapper<Token> tokenRowMapper = (row, rowNumber) ->
//            Token.builder()
//                    .id(row.getBigDecimal("id").longValue())
//                    .value(row.getString("value"))
//                    .build();
//
//
//    @Override
//    public Optional<Token> findByValue(String value) {
//        try {
//            Token token = jdbcTemplate.queryForObject(SQL_SELECT_BY_VALUE, tokenRowMapper, value);
//            return Optional.ofNullable(token);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public Optional<Token> find(Long id) {
//        try {
//            Token token = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, tokenRowMapper, id);
//            return Optional.ofNullable(token);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public void update(Token entity) {
//        jdbcTemplate.update(SQL_UPDATE, entity.getValue());
//    }
//
//    @Override
//    public List<Token> findAll() {
//        return jdbcTemplate.query(SQL_SELECT_ALL, tokenRowMapper);
//    }
//
//    @Override
//    public void save(Token entity) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement statement = connection
//                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, entity.getValue());
//            return statement;
//        }, keyHolder);
//
//        entity.setId(keyHolder.getKey().longValue());
//    }
//
//    @Override
//    public void delete(Long id) {
//        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
//    }
//
//}
//
