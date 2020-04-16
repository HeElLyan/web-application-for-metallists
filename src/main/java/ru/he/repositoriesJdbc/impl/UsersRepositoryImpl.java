package ru.he.repositoriesJdbc.impl;//package ru.he.repositoriesJdbc.impl;
//
//import ru.he.model.Role;
//import ru.he.model.State;
//import ru.he.model.entities.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Component;
//import ru.he.repositories.UsersRepository;
//
//import java.sql.PreparedStatement;
//import java.sql.Statement;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class UsersRepositoryImpl implements UsersRepository {
//
//    //language=SQL
//    public static final String SQL_SELECT_ALL = "SELECT * FROM user";
//
//    //language=SQL
//    public static final String SQL_SELECT_BY_ID = "SELECT * FROM user WHERE id = ?";
//
//    //language=SQL
//    public static final String SQL_SELECT_BY_CONFIRM_ID = "SELECT * FROM user WHERE confirm_id = ?";
//
//    //language=SQL
//    public static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
//
//    //language=SQL
//    private static final String SQL_UPDATE = "UPDATE user SET email = ?, password = ?, state = ?, confirm_id = ?, role = ? WHERE id = ?";
//
//    //language=SQL
//    private static final String SQL_DELETE_BY_ID = "DELETE FROM user WHERE id = ?";
//
//    //language=SQL
//    private static final String SQL_INSERT = "INSERT INTO user(email, password, state, confirm_id, role) VALUES (?, ?, ?, ?, ?)";
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    private RowMapper<User> userRowMapper = (row, rowNumber) ->
//            User.builder()
//                    .id(row.getBigDecimal("id").longValue())
//                    .email(row.getString("email"))
//                    .password(row.getString("password"))
//                    .state(State.valueOf(row.getString("state")))
//                    .confirmId(row.getString("confirm_id"))
//                    .role(Role.valueOf(row.getString("role")))
//                    .build();
//
//
//    @Override
//    public Optional<User> find(Long id) {
//        try {
//            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
//            return Optional.ofNullable(user);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public void update(User entity) {
//        jdbcTemplate.update(SQL_UPDATE, entity.getEmail(), entity.getPassword(), entity.getState().toString(), entity.getConfirmId(), entity.getId());
//    }
//
//    @Override
//    public List<User> findAll() {
//        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
//    }
//
//    @Override
//    public void save(User entity) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement statement = connection
//                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, entity.getEmail());
//            statement.setString(2, entity.getPassword());
//            statement.setString(3, entity.getState().toString());
//            statement.setString(4, entity.getConfirmId());
//            statement.setString(5, entity.getRole().toString());
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
//    @Override
//    public Optional<User> findByEmail(String email) {
//        try {
//            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email);
//            return Optional.ofNullable(user);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public Optional<User> findByConfirmId(String confirmedId) {
//        try {
//            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_CONFIRM_ID, userRowMapper, confirmedId);
//            return Optional.ofNullable(user);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//}
