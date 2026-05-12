package br.pucrs.julia.tietbohl002.exemplo;

import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class Acervo {
    private List<Livro> livros;

    public Acervo() {
        livros = new ArrayList<>();

        livros.add(new Livro(110, "Aprendendo Java", "Maria da Silva", 2015));
        livros.add(new Livro(120, "Spring-Boot", "Jose de Souza", 2020));
        livros.add(new Livro(130, "Principios SOLID", "Pedro da Silva", 2023));
        livros.add(new Livro(140, "Padroes de Projeto", "Joana Moura", 2023));
        livros.add(new Livro(150, "Teste Unitario", "Pedro da Silva", 2024)); 
    }

    public List<Livro> getLivros() {
        return livros;
    }
    
    public List<String> getTitulos() {
        return livros.stream()
               .map(livro->livro.getTitulo())
               .toList();
    }

    public List<String> getListaAutores() {
        return livros.stream()
                .map(l -> l.getAutor())
                .distinct()
                .toList();
    }

    public List<Livro> getLivrosDoAutor(String autor) {
        return livros.stream()
                  .filter(livro->livro.getAutor().equals(autor))
                  .toList();
    }

    public List<Livro> getLivrosDoAutor(String autor,
                                        int ano) {
       return livros.stream()
         .filter(livro->livro.getAutor().equals(autor))
         .filter(livro->livro.getAno() == ano)
         .toList();
    }
    
    public boolean cadastraLivroNovo(Livro livro) {
        livros.add(livro);
        return true;
    }

    public Livro getLivroTitulo(String titulo) {
            Livro resp = livros.stream()
                   .filter(livro->livro.getTitulo().equals(titulo))
                   .findFirst()
                   .orElse(null);   
            return resp;
        }

    public boolean removeLivrosAno(int ano) {
        boolean removeu = false;
        int i = 0;
        while(i < livros.size()) {
            Livro aux = livros.get(i);
            if(aux.getAno() == ano) {
                livros.remove(aux);
                removeu = true;
            }
            else
                i++;
        }
        return removeu;
    }

    public boolean atualizaLivro(Livro aux) {
        for(Livro l : livros) {
            if(l.getId() == aux.getId()) {
                l.setTitulo(aux.getTitulo());
                l.setAutor(aux.getAutor());
                l.setAno(aux.getAno());
                return true;
            }
        }
        return false;
    }

}
