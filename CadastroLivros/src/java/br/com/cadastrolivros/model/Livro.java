package br.com.cadastrolivros.model;
import java.util.Date;
import java.util.Objects;

public class Livro {
    private int id;
    private String nomelivro;
    private String isbn;
    private String autor;
    private Date datapublicacao;
    private double valorlivro;

    public Livro() {
        this.id = 0;
        this.nomelivro = "";
        this.isbn = "";
        this.autor = "";
        this.datapublicacao = null;
        this.valorlivro = 0;
    }

    public Livro(int id, String nomelivro, String isbn, String autor, Date datapublicacao, double valorlivro) {
        this.id = id;
        this.nomelivro = nomelivro;
        this.isbn = isbn;
        this.autor = autor;
        this.datapublicacao = datapublicacao;
        this.valorlivro = valorlivro;
    }

    public int getId() {
        return id;
    }

    public String getNomelivro() {
        return nomelivro;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }

    public Date getDatapublicacao() {
        return datapublicacao;
    }

    public double getValorlivro() {
        return valorlivro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomelivro(String nomelivro) {
        this.nomelivro = nomelivro;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDatapublicacao(Date datapublicacao) {
        this.datapublicacao = datapublicacao;
    }

    public void setValorlivro(double valorlivro) {
        this.valorlivro = valorlivro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.nomelivro);
        hash = 53 * hash + Objects.hashCode(this.isbn);
        hash = 53 * hash + Objects.hashCode(this.autor);
        hash = 53 * hash + Objects.hashCode(this.datapublicacao);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.valorlivro) ^ (Double.doubleToLongBits(this.valorlivro) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livro other = (Livro) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorlivro) != Double.doubleToLongBits(other.valorlivro)) {
            return false;
        }
        if (!Objects.equals(this.nomelivro, other.nomelivro)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        return Objects.equals(this.datapublicacao, other.datapublicacao);
    }
    
    
    
    
}
