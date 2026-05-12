package br.pucrs.julia.tietbohl002.exemplo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.pucrs.julia.tietbohl002.exemplo.Interfaces.IAcervoRepository;
@Repository
@Primary
public class AcervoRepoJdbcImpl implements IAcervoRepository {
 private JdbcTemplate jdbcTemplate;
 @Autowired
 public AcervoRepoJdbcImpl(JdbcTemplate jdbcTemplate) {
 this.jdbcTemplate = jdbcTemplate;
 }
 @Override
 public List<Livro> getLivros() {
 List<Livro> resp = this.jdbcTemplate.query("SELECT * FROM livros",
 (rs, rowNum) -> new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"),
 rs.getInt("ano")));
 return resp;
 }
 @Override
 public boolean removeLivro(int id) {
 String sql = "DELETE FROM livros WHERE id = " + id;
 this.jdbcTemplate.batchUpdate(sql);
 return true;
 }
 @Override
 public List<String> getTitulos() {
    return this.jdbcTemplate.queryForList("SELECT titulo FROM livros", String.class);
 }
 @Override
    public List<String> getAutores() {
        return this.jdbcTemplate.queryForList("SELECT autor FROM livros", String.class);
    }
 @Override
 public List<Livro> getLivrosDoAutor(String autor) {
 String sql = "SELECT * FROM livros WHERE autor = '" + autor + "'";
 List<Livro> resp = this.jdbcTemplate.query(sql,
 (rs, rowNum) -> new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"),
 rs.getInt("ano")));
 return resp;
 }
    @Override
    public List<Livro> getLivrosDoAutor(String autor, int ano) {
        String sql = "SELECT * FROM livros WHERE autor = '" + autor + "' AND ano = " + ano;
        List<Livro> resp = this.jdbcTemplate.query(sql,
                (rs, rowNum) -> new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano")));
        return resp;
    }
 @Override
 public Livro getLivroTitulo(String titulo) {
 String sql = "SELECT * FROM livros WHERE titulo = '" + titulo + "'";
 List<Livro> resp = this.jdbcTemplate.query(sql,
 (rs, rowNum) -> new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"),
 rs.getInt("ano")));
 if (resp.size() > 0) {
 return resp.get(0);
}
 return null;
 }
 @Override
 public boolean cadastraLivroNovo(Livro livro) {
 String sql = "INSERT INTO livros (titulo, autor, ano) VALUES ('" + livro.getTitulo() + "', '" + livro.getAutor() + "', " + livro.getAno() + ")";
 this.jdbcTemplate.batchUpdate(sql);
 return true; 
 }
}
