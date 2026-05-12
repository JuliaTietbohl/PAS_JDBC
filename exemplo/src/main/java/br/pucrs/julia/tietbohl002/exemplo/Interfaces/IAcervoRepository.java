package br.pucrs.julia.tietbohl002.exemplo.Interfaces;

import java.util.List;
import org.springframework.http.ResponseEntity;
import br.pucrs.julia.tietbohl002.exemplo.Livro;

public interface IAcervoRepository {
 List<Livro> getLivros();
 List<String> getTitulos();
 List<String> getAutores();
 List<Livro> getLivrosDoAutor(String autor);
 List<Livro> getLivrosDoAutor(String autor, int ano);
 Livro getLivroTitulo(String titulo);
 boolean cadastraLivroNovo(Livro livro);
 boolean removeLivro(int id);
}
